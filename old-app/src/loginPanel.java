import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class loginPanel extends JPanel
{
    public JLabel headerLabel,startSharingLabel;
    public JTextField usernameField;
    public JButton loginButton,clearButton;
    public JPanel lpanel;

    public loginPanel()
    {
        loginButton = new JButton("Login");
        lpanel = new JPanel();
        lpanel.setLayout(null);

        headerLabel = new JLabel("Log in");
        headerLabel.setBounds(220,70,200,80);
        headerLabel.setFont(new Font("Calibri",Font.BOLD,50));
        lpanel.add(headerLabel);

        startSharingLabel = new JLabel("Start sharing as : ");
        startSharingLabel.setBounds(160,200,350,50);
        startSharingLabel.setFont(new Font("Calibri",Font.BOLD,30));
        lpanel.add(startSharingLabel);

        usernameField = new JTextField("Enter a username",50);
        usernameField.setBounds(100,270,400,50);
        usernameField.addFocusListener(new FocusAdapter(){
            public void focusGained(FocusEvent f)
            {
                usernameField.setText("");
            }
        });

        lpanel.add(usernameField);

        clearButton = new JButton("Clear");

        loginButton.setBounds(350,380,100,50);
        clearButton.setBounds(150,380,100,50);

        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                String username = usernameField.getText();
                if(!username.equals("")&&!username.equals("Enter a username"))
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
        });
        clearButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                usernameField.setText("");
            }
        });

        lpanel.add(loginButton);
        lpanel.add(clearButton);
    }
    
    
}