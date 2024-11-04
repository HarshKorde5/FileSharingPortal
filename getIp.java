
import java.net.InetAddress;
import java.net.*;

class getIp
{
    public static void main(String[] arg)
    {
        try
        {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("IP Adrress :: "+address.getHostAddress());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}