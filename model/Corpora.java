/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.json.JSONObject;

/**
 *
 * @author mathias
 */
public class Corpora {
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
