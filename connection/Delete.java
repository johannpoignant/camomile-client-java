/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import camomile.client.java.CamomileClientJava;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author mathias
 */
public class Delete extends Http {

    public Delete(String action, String target) {
        super(action + "/" + target);
        this.requestMethod = "GET";
    }

    @Override
    public JSONObject execute() {
        try {
            initConnection();

            System.out.println(">>> Sending request : " + connection.toString());

            //Méthode pour récupérer le code de la réponse du serveur
            getRespCode();

            //Méthode pour récupérer la réponse du serveur
            return getResp();
        } catch (Exception ex) {
            Logger.getLogger(CamomileClientJava.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new JSONObject("{\"error\":\"deuxieme return\"}");
    }

}
