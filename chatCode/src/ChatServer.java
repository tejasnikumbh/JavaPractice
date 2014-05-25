import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.net.*;

public class ChatServer{

    ArrayList clientOutputStreams;
    public static void main(String[] args){
        ChatServer server = new ChatServer();
        server.fire();
    }
    
    private void fire(){
        clientOutputStreams = new ArrayList();
        try{
            ServerSocket socket = new ServerSocket(5000);
            while(true){
                Socket clientSocket = socket.accept();
                
                PrintWriter writer =  new PrintWriter(
                                          clientSocket.getOutputStream()
                                          );
                // Adding the current connection print writer to the
                // clientOutputStreams arraylist. This is done so that we can
                // broadcast to a range of clients that have a connectin.
                clientOutputStreams.add(writer);
                // New Thread that handles the Job of listening to the client
                Thread clientListener = new Thread(
                                            new ClientHandler(clientSocket)
                                            );
                clientListener.start();
                // Print the status                                       
                System.out.println("Got a connection");
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private class ClientHandler implements Runnable{
        private BufferedReader reader;
        private Socket sock;
        
        public ClientHandler(Socket clientSocket){
            try{
                sock = clientSocket;
                InputStreamReader iReader = new InputStreamReader(
                                                sock.getInputStream()
                                                );                                              
                reader = new BufferedReader(iReader);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        
        public void run(){
            String message;
            try{
                while((message = reader.readLine()) != null){
                    System.out.println("Server read  : " + message);
                    tellEveryone(message);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    private void tellEveryone(String message){
        Iterator it = clientOutputStreams.iterator();
        while(it.hasNext()){
            try{
                PrintWriter writer = (PrintWriter) (it.next());
                writer.println(message);
                writer.flush();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
    
    
}
