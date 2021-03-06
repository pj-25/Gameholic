package gameholic.server;

import gameholic.server.services.PlayerConnection;
import jsc.jServer.JServer;

import java.io.IOException;

public class GameholicServer {
    public static void main(String []s){
        if(s.length > 1){
            System.err.println("Invalid number of arguments!");
            return;
        }
        try{

            JServer serverApp;
            if(s.length == 1){
                serverApp = new JServer(Integer.parseInt(s[0]), PlayerConnection.class);
            }else {
                serverApp = new JServer(PlayerConnection.class);
            }
            serverApp.start();

        }catch (IOException e){
            System.err.println("Unable to start Server :(");
        }catch (NumberFormatException nfe){
            System.err.println("Invalid server port number!");
        }
    }
}
