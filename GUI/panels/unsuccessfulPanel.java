package panels;

import java.awt.*;
import javax.swing.*;

public class unsuccessfulPanel extends JPanel
{
    
    public JPanel unpanel;
    public JLabel unsuccessLabel,infoLabel;

    public unsuccessfulPanel()
    {
        unpanel = new JPanel();
        unpanel.setLayout(null);

        unsuccessLabel = new JLabel("<html>Connection unsuccessful!</html>");
        unsuccessLabel.setBounds(200,10,550,100);
        unsuccessLabel.setFont(new Font("Calibri",Font.BOLD,40));

        infoLabel = new JLabel("<html>We're sorry,please check your wifi and hotspot,and try again</html>");
        infoLabel.setBounds(400,50,400,100);
        infoLabel.setFont(new Font("Calibri",Font.BOLD,20));

        unpanel.add(unsuccessLabel);
        unpanel.add(infoLabel);
    }
}