/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camomile.client.java;

import connection.Delete;
import connection.Get;
import connection.Post;
import connection.Put;
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
import model.Annotation;
import model.Corpora;
import model.Layer;
import model.Login;
import model.Media;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
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
    public boolean login(Login login) throws Exception {
        Post post = new Post("/login", login.toArgs());
        JSONObject jso = post.execute();

        Map<String, List<String>> headerFields = post.getConnection().getHeaderFields();
        if (headerFields.containsKey(SET_COOKIE)) {
            List<String> cookieValues = headerFields.get(SET_COOKIE);
            for (String cookieH : cookieValues) {
                cookie = HttpCookie.parse(cookieH).get(0);
                post.setCookie(cookie);
            }
            System.out.println("Cookies added : " + cookie.getValue());
        }

        return jso.isNull("error");
    }

    public boolean logout() throws Exception {
        return new Post("/logout").execute().isNull("success");
    }

    public User getMe() throws Exception{
        return new User(new Get("/me").execute());
    }

    /////////////////////////////////
    //            Users
    /////////////////////////////////
    public User createUser(User user) throws Exception {
        /*String argsPost = "username=" + user.getName() + "&password=" + user.getPassword()
                + "&description=" + user.getDescription().toString() + "&role=" + user.getRole();
        JSONObject jso = sendPost("/user", argsPost);*/

        JSONObject jso = new Post("/user", user.toArgs()).execute();
        if (jso.get("error") != null) {
            throw new CamomileClientException((String) jso.get("error"));
        }
        user.setId(jso.getString("_id"));

        return new User(jso);
    }

    public boolean deleteUser(String id)throws Exception {
        return new Delete("/user", id).execute().isNull("success");
    }

    public boolean deleteUser(User user)throws Exception {
        return new Delete("/user", user.getId()).execute().isNull("success");
    }

    public ArrayList<User> getAllUsers()throws Exception {
        ArrayList<User> ret = new ArrayList<>();
        JSONArray jsa = new Get("/user").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new User(jsa.getJSONObject(i)));
        }

        return ret;
    }

    public User getUser(String id) throws Exception{
        return new User(new Get("/user/" + id).execute());
    }

    public User updateUser(User user)throws Exception {
        return new User(new Put("/user", user.toArgs()).execute());
    }

    /////////////////////////////////
    //            Corpora
    /////////////////////////////////
    public Corpora createCorpus(Corpora corpora) throws Exception{
        return new Corpora(new Post("/corpus", corpora.toArgs()).execute());
    }

    public boolean deleteCorpus(String id) throws Exception{
        return new Delete("/corpus", id).execute().isNull("success");
    }

    public boolean deleteCorpus(Corpora corpora)throws Exception {
        return new Delete("/corpus", corpora.getId()).execute().isNull("success");
    }

    public ArrayList<Corpora> getAllCorpus()throws Exception {
        ArrayList<Corpora> ret = new ArrayList<>();
        JSONArray jsa = new Get("/corpus").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Corpora(jsa.getJSONObject(i)));
        }
        return ret;
    }

    public Corpora getCorpus(String id)throws Exception {
        return new Corpora(new Get("/corpus", id).execute());
    }

    public Corpora updateCorpus(Corpora corpora)throws Exception {
        return new Corpora(new Put("/corpus", corpora.toArgs()).execute());
    }

    /////////////////////////////////
    //            Media
    /////////////////////////////////
    public Media createMedia(Media media, Corpora corpora)throws Exception {
        return new Media(new Post("/corpus/" + corpora.getId() + "/medium", media.toArgs()).execute());
    }

    public Media createMedia(Media media, String idCorpora)throws Exception {
        return new Media(new Post("/corpus/" + idCorpora + "/medium", media.toArgs()).execute());
    }

    public Media createMedia(Media media) throws Exception {
        if (media.getIdCorpus().isEmpty()) {
            throw new CamomileClientException("Ce Media ne dispose pas d'id_corpus");
        }
        return new Media(new Post("/corpus/" + media.getIdCorpus() + "/medium", media.toArgs()).execute());
    }

    public boolean deleteMedia(String idMedia)throws Exception {
        return new Delete("/medium", idMedia).execute().isNull("success");
    }

    public boolean deleteMedia(Media media) throws Exception{
        return new Delete("/medium", media.getId()).execute().isNull("success");
    }

    public ArrayList<Media> getAllMedia()throws Exception {
        ArrayList<Media> ret = new ArrayList<>();
        JSONArray jsa = new Get("/media").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Media(jsa.getJSONObject(i)));
        }
        return ret;
    }

    public ArrayList<Media> getAllMediaFromCorpora(Corpora corpora) throws Exception{
        ArrayList<Media> ret = new ArrayList<>();
        JSONArray jsa = new Get("/media/" + corpora.getId() + "/medium").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Media(jsa.getJSONObject(i)));
        }
        return ret;
    }

    public ArrayList<Media> getAllMediaFromIdCorpora(String idCorpora) throws Exception{
        ArrayList<Media> ret = new ArrayList<>();
        JSONArray jsa = new Get("/media/" + idCorpora + "/medium").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Media(jsa.getJSONObject(i)));
        }
        return ret;
    }

    public Media getMedia(String idMedia)throws Exception {
        return new Media(new Get("/medium/" + idMedia).execute());
    }

    public Media updateMedia(Media media)throws Exception {
        return new Media(new Put("/medium/" + media.getId(), media.toArgs()).execute());
    }

    /////////////////////////////////
    //            Layers
    /////////////////////////////////
    public Layer createLayer(Layer layer)throws Exception {
        return new Layer(new Post("/corpus/" + layer.getIdCorpus() + "/layer", layer.toArgs()).execute());
    }

    public Layer createLayer(Layer layer, String idCorpus) throws Exception{
        return new Layer(new Post("/corpus/" + idCorpus + "/layer", layer.toArgs()).execute());
    }

    public Layer createLayer(Layer layer, Corpora corpora) throws Exception{
        return new Layer(new Post("/corpus/" + corpora.getId() + "/layer", layer.toArgs()).execute());
    }

    public boolean deleteLayer(String idLayer) throws Exception{
        return new Delete("/layer", idLayer).execute().isNull("success");
    }

    public ArrayList<Layer> getAllLayer() throws Exception{
        ArrayList<Layer> ret = new ArrayList<>();
        JSONArray jsa = new Get("/layer").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Layer(jsa.getJSONObject(i)));
        }
        return ret;
    }

    public ArrayList<Layer> getAllLayerFromCorpus(Corpora corpora) throws Exception{
        ArrayList<Layer> ret = new ArrayList<>();
        JSONArray jsa = new Get("/layer/" + corpora.getId() + "/layer").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Layer(jsa.getJSONObject(i)));
        }
        return ret;
    }

    public ArrayList<Layer> getAllLayerFromIdCorpus(String idCorpus) throws Exception{
        ArrayList<Layer> ret = new ArrayList<>();
        JSONArray jsa = new Get("/layer/" + idCorpus + "/layer").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Layer(jsa.getJSONObject(i)));
        }
        return ret;
    }

    public Layer updateLayer(Layer layer) throws Exception{
        return new Layer(new Post("/layer/"+layer.getId(), layer.toArgs()).execute());
    }

    /////////////////////////////////
    //            Annotation
    /////////////////////////////////
    public Annotation createAnnotation(Annotation annotation)throws Exception {
        return new Annotation(new Post("Layer/" + annotation.getIdLayer() + "/annotation", annotation.toArgs()).execute());
    }

    public boolean deleteAnnotation(String idAnnotation) throws Exception{
        return new Delete("/annotation", idAnnotation).execute().isNull("success");
    }

    public ArrayList<Annotation> getAllAnnotation() throws Exception{
        ArrayList<Annotation> ret = new ArrayList<>();
        JSONArray jsa = new Get("/annotation").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Annotation(jsa.getJSONObject(i)));
        }
        return ret;
    }

    public ArrayList<Annotation> getAllAnnotationFromLayer(Layer layer) throws Exception{
        ArrayList<Annotation> ret = new ArrayList<>();
        JSONArray jsa = new Get("/annotation/" + layer.getId() + "/annotation").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Annotation(jsa.getJSONObject(i)));
        }
        return ret;
    }

    public ArrayList<Annotation> getAllAnnotationFromidLayer(String idLayer)throws Exception {
        ArrayList<Annotation> ret = new ArrayList<>();
        JSONArray jsa = new Get("/annotation/" + idLayer + "/annotation").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Annotation(jsa.getJSONObject(i)));
        }
        return ret;
    }

    public Annotation getAnnotation(String idAnnotation)throws Exception {
        return new Annotation(new Get("/annotation/" + idAnnotation).execute());
    }

    public Annotation updateAnnotation(Annotation annotation) throws Exception{
        return new Annotation(new Put("/annotation/" + annotation.getId(), annotation.toArgs()).execute());
    }

}
