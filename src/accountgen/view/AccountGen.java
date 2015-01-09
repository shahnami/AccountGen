/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package accountgen.view;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.UIManager;

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
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, ClassNotFoundException, SQLException, ParseException {
        MainFrame frame = new MainFrame();
        frame.setTitle("Fake name generator");
        frame.setVisible(true);
        frame.pack();
        //Controller.getInstance().generate(Consts.AMOUNT, Consts.EXPORT_TO_FILE, Consts.EXPORT_TO_DB);
        //Controller.getInstance().printPerson(1);
        //Controller.getInstance().readFile(Consts.PATH_URL);
        //Controller.getInstance().exportToDB(Consts.PATH_URL, true);
        //System.out.println("================================");
        //Controller.getInstance().printPerson(1);
        //Controller.getInstance().getDBSize();
        //Controller.getInstance().exportToFile(Consts.PATH_URL, true);
        
    }
    
}
