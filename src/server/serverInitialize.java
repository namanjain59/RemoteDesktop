package server;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.BorderLayout;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

public class serverInitialize extends HttpServlet 
{
    public serverInitialize() 
    {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 String pt = request.getParameter("pot");
	     new serverInitialize().initialize(Integer.parseInt(pt));
	}

       
    private JFrame frame = new JFrame();
    private JDesktopPane desktop = new JDesktopPane();   

    public void initialize(int port)
    {
        try 
        {
            ServerSocket sc = new ServerSocket(port);
            drawGUI();
            while(true)
            {
                Socket client = sc.accept();
                System.out.println("client Connected");
                new ConnectClients(client,desktop);
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public void drawGUI()
    {
            frame.add(desktop,BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
            frame.setState(frame.NORMAL); 
            frame.toFront();
            frame.requestFocus();
    }

}
