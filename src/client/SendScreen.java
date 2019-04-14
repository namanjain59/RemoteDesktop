package client;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.ImageIcon;

class SendScreen extends Thread {

    Socket socket = null; 
    Robot robot = null;
    Rectangle rectangle = null;
    boolean loop = true;
    
    public SendScreen(Socket socket, Robot robot,Rectangle rect) 
    {
        this.socket = socket;
        this.robot = robot;
        rectangle = rect;
        start();
    }

    public void run()
    {
        ObjectOutputStream oos = null; 
        try
        {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(rectangle);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

       while(loop)
       {
            BufferedImage image = robot.createScreenCapture(rectangle);
            ImageIcon imageIcon = new ImageIcon(image);

            try 
            {
                oos.writeObject(imageIcon);
                oos.reset(); 
            } 
            catch (IOException ex) 
            {
               ex.printStackTrace();
            }

            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

    }

}
