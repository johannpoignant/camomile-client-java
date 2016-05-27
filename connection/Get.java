/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

/**
 * GET request, usually for getter
 * @author mathias
 */
public class Get extends Http {

    /**
     * Get the object(s) corresponding to the path
     * @param path String containing the object type and it _id 
     */
    public Get(String path) {
        super(path);
        this.requestMethod = "GET";
    }

    /**
     * Get the object(s) corresponding to the path + args
     * @param path
     * @param args 
     */
    public Get(String path, String args) {
        super(path + "?" + args);
        this.requestMethod = "GET";
    } 
    

}
