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

    public Delete(String action, String targetId) {
        super(action + "/" + targetId);
        this.requestMethod = "GET";
    }

    public Delete(String action) {
        super(action);
        this.requestMethod = "GET";
    }

}
