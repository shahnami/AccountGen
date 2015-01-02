/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accountgen.model;

import accountgen.controller.Database;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author namishah
 */
public class Reader {
    private String _filepath;
    private List<Person> _accounts;
    private Person _account;
    
    
    public Reader(String filepath){
        this._filepath = filepath;
        this._accounts = new ArrayList<>();
    }
    
    public void readFromDB(int id) throws SQLException, ClassNotFoundException{
        _accounts = new ArrayList<>();
        if(id == -1){
            _accounts = Database.getInstance().getAllAccounts();
        } else {
            _accounts.add(Database.getInstance().getPerson(id));
        }
        for(Person account:_accounts){
            System.out.println(account.toString());
        }
    }
    
    private int MonthToInt(String monthname) throws ParseException{
        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("MMM").parse(monthname));
        int monthInt = cal.get(Calendar.MONTH) + 1;
        return monthInt;
    }
    
    public List<Person> readPersonFromFile() throws ParseException{
        _accounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this._filepath))) {
            String sCurrentLine;
            br.readLine();
            br.readLine();

            while ((sCurrentLine = br.readLine()) != null) {
                Person p = new Person();
                Address a = new Address();
                Date d = new Date();
                d.setDate(Integer.parseInt(sCurrentLine.split(":")[8]));
                d.setMonth(MonthToInt(sCurrentLine.split(":")[9]));
                d.setYear(Integer.parseInt(sCurrentLine.split(":")[10]));
                p.setFirstname(sCurrentLine.split(":")[0]);
                p.setLastname(sCurrentLine.split(":")[1]);
                p.setMiddlename("");
                p.setPhone(sCurrentLine.split(":")[2]);
                p.setEmail(sCurrentLine.split(":")[3]);
                p.setBirthday(d);
                a.setStreetname(sCurrentLine.split(":")[4]);
                a.setStreetnumber(sCurrentLine.split(":")[5]);
                a.setPostcode(sCurrentLine.split(":")[7]);
                a.setState(sCurrentLine.split(":")[6]);
                p.setAdress(a);
                p.setInbox("http://www.fakemailgenerator.com/#/"+p.getEmail().split("@")[1]+"/"+p.getEmail().split("@")[0]+"/");
                _accounts.add(p);
            }
        } catch (IOException e) {
            //
        }
        return _accounts;
    }
    
    public void printFile(){
        try (BufferedReader br = new BufferedReader(new FileReader(this._filepath))) {
            String sCurrentLine;
            br.readLine();
            br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println("firstname: " +sCurrentLine.split(":")[0]);
                System.out.println("lastname: " +sCurrentLine.split(":")[1]);
                System.out.println("phone: " +sCurrentLine.split(":")[2]);
                System.out.println("email: " +sCurrentLine.split(":")[3]);
                System.out.println("streetname: " +sCurrentLine.split(":")[4]);
                System.out.println("streetnumber: " +sCurrentLine.split(":")[5]);
                System.out.println("state: " +sCurrentLine.split(":")[6]);
                System.out.println("postcode: " +sCurrentLine.split(":")[7]);
                System.out.println("birthday: " +sCurrentLine.split(":")[8]);
                System.out.println("birthmonth: " +sCurrentLine.split(":")[9]);
                System.out.println("birthyear: " +sCurrentLine.split(":")[10]);
                System.out.println("==========================================");
            }
        } catch (IOException e) {
            //
        } 
    }
}
