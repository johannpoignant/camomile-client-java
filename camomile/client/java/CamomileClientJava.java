/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camomile.client.java;

import connection.Get;
import connection.Post;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Map;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.parser.JSONParser;
import model.Corpora;
import model.Login;
import model.User;
import org.json.JSONObject;

/**
 *
 * @author mathias
 */
public class CamomileClientJava {

    static private HttpCookie cookie;
    private static final String SET_COOKIE = "set-cookie";
    private static final String COOKIE_REQ_PROP = "Cookie";

    private static final String USERNAME = "oz";
    private static final String PASSWORD = "ozozozoz";
    private static final String ROOT_USERNAME = "oz";
    private static final String ROOT_PASSWORD = "ozozozoz";

    private String address;
    private URL url;
    private HttpURLConnection connection;
    private String id;

    public CamomileClientJava() {
        this.address = "http://localhost:3000";
    }

    /////////////////////////////////
    //          Authentication
    /////////////////////////////////
    public void login(Login login) {
        Post post = new Post("/login", login.toArgs());
        post.execute();

        Map<String, List<String>> headerFields = post.getConnection().getHeaderFields();
        if (headerFields.containsKey(SET_COOKIE)) {
            List<String> cookieValues = headerFields.get(SET_COOKIE);
            for (String cookieH : cookieValues) {
                cookie = HttpCookie.parse(cookieH).get(0);
                post.setCookie(cookie);
            }
            System.out.println("Cookies added : " + cookie.getValue());
        }

        //ERROR HANDLER
    }

    public void logout() {
        new Post("/logout").execute();
    }

    public User getMe() {
        return new User(new Get("/me").execute());
    }

    /////////////////////////////////
    //            Users
    /////////////////////////////////
    public void createUser(User user) throws CamomileClientException {
        /*String argsPost = "username=" + user.getName() + "&password=" + user.getPassword()
                + "&description=" + user.getDescription().toString() + "&role=" + user.getRole();
        JSONObject jso = sendPost("/user", argsPost);*/

        JSONObject jso = sendPost("/user", user.toArgs());
        if (jso.get("error") != null) {
            throw new CamomileClientException((String) jso.get("error"));
        }
        user.setId(jso.getString("_id"));
    }

    public String deleteUser(String id) {
        return sendDelete("/user/" + id);
    }

    public String deleteUser(User user) {
        return sendDelete("/user/" + user.getId());
    }

    public String getAllUser() {
        return sendGet("/user");
    }

    public String getUser(String id) {
        return sendGet("/user/" + id);
    }

    public String updateUser(User user) {
        return sendPut("/user/" + user.getId(), "description=" + user.getDescription());
    }

    /////////////////////////////////
    //            Corpora
    /////////////////////////////////
    public String createCorpus(Corpora copora) {
        return sendPost("/corpus", "name=" + copora.getName() + "&description=" + copora.getDescription().toString());
    }

    public String deleteCorpus(String id) {
        return sendDelete("/corpus/" + id);
    }

    public String deleteCorpus(Corpora copora) {
        return sendDelete("/corpus/" + copora.getId());
    }

    public String getAllCorpus() {
        return sendGet("/corpus");
    }

    public String getCorpus(String id) {
        return sendGet("/corpus/" + id);
    }

    public String updateCorpus(String id, String name, String licence) {
        return sendPut("/corpus/" + id, "name=" + name + "&description={'license':" + licence + "}"); //ordre changer, voir doc
    }

    /////////////////////////////////
    //            Media
    /////////////////////////////////
    public String createMedia(String name, String idCorpus) {
        return sendPost("/corpus/" + idCorpus + "/medium", "name=" + name);
    }

    public String deleteMedia(String idMedia) {
        return sendDelete("/medium/" + idMedia);
    }

    public String getAllMedia() {
        return sendGet("/medium");
    }

    public String getMedia(String idMedia) {
        return sendGet("/medium/" + idMedia);
    }

    public String updateMedia(String idMedia, String description) {
        return sendPut("/medium/" + idMedia, "description=" + description);
    }

    /////////////////////////////////
    //            Layers
    /////////////////////////////////
    public String createLayer(String name, String idCorpus) {
        return sendPost("/corpus/" + idCorpus + "/layer", "name=" + name);
    }

    public String deleteLayer(String idLayer) {
        return sendDelete("/layer/" + idLayer);
    }

    public String getAllLayer() {
        return sendGet("/layer");
    }

    public String getLayer(String idLayer) {
        return sendGet("/layer/" + idLayer);
    }

    public String updateLayer(String idLayer, String description) {
        return sendPut("/layer/" + idLayer, "description=" + description);
    }

    /////////////////////////////////
    //            Annotation
    /////////////////////////////////
    public String createAnnotation(String idLayer, String idMedium, String fragment, String data) {
        return sendPost("/layer/" + idLayer + "/annotation", "id_medium=" + idMedium + "&fragment=" + fragment + "&data=" + data);
    }

    public String deleteAnnotation(String idAnnotation) {
        return sendDelete("/annotation/" + idAnnotation);
    }

    public String getAllAnnotation() {
        return sendGet("/annotation");
    }

    public String getAnnotation(String idAnnotation) {
        return sendGet("/annotation/" + idAnnotation);
    }

    public String updateAnnotation(String idAnnotation, String fragment, String data) {
        return sendPut("/annotation/" + idAnnotation, "fragment=" + fragment + "&data=" + data);
    }

    /////////////////////////////////
    //            HTTP
    /////////////////////////////////
    ///////////////////
    //      POST
    ///////////////////
    private JSONObject sendPost(String action, String arg) {
        try {
            String adr = address + action;
            url = new URL(adr);
            connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            if (cookie != null) {
                connection.setRequestProperty(COOKIE_REQ_PROP, "camomile.sid=" + cookie.getValue());
            }

            connection.setRequestProperty("Content-Type", "application/json");

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(arg);
            wr.flush();
            //wr.close();

            System.out.println(">>> Sending request : " + connection.toString() + "\n\tPOST : " + arg);

            //Méthode pour récupérer le code de la réponse du serveur
            getRespCode();

            //Méthode pour récupérer la réponse du serveur
            return getResp();
        } catch (Exception ex) {
            Logger.getLogger(CamomileClientJava.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new JSONObject("{\"error\":\"deuxieme return\"}");
    }

    private JSONObject sendPost(String action) {
        try {
            String adr = address + action;
            url = new URL(adr);
            connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");

            if (cookie != null) {
                connection.setRequestProperty(COOKIE_REQ_PROP, "camomile.sid=" + cookie.getValue());
            }

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

    ///////////////////
    //      GET
    ///////////////////
    private JSONObject sendGet(String action) {
        try {
            String adr = address + action;
            url = new URL(adr);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            if (cookie != null) {
                connection.setRequestProperty(COOKIE_REQ_PROP, "camomile.sid=" + cookie.getValue());
            }

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

    ///////////////////
    //      DELETE
    ///////////////////
    private JSONObject sendDelete(String target) {
        try {
            String adr = address + target;
            url = new URL(adr);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("DELETE");

            if (cookie != null) {
                connection.setRequestProperty(COOKIE_REQ_PROP, "camomile.sid=" + cookie.getValue());
            }

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

    ///////////////////
    //      PUT
    ///////////////////
    private JSONObject sendPut(String action, String arg) {
        try {
            String adr = address + action;
            url = new URL(adr);
            connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("PUT");

            if (cookie != null) {
                connection.setRequestProperty(COOKIE_REQ_PROP, "camomile.sid=" + cookie.getValue());
            }

            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(arg);
            wr.flush();
            //wr.close();

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

    ///////////////////
    //      META
    ///////////////////
    private int getRespCode() throws Exception {
        int responseCode = connection.getResponseCode();

        if (responseCode < 200 || responseCode > 300) {
            System.err.println(responseCode + " : " + connection.getResponseMessage());
            throw new Exception("ResponseCodeError");
        }

        System.out.println(">> Response Code : " + responseCode);

        return responseCode;
    }

    private JSONObject getResp() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(">> Response : " + response.toString());

        JSONObject ret = new JSONObject(response.toString());
        return ret;
    }

    public class CamomileClientException extends Exception {

        public CamomileClientException(String message) {
            super(message);
        }

    }
}
