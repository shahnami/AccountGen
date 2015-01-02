/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accountgen.view;

import accountgen.controller.Controller;
import accountgen.model.Consts;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

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
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, ClassNotFoundException, SQLException {
        Controller.getInstance().generate(Consts.AMOUNT, Consts.EXPORT_TO_FILE, Consts.EXPORT_TO_DB);
        //Controller.getInstance().readFile(Consts.PATH_URL);
        //Controller.getInstance().readFromDB(1);
        //Controller.getInstance().getDBSize();
    }
    
}
