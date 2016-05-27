/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ProtocolException;
import org.json.JSONObject;

/**
 * Abstract class for the request that write args in the connection :
 * - PUT
 * - POST
 * 
 * @author mathias
 */
public abstract class Args extends Http {

    public Args(String action) {
        super(action);
    }

    public Args(String action, String args) {
        super(action, args);
    }

    @Override
    public JSONObject execute() throws ProtocolException, IOException, Exception {
        //Méthode pour créer la connection
        requestServer();

        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(args);
        wr.flush();
        //wr.close();


        //Méthode pour récupérer la réponse du serveur
        return responseCodeHandler();
    }

}
