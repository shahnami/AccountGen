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
public final class Consts {
    //http://www.fakenamegenerator.com/advanced.php?t=country&n[]=nl&c[]=bg&gen=67&age-min=19&age-max=64
    
    public static final String GENERATOR_URL = "http://www.fakenamegenerator.com/advanced.php"; //gen-random-nl-bg.php
    public static final String INBOX_URL = "http://www.fakemailgenerator.com/#/";
    public static final String NAMESET = "nl";
    public static final String COUNTRY = "bg";
    public static final int GENDER = 50; //FEMALE 0|--x--|100 MALE
    public static final int MAX_AGE = 100;
    public static final int MIN_AGE = 18;
    public static final int AMOUNT = 100;
    public static final boolean EXPORT_TO_FILE = true;
    public static final boolean EXPORT_TO_DB = true;
    public static final String PATH_URL = "accounts.txt";
    
    private Consts(){
        throw new AssertionError();
    }
}
