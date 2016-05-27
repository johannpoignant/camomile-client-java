/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class Annotation is a Java representation of the corresponding Camomile's
 * object. it contains :
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
public class Annotation extends Resource {

    private String id;
    private String idLayer;
    private String idMedium;
    private JSONObject fragment;
    private JSONObject data;

    public Annotation(String idLayer, String idMedium, JSONObject fragment, JSONObject data) {
        this.idLayer = idLayer;
        this.idMedium = idMedium;
        this.fragment = fragment;
        this.data = data;
    }

    public Annotation(String idMedium, JSONObject fragment, JSONObject data) {
        this.idMedium = idMedium;
        this.fragment = fragment;
        this.data = data;
    }

    /**
     * Constructor for User, usually used with a JSON's response of the server
     *
     * @param jso
     */
    public Annotation(JSONObject jso) {
        this.id = jso.getString("_id");
        this.idLayer = jso.getString("idLayer");
        this.idMedium = jso.getString("idMedium");
        this.fragment = jso.getJSONObject("fragment");
        this.data = jso.getJSONObject("data");
    }

    public String getId() {
        return id;
    }

    public String getIdLayer() {
        return idLayer;
    }

    public String getIdMedium() {
        return idMedium;
    }

    public JSONObject getFragment() {
        return fragment;
    }

    public JSONObject getData() {
        return data;
    }

    public void setFragment(JSONObject fragment) {
        this.fragment = fragment;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

}
