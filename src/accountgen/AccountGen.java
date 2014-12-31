/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accountgen;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author namishah
 */
public class AccountGen {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws java.io.UnsupportedEncodingException
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        // TODO code application logic here
        Controller.getInstance().generate(100, true);
    }
    
}
