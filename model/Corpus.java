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
