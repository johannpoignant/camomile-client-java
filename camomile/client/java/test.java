/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camomile.client.java;

import model.User;
import org.json.JSONObject;

/**
 *
 * @author mathias
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        User user = new User("user", "123456789", "user");
        JSONObject jso = new JSONObject(user);
        System.out.println(jso.toString());
    }
    
}
