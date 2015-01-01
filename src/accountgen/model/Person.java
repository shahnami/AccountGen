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
    private Date _expires;
    private String _cvc2;
    private String _ssn; //optional
    private String _favoritecolor;
    private String _occupation;
    private String _company;
    private String _website;
    private String _vehicle;
    private String _upsnr;
    private String _bloodtype;
    private String _weight;
    private String _height;
    private String _guid;
    private String _geoco;
    
    
    //No middlename
    public Person(String firstname, String lastname, Address address, String phone, String email, String inbox,
                  String username, String password, String mmn, Date birthday, String mastercard, Date expires, String cvc2, String ssn,
                  String favoritecolor, String occupation, String company, String website, String vehicle, String upsnr, String bloodtype,
                  String weight, String height, String guid, String geoco){
        
        this(firstname, "", lastname, address, phone, email, inbox, username, password, mmn, birthday, mastercard, expires, cvc2, ssn,
                favoritecolor, occupation, company, website, vehicle, upsnr, bloodtype, weight, height, guid, geoco);
    
    }
    
    //No ssn
    public Person(String firstname, String middlename, String lastname, Address address, String phone, String email, String inbox,
                  String username, String password, String mmn, Date birthday, String mastercard, Date expires, String cvc2,
                  String favoritecolor, String occupation, String company, String website, String vehicle, String upsnr, String bloodtype,
                  String weight, String height, String guid, String geoco){
        
        this(firstname, middlename, lastname, address, phone, email, inbox, username, password, mmn, birthday, mastercard, expires, cvc2, "",
                favoritecolor, occupation, company, website, vehicle, upsnr, bloodtype, weight, height, guid, geoco);
    
    }
    
    //No middlename && no ssn
    public Person(String firstname, String lastname, Address address, String phone, String email, String inbox,
                  String username, String password, String mmn, Date birthday, String mastercard, Date expires, String cvc2,
                  String favoritecolor, String occupation, String company, String website, String vehicle, String upsnr, String bloodtype,
                  String weight, String height, String guid, String geoco){
        
        this(firstname, "", lastname, address, phone, email, inbox, username, password, mmn, birthday, mastercard, expires, cvc2, "",
                favoritecolor, occupation, company, website, vehicle, upsnr, bloodtype, weight, height, guid, geoco);
    
    }
    

    
    public Person(String firstname, String middlename, String lastname, Address address, String phone, String email, String inbox,
                  String username, String password, String mmn, Date birthday, String mastercard, Date expires, String cvc2, String ssn,
                  String favoritecolor, String occupation, String company, String website, String vehicle, String upsnr, String bloodtype,
                  String weight, String height, String guid, String geoco){
        
        this._firstname = firstname;
        this._middlename = middlename;
        this._lastname = lastname;
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
        this._cvc2 = cvc2;
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
        this._geoco = geoco;
    
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

    public String getCvc2() {
        return _cvc2;
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

    public String getVehicle() {
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

    public String getGeoco() {
        return _geoco;
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

    public void setCvc2(String _cvc2) {
        this._cvc2 = _cvc2;
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

    public void setVehicle(String _vehicle) {
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

    public void setGeoco(String _geoco) {
        this._geoco = _geoco;
    }
    
    public String toExportString(){
        return String.format("%s:%s:%s:%s:%s:%s:%s:%s:%s:%s:%s", _firstname, _lastname, _phone, _email, _address.getStreetname(), _address.getStreetnumber(), _address.getState(), _address.getPostcode() , _birthday.getDate(), monthName(_birthday.getMonth()), _birthday.getYear());
    }
    
    @Override
    public String toString(){
        return String.format("name: %s %s\nphone: %s\nemail: %s\naddress: %s %s %s %s\nbirthday: %s %s %s\n========================================", _firstname, _lastname, _phone, _email, _address.getStreetname(), _address.getStreetnumber(), _address.getState(), _address.getPostcode() , _birthday.getDate(), monthName(_birthday.getMonth()), _birthday.getYear());
    }
    
    private String monthName(int month){
        return new DateFormatSymbols().getMonths()[month];
    }
}