package server;

import java.awt.Graphics;
import java.awt.Image;
import java.io.ObjectInputStream;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class GetScreen extends Thread 
{

    private ObjectInputStream obIS = null;
    private JPanel clientPanel = null;
    private boolean i = true;

    public GetScreen(ObjectInputStream ois, JPanel jp) 
    {
        obIS = ois;
        clientPanel = jp;
        start();
    }

    public void run()
    {
            try 
            {
            	while(i)
            	{
                   ImageIcon imageIcon = (ImageIcon) obIS.readObject();
                   Image image = imageIcon.getImage();
                   image = image.getScaledInstance(clientPanel.getWidth(),clientPanel.getHeight(),Image.SCALE_FAST);
                   Graphics graphics = clientPanel.getGraphics();
                   graphics.drawImage(image, 0, 0, clientPanel.getWidth(),clientPanel.getHeight(),clientPanel);
            	}
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            } 
     }
}
