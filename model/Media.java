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
 * Class Media is a Java representation of the corresponding Camomile's object.
 * it contains :
 * <ul>
 * <li> An id corresponding to the Camomile's Annotation attribute _id </li>
 * <li> An idCorpus corresponding to the Camomile's Annotation attribute
 * id_corpus
 * </li>
 * <li> A name corresponding to the Camomile's Annotation attribute name </li>
 * <li> An url corresponding to the Camomile's Annotation attribute url
 * </li>
 * <li> A description corresponding to the Camomile's Annotation attribute
 * description </li>
 * </ul>
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

    /**
     * Constructor for User, usually used with a JSON's response of the server
     *
     * @param jso
     */
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
