

import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

class ServerSide
{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private sharingPanel guiObj;
    // private DataInputStream din;
    // private DataOutputStream dout;

    public ServerSide(sharingPanel g)
    {
        this.guiObj = g;
    }


    public void create()
    {     
        new Thread(new Runnable(){
            public void run()
            {        
                try
                {
                    System.out.println("Server Application is running...");
                    serverSocket = new ServerSocket(6969);
                    System.out.println("Server is running at port number : 6969");
                    clientSocket = serverSocket.accept();
                    System.out.println("Server and client connection is succesful");

                    DataInputStream din = new DataInputStream(clientSocket.getInputStream());
                    DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());

                    int msg;
                    if(clientSocket != null)
                    {
                    while(true)
                    {
                        msg = din.readInt();
                        
                        if(msg == 0)
                        {
                            //notify gui that other device has stopped sharing
                            JOptionPane.showMessageDialog(guiObj.sharingpanel,"Other device has stopped sharing,Thankyou!","Alert",JOptionPane.INFORMATION_MESSAGE);
                            din.close();
                            dout.close();
                            break;
                        }
                        else
                        {


                            byte[] fileNameBytes = new byte[msg];
                            din.readFully(fileNameBytes,0,fileNameBytes.length);
                            String fileName = new String(fileNameBytes);
                            int fileContentLen = din.readInt();

                            //notify on recieve panel showconfirmdialog
                            int result = JOptionPane.showConfirmDialog(guiObj.imageLabel,"Do you want to download : "+fileName+" ?","Download file",JOptionPane.YES_NO_OPTION);

                            if(result == 0)
                            {
                                recieveFile(msg,fileName,fileContentLen);
                            }
                        }
                    }
                    }
                }
                catch(IOException io)
                {
                    System.out.println(io);

                }     
            }
        }).start();
    }

    public void stopSharing()
    {
        
        try
        {
            DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
            dout.writeInt(0);
            // din.close();
            // dout.close();
        }
        catch(IOException io)
        {
            System.out.println(io);
        }
    }


    public void recieveFile(int len,String fname,int flen) 
    {
        Thread t = new Thread(new Runnable(){
            public void run()
            {

                try
                {
                    DataInputStream din = new DataInputStream(clientSocket.getInputStream());
                    DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());

                    int fileNameLen = len;
                    int fileContentLen = flen;
                    String fileName = fname;

                    if(fileContentLen > 0)
                    {
                        byte[] fileContentBytes = new byte[fileContentLen];

                        din.readFully(fileContentBytes,0,fileContentLen);

                        
                        FileOutputStream fileout = new FileOutputStream(fileName);

                        fileout.write(fileContentBytes);

                        fileout.close();
                        
                    }   
                    System.out.println("File is Received");
                    

                }
                catch(IOException error)
                {
                    System.out.println(error);
                }
                
            }
        });

        try
        {
            t.start();
            t.join();
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }
    }

    public void sendFile(File file)
    {
        Thread t = new Thread(new Runnable(){
            public void run()
            {
                try
                {
                    DataInputStream din = new DataInputStream(clientSocket.getInputStream());
                    DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());

                    System.out.println("Sending file to server");

                    String fileName = file.getName();
                    byte[] fileNameBytes = fileName.getBytes();

                    byte[] fileContentBytes = new byte[(int)file.length()];
                    FileInputStream filein = new FileInputStream(file);


                    dout.writeInt(fileNameBytes.length);
                    dout.write(fileNameBytes);

                    dout.writeInt(fileContentBytes.length);
                    dout.write(fileContentBytes);

                
                    System.out.println("File sent");

                }
                catch(Exception error)
                {
                    System.out.println(error);
                }

            }
        });

        try
        {
            t.start();
            t.join();
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }

    }
}