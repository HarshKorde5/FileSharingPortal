import java.net.*;
import java.io.*;

class ServerSide
{
    public DataInputStream din;
    public DataOutputStream dout;

    public void startServer()
    {
        try
        {
            System.out.println("Server Application is running...");
            ServerSocket ssobj = new ServerSocket(2100);
            System.out.println("Server is running at port number : 2100");

            Socket s = ssobj.accept();
            System.out.println("Server and client connection is succesful");

            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());

            recieveFile("newFile1.jpg");

            din.close();
            dout.close();
            s.close();
            System.out.println("Thank you for using chat messanger...");
        }
        catch(Exception E)
        {
            System.out.println(E);
        }

    }

    public void recieveFile(String fileName)
        throws Exception
    {
        int bytes = 0;
        FileOutputStream fileout
            = new FileOutputStream(fileName);
 
        long size
            = din.readLong(); // read file size
        byte[] buffer = new byte[4 * 1024];
        while (size > 0
               && (bytes = din.read(
                       buffer, 0,
                       (int)Math.min(buffer.length, size)))
                      != -1) {
            // Here we write the file using write method
            fileout.write(buffer, 0, bytes);
            size -= bytes; // read upto file size
        }
        // Here we received file
        System.out.println("File is Received");
        fileout.close();
    }
}