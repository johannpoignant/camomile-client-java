/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camomile.client.java;

import model.Login;
import model.User;
import org.json.JSONObject;

/**
 *
 * @author mathias
 */
public class testUser {

    public static String PASSWORD = "123456789";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            CamomileClientJava camomile = new CamomileClientJava("http://localhost:3000");
            camomile.login(new Login("root", "admin"));

            User user = new User("user160", PASSWORD, "user");
            User user2 = new User("user161", PASSWORD, "user");

            user = camomile.createUser(user);
            user2 = camomile.createUser(user2);

            user2.setDescription(new JSONObject("{\"TestDescriptionUPdate\":\"réussi\"}"));

            camomile.updateUser(user2);

            camomile.getUser(user2.getId());

            camomile.getAllUsers();

            camomile.deleteUser(user);
            camomile.deleteUser(user2.getId());

            camomile.logout();
        } catch (Exception ex) {
            System.err.println("\t" + ex.getMessage());
            ex.printStackTrace();

        }
    }

}
