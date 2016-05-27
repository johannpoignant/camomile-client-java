/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import org.json.JSONObject;

/**
 * Class Layer is a Java representation of the corresponding Camomile's object.
 * it contains :
 * <ul>
 * <li> An id corresponding to the Camomile's Annotation attribute _id </li>
 * <li> An idLayer corresponding to the Camomile's Annotation attribute id_Layer
 * </li>
 * <li> An idMedium corresponding to the Camomile's Annotation attribute
 * id_medium </li>
 * <li> A fragment corresponding to the Camomile's Annotation attribute fragment
 * </li>
 * <li> A data corresponding to the Camomile's Annotation attribute data </li>
 * </ul>
 *
 * @author mathias
 */
public class Layer extends Resource {

    private String id;
    private String idCorpus;
    private String name;
    private JSONObject description;
    private JSONObject fragment_type;
    private JSONObject data_type;
    // List d'annotation non implémenté pour le moment

    public Layer(String name) {
        this.name = name;
    }

    public Layer(String idCorpus, String name) {
        this.idCorpus = idCorpus;
        this.name = name;
    }

    public Layer(String idCorpus, String name, JSONObject description, JSONObject fragment_type, JSONObject data_type) {
        this.idCorpus = idCorpus;
        this.name = name;
        this.description = description;
        this.fragment_type = fragment_type;
        this.data_type = data_type;
    }

    public Layer(String idCorpus, String name, JSONObject description) {
        this.idCorpus = idCorpus;
        this.name = name;
        this.description = description;
    }

    public Layer(String idCorpus, String name, JSONObject fragment_type, JSONObject data_type) {
        this.idCorpus = idCorpus;
        this.name = name;
        this.fragment_type = fragment_type;
        this.data_type = data_type;
    }

    public Layer(String name, JSONObject description) {
        this.name = name;
        this.description = description;
    }

    public Layer(String name, JSONObject description, JSONObject fragment_type, JSONObject data_type) {
        this.name = name;
        this.description = description;
        this.fragment_type = fragment_type;
        this.data_type = data_type;
    }

    public Layer(String name, JSONObject fragment_type, JSONObject data_type) {
        this.name = name;
        this.fragment_type = fragment_type;
        this.data_type = data_type;
    }

    /**
     * Constructor for User, usually used with a JSON's response of the server
     *
     * @param jso
     */
    public Layer(JSONObject jso) {
        this.id = jso.getString("_id");
        this.idCorpus = jso.getString("id_corpus");
        this.name = jso.getString("name");
        this.fragment_type = jso.getJSONObject("fragment_type");
        this.data_type = jso.getJSONObject("deta_type");
    }

    public String getId() {
        return id;
    }

    public String getIdCorpus() {
        return idCorpus;
    }

    public String getName() {
        return name;
    }

    public JSONObject getDescription() {
        return description;
    }

    public JSONObject getFragment_type() {
        return fragment_type;
    }

    public JSONObject getData_type() {
        return data_type;
    }

}
