import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
// import javax.swing.border.LineBorder;

class loginWindow extends FocusAdapter
{
    public JFrame frame;
    public JLabel headerLabel;
    public JTextField usernameField,passwordField;
    public JButton loginButton,clearButton;

    public void displayWindow()
    {
        frame = new JFrame("File Sharing Portal");
        frame.setSize(600,600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        headerLabel = new JLabel("Log in");
        headerLabel.setBounds(220,70,200,100);
        // header.setPreferredSize(new Dimension(200,200));
        headerLabel.setFont(new Font("Calibri",Font.BOLD,50));
        frame.add(headerLabel);

        usernameField = new JTextField("Enter username",50);
        passwordField = new JTextField("Enter password",50);

        usernameField.setBounds(100,200,400,50);
        passwordField.setBounds(100,300,400,50);

        usernameField.addFocusListener(this);
        passwordField.addFocusListener(this);

        // usernameField.setBorder(new LineBorder(Color.black,1,true));
        frame.add(usernameField);
        frame.add(passwordField);

        loginButton = new JButton("Login");
        clearButton = new JButton("Clear");

        loginButton.setBounds(350,380,100,50);
        clearButton.setBounds(150,380,100,50);

        frame.add(loginButton);
        frame.add(clearButton);
    }   

    public void focusGained(FocusEvent f)
    {
        if(f.getSource() == usernameField)
        {
            usernameField.setText("");
        }
        if(f.getSource() == passwordField)
        {
            passwordField.setText("");
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
