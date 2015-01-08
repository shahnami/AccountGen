/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accountgen.model;

import java.text.DateFormatSymbols;
import java.util.Date;

/**
 *
 * @author namishah
 */
public class Person {
    private String _firstname;
    private String _middlename; //optional
    private String _gender;
    private String _lastname;
    private Address _address;
    private String _phone;
    private String _email;
    private String _inbox;
    private String _username;
    private String _password;
    private String _mmn;
    private Date _birthday;
    private String _mastercard;
    private Date _expires = new Date();
    private String _cvv2;
    private String _ssn; //optional
    private String _favoritecolor;
    private String _occupation;
    private String _company;
    private String _website;
    private Vehicle _vehicle;
    private String _upsnr;
    private String _bloodtype;
    private String _weight;
    private String _height;
    private String _guid;
    private String _geo_x;
    private String _geo_y;
    
    public Person(String firstname, String middlename, String lastname, String gender, Address address, String phone, String email, String inbox,
                  String username, String password, String mmn, Date birthday, String mastercard, Date expires, String cvv2, String ssn,
                  String favoritecolor, String occupation, String company, String website, Vehicle vehicle, String upsnr, String bloodtype,
                  String weight, String height, String guid, String geox, String geoy){
        
        this._firstname = firstname;
        this._middlename = middlename;
        this._lastname = lastname;
        this._gender = gender;
        this._address = address;
        this._phone = phone;
        this._email = email;
        this._inbox = inbox;
        this._username = username;
        this._password = password;
        this._mmn = mmn;
        this._birthday = birthday;
        this._mastercard = mastercard;
        this._expires = expires;
        this._cvv2 = cvv2;
        this._ssn = ssn;
        this._favoritecolor = favoritecolor;
        this._occupation = occupation;
        this._company = company;
        this._website = website;
        this._vehicle = vehicle;
        this._upsnr = upsnr;
        this._bloodtype = bloodtype;
        this._weight = weight;
        this._height = height;
        this._guid = guid;
        this._geo_x = geox;
        this._geo_y = geoy;
    
    }
    
    public Person(){
        //
    }

    public String getFirstname() {
        return _firstname;
    }

    public String getMiddlename() {
        return _middlename;
    }

    public String getLastname() {
        return _lastname;
    }

    public String getGender() {
        return _gender;
    }

    public Address getAdress() {
        return _address;
    }

    public String getPhone() {
        return _phone;
    }

    public String getEmail() {
        return _email;
    }

    public String getInbox() {
        return _inbox;
    }

    public String getUsername() {
        return _username;
    }

    public String getPassword() {
        return _password;
    }

    public String getMmn() {
        return _mmn;
    }

    public Date getBirthday() {
        return _birthday;
    }

    public String getMastercard() {
        return _mastercard;
    }

    public Date getExpires() {
        return _expires;
    }

    public String getCvv2() {
        return _cvv2;
    }

    public String getSsn() {
        return _ssn;
    }

    public String getFavoritecolor() {
        return _favoritecolor;
    }

    public String getOccupation() {
        return _occupation;
    }

    public String getCompany() {
        return _company;
    }

    public String getWebsite() {
        return _website;
    }

    public Vehicle getVehicle() {
        return _vehicle;
    }

    public String getUpsnr() {
        return _upsnr;
    }

    public String getBloodtype() {
        return _bloodtype;
    }

    public String getWeight() {
        return _weight;
    }

    public String getHeight() {
        return _height;
    }

    public String getGuid() {
        return _guid;
    }

    public String getGEOX() {
        return _geo_x;
    }

    public String getGEOY() {
        return _geo_y;
    }
    
    public void setFirstname(String _firstname) {
        this._firstname = _firstname;
    }

    public void setMiddlename(String _middlename) {
        this._middlename = _middlename;
    }

    public void setLastname(String _lastname) {
        this._lastname = _lastname;
    }

    public void setGender(String _gender) {
        this._gender = _gender;
    }

    public void setAdress(Address _address) {
        this._address = _address;
    }

    public void setPhone(String _phone) {
        this._phone = _phone;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    public void setInbox(String _inbox) {
        this._inbox = _inbox;
    }

    public void setUsername(String _username) {
        this._username = _username;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public void setMmn(String _mmn) {
        this._mmn = _mmn;
    }

    public void setBirthday(Date _birthday) {
        this._birthday = _birthday;
    }

    public void setMastercard(String _mastercard) {
        this._mastercard = _mastercard;
    }

    public void setExpires(Date _expires) {
        this._expires = _expires;
    }

    public void setCvv2(String _cvv2) {
        this._cvv2 = _cvv2;
    }

    public void setSsn(String _ssn) {
        this._ssn = _ssn;
    }

    public void setFavoritecolor(String _favoritecolor) {
        this._favoritecolor = _favoritecolor;
    }

    public void setOccupation(String _occupation) {
        this._occupation = _occupation;
    }

    public void setCompany(String _company) {
        this._company = _company;
    }

    public void setWebsite(String _website) {
        this._website = _website;
    }

    public void setVehicle(Vehicle _vehicle) {
        this._vehicle = _vehicle;
    }

    public void setUpsnr(String _upsnr) {
        this._upsnr = _upsnr;
    }

    public void setBloodtype(String _bloodtype) {
        this._bloodtype = _bloodtype;
    }

    public void setWeight(String _weight) {
        this._weight = _weight;
    }

    public void setHeight(String _height) {
        this._height = _height;
    }

    public void setGuid(String _guid) {
        this._guid = _guid;
    }

    public void setGEOX(String _geoco) {
        this._geo_x = _geoco;
    }
    
    public void setGEOY(String _geoco) {
        this._geo_y = _geoco;
    }
    
    
    public String toExportString(){
        return String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s", 
                            _firstname, _middlename, _lastname, _gender, _phone, _email, _inbox, 
                            _birthday.getDate(), _birthday.getMonth()+1, _birthday.getYear()+1900, 
                            _address.getCountry(), _address.getStreetname(), _address.getStreetnumber(), _address.getPostcode(), _address.getState(), 
                            _username, _password, _mmn, _mastercard, _cvv2, _expires.getMonth()+1, _expires.getYear()+1900, _ssn, _favoritecolor, _occupation, 
                            _website, _company, _vehicle.getBrand(), _vehicle.getModel(), _vehicle.getYear(), _upsnr, _bloodtype, _weight, _height, _guid, _geo_x, _geo_y
                );
    }
    
    @Override
    public String toString(){
        return String.format("name: %s %s\nphone: %s\nemail: %s\naddress: %s %s %s %s\nbirthday: %s %s %s\n========================================", _firstname, _lastname, _phone, _email, _address.getStreetname(), _address.getStreetnumber(), _address.getState(), _address.getPostcode() , _birthday.getDate(), monthName(_birthday.getMonth()), _birthday.getYear()+1900);
    }
    
    private String monthName(int month){
        return new DateFormatSymbols().getMonths()[month];
    }
}