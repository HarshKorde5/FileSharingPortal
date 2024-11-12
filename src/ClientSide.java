
import java.net.*;
import java.util.*;
import java.io.*;

class ClientSide
{
    
    private Socket clientSocket;
    
    public ClientSide()
    {
        try
        {
            System.out.println("Client application is running...");
            System.out.println("Client is waiting for connection ");

            String ip = getIp();
            System.out.println("IP : "+ip);

            clientSocket = new Socket(ip,2100);

            System.out.println("Connection successful!You can now start sharing");
        }
        catch(UnknownHostException ue)
        {
            ue.getMessage();
        }
        catch(IOException io)
        {
            io.getMessage();

        }


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
}
