package client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class clientInitialize extends HttpServlet 
{
	Socket socket = null;
 
    public clientInitialize() 
    {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        String ip = request.getParameter("ip");
        String port = request.getParameter("port");
        new clientInitialize().initialize(ip, Integer.parseInt(port));
	}
 
    public void initialize(String ip, int port ){

        Robot robot = null; 
        Rectangle rectangle = null;

        try {
            socket = new Socket(ip, port);

            GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev = gEnv.getDefaultScreenDevice();

            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            rectangle = new Rectangle(dim);

            robot = new Robot(gDev);

            drawGUI();

            new SendScreen(socket,robot,rectangle);
            new ServerCommands(socket,robot);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        } 
    }

    private void drawGUI() 
    {
        JFrame frame = new JFrame("Remote Admin");
        JButton button= new JButton("End");
        
        frame.setBounds(100,100,150,150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(button);
        button.addActionListener( new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0);
            }
        }
      );
      frame.setVisible(true);
    }	
	
	
	
	
}
