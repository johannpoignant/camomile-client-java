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
