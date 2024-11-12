

import java.net.*;
import java.io.*;
import java.util.*;

class ServerSide
{
    private ServerSocket serverSocket;
    private Socket clientSocket;


    public ServerSide()
    {
        try
        {
            System.out.println("Server Application is running...");
            serverSocket = new ServerSocket(2100);
            System.out.println("Server is running at port number : 2100");
            clientSocket = serverSocket.accept();
            System.out.println("Server and client connection is succesful");
        }
        catch(IOException io)
        {
            io.getMessage();

        }
    }


    public void recieveFile() 
    {
        try
        {
           DataInputStream din = new DataInputStream(clientSocket.getInputStream());

            int fileNameLen = din.readInt();

            if(fileNameLen > 0)
            {
                byte[] fileNameBytes = new byte[fileNameLen];

                din.readFully(fileNameBytes,0,fileNameBytes.length);
            
                String fileName = new String(fileNameBytes);

                int fileContentLen = din.readInt();

                if(fileContentLen > 0)
                {
                    byte[] fileContentBytes = new byte[fileContentLen];

                    din.readFully(fileContentBytes,0,fileContentLen);


                    System.out.println("Do you want to download the file ? (y/n)");
                    Scanner sc = new Scanner(System.in);

                    String choice = sc.next();

                    if(choice.equalsIgnoreCase("y"))
                    {
                        try
                        {
                    
                            FileOutputStream fileout = new FileOutputStream(fileName);

                            fileout.write(fileContentBytes);

                            fileout.close();
                        }
                        catch(IOException d)
                        {
                            d.printStackTrace();
                        }
                    }
                    
                }   
                System.out.println("File is Received");
            }
            din.close();


        }
        catch(IOException error)
        {
            error.printStackTrace();
        }
    }

    public void sendFile(File file)
    {
        try
        {
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

            dout.close();
        
            System.out.println("File sent");

        }
        catch(Exception error)
        {
            error.printStackTrace();
        }


    }
}