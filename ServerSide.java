import java.net.*;
import java.io.*;

class ServerSide
{
    public void startServer(String arg[])
    {
        try
        {
            System.out.println("Server Application is running...");
            ServerSocket ssobj = new ServerSocket(2100);
            System.out.println("Server is running at port number : 2100");

            Socket s = ssobj.accept();
            System.out.println("Server and client connection is succesful");

            PrintStream ps = new PrintStream(s.getOutputStream());

            BufferedReader br1 = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));

            String str1,str2;
            while((str1 = br1.readLine()) != null)
            {
                System.out.println("Client says : "+str1);
                System.out.println("Enter message for client : ");
                str2 = br2.readLine();
                ps.println(str2);
            }
            System.out.println("Thank you for using chat messanger...");
        }
        catch(Exception E)
        {
            System.out.println(E);
        }

    }
}