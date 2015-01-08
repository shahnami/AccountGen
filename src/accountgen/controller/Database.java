/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accountgen.controller;

import accountgen.model.Address;
import accountgen.model.Person;
import accountgen.model.Vehicle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author namishah
 */
public class Database {
    private static Database _instance = null;
    private static Connection _connection = null;
    
    public static Database getInstance(){
        if(_instance == null){
            _instance = new Database();
        }
        return _instance;
    }
    
    
    public void openConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        _connection = DriverManager.getConnection("jdbc:mysql://localhost/account?user=root&password=root");
    }
    
    public void closeConnection(){
        try {
            _connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getDBSize() throws ClassNotFoundException, SQLException{
        int size = 0;
        openConnection();
        try (PreparedStatement stmt = _connection.prepareStatement("SELECT DISTINCT COUNT(idperson) FROM person")){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                size = rs.getInt(1);
            }
        }
        closeConnection();
        return size;
    }
    
    public void insertAccount(Person p) throws ClassNotFoundException, SQLException{
        //openConnection();
        
        String personSQL = 
                    "insert into person "+
                    "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String addressSQL = 
                    "insert into address "+
                    "values (?, ?, ?, ?, ?, ?)";
        
        String vehicleSQL = 
                    "insert into vehicle "+
                    "values (?, ?, ?, ?)";
        int addressID = 0;
        int vehicleID = 0;
        
        //Add address
        try(PreparedStatement stmt = _connection.prepareStatement(addressSQL, Statement.RETURN_GENERATED_KEYS)){
            stmt.setNull(1, Types.INTEGER);
            stmt.setString(2, p.getAdress().getStreetname());
            stmt.setInt(3, Integer.parseInt(p.getAdress().getStreetnumber()));
            stmt.setInt(4, Integer.parseInt(p.getAdress().getPostcode()));
            stmt.setString(5, p.getAdress().getState());
            stmt.setString(6, p.getAdress().getCountry());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
               addressID = rs.getInt(1);
            }
        }
        
        //add vehicle
        try(PreparedStatement stmt = _connection.prepareStatement(vehicleSQL, Statement.RETURN_GENERATED_KEYS)){
            stmt.setNull(1, Types.INTEGER);
            stmt.setString(2, p.getVehicle().getBrand());
            stmt.setString(3, p.getVehicle().getModel());
            stmt.setInt(4, p.getVehicle().getYear());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
               vehicleID = rs.getInt(1);
            }
        }
        
        try (PreparedStatement stmt = _connection.prepareStatement(personSQL)){
            stmt.setNull(1, Types.INTEGER);
            stmt.setString(2, p.getFirstname());
            stmt.setString(3, p.getMiddlename());
            stmt.setString(4, p.getLastname());
            stmt.setInt(5, addressID);
            stmt.setString(6, p.getPhone());
            stmt.setString(7, p.getEmail());
            stmt.setString(8, p.getInbox());
            stmt.setString(9, p.getUsername());
            stmt.setString(10, p.getPassword());
            stmt.setString(11, p.getMmn());
            stmt.setDate(12, new java.sql.Date(p.getBirthday().getTime()));
            stmt.setString(13, p.getMastercard());
            stmt.setDate(14, new java.sql.Date(p.getExpires().getTime()));
            stmt.setString(15, p.getCvv2());
            stmt.setString(16, p.getSsn());
            stmt.setString(17, p.getFavoritecolor());
            stmt.setString(18, p.getOccupation());
            stmt.setString(19, p.getCompany());
            stmt.setString(20, p.getWebsite());
            stmt.setInt(21, vehicleID);
            stmt.setString(22, p.getUpsnr());
            stmt.setString(23, p.getBloodtype());
            stmt.setString(24, p.getWeight());
            stmt.setString(25, p.getHeight());
            stmt.setString(26, p.getGuid());
            stmt.setString(27, p.getGEOX());
            stmt.setString(28, p.getGEOY());
            stmt.setString(29, p.getGender());
            stmt.executeUpdate();
        }
        //closeConnection();
    }
    
    public List<Person> getAllPerson() throws ClassNotFoundException, SQLException{
        openConnection();
        List<Person> persons = new ArrayList<>();
        PreparedStatement stmt = _connection.prepareStatement("SELECT * FROM person p JOIN address a ON a.idaddress = p.idaddress JOIN vehicle v ON v.idvehicle = p.idvehicle");
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            Person p = new Person();   
            p.setFirstname(rs.getString("firstname"));
            p.setMiddlename(rs.getString("middlename"));
            p.setLastname(rs.getString("lastname"));
            p.setGender(rs.getString("gender"));
            p.setPhone(rs.getString("phone"));
            p.setEmail(rs.getString("email"));
            p.setInbox(rs.getString("inbox"));
            p.setBirthday(rs.getDate("birthday"));
            Address ad = new Address();
            ad.setPostcode(String.valueOf(rs.getInt("postcode")));
            ad.setState(rs.getString("state"));
            ad.setStreetname(rs.getString("streetname"));
            ad.setStreetnumber(String.valueOf(rs.getInt("streetnumber")));
            ad.setCountry(rs.getString("country"));
            p.setAdress(ad);
            p.setUsername(rs.getString("username"));
            p.setPassword(rs.getString("password"));
            p.setMmn(rs.getString("mmn"));
            p.setMastercard(rs.getString("mastercard"));
            p.setCvv2(rs.getString("cvv2"));
            p.setExpires(rs.getDate("expires"));
            p.setSsn(rs.getString("ssn"));
            p.setFavoritecolor(rs.getString("favoritecolor"));
            p.setOccupation(rs.getString("occupation"));
            p.setWebsite(rs.getString("website"));
            p.setCompany(rs.getString("company"));
            Vehicle v = new Vehicle();
            v.setBrand(rs.getString("brand"));
            v.setModel(rs.getString("model"));
            v.setYear(rs.getInt("year"));
            p.setVehicle(v);
            p.setUpsnr(rs.getString("upsnr"));
            p.setBloodtype(rs.getString("bloodtype"));
            p.setWeight(rs.getString("weight"));
            p.setHeight(rs.getString("height"));
            p.setGuid(rs.getString("guid"));
            p.setGEOX(rs.getString("geo_x"));
            p.setGEOY(rs.getString("geo_y"));
            persons.add(p);
        }
        closeConnection();
        return persons; 
    }
    
    public Person getPerson(int id) throws SQLException, ClassNotFoundException{
        openConnection();
        List<Person> persons = new ArrayList<>();
        PreparedStatement stmt = _connection.prepareStatement("SELECT * FROM person p JOIN address a ON a.idaddress = p.idaddress JOIN vehicle v ON v.idvehicle = p.idvehicle WHERE idperson = ?");
        stmt.setInt(1, id); 
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            Person p = new Person();   
            p.setFirstname(rs.getString("firstname"));
            p.setMiddlename(rs.getString("middlename"));
            p.setLastname(rs.getString("lastname"));
            p.setGender(rs.getString("gender"));
            p.setPhone(rs.getString("phone"));
            p.setEmail(rs.getString("email"));
            p.setInbox(rs.getString("inbox"));
            p.setBirthday(rs.getDate("birthday"));
            Address ad = new Address();
            ad.setPostcode(String.valueOf(rs.getInt("postcode")));
            ad.setState(rs.getString("state"));
            ad.setStreetname(rs.getString("streetname"));
            ad.setStreetnumber(String.valueOf(rs.getInt("streetnumber")));
            ad.setCountry(rs.getString("country"));
            p.setAdress(ad);
            p.setUsername(rs.getString("username"));
            p.setPassword(rs.getString("password"));
            p.setMmn(rs.getString("mmn"));
            p.setMastercard(rs.getString("mastercard"));
            p.setCvv2(rs.getString("cvv2"));
            p.setExpires(rs.getDate("expires"));
            p.setSsn(rs.getString("ssn"));
            p.setFavoritecolor(rs.getString("favoritecolor"));
            p.setOccupation(rs.getString("occupation"));
            p.setWebsite(rs.getString("website"));
            p.setCompany(rs.getString("company"));
            Vehicle v = new Vehicle();
            v.setBrand(rs.getString("brand"));
            v.setModel(rs.getString("model"));
            v.setYear(rs.getInt("year"));
            p.setVehicle(v);
            p.setUpsnr(rs.getString("upsnr"));
            p.setBloodtype(rs.getString("bloodtype"));
            p.setWeight(rs.getString("weight"));
            p.setHeight(rs.getString("height"));
            p.setGuid(rs.getString("guid"));
            p.setGEOX(rs.getString("geo_x"));
            p.setGEOY(rs.getString("geo_y"));
            persons.add(p);
        }
        closeConnection();
        return persons.get(0);
    }
    
    private Database(){
        //
    }
}
