import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.net.*;

public class ChatClient{

    private JTextArea incoming;
    private JTextField outgoing;
    private BufferedReader reader;
    private PrintWriter writer;
    private Socket sock;
    
    public static void main(String[] args){
        ChatClient applet = new ChatClient();
        applet.launch();
    }
    
    private void launch(){
        JFrame frame = new JFrame("Chat Client");
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(15,20);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane iScroller = new JScrollPane(incoming);
        iScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        iScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(iScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);
        setUpNetworking();
        
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
        
        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setVisible(true);        
    }
    
    private void setUpNetworking(){
        try{
            sock = new Socket("127.0.0.1",5000);
            InputStreamReader iReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(iReader);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("Networking Established!");
        }catch(IOException ex){
            ex.printStackTrace();
        }   
    }
    
    private class SendButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            try{
                writer.println(outgoing.getText());
                writer.flush();
            }catch(Exception ex){
                ex.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }
    
    private class IncomingReader implements Runnable{
        public void run(){
            String message;
            try{
                while((message = reader.readLine())!=null){
                    System.out.println("Message : " + message);
                    incoming.append(message + "\n");
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
