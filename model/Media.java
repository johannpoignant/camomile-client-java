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
public class Media extends Resource {

    private String id;
    private String idCorpus;
    private String name;
    private String url;
    private JSONObject description;

    public Media(String name) {
        this.name = name;
    }

    public Media(String name, String idCorpus, String url) {
        this.name = name;
        this.idCorpus = idCorpus;
        this.url = url;
    }

    public Media(String name, String idCorpus, String url, JSONObject description) {
        this.name = name;
        this.idCorpus = idCorpus;
        this.url = url;
        this.description = description;
    }

    public Media(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Media(String name, JSONObject description) {
        this.name = name;
        this.description = description;
    }

    public Media(String name, String url, JSONObject description) {
        this.name = name;
        this.url = url;
        this.description = description;
    }

    public Media(JSONObject jso) {
        this.id = jso.getString("_id");
        this.name = jso.getString("name");
        this.url = jso.getString("url");
        this.idCorpus = jso.getString("id_corpus");
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

    public String getUrl() {
        return url;
    }

    public String getIdCorpus() {
        return idCorpus;
    }

    public JSONObject getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(JSONObject description) {
        this.description = description;
    }

}
