import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


class loginWindow
{
    public JFrame frame;
    public JPanel mainPanel,login;
    public CardLayout crd;
    public loginPanel lobj;
    public selectPanel sobj;
    public createPanel createObj;
    public joinPanel joinObj;
    public String hotspotName,room;
    public boolean roomFound,connected,reqAccepted,started;
    public configHotspot hotspot;
    public configWifi wifi;
    public sharingPanel sharingObj;
    public ClientSide client ;
    public ServerSide server;

    public Timer timer,timer1;

    public loginWindow()
    {
        roomFound = false;
        connected = false;
        reqAccepted = false;
        hotspotName = "";
        room = "";
    }

    public static void countDown()
    {  
        System.out.println("Inside countDown");
        try 
        {
            Thread.sleep(5000);
        } 
        catch (InterruptedException ex) 
        {
            Thread.currentThread().interrupt();
        }        
    }

    public void displayWindow()
    {
        

        frame = new JFrame("File Sharing Portal");    
        frame.setIconImage(new ImageIcon("file.jpg").getImage());
        frame.setSize(600,600);
        // frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        crd = new CardLayout(20,20);

        mainPanel = new JPanel();
        mainPanel.setLayout(crd);
        mainPanel.setBackground(Color.CYAN);
        mainPanel.setSize(300,300);

        lobj = new loginPanel();
        sobj = new selectPanel();
        createObj = new createPanel();
        joinObj = new joinPanel();
        sharingObj = new sharingPanel();

        mainPanel.add("1",lobj.lpanel);
        mainPanel.add("2",sobj.spanel);
        mainPanel.add("3",joinObj.jpanel);
        mainPanel.add("4",createObj.cpanel);
        mainPanel.add("7",sharingObj.sharingpanel);

        frame.add(mainPanel);
        

        lobj.loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                hotspotName = lobj.usernameField.getText();
                if(!hotspotName.equals("")&&!hotspotName.equals("Enter a hotspotName"))
                {
                    sobj.gethotspotName(hotspotName);
                    crd.show(mainPanel,"2");
                }
            }
        });

        sobj.logoutButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae1)
            {
                lobj.usernameField.setText("Enter a hotspotName");
                crd.show(mainPanel,"1");
            }
        });

        sobj.createButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae2)
            {
                createObj.helloLabel.setText("Hello "+hotspotName+"!");
                createObj.infoLabel.setText("<html><h3>Ask the other user to click \"Join\" and \"Send request\" for connection to \""+hotspotName+"room\"</h3></html>");
                createObj.gethotspotName(hotspotName);
                createObj.idLabel.setText("Room ID : "+hotspotName);

                // code to turn on hotspot

                hotspot = new configHotspot(hotspotName);

                hotspot.createHotspot();

                joinObj.availabelLabel.setText("Available rooms : "+hotspotName+"room");
                
                crd.show(mainPanel,"4");

                createObj.startProgress();


                timer = new Timer(10000,new ActionListener(){
                    public void actionPerformed(ActionEvent a)
                    {
                        crd.show(mainPanel,"7");
                    }
                });
        
                timer.start();
                try{Thread.sleep(300);}catch(Exception e){}

                timer1 = new Timer(5000,new ActionListener(){
                    public void actionPerformed(ActionEvent a)
                    {
                        // server = new ServerSide();

                        // server.startServer();

                    }
                });

                timer1.start();


            }
        });

        sobj.joinButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae3)
            {
                joinObj.helloLabel.setText("Hello "+hotspotName+"!");
                joinObj.infoLabel.setText("<html><p>Ask the other user to click \"Create\" and wait for the connection to establish</p></html>");
                joinObj.hotspotName = hotspotName;

                wifi = new configWifi();

                room = wifi.searchRoom();
                
                if(room.equals("0"))
                {
                }
                else
                {
                    joinObj.availabelLabel.setText("Available rooms : "+room);
                    roomFound = true;
                    connected = wifi.connectRoom();
                }

                crd.show(mainPanel,"3");

                joinObj.startProgress();

                




                timer = new Timer(10000,new ActionListener(){
                    public void actionPerformed(ActionEvent a)
                    {
                        crd.show(mainPanel,"7");
                    }
                });

                timer.start();

                try{Thread.sleep(300);}catch(Exception e){}

                timer1 = new Timer(15000,new ActionListener(){
                    public void actionPerformed(ActionEvent a)
                    {
                        // client = new ClientSide();
                    }
                });

                timer1.start();

            }
        });


        

    }
}

class Login
{
    public static void main(String arg[])
    {
        loginWindow screen = new loginWindow();
        screen.displayWindow();

    }
}
