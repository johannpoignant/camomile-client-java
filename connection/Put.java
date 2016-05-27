/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

/**
 * PUT request, usually for update
 *
 * @author mathias
 */
public class Put extends Args {

    /**
     * Put requets of the args to the path
     *
     * @param path
     * @param args
     */
    public Put(String path, String args) {
        super(path, args);
        this.requestMethod = "PUT";
    }

}
