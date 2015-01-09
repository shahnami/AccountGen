/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accountgen.controller;

import accountgen.model.Address;
import accountgen.model.Consts;
import accountgen.model.Person;
import accountgen.model.Reader;
import accountgen.model.Vehicle;
import accountgen.view.MainFrame;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author namishah
 */
public class Controller {
    private static Controller instance = null;
    private List<Person> accounts = null;
    private Reader reader = null;
    
    public static Controller getInstance(){
        if(instance == null){
            instance = new Controller();
        }
        return instance;
    }
    
    private Document setConnection(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(1000).get();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doc;
    }
    
    private class MySwingWorker extends SwingWorker<String, Double>{
        private final JProgressBar fProgressBar;
        private final int _amount;
        private final boolean _export_to_file;
        private final boolean _export_to_db;
        private MySwingWorker(int amount, boolean export_to_file, boolean export_to_db, JProgressBar aProgressBar) {
          fProgressBar = aProgressBar;
          _amount = amount;
          _export_to_file = export_to_file;
          _export_to_db = export_to_db;
          accounts = new ArrayList<>();
          fProgressBar.setStringPainted(true);
        }

        @Override
        protected String doInBackground() throws Exception {
            for(int i=0;i<_amount;i++){
                accounts.add(createAccount());
                double factor = ((double)(i+1) / _amount);
                double bias = ((double)(1.0/_amount));
                publish( factor + bias);//publish the progress
            }
            return "Finished";
        }
        @Override
        protected void process( List<Double> aDoubles ) {
          //update the percentage of the progress bar that is done
          int amount = fProgressBar.getMaximum() - fProgressBar.getMinimum();
          fProgressBar.setValue( ( int ) (fProgressBar.getMinimum() + ( amount * aDoubles.get( aDoubles.size() - 1 ))) );
          fProgressBar.setString((int)(fProgressBar.getMinimum() + ( amount * aDoubles.get( aDoubles.size() - 1 )))+"%");
        }

        @Override
        protected void done() {
            publish(0.0);
            fProgressBar.setStringPainted(false);
            if(_export_to_file){
                try {
                    exportToFile(Consts.PATH_URL, false);
                } catch (SQLException | ClassNotFoundException | FileNotFoundException | UnsupportedEncodingException ex) {
                    //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(_export_to_db){
                try {
                    exportToDB(Consts.PATH_URL, false);
                } catch (ParseException | ClassNotFoundException | SQLException ex) {
                    //Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    
    public void generate(int amount, boolean export_to_file, boolean export_to_db) throws FileNotFoundException, UnsupportedEncodingException, ClassNotFoundException, SQLException, ParseException{
        MySwingWorker mySwingWorker = new MySwingWorker(amount, export_to_file, export_to_db, MainFrame.pgrBar);
        mySwingWorker.execute();
        /*
        accounts = new ArrayList<>();
        for(int i=0;i<amount;i++){
            accounts.add(createAccount());
            //System.out.println(i+1 + "/"+amount+" accounts crawled");
        }
        if(export_to_file){
            exportToFile(Consts.PATH_URL, false);
        }
        if(export_to_db){
            exportToDB(Consts.PATH_URL, false);
        }
                
        */
    }
    
    public void exportToDB(String path, boolean all) throws ParseException, ClassNotFoundException, SQLException{
        if(all){
            accounts = new ArrayList<>();
            reader = new Reader(path);
            accounts = reader.personFromFile();
        }
        Database.getInstance().openConnection();
        for(Person account:accounts){
            Database.getInstance().insertAccount(account);
        }
        Database.getInstance().closeConnection();
    }
    
    public void exportToFile(String path, boolean all) throws SQLException, ClassNotFoundException, FileNotFoundException, UnsupportedEncodingException{
        if(all) {
            accounts = new ArrayList<>();
            reader = new Reader(path);
            accounts = reader.getPerson(-1); //All accounts
        }
        try (PrintWriter writer = new PrintWriter(path, "UTF-8")) {
            writer.println("firstname|middlename|lastname|gender|phone|email|inbox|birthday|birthmonth|birthyear|country|streetname|streetnumber|postcode|state|"
                    + "username|password|mmn|mastercard|cvv2|expiremonth|expireyear|ssn|favoritecolor|occupation|website|company|brand|model|year|upsnr|bloodtype|"
                    + "weight|height|guid|geo_x|geo_y");
            writer.println("");
            for(Person account:accounts){
                writer.println(account.toExportString());
            }
            writer.close();
        }
    }
    
    private void setName(Document doc, Person p){
        Elements e = doc.getElementsByClass("address");
        Element name = e.select("h3").first();
        p.setFirstname(StringEscapeUtils.unescapeHtml4(name.html().split(" ")[0]).trim());
        p.setMiddlename("");
        p.setLastname(StringEscapeUtils.unescapeHtml4(name.html().split(name.html().split(" ")[0])[1]).trim());
    }
    
    private void setEmail(Document doc, Person p){
        Element em = doc.select(".email").first();
        p.setEmail(em.text().split(" ")[0]);
    }
    
    private void setInbox(Person p){
        p.setInbox(Consts.INBOX_URL+p.getEmail().split("@")[1]+"/"+p.getEmail().split("@")[0]+"/");
    }
    
    private void setAddress(Document doc, Person p){
        Elements e = doc.getElementsByClass("address");
        Element ad = e.select(".adr").first();
        Address address = new Address();
        String streetnumber = StringEscapeUtils.unescapeHtml4(ad.html().split("<br />")[0].split(" ")[ad.html().split("<br />")[0].split(" ").length-1]).trim();
        String state = StringEscapeUtils.unescapeHtml4(ad.html().split("<br />")[1].split(" ")[ad.html().split("<br />")[1].split(" ").length-1]).trim();
        address.setStreetnumber(streetnumber);
        address.setStreetname(StringEscapeUtils.unescapeHtml4(ad.html().split(streetnumber)[0]).trim());
        address.setState(state);
        address.setPostcode(StringEscapeUtils.unescapeHtml4(ad.html().split("<br />")[1].split(state)[0]).trim().split(" ")[0]);
        address.setCountry(Consts.COUNTRY);
        p.setAdress(address);
    }
    
    private void setPhone(Document doc, Person p){
        Element tel = doc.select(".tel").first();
        p.setPhone(tel.text());
    }
    
    private void setBday(Document doc, Person p){
        Element bday = doc.select(".bday").first();
        Date bd = new Date();

        Date date = null;
        try {
            date = new SimpleDateFormat("MMM",Locale.ENGLISH).parse(bday.text().split(" ")[0]);
        } catch (ParseException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int month = cal.get(Calendar.MONTH);
        bd.setMonth(month);
        bd.setDate(Integer.parseInt(bday.text().split(" ")[1].replace(",", "")));
        bd.setYear(Integer.parseInt(bday.text().split(",")[1].substring(1, 5))-1900);
        p.setBirthday(bd);
    }
    
    private void setGender(Document doc, Person p){
        Element gen = doc.select(".bcs").first().select(".content").first().select("img").first();
        String g = gen.attr("alt");
        p.setGender(g);
    }
    
    private void setGEO(Document doc, Person p){
        String geo_x = doc.select("#geo").first().text().split(", ")[0];
        String geo_y = doc.select("#geo").first().text().split(", ")[1];
        p.setGEOX(geo_x);
        p.setGEOY(geo_y);
    }
    
    private void getListTags(Document doc, Person p){
        Elements li = doc.select(".extra").select("li:not(.lab)");
        p.setUsername(li.get(2).text());
        p.setPassword(li.get(3).text());
        p.setMmn(li.get(4).text());
        p.setMastercard(li.get(6).text());
        p.setSsn("");
        Date d = new Date();
        d.setDate(1);
        d.setYear(Integer.parseInt(li.get(7).text().split("/")[1])-1900);
        d.setMonth(Integer.parseInt(li.get(7).text().split("/")[0])-1);
        p.setExpires(d);
        p.setCvv2(li.get(8).text());
        p.setFavoritecolor(li.get(9).text());
        p.setOccupation(li.get(10).text());
        p.setCompany(li.get(11).text());
        p.setWebsite(li.get(12).text());
        Vehicle v = new Vehicle();
        v.setModel(li.get(13).text().split(" ")[li.get(13).text().split(" ").length-1].trim());
        v.setYear(Integer.parseInt(li.get(13).text().split(" ")[0].trim()));
        v.setBrand(li.get(13).text().replace(li.get(13).text().split(" ")[li.get(13).text().split(" ").length-1], "").replace(li.get(13).text().split(" ")[0], "").trim());
        p.setVehicle(v);
        p.setUpsnr(li.get(14).text());
        p.setBloodtype(li.get(15).text());
        p.setWeight(li.get(16).text().split("\\(")[1].split(" ")[0]);
        p.setHeight(li.get(17).text().split("\\(")[1].split(" ")[0]);
        p.setGuid(li.get(18).text());
    }
    
    
    private Person createAccount(){
        Document doc = setConnection(Consts.GENERATOR_URL+"?t=country&n[]="+Consts.NAMESET+"&c[]="+Consts.COUNTRY+"&gen="+Consts.GENDER+"&age-min="+Consts.MIN_AGE+"&age-max="+Consts.MAX_AGE); //?t=country&n[]=nl&c[]=bg&gen=67&age-min=19&age-max=64
        Person p = new Person();
        setName(doc, p);
        setAddress(doc, p);
        setPhone(doc, p);
        setEmail(doc, p);
        setInbox(p);
        setBday(doc, p);
        setGender(doc, p);
        setGEO(doc, p);
        getListTags(doc, p);
        return p;
    }
    
    private void showAccounts(){
        for (Person account : accounts) {
            System.out.println(account.toString());
        }
    }
    
    public Controller(){
        //
    }
    
    public void readFile(String path){
        reader = new Reader(path);
        reader.printFile();
    }
    
    public void printPerson(int id) throws SQLException, ClassNotFoundException{
        accounts = new ArrayList<>();
        reader = new Reader("");
        accounts = reader.getPerson(id);
        showAccounts();
    }
    
    public List<Person> readDB(int id) throws SQLException, ClassNotFoundException{
        reader = new Reader("");
        return reader.getPerson(id);
    }
    
    public int getDBSize() throws ClassNotFoundException, SQLException{
        return Database.getInstance().getDBSize();
    }
    
    
}
