import java.net.*;
import java.io.*;

class ClientSide
{
    public DataInputStream din;
    public DataOutputStream dout;

    public String executeCommand(String cmd[])
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

    public String getIp()
    {
        String[] cmd = {"ip", "route", "show", "default"};

        String output = executeCommand(cmd);

        String[] splitted = output.split(" ");

        return splitted[2];
    }


    public void sendFile(String path)
    {
        int bytes = 0;
        try
        {
            File file = new File(path);
            FileInputStream filein = new FileInputStream(file);

            dout.writeLong(file.length());

            byte buffer[] = new byte[4*1024];

            while((bytes = filein.read(buffer)) != -1)
            {
                dout.write(buffer,0,bytes);
                dout.flush();
            }

            filein.close();

        }
        catch(Exception e)
        {

        }
    }

    public void createClient()
    {
        try
        {
        System.out.println("Client application is running...");

        System.out.println("Client is waiting for connection ");
        String ip = getIp();
        System.out.println("IP : "+ip);

        Socket s = new Socket(ip,2100);

        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());

        System.out.println("Sending file to server");
        sendFile("/file.jpg");

        din.close();
        dout.close();
        
        System.out.println("File sent");
        }
        catch(Exception E)
        {
            System.out.println("File not sent");
            System.out.println(E);
        }
    }
}
