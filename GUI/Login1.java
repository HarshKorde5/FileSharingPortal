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
    public static String userName = "";

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
                sendObj.userName = userName;
                crd.show(mainPanel,"4");
            }
        });

        sobj.joinButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae3)
            {
                recieveObj.helloLabel.setText("Hello "+userName+"!");
                recieveObj.userName = userName;
                crd.show(mainPanel,"3");
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
