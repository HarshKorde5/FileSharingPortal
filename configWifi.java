import java.io.*;
import java.util.*;

class configWifi
{
    public static void main(String arg[]) throws Exception
    {
        String turnOn[] = {"nmcli", "device", "wifi", "list"};

        ProcessBuilder pb = new ProcessBuilder(turnOn);
        Process p = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;

        while((line = reader.readLine()) != null)
        {
            System.out.println(line);
        }

        p.waitFor();
        
    }
}