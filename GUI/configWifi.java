import java.io.*;
import java.util.*;

class configWifi
{
    public String SSID;

    public configWifi()
    {
        SSID = "";
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


    public String getSSID(String output)
    {
        String trimmed = output.replace("\n"," ").replace("\r"," ");
        String[] splitted = trimmed.split(" ");

        for(int i = 0;i < splitted.length;i++)
        {
            if(splitted[i].endsWith("room"))
            {
                return splitted[i];
            }
        }

        return "0";
    }

    public String searchRoom()
    {
        String getList[] = {"nmcli","-f", "SSID", "device", "wifi", "list"};  //17length
        // String turnOn[] = {"nmcli","device", "wifi", "list"};    //94length


        String output = executeCommand(getList);

        this.SSID = getSSID(output);

        if(SSID.equals("0"))
        {
          System.out.println("No room found! Try again");
        }
        else
        {
            System.out.println("Room found with SSID :: "+SSID+"room");
        }
        return SSID;
    }

    public boolean connectRoom()
    {
        if(this.SSID.equals("0"))
        {
            System.out.println("No room found,please try again later");
            return false;
        }

        boolean connected = true;
        String password = "fileshare";

        String[] turnOn = {"nmcli","device","wifi","connect",SSID,"password",password};

        String output = executeCommand(turnOn);

        if(output.startsWith("Error"))
        {
            connected = false;
        }
        
        
        if(connected)
        {
            System.out.println("Connection successful to "+SSID+", ready to share!");
        }
        else
        {
            System.out.println("Connection to the room "+SSID+" failed, try again!");
        }

        return connected;
    }

}