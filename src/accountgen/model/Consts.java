/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accountgen.model;

/**
 *
 * @author namishah
 */
public class Consts {
    //http://www.fakenamegenerator.com/advanced.php?t=country&n[]=nl&c[]=bg&gen=67&age-min=19&age-max=64
    
    public static final String GENERATOR_URL = "http://www.fakenamegenerator.com/advanced.php"; //gen-random-nl-bg.php
    public static final String INBOX_URL = "http://www.fakemailgenerator.com/#/";
    public static final String NAMESET = "nl";
    public static String COUNTRY = "bg";
    public static int GENDER = 50; //FEMALE 0|--x--|100 MALE
    public static int MAX_AGE = 100;
    public static int MIN_AGE = 18;
    public static final int AMOUNT = 10; //Overrides in GUI
    public static final boolean EXPORT_TO_FILE = true;  //Overrides in GUI
    public static final boolean EXPORT_TO_DB = true;    //Overrides in GUI
    public static final String PATH_URL = "accounts.txt";
    
    public void setGender(int g){
        GENDER = g;
    }
    
    public void setAge(int min, int max){
        MAX_AGE = max;
        MIN_AGE= min;
    }
    
    public void setCountry(String c){
        switch(c){
            case "Belgium":
                COUNTRY = "bg";
                break;
            default:
                COUNTRY = "bg";
        }
    }
    
    public Consts(){
        //throw new AssertionError();
    }
}
