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
