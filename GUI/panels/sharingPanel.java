package panels;

import java.awt.*;
import javax.swing.*;

public class sharingPanel extends JPanel
{
    public JPanel sharingpanel;
    public JLabel header;

    public sharingPanel()
    {
        sharingpanel = new JPanel();
        sharingpanel.setLayout(null);
        header = new JLabel("Share files");
        header.setBounds(100,100,100,100);
        header.setFont(new Font("Calibri",Font.BOLD,50));
        
        sharingpanel.add(header);
    }
}
