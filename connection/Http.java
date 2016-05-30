/*
The MIT License (MIT)

Copyright (c) 2016 CAMOMILE project

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
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
 * Abstract class for all the possible request type - GET - DELETE - Args : -
 * POST - PUT
 *
 * @author mathias
 */
public abstract class Http {

    protected static String SERVER_ADDRESS;
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

        System.out.println("\n\n>>> Sending request : " + requestMethod + " " + connection.toString());
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

        JSONObject ret;
        try {
            ret = new JSONObject(response.toString());
        } catch (JSONException e) {
            ret = new JSONObject("{\"error\":"+"\""+response.toString()+"\"}");
        }

        throw new CamomileClientException(ret.get("error").toString());
    }

    public HttpURLConnection getConnection() {
        return connection;
    }

    public static String getSERVER_ADDRESS() {
        return SERVER_ADDRESS;
    }

    public static void setSERVER_ADDRESS(String SERVER_ADDRESS) {
        Http.SERVER_ADDRESS = SERVER_ADDRESS;
    }

}
