/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import camomile.client.java.CamomileClientException;
import camomile.client.java.CamomileClientJava;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author mathias
 */
public abstract class Http {

    protected static final String SERVER_ADDRESS = "http://localhost:3000";
    protected static final String SET_COOKIE = "set-cookie";
    protected static final String COOKIE_REQ_PROP = "Cookie";
    protected static HttpCookie cookie;

    protected HttpURLConnection connection;
    protected URL url;
    protected String requestMethod;
    protected String action;
    protected String args;

    public Http(String action) {
        this.action = action;
        this.args = "";
    }

    public Http(String action, String args) {
        this.action = action;
        this.args = args;
    }

    //public abstract JSONObject execute();
    public void setCookie(HttpCookie cookie) {
        this.cookie = cookie;
    }

    public JSONObject execute() throws ProtocolException, IOException, Exception {
        requestServer();

        //Méthode pour récupérer la réponse du serveur
        return responseCodeHandler();

    }

    protected void requestServer() throws MalformedURLException, ProtocolException, IOException {
        url = new URL(SERVER_ADDRESS + action);
        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(this.requestMethod);

        if (cookie != null) {
            connection.setRequestProperty(COOKIE_REQ_PROP, "camomile.sid=" + cookie.getValue());
        }

        connection.setRequestProperty("Content-Type", "application/json");

        System.out.println("\n\n>>> Sending request : " + connection.toString());
    }

    protected JSONObject responseCodeHandler() throws IOException, CamomileClientException {
        int responseCode = connection.getResponseCode();
        System.out.println("\tResponse Code : " + responseCode);
        if (responseCode < 200 || responseCode > 300) {
            getError();
        }

        return getResp();
    }

    protected JSONObject getResp() throws IOException, CamomileClientException {
        //Initialisation des objets de lecture
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        //Lecture de la réponse du serveur
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println("\t> Response : " + response.toString());

        JSONObject ret;
        try {//Si le JSON est un tableau on le transforme en simple object
            ret = new JSONObject(response.toString());
        } catch (JSONException e) {
            JSONArray jsa = new JSONArray(response.toString());
            ret = new JSONObject().append("array", jsa);
        }

        return ret;
    }

    protected JSONObject getError() throws IOException, CamomileClientException {
        //Initialisation des objets de lecture
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        //Lecture de la réponse du serveur
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println("\t> Response : " + response.toString());

        JSONObject ret = new JSONObject(response.toString());

        throw new CamomileClientException(ret.getString("error"));
    }

    public HttpURLConnection getConnection() {
        return connection;
    }

}
