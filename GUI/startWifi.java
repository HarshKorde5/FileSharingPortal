import java.io.*;
import java.util.*;

public class startWifi
{
    public String wuserName;

    
    public startWifi(String name)
    {
        this.wuserName = name;
        
    }

    public static void executeCommand(String cmd[])
    {

    }

    public static void startHotspot(String cmd[])
    {

    }

    
    public static String searchRoom() throws Exception
    {
        String getList[] = {"nmcli","-f", "SSID", "device", "wifi", "list"};  //17length
        // String turnOn[] = {"nmcli","device", "wifi", "list"};    //94length

        String SSID = "";
        boolean roomFound = false;


        

        ProcessBuilder pb = new ProcessBuilder(getList);
        Process p = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;

        while((line = reader.readLine()) != null)
        {
            // System.out.println(line.length());
            line = line.trim();

            if(line.endsWith("room"))
            {
                System.out.println(line);
                roomFound = true;
                SSID = line;
                System.out.println("SSID :: "+SSID);
            }
        }

        p.waitFor();
    
        if(SSID.equals(""))
        {
            return "0";
        }
        else
        {
            return SSID;
        }
    }

    public static boolean connectRoom(String ssid) throws Exception
    {
        boolean connected = true;
        String password = "fileshare";

        String[] turnOn = {"nmcli","device","wifi","connect",ssid,"password",password};

        ProcessBuilder pb = new ProcessBuilder(turnOn);
        Process p = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;

        while((line = reader.readLine())!= null)
        {
            if(line.startsWith("Error"))
            {
                connected = false;
                break;
            }
            System.out.println(line);
        }

        return connected;
    }


}