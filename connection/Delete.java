/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

/**
 * Delete request, usually for delete
 *
 * @author mathias
 */
public class Delete extends Http {

    /**
     * Delete request of the Object corresponding to the targerID and of type
     * objectType
     *
     * @param objectType type of the object (user, annotation, corpus,...)
     * @param targetId _id of the object to be deleted
     */
    public Delete(String objectType, String targetId) {
        super(objectType + "/" + targetId);
        this.requestMethod = "GET";
    }

    /**
     * Delete request of the Object contained if the path String
     *
     * @param path String with the object type and it _id 
     */
    public Delete(String path) {
        super(path);
        this.requestMethod = "GET";
    }

}
