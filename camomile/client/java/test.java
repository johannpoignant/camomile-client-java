/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package camomile.client.java;

import model.User;
import org.json.JSONObject;

/**
 *
 * @author mathias
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        tt t = new tt(10);
        System.out.println("t = " + t.getA());
    }

    public static tt test(tt a) {
        return a = new tt(80);
    }

    private static class tt {

        private int a;

        public tt(int a) {
            this.a = a;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

    }

}
