/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.json.JSONObject;

/**
 * The abstract class for all the Camomile's objects :
 * <ul>
 * <li>Annotation</li>
 * <li>Layer</li>
 * <li>Media</li>
 * <li>Corpus</li>
 * <li>User</li>
 * </ul>
 *
 * it contains the method toArgs that transform the Java object to arguments for
 * the http request
 *
 * @author mathias
 */
public abstract class Resource {

    /**
     * transform the Java object to arguments for the http request
     *
     * @return String of the JSONObject corresponding to a Java object
     */
    public String toArgs() {
        return new JSONObject(this).toString();
    }
}
