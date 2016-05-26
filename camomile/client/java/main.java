/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camomile.client.java;

import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import model.Corpora;
import model.Login;
import model.User;

/**
 *
 * @author mathias
 */
public class main {

    /**
     * @param args the command line arguments
     */
    //localhost:3000
    public static void clear() {
        for (int i = 0; i < 4; i++) {
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        try {
            CamomileClientJava camomile = new CamomileClientJava();
            Corpora c = new Corpora("corpora2");
            User u = new User("user11", "123456789", "user");
            
            camomile.login(new Login("root", "admin"));
            
            camomile.getAllUsers();
            
            camomile.createUser(u);
            
            camomile.createCorpus(c);
            
            
            camomile.logout();
        } catch (Exception ex) {
            System.err.println("\t" + ex.getMessage());
        }
    }

}
