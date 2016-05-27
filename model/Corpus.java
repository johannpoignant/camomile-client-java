/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class Corpus is a Java representation of the corresponding Camomile's object.
 * it contains :
 * <ul>
 * <li> An id corresponding to the Camomile's Annotation attribute _id </li>
 * <li> A name corresponding to the Camomile's Annotation attribute name
 * </li>
 * <li> A description corresponding to the Camomile's Annotation attribute
 * description </li>
 * </ul>
 *
 * @author mathias
 */
public class Corpus extends Resource {

    private String id;
    private String name;
    private JSONObject description;

    public Corpus(String name, JSONObject description) {
        this.name = name;
        this.description = description;
    }

    public Corpus(String name) {
        this.name = name;
    }

    /**
     * Constructor for User, usually used with a JSON's response of the server
     *
     * @param jso
     */
    public Corpus(JSONObject jso) {
        this.id = jso.getString("_id");
        this.name = jso.getString("name");
        try {
            this.description = jso.getJSONObject("description");
        } catch (JSONException e) {
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public JSONObject getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(JSONObject description) {
        this.description = description;
    }

}
