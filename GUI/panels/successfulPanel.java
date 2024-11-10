package panels;

import java.awt.*;
import javax.swing.*;

public class successfulPanel extends JPanel
{
    public JPanel supanel;
    public JLabel successLabel,infoLabel;

    public successfulPanel()
    {
        supanel = new JPanel();
        supanel.setLayout(null);

        successLabel = new JLabel("<html>Connection successful!</html>");
        successLabel.setBounds(200,10,550,100);
        successLabel.setFont(new Font("Calibri",Font.BOLD,40));

        infoLabel = new JLabel("<html>You can start sharing files</html>");
        infoLabel.setBounds(400,50,400,100);
        infoLabel.setFont(new Font("Calibri",Font.BOLD,20));

        supanel.add(successLabel);
        supanel.add(infoLabel);
    }

}