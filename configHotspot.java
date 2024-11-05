import java.io.*;
import java.util.*;

class configHotspot
{
    public static void main(String arg[])    
    {
        try
        {
            // List<String> command = new ArrayList<String>();
            // command.add("nmcli dev wifi hotspot ssid filesharing password fileshare");
            
            String[] turnOn = {"nmcli","dev","wifi","hotspot","ssid","filesharing","password","fileshare"};
            String[] turnOf = {"nmcli","connection","down","Hotspot-2"};

            // String[] args = {"dev wifi hotspot ssid filesharing password fileshare"};

            ProcessBuilder pb = new ProcessBuilder(turnOn);

            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;

            System.out.println("This is the output after connection start");

            while((line = reader.readLine())!= null)
            {
                System.out.println(line);
            }

            process.waitFor();

            while(true)
            {
                System.out.println("Do you want to stop the hotspot theatering? (y/n)");
                Scanner sc = new Scanner(System.in);

                String stop = sc.next();

                if(stop.equals("y"))
                {
                    ProcessBuilder pbstop = new ProcessBuilder(turnOf);

                    Process p = pbstop.start();
                    System.out.println("This is the output after connection down");
                    
                    BufferedReader reader1 = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line1;

                    while((line1 = reader1.readLine())!= null)
                    {
                        System.out.println(line1);
                    }

                    process.waitFor();
                    System.exit(0);
                }
                else
                {
                    System.exit(0);
                }
                
            }
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
    }
}