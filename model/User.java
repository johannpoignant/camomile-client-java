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
public class User extends Resource {

    private String id;
    private String username;
    private String password;
    private String role;
    private JSONObject description;

    public User(String name, String password, String role, JSONObject description) {
        this.username = name;
        this.password = password;
        this.role = role;
        this.description = description;
    }

    public User(String name, String password, String role) {
        this.username = name;
        this.password = password;
        this.role = role;
    }

    public User(JSONObject jso) {
        this.id = jso.getString("_id");
        this.username = jso.getString("username");
        this.role = jso.getString("role");
        try {
            this.description = jso.getJSONObject("description");
        } catch (JSONException e) {
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public JSONObject getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setDescription(JSONObject description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

}
