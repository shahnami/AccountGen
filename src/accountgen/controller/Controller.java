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
    
    public void generate(int amount, boolean export_to_file, boolean export_to_db) throws FileNotFoundException, UnsupportedEncodingException, ClassNotFoundException, SQLException{
        accounts = new ArrayList<>();
        PrintWriter writer = new PrintWriter(Consts.PATH_URL, "UTF-8");
        for(int i=0;i<amount;i++){
            accounts.add(createAccount());
            System.out.println(i+1 + "/"+amount+" accounts created");
        }
        if(export_to_file){
            writer.println("firstname:lastname:phone:email:streetname:streetnumber:state:postcode:birthday:birthmonth:birthyear");
            writer.println("====================================================================================================");
            for(Person account:accounts){
                writer.println(account.toExportString());
            }
            writer.close();
        }
        if(export_to_db){
            Database.getInstance().openConnection();
            for(Person account:accounts){
                Database.getInstance().insertAccount(account);
            }
            Database.getInstance().closeConnection();
        }
    }
    
    public void exportToDB(String path) throws ParseException, ClassNotFoundException, SQLException{
        accounts = new ArrayList<>();
        reader = new Reader(path);
        accounts = reader.readPersonFromFile();
        Database.getInstance().openConnection();
        for(Person account:accounts){
            Database.getInstance().insertAccount(account);
        }
        Database.getInstance().closeConnection();
        
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
        p.setInbox("http://www.fakemailgenerator.com/#/"+p.getEmail().split("@")[1]+"/"+p.getEmail().split("@")[0]+"/");
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
        bd.setYear(Integer.parseInt(bday.text().split(",")[1].substring(1, 5)));
        p.setBirthday(bd);
    }
    
    
    private Person createAccount(){
        Document doc = setConnection(Consts.GENERATOR_URL+Consts.GENDER+Consts.NAMESET+Consts.COUNTRY+Consts.GENERATOR_URL_EXT); //gen-male-dk-bg.php
        Person p = new Person();
        setName(doc, p);
        setAddress(doc, p);
        setPhone(doc, p);
        setEmail(doc, p);
        setInbox(p);
        setBday(doc, p);
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
    
    public void readFromDB(int id) throws SQLException, ClassNotFoundException{
        reader = new Reader("");
        reader.readFromDB(id);
    }
    
    public void getDBSize() throws ClassNotFoundException, SQLException{
        System.out.println(Database.getInstance().getDBSize());
    }
    
    
}
