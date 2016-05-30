/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camomile.client.java;

import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import model.Annotation;
import model.Corpus;
import model.Layer;
import model.Login;
import model.Media;
import model.User;
import org.json.JSONObject;

/**
 *
 * @author mathias
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            CamomileClientJava camomile = new CamomileClientJava("http://localhost:3000");
            camomile.login(new Login("root", "admin"));

            Corpus corpus = new Corpus("corpus42");
            Layer layer = new Layer("Layer20",
                    new JSONObject("{\"Test\":\"Annotation\"}"),
                    new JSONObject("{\"Create\":\"layer\"}"));
            Media media = new Media("media18");
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
