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
package camomile.client.java;

import model.Annotation;
import model.Corpus;
import model.Layer;
import model.Login;
import model.Media;
import org.json.JSONObject;

/**
 *
 * @author mathias
 */
public class testAnnotation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            CamomileClientJava camomile = new CamomileClientJava("http://localhost:3000");
            camomile.login(new Login("root", "admin"));

            Corpus corpus = new Corpus("corpus44");
            Layer layer = new Layer("Layer22",
                    new JSONObject("{\"Test\":\"Annotation\"}"),
                    new JSONObject("{\"Create\":\"layer\"}"));
            Media media = new Media("media20");
            corpus = camomile.createCorpus(corpus);
            media = camomile.createMedia(media, corpus);
            Annotation annotation = new Annotation(media.getId(),
                    new JSONObject("{\"mediaFragment\":\"mediaFragment\"}"),
                    new JSONObject("{\"mediaData\":\"mediaData\"}"));

            layer = camomile.createLayer(layer, corpus);
            annotation = camomile.createAnnotation(annotation, layer);

            annotation.setData(new JSONObject("{\"mediaDataUpdate\":\"mediaDataUpdate\"}"));
            camomile.updateAnnotation(annotation);

            camomile.getAllAnnotation();

            camomile.getAllAnnotationFromLayer(layer);

            camomile.deleteCorpus(corpus);

            camomile.logout();
        } catch (Exception ex) {
            System.err.println("\t" + ex.getMessage());
            ex.printStackTrace();

        }
    }
    
}
