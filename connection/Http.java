/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
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

    public abstract JSONObject execute();

    public void setCookie(HttpCookie cookie) {
        this.cookie = cookie;
    }

    protected void initConnection() throws MalformedURLException, ProtocolException, IOException {
        url = new URL(SERVER_ADDRESS + action);
        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(this.requestMethod);

        if (cookie != null) {
            connection.setRequestProperty(COOKIE_REQ_PROP, "camomile.sid=" + cookie.getValue());
        }

        connection.setRequestProperty("Content-Type", "application/json");
    }

    protected int getRespCode() throws Exception {
        int responseCode = connection.getResponseCode();

        if (responseCode < 200 || responseCode > 300) {
            System.err.println(responseCode + " : " + connection.getResponseMessage());
            throw new Exception("ResponseCodeError");
        }

        System.out.println("\tResponse Code : " + responseCode);

        return responseCode;
    }

    protected JSONObject getResp() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println("\t> Response : " + response.toString());

        JSONObject ret = new JSONObject(response.toString());
        return ret;
    }

    public HttpURLConnection getConnection() {
        return connection;
    }

}
