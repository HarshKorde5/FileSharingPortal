import java.io.*;
import java.util.*;

class startHotspot
{
    public String userName;
    public String UUID;
    Scanner sc;
    public startHotspot(String name)
    {
        sc = new Scanner(System.in);
        UUID = "";
        this.userName = name+"room";
        System.out.println(userName);
    }

    public void stopHotspot()
    {
        String[] turnOf = {"nmcli","connection","down",this.UUID};
        
        
            String output = executeCommand(turnOf);
        
    }


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

    public String extractUUID(String output)
    {

        //Extract the uuid from output recieved
        String regex = "[,\\.\\''\\s]";
        String[] myArray = output.split(regex);
        String UUID = myArray[8];

        System.out.println("UUID :: "+UUID);
       
       return UUID;
    }

    public void createHotspot()
    {
        String[] turnOn = {"nmcli","dev","wifi","hotspot","ssid",this.userName,"password","fileshare"};
        String output = executeCommand(turnOn);
        String UUID = extractUUID(output);
        this.UUID = UUID;

    }

    public void monitorConenction(String connectionName)
    {
        String[] cmd = {"nmcli", "device", "wifi", "list"};
        String[] getMac = {"nmcli", "-f","BSSID","device", "wifi", "list"};
        String prev = executeCommand(getMac);;

        while(true)
        {
            String output = executeCommand(getMac);
            
            if(!prev.equals(output))
            {
                System.out.println("Accept connection? (y/n)"+output);
                String s = this.sc.next();


                if(s.equalsIgnoreCase("y"))
                {
                    acceptConnection(connectionName);
                    return;
                }
                else
                {
                    rejectConnection(connectionName);
                    return;
                }
            }
            
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void acceptConnection(String connectionName) 
    {
        String[] cmd = {"nmcli", "connection", "modify", connectionName, "wifi.accept", "yes"};
        executeCommand(cmd);
    }


    public void rejectConnection(String connectionName)
    {
        String[] cmd = {"nmcli", "connection", "modify", connectionName, "wifi.accept", "no"};
        executeCommand(cmd);
    }
}


class configHotspot
{
    public static void main(String arg[])
    {
        startHotspot s = new startHotspot("Harsh");
        s.createHotspot();
        monitorConenction(userName);
        s.stopHotspot();
    }
}