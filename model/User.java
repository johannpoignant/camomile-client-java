/*
The MIT License (MIT)

Copyright (c) 2016 CAMOMILE project

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class User is a Java representation of the corresponding Camomile's object.
 * it contains :
 * <ul>
 * <li> An id corresponding to the Camomile's Annotation attribute _id </li>
 * <li> An username corresponding to the Camomile's Annotation attribute
 * username
 * </li>
 * <li> A password corresponding to the User's password </li>
 * <li> An role corresponding to the Camomile's Annotation attribute role
 * </li>
 * <li> A description corresponding to the Camomile's Annotation attribute
 * description </li>
 * </ul>
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

    /**
     * Constructor for User, usually used with a JSON's response of the server
     * @param jso 
     */
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
