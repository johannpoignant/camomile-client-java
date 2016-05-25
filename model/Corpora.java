/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author mathias
 */
public class Corpora extends Resource{
    private String id;
    private String name;
    private JSONObject description;

    public Corpora(String name, JSONObject description) {
        this.name = name;
        this.description = description;
    }

    public Corpora(String name) {
        this.name = name;
    }
    
    public Corpora(JSONObject jso) {
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
