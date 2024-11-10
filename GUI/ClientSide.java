import java.net.*;
import java.io.*;

class ClientSide
{
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

    public void createClient()
    {
        try
        {
        System.out.println("Client application is running...");

        System.out.println("Client is waiting for connection ");
        String ip = getIp();
        System.out.println("IP : "+ip);

        Socket s = new Socket(ip,2100);

        PrintStream ps = new PrintStream(s.getOutputStream());

        BufferedReader br1 = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));

        String str1,str2;
        while(!(str1 = br2.readLine()).equals("end"))
        {
            ps.println(str1);
            System.out.println("Enter message for server : ");
            str2 = br1.readLine();
            System.out.println("Server says : "+str2);
        }
        System.out.println("Thank you for using chat messanger...");
        }
        catch(Exception E)
        {
            System.out.println(E);
        }
    }
}
