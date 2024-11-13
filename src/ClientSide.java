
import java.net.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

class ClientSide
{
    
    private Socket clientSocket;
    private sharingPanel guiObj;
    // private DataInputStream din;
    // private DataOutputStream dout;
    
    public ClientSide(sharingPanel g)
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
                    System.out.println("Client application is running...");
                    System.out.println("Client is waiting for connection ");

                    // String ip = getIp();
                    // System.out.println("IP : "+ip);

                    // clientSocket = new Socket(ip,2100);
                    clientSocket = new Socket("localhost",6969);

                    System.out.println("Connection successful!You can now start sharing");


                    DataInputStream din = new DataInputStream(clientSocket.getInputStream());
                    DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());

                    if(clientSocket != null)
                    {
                    int msg;
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

    public static String executeCommand(String cmd[])
    {
        StringBuilder output = new StringBuilder();

        try
        {
            ProcessBuilder pb = new ProcessBuilder(cmd);
            Process process = pb.start();

            //Reading and displaying the output after command execution
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;

            System.out.println("This is the output after connection start");
            while((line = reader.readLine())!= null)
            {
                output.append(line);
                System.out.println(line);
            }

            //wait for completion of the executing process i.e turning on hotspot
            process.waitFor();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
            
        return output.toString();

    }

    public static String getIp()
    {
        String[] cmd = {"ip", "route", "show", "default"};

        String output = executeCommand(cmd);

        String[] splitted = output.split(" ");

        return splitted[2];
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
