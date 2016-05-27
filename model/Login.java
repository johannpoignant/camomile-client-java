/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.json.JSONObject;

/**
 * Class Login is use for the CamomileClientJava's method login it contain :
 * <ul>
 * <li> An username </li>
 * <li> A password </li>
 * </ul>
 *
 * @author mathias
 */
public class Login {

    private String username;
    private String password;

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String toArgs() {
        JSONObject jso = new JSONObject(this);
        return jso.toString();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
