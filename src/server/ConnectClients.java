package server;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

class ConnectClients extends Thread 
{
    private JDesktopPane dtPane = null;
    private Socket clientSocket = null;
    private JInternalFrame iFrame = new JInternalFrame("Client Screen",true, true, true);
    private JPanel clientPanel = new JPanel();
    
    public ConnectClients(Socket clientSocket, JDesktopPane dtPane) 
    {
        this.clientSocket = clientSocket;
        this.dtPane = dtPane;
        start();
    }

    public void run()
    {
    	drawGUI();
        Rectangle scr = null;
        ObjectInputStream obIS = null;
        
        try
        {
            obIS = new ObjectInputStream(clientSocket.getInputStream());
            scr =(Rectangle) obIS.readObject();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
 
        new GetScreen(obIS,clientPanel);
        new UserCommands(clientSocket,clientPanel,scr);
    }
    
    public void drawGUI()
    {
        iFrame.setLayout(new BorderLayout());
        iFrame.getContentPane().add(clientPanel,BorderLayout.CENTER);
        iFrame.setSize(100,100);
        dtPane.add(iFrame);
        try 
        {
           iFrame.setMaximum(true);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        clientPanel.setFocusable(true);
        iFrame.setVisible(true);
    }
    

}
