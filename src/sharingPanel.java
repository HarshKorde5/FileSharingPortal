
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;


class sharingPanel extends JPanel
{

    public JPanel sharingpanel,send,recieve;
    public JLabel headerLabel,confirmFileLabel,imageLabel;

    public JButton chooseFileButton,sendFileButton,stopSharing;

    public JFileChooser filechooser;
    private File selectedFile;
    
    public sharingPanel(ServerSide server)
    {}
    
    public sharingPanel(ClientSide client)
    {}

    public sharingPanel()
    {
        
        send = new JPanel();
        recieve = new JPanel(){
            protected void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                g2.setStroke(new BasicStroke(1));
                g2.drawLine(20,10,540,10);
            }
        };

        send.setLayout(null);
        recieve.setLayout(null);
        send.setBounds(0,0,600,280);
        recieve.setBounds(0,280,600,280);

        selectedFile = null;
        sharingpanel = new JPanel();
        sharingpanel.setLayout(null);
        headerLabel = new JLabel("You can start sharing files now!");
        headerLabel.setBounds(10,20,550,30);
        headerLabel.setFont(new Font("Calibri",Font.BOLD,30));
        
        chooseFileButton = new JButton("Choose file");
        chooseFileButton.setBounds(180,100,200,30);
        chooseFileButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent a)
            {
                openFileSelector();
            }
        });


        confirmFileLabel = new JLabel("<html><h3>Do you want to send : </h3></html>");
        confirmFileLabel.setBounds(40,150,550,70);

        sendFileButton = new JButton("Send file");
        sendFileButton.setBounds(180,230,200,30);

        sendFileButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent a)
            {
                sendSelectedFile();

                selectedFile = null;
            }
        });   

        stopSharing = new JButton("<html>Stop sharing</html>");
        stopSharing.setBounds(420,190,130,40);
        stopSharing.setForeground(Color.red);
        stopSharing.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e)
            {
                int result = JOptionPane.showConfirmDialog(sharingpanel,"Sure?You want to stop sharing and exit?","Confirm Exit",JOptionPane.YES_NO_OPTION);

                System.out.println(result);
                if(result == 0) System.exit(0);

            }
        });


        ImageIcon icon = new ImageIcon("downloadFile.png");
        Image image = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

        imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setBounds(175,10,200,200);

        send.add(headerLabel);
        send.add(confirmFileLabel);
        send.add(sendFileButton);
        send.add(chooseFileButton);
        

        recieve.add(stopSharing);
        recieve.add(imageLabel);

        sharingpanel.add(send);
        sharingpanel.add(recieve);

    }

    private void openFileSelector()
    {
        filechooser = new JFileChooser();
        int returnValue = filechooser.showOpenDialog(sharingpanel);

        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = filechooser.getSelectedFile();

            confirmFileLabel.setText("<html><h3>Do you want to send : "+selectedFile.getName()+"</h3></html>");
            System.out.println("Selected File :: "+selectedFile.getAbsolutePath());
        }
    }

    private void sendSelectedFile()
    {
        //now call the clients sendfile method and send the filename
        if(selectedFile == null)
        {
            JOptionPane.showMessageDialog(confirmFileLabel,"Please select a file first","Alert",JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            confirmFileLabel.setText("<html><h3>Do you want to send : </h3></html>");
            System.out.println("File sent!");
            JOptionPane.showMessageDialog(confirmFileLabel,"File sent","Alert",JOptionPane.INFORMATION_MESSAGE);

        }
    }
}