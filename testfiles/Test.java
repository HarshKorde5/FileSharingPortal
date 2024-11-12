
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.*;
import java.io.*;
import java.util.*;
class Test
{
    public static void main(String arg[]) throws Exception
    {
        List <String> list = new ArrayList<String>();
        list.add("wpa_supplicant -i wlan0 -D nl80211 -c /etc/wpa_supplicant.conf");
        list.add("wpa_cli -i wlan0 p2p_find");

        // Initialize wpa_supplicant
        // Process process = Runtime.getRuntime().exec("wpa_supplicant -i wlan0 -D nl80211 -c /etc/wpa_supplicant.conf");

        // Discover peers
        ProcessBuilder build = new ProcessBuilder(list);
        Process p = build.start();

        // process = build.getRuntime().exec("wpa_cli -i wlan0 p2p_find");
        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }
}