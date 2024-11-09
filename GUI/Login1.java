import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import panels.loginPanel;
import panels.selectPanel;
import panels.sendPanel;
import panels.recievePanel;

class loginWindow
{
    public JFrame frame;
    public JPanel mainPanel,login;
    public CardLayout crd;
    public loginPanel lobj;
    public selectPanel sobj;
    public sendPanel sendObj;
    public recievePanel recieveObj;
    public String userName,room;
    public boolean roomFound,connected,reqAccepted,started;
    public configHotspot hotspot;
    public configWifi wifi;

    public loginWindow()
    {
        roomFound = false;
        connected = false;
        reqAccepted = false;
        userName = "";
        room = "";
    }

    public void displayWindow()
    {
        

        frame = new JFrame("File Sharing Portal");    
        frame.setIconImage(new ImageIcon("file.jpg").getImage());
        frame.setSize(600,600);
        // frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        // frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        crd = new CardLayout(20,20);

        mainPanel = new JPanel();
        mainPanel.setLayout(crd);
        mainPanel.setBackground(Color.CYAN);
        mainPanel.setSize(300,300);

        lobj = new loginPanel();
        sobj = new selectPanel();
        sendObj = new sendPanel();
        recieveObj = new recievePanel();

        mainPanel.add("1",lobj.lpanel);
        mainPanel.add("2",sobj.spanel);
        mainPanel.add("3",recieveObj.rpanel);
        mainPanel.add("4",sendObj.spanel);

        frame.add(mainPanel);
        
        lobj.loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                userName = lobj.usernameField.getText();
                if(!userName.equals("")&&!userName.equals("Enter a username"))
                {
                    sobj.getuserName(userName);
                    crd.show(mainPanel,"2");
                }
            }
        });

        sobj.logoutButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae1)
            {
                lobj.usernameField.setText("Enter a username");
                crd.show(mainPanel,"1");
            }
        });

        sobj.createButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae2)
            {
                sendObj.helloLabel.setText("Hello "+userName+"!");
                sendObj.infoLabel.setText("<html><h3>Ask the other user to click \"Join\" and \"Send request\" for connection to \""+userName+"room\"</h3></html>");
                sendObj.getuserName(userName);

                crd.show(mainPanel,"4");

                //code to turn on hotspot

                hotspot = new configHotspot(userName);

                hotspot.createHotspot();

                recieveObj.availabelLabel.setText("Available rooms : "+userName+"room");
                
            }
        });

        sobj.joinButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae3)
            {
                recieveObj.helloLabel.setText("Hello "+userName+"!");
                recieveObj.infoLabel.setText("<html><h3>Ask the other user to click \"Create\" and \"Accept\" the connection request from \""+userName+"\"</h3></html>");
                recieveObj.userName = userName;
                recieveObj.sendreqButton.setEnabled(false);
                crd.show(mainPanel,"3");
                //code to turn on wifi
                wifi = new configWifi();


                room = wifi.searchRoom();
                    

                if(room.equals("0"))
                {
                    recieveObj.sendreqButton.setEnabled(false);
                }
                else
                {
                    recieveObj.availabelLabel.setText("Available rooms : "+room);
                    roomFound = true;
                    recieveObj.sendreqButton.setEnabled(true);
                    connected = wifi.connectRoom();
                }

            }
        });


        sendObj.acceptButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae4)
            {
                reqAccepted = true;
            }
        });

        sendObj.denyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae5)
            {
                reqAccepted = false;
            }
        });

        recieveObj.sendreqButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae6)
            {
                if(roomFound)
                { 
                    try
                    {
                        connected = startWifi.connectRoom(room);

                    }
                    catch(Exception eobj1){}

                    if(connected)
                    {
                        System.out.println("Connection successful to "+room+", ready to share!");
                    }
                    else
                    {
                        System.out.println("Connection to the room "+room+" failed, try again!");
                    }
                }
                else
                {
                    System.out.println("Room not found");
                }
            }
        });
    }
}

class Login1
{
    public static void main(String arg[])
    {
        loginWindow screen = new loginWindow();
        screen.displayWindow();

    }
}
