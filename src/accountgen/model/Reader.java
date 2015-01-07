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
    
    public List<Person> getPerson(int id) throws SQLException, ClassNotFoundException{
        _accounts = new ArrayList<>();
        _accounts.add(Database.getInstance().getPerson(id));
        return _accounts;
    }
    
    private int MonthToInt(String monthname) throws ParseException{
        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("MMM").parse(monthname));
        int monthInt = cal.get(Calendar.MONTH) + 1;
        return monthInt;
    }
    
    public List<Person> personFromFile() throws ParseException{
        _accounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this._filepath))) {
            String sCurrentLine;
            br.readLine();
            br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                Person p = new Person();
                Address a = new Address();
                Vehicle v = new Vehicle();
                Date d = new Date();
                p.setFirstname(sCurrentLine.split(":")[0]);
                p.setMiddlename(sCurrentLine.split(":")[1]);
                p.setLastname(sCurrentLine.split(":")[2]);
                p.setGender(sCurrentLine.split(":")[3]);
                p.setPhone(sCurrentLine.split(":")[4]);
                p.setEmail(sCurrentLine.split(":")[5]);
                p.setInbox(sCurrentLine.split(":")[6]);
                d.setDate(Integer.parseInt(sCurrentLine.split(":")[7]));
                d.setMonth(Integer.parseInt(sCurrentLine.split(":")[8]));
                d.setYear(Integer.parseInt(sCurrentLine.split(":")[9]));
                p.setBirthday(d);
                a.setPostcode(sCurrentLine.split(":")[10]);
                a.setState(sCurrentLine.split(":")[11]);
                a.setStreetname(sCurrentLine.split(":")[12]);
                a.setStreetnumber(sCurrentLine.split(":")[13]);
                a.setCountry(sCurrentLine.split(":")[14]);
                p.setUsername(sCurrentLine.split(":")[15]);
                p.setPassword(sCurrentLine.split(":")[16]);
                p.setMmn(sCurrentLine.split(":")[17]);
                p.setMastercard(sCurrentLine.split(":")[18]);
                p.setCvv2(sCurrentLine.split(":")[19]);
                d = new Date();
                d.setDate(1);
                d.setMonth(Integer.parseInt(sCurrentLine.split(":")[20]));
                d.setYear(Integer.parseInt(sCurrentLine.split(":")[21]));
                p.setExpires(d);
                p.setSsn(sCurrentLine.split(":")[22]);
                p.setFavoritecolor(sCurrentLine.split(":")[23]);
                p.setOccupation(sCurrentLine.split(":")[24]);
                p.setWebsite(sCurrentLine.split(":")[25]);
                p.setCompany(sCurrentLine.split(":")[26]);
                v.setBrand(sCurrentLine.split(":")[27]);
                v.setModel(sCurrentLine.split(":")[28]);
                v.setYear(Integer.parseInt(sCurrentLine.split(":")[29]));
                p.setUpsnr(sCurrentLine.split(":")[30]);
                p.setBloodtype(sCurrentLine.split(":")[31]);
                p.setWeight(sCurrentLine.split(":")[32]);
                p.setHeight(sCurrentLine.split(":")[33]);
                p.setGuid(sCurrentLine.split(":")[34]);
                p.setGEOX(sCurrentLine.split(":")[35]);
                p.setGEOY(sCurrentLine.split(":")[36]);
                p.setAdress(a);
                p.setVehicle(v);
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
