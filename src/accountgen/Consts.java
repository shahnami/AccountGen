/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accountgen;

/**
 *
 * @author namishah
 */
public final class Consts {
    public static final String GENERATOR_URL = "http://www.fakenamegenerator.com/gen"; //gen-random-nl-bg.php
    public static final String GENERATOR_URL_EXT = ".php";
    public static final String GENDER = "-random";
    public static final String NAMESET = "-nl";
    public static final String COUNTRY = "-bg";   
    
    private Consts(){
        throw new AssertionError();
    }
}
