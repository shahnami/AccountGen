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
import java.util.ArrayList;
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
    
    public void readFromDB(int id) throws SQLException, ClassNotFoundException{
        if(id == -1){
            _accounts = Database.getInstance().getAllAccounts();
        } else {
            _accounts.add(Database.getInstance().getPerson(id));
        }
        for(Person account:_accounts){
            System.out.println(account.toString());
        }
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
