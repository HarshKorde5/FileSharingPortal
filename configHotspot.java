import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class configHotspot
{
    public static void main(String arg[])    
    {
        try
        {
            
            String[] turnOn = {"nmcli","dev","wifi","hotspot","ssid","filesharing","password","fileshare"};
            String[] turnOf = {"nmcli","connection","down",""};


            //Turning on the hotspot

            ProcessBuilder pb = new ProcessBuilder(turnOn);
            Process process = pb.start();

            //Reading and displaying the output after command execution
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;


            StringBuilder output = new StringBuilder();

            System.out.println("This is the output after connection start");
            while((line = reader.readLine())!= null)
            {
                output.append(line);
                System.out.println(line);
            }

            //wait for completion of the executing process i.e turning on hotspot
            process.waitFor();


            //Extract the uuid from output recieved
            String str = output.toString();
            String regex = "[,\\.\\''\\s]";
            String[] myArray = str.split(regex);
            String UUID = myArray[8];

            System.out.println("UUID :: "+UUID);

            turnOf[turnOf.length-1] = UUID;

            //demo code for turning off the hotspot
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