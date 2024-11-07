import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


class loginWindow extends FocusAdapter implements ActionListener
{
    public JFrame frame;
    public JLabel headerLabel,startSharingLabel;
    public JTextField usernameField;
    public JButton loginButton,clearButton;

    public void displayWindow()
    {
        frame = new JFrame("File Sharing Portal");
        
        frame.setIconImage(new ImageIcon("file.jpg").getImage());
        frame.setSize(600,600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        

        headerLabel = new JLabel("Log in");
        headerLabel.setBounds(220,70,200,80);
        headerLabel.setFont(new Font("Calibri",Font.BOLD,50));
        frame.add(headerLabel);

        startSharingLabel = new JLabel("Start sharing as : ");
        startSharingLabel.setBounds(160,200,350,50);
        startSharingLabel.setFont(new Font("Calibri",Font.BOLD,30));
        frame.add(startSharingLabel);

        usernameField = new JTextField("Enter a username",50);
        usernameField.setBounds(100,270,400,50);
        usernameField.addFocusListener(this);

        // usernameField.setBorder(new LineBorder(Color.black,1,true));
        frame.add(usernameField);

        loginButton = new JButton("Login");
        clearButton = new JButton("Clear");

        loginButton.setBounds(350,380,100,50);
        clearButton.setBounds(150,380,100,50);

        loginButton.addActionListener(this);
        clearButton.addActionListener(this);

        frame.add(loginButton);
        frame.add(clearButton);
    }   

    public void focusGained(FocusEvent f)
    {
        if(f.getSource() == usernameField)
        {
            usernameField.setText("");
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == clearButton)
        {
            usernameField.setText("");
        }
        if(e.getSource() == loginButton)
        {
            String username = usernameField.getText();
            if(!username.equals(""))
            {
                System.out.println("Welcome "+username+"\nPlease select to send or recieve files");
            }
            else
            {
                //display msg dialog box
                JOptionPane.showMessageDialog(usernameField, "Please enter a valid username", 
                                          "Alert", 
                                          JOptionPane.INFORMATION_MESSAGE);

            }
        }
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
