/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camomile.client.java;

import connection.Delete;
import connection.Get;
import connection.Http;
import connection.Post;
import connection.Put;
import java.net.HttpCookie;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import model.Annotation;
import model.Corpus;
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
    
    private static final String SET_COOKIE = "set-cookie";
    
    private static final String ROOT_USERNAME = "root";
    private static final String ROOT_PASSWORD = "admin";

    /**
     * Constructor for the CamomileClient
     *
     * @param camomileAdrress The address of the Camomile server
     *
     */
    public CamomileClientJava(String camomileAdrress) {
        Http.setSERVER_ADDRESS(camomileAdrress);
    }

    /////////////////////////////////
    //          Authentication
    /////////////////////////////////
    /**
     *
     * Method for connect to the camomile server, for this create a login object
     * and pass it to the method. This method will return true if the
     * authentication succeeded
     *
     * @param login a login object
     * @return boolean; true : authentication succeeded
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public boolean login(Login login) throws Exception {
        Post post = new Post("/login", login.toArgs());
        JSONObject jso = post.execute();
        HttpCookie cookie;
        
        Map<String, List<String>> headerFields = post.getConnection().getHeaderFields();
        if (headerFields.containsKey(SET_COOKIE)) {
            List<String> cookieValues = headerFields.get(SET_COOKIE);
            
            for (String cookieH : cookieValues) {
                cookie = HttpCookie.parse(cookieH).get(0);
                post.setCookie(cookie);
            }
        }
        
        return jso.isNull("error");
    }

    /**
     * Method to logout from the server, return true if succeeded
     *
     * @return boolean; true : logout succeeded
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public boolean logout() throws Exception {
        return new Post("/logout").execute().isNull("success");
    }

    /**
     * Method for get The user currently logged
     *
     * @return The user currently logged
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public User getMe() throws Exception {
        return new User(new Get("/me").execute());
    }

    /////////////////////////////////
    //            Users
    /////////////////////////////////
    /**
     * Method to create an User on the server by it Java object
     *
     * @param user an User object that we want created on the server
     * @return The User created from the server with it _id attribute filled
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public User createUser(User user) throws Exception {        
        return new User(new Post("/user", user.toArgs()).execute());
    }

    /**
     * Method to delete an user by it _id
     *
     *
     * @param id _id from the user to delete
     * @return boolean; true : User deleted
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public boolean deleteUser(String id) throws Exception {
        return new Delete("/user", id).execute().isNull("success");
    }

    /**
     * Method to delete an user by it java object
     *
     *
     * @param user User to delete
     * @return boolean; true : User deleted
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public boolean deleteUser(User user) throws Exception {
        return new Delete("/user", user.getId()).execute().isNull("success");
    }

    /**
     *
     * Method to get all the users of the server
     *
     * @return ArrayList of all the users of the server
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public ArrayList<User> getAllUsers() throws Exception {
        ArrayList<User> ret = new ArrayList<>();
        JSONArray jsa = new Get("/user").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new User(jsa.getJSONObject(i)));
        }
        
        return ret;
    }

    /**
     * Method to get an user by it _id
     *
     * @param id _id of the user to get from the server
     * @return The user that correspond to that _id
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public User getUser(String id) throws Exception {
        return new User(new Get("/user/" + id).execute());
    }

    /**
     * Method to update an user of the server by it Java object
     *
     * @param user The User that you want updated
     * @return the updated User
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public User updateUser(User user) throws Exception {
        return new User(new Put("/user", user.toArgs()).execute());
    }

    /////////////////////////////////
    //            Corpus
    /////////////////////////////////
    /**
     *
     * Create a corpus on the server by it Java object
     *
     * @param corpus the corpus that you want created
     * @return The corpus created from the server with it _id attribute filled
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public Corpus createCorpus(Corpus corpus) throws Exception {
        return new Corpus(new Post("/corpus", corpus.toArgs()).execute());
    }

    /**
     * Method to delete a corpus by it _id
     *
     * @param id _id of the Corpus that will be deleted
     * @return boolean; true : User deleted
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public boolean deleteCorpus(String id) throws Exception {
        return new Delete("/corpus", id).execute().isNull("success");
    }

    /**
     * Method to delete a corpus by it Java object
     *
     * @param corpus Corpus that will be deleted
     * @return boolean; true : corpus deleted
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public boolean deleteCorpus(Corpus corpus) throws Exception {
        return new Delete("/corpus", corpus.getId()).execute().isNull("success");
    }

    /**
     * Method to get all the Corpus of the server
     *
     * @return ArrayList of all the Corpus of the server
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public ArrayList<Corpus> getAllCorpus() throws Exception {
        ArrayList<Corpus> ret = new ArrayList<>();
        JSONArray jsa = new Get("/corpus").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Corpus(jsa.getJSONObject(i)));
        }
        return ret;
    }

    /**
     * Method to get a corpus by it _id
     *
     * @param id _id of the corpus that you want
     * @return the corpus corresponding to that _id
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public Corpus getCorpus(String id) throws Exception {
        return new Corpus(new Get("/corpus", id).execute());
    }

    /**
     * Method to update a corpus by it Java object
     *
     * @param corpus that you want updated on the server
     * @return The updated corpus
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public Corpus updateCorpus(Corpus corpus) throws Exception {
        return new Corpus(new Put("/corpus", corpus.toArgs()).execute());
    }

    /////////////////////////////////
    //            Media
    /////////////////////////////////
    /**
     * Create a media on the server by a Java object and it destination corpus
     *
     * @param media the media that you want created
     * @param corpus the corpus where you want the media created
     * @return the media created whit it _id and id_corpus attributes filled
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public Media createMedia(Media media, Corpus corpus) throws Exception {
        return new Media(new Post("/corpus/" + corpus.getId() + "/medium", media.toArgs()).execute());
    }

    /**
     * Create a media on the server by a Java object and the id of the
     * destination corpus
     *
     * @param media the media that you want created
     * @param idcorpus the id of the destination corpus
     * @return the media created whit it _id and id_corpus attributes filled
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public Media createMedia(Media media, String idcorpus) throws Exception {
        return new Media(new Post("/corpus/" + idcorpus + "/medium", media.toArgs()).execute());
    }

    /**
     * Create a media on the server by a Java object if it contain the id_corpus
     * attribute
     *
     * @param media the media that you want created
     * @return the media created whit it _id and id_corpus attributes filled
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server. In this case the exception might come because
     * the media doesn't have an id_corpus attribute set
     */
    public Media createMedia(Media media) throws Exception {
        if (media.getIdCorpus().isEmpty()) {
            throw new CamomileClientException("Ce Media ne dispose pas d'id_corpus");
        }
        return new Media(new Post("/corpus/" + media.getIdCorpus() + "/medium", media.toArgs()).execute());
    }

    /**
     * Delete a media from the server by it _id
     *
     * @param idMedia the _id of the media that will be deleted
     * @return boolean; true : media delete
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public boolean deleteMedia(String idMedia) throws Exception {
        return new Delete("/medium", idMedia).execute().isNull("success");
    }

    /**
     * Delete a media from the server by it Java object
     *
     * @param media the media that will be deleted
     * @return boolean; true : media delete
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public boolean deleteMedia(Media media) throws Exception {
        return new Delete("/medium", media.getId()).execute().isNull("success");
    }

    /**
     * Get all the media from the server
     *
     * @return all the media of the server
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public ArrayList<Media> getAllMedia() throws Exception {
        ArrayList<Media> ret = new ArrayList<>();
        JSONArray jsa = new Get("/media").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Media(jsa.getJSONObject(i)));
        }
        return ret;
    }

    /**
     * Get all the media from a corpus by it Java object
     *
     * @param corpus the corpus that you want the media
     * @return all the media of a corpus
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public ArrayList<Media> getAllMediaFromcorpus(Corpus corpus) throws Exception {
        ArrayList<Media> ret = new ArrayList<>();
        JSONArray jsa = new Get("/media/" + corpus.getId() + "/medium").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Media(jsa.getJSONObject(i)));
        }
        return ret;
    }

    /**
     * Get all the media from a corpus by it id_corpus
     *
     * @param idcorpus the id_corpus of the corpus that you want the media
     * @return all the media of a corpus
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public ArrayList<Media> getAllMediaFromIdcorpus(String idcorpus) throws Exception {
        ArrayList<Media> ret = new ArrayList<>();
        JSONArray jsa = new Get("/media/" + idcorpus + "/medium").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Media(jsa.getJSONObject(i)));
        }
        return ret;
    }

    /**
     * Get a media from it _id
     *
     * @param idMedia the _id of the media that you want
     * @return the Media corresponding to the idMedia
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public Media getMedia(String idMedia) throws Exception {
        return new Media(new Get("/medium/" + idMedia).execute());
    }

    /**
     * Update a media from it Java object
     *
     * @param media The Media to be updated
     * @return the updated media
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public Media updateMedia(Media media) throws Exception {
        return new Media(new Put("/medium/" + media.getId(), media.toArgs()).execute());
    }

    /////////////////////////////////
    //            Layers
    /////////////////////////////////
    /**
     * Create a Layer on the server by a Java object
     *
     * @param layer Layer that will be created
     * @return the Layer with it _id attribute set
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server. Or in that case, if the id_corpus of the Layer
     * is empty
     */
    public Layer createLayer(Layer layer) throws Exception {
        if (layer.getIdCorpus().isEmpty()) {
            throw new CamomileClientException("The Layer doesn't have an id_corpus");
        }
        return new Layer(new Post("/corpus/" + layer.getIdCorpus() + "/layer", layer.toArgs()).execute());
    }

    /**
     * Create a Layer on the server by a Java object and the id of the
     * destination corpus
     *
     * @param layer Layer that will be created
     * @param idCorpus the id_corpus of the destination corpus
     * @return the Layer with it _id and id_corpus attributes filled
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public Layer createLayer(Layer layer, String idCorpus) throws Exception {
        return new Layer(new Post("/corpus/" + idCorpus + "/layer", layer.toArgs()).execute());
    }

    /**
     * Create a Layer on the server by a Java object and the destination corpus
     *
     * @param layer Layer that will be created
     * @param corpus the destination corpus
     * @return the Layer with it _id and id_corpus attributes filled
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public Layer createLayer(Layer layer, Corpus corpus) throws Exception {
        return new Layer(new Post("/corpus/" + corpus.getId() + "/layer", layer.toArgs()).execute());
    }

    /**
     * Delete a Layer the corresponding to the idLayer
     *
     * @param idLayer _id of the Layer that will be deleted
     * @return boolean; true : Layer deleted
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public boolean deleteLayer(String idLayer) throws Exception {
        return new Delete("/layer", idLayer).execute().isNull("success");
    }

    /**
     * Get all the Layer from the server
     *
     * @return ArrayList of all the Layer on the server
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public ArrayList<Layer> getAllLayer() throws Exception {
        ArrayList<Layer> ret = new ArrayList<>();
        JSONArray jsa = new Get("/layer").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Layer(jsa.getJSONObject(i)));
        }
        return ret;
    }

    /**
     * Get all the Layer from a corpus
     *
     * @param corpus The corpus which you want the Layers
     * @return all the Layers of a corpus
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public ArrayList<Layer> getAllLayerFromCorpus(Corpus corpus) throws Exception {
        ArrayList<Layer> ret = new ArrayList<>();
        JSONArray jsa = new Get("/layer/" + corpus.getId() + "/layer").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Layer(jsa.getJSONObject(i)));
        }
        return ret;
    }

    /**
     * Get all the Layer from the corpus corresponding to the idCorpus
     *
     * @param idCorpus The idCorpus of the Corpus which you want the Layers
     * @return all the Layers of a corpus
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public ArrayList<Layer> getAllLayerFromIdCorpus(String idCorpus) throws Exception {
        ArrayList<Layer> ret = new ArrayList<>();
        JSONArray jsa = new Get("/layer/" + idCorpus + "/layer").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Layer(jsa.getJSONObject(i)));
        }
        return ret;
    }

    /**
     * Get a layer from it _id
     *
     * @param idLayer the _id of the layer that you want
     * @return the Layer corresponding to the idLayer
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public Layer getLayer(String idLayer) throws Exception {
        return new Layer(new Get("/layer/" + idLayer).execute());
    }

    /**
     * Update a layer on the server by it Java object
     *
     * @param layer Layer to be updated
     * @return the updated Layer
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public Layer updateLayer(Layer layer) throws Exception {
        return new Layer(new Post("/layer/" + layer.getId(), layer.toArgs()).execute());
    }

    /////////////////////////////////
    //            Annotation
    /////////////////////////////////
    /**
     * Create an Annotation on the serveur by a Java object
     *
     * @param annotation Annotation that will be created
     * @return the Annotation created with it _id attribute filled
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server. Or in that case, if the id_layer attribute of
     * the Layer is empty
     *
     */
    public Annotation createAnnotation(Annotation annotation) throws Exception {
        if (annotation.getIdLayer().isEmpty()) {
            throw new CamomileClientException("The id_layer attribute is empty");
        }
        return new Annotation(new Post("Layer/" + annotation.getIdLayer() + "/annotation", annotation.toArgs()).execute());
    }

    /**
     * Create an Annotation on the serveur by a Java object and the id of the
     * destination layer
     *
     * @param annotation Annotation that will be created
     * @param idLayer the id_layer of the destination layer
     * @return the Annotation created with it _id and id_layer attributes filled
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server.
     *
     */
    public Annotation createAnnotation(Annotation annotation, String idLayer) throws Exception {
        return new Annotation(new Post("Layer/" + idLayer + "/annotation", annotation.toArgs()).execute());
    }

    /**
     * Create an Annotation on the serveur by a Java object the destination
     * layer
     *
     * @param annotation Annotation that will be created
     * @param layer the destination layer
     * @return the Annotation created with it _id and id_layer attributes filled
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server.
     *
     */
    public Annotation createAnnotation(Annotation annotation, Layer layer) throws Exception {
        return new Annotation(new Post("Layer/" + layer.getId() + "/annotation", annotation.toArgs()).execute());
    }

    /**
     * Delete an Annotation by it corresponding _id
     *
     * @param idAnnotation the _id of the Annotation that will be deleted
     * @return boolean; true : Annotation deleted
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server.
     */
    public boolean deleteAnnotation(String idAnnotation) throws Exception {
        return new Delete("/annotation", idAnnotation).execute().isNull("success");
    }

    /**
     * Delete an Annotation by it corresponding Java object
     *
     * @param annotation the Annotation that will be deleted
     * @return boolean; true : Annotation deleted
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server.
     */
    public boolean deleteAnnotation(Annotation annotation) throws Exception {
        return new Delete("/annotation", annotation.getId()).execute().isNull("success");
    }

    /**
     * Get all the Annotations from the server
     *
     * @return ArrayList of all the Annotations of the server
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server.
     */
    public ArrayList<Annotation> getAllAnnotation() throws Exception {
        ArrayList<Annotation> ret = new ArrayList<>();
        JSONArray jsa = new Get("/annotation").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Annotation(jsa.getJSONObject(i)));
        }
        return ret;
    }

    /**
     * Get all Annotations from the server of a layer
     *
     * @param layer The Layer which you want the Annotations
     * @return ArrayList of all the Annotations of a Layer
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server.
     */
    public ArrayList<Annotation> getAllAnnotationFromLayer(Layer layer) throws Exception {
        ArrayList<Annotation> ret = new ArrayList<>();
        JSONArray jsa = new Get("/annotation/" + layer.getId() + "/annotation").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Annotation(jsa.getJSONObject(i)));
        }
        return ret;
    }

    /**
     * Get all Annotations from the server of the layer corresponding to the
     * idLayer
     *
     * @param idLayer the _id of the Layer that you want the Annotations
     * @return ArrayList of all the Annotations of a Layer
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server.
     */
    public ArrayList<Annotation> getAllAnnotationFromidLayer(String idLayer) throws Exception {
        ArrayList<Annotation> ret = new ArrayList<>();
        JSONArray jsa = new Get("/annotation/" + idLayer + "/annotation").execute().getJSONArray("array").getJSONArray(0); //PAS compris le .getJSONArray(0) à la fin
        for (int i = 0; i < jsa.length(); i++) {
            ret.add(new Annotation(jsa.getJSONObject(i)));
        }
        return ret;
    }

    /**
     * Get a Annotation from it _id
     *
     * @param idAnnotation the _id of the Annotation that you want
     * @return the Annotation corresponding to the idAnnotation
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public Annotation getAnnotation(String idAnnotation) throws Exception {
        return new Annotation(new Get("/Annotation/" + idAnnotation).execute());
    }

    /**
     * Update an Annotation by it Java object
     *
     * @param annotation Annotation that will be updated
     * @return the updated Annotation
     * @throws Exception If failed, an exception is throwed with the error
     * message from the server
     */
    public Annotation updateAnnotation(Annotation annotation) throws Exception {
        return new Annotation(new Put("/annotation/" + annotation.getId(), annotation.toArgs()).execute());
    }
    
}
