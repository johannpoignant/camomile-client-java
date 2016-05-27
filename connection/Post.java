/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

/**
 * POST request, usually for create
 *
 * @author mathias
 */
public class Post extends Args {

    /**
     * Post request to the path path with the arguments args
     *
     * @param path path to the target insert
     * @param args args of the insert
     */
    public Post(String path, String args) {
        super(path, args);
        this.requestMethod = "POST";
    }

    /**
     * Post request to the path path
     *
     * @param path path of the command to execute
     */
    public Post(String path) {
        super(path);
        this.requestMethod = "POST";
    }

}
