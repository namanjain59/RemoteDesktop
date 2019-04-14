package server;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JPanel;

class UserCommands implements KeyListener, MouseMotionListener, MouseListener 
{

    private Socket cSoc = null;
    private JPanel cp = null;
    private PrintWriter writer = null;
    private Rectangle scr = null;

    UserCommands(Socket s, JPanel p, Rectangle r) 
    {
        cSoc = s;
        cp = p;
        scr = r;
        cp.addKeyListener(this);
        cp.addMouseListener(this);
        cp.addMouseMotionListener(this);
        try 
        {

            writer = new PrintWriter(cSoc.getOutputStream());
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        
    }

    public void mouseMoved(MouseEvent e) 
    {
        double xScale = scr.getWidth()/cp.getWidth();
        double yScale = scr.getHeight()/cp.getHeight();
        writer.println(-5);
        writer.println((int)(e.getX() * xScale));
        writer.println((int)(e.getY() * yScale));
        writer.flush();
    }


    public void mousePressed(MouseEvent e) 
    {
        writer.println(-1);
        int button = e.getButton();
        int xButton = 16;
        if (button == 3) {
            xButton = 4;
        }
        writer.println(xButton);
        writer.flush();
    }

    public void mouseReleased(MouseEvent e) 
    {
        writer.println(-2);
        int button = e.getButton();
        int xButton = 16;
        if (button == 3) {
            xButton = 4;
        }
        writer.println(xButton);
        writer.flush();
    }
    
    public void keyPressed(KeyEvent e) 
    {
        writer.println(-3);
        writer.println(e.getKeyCode());
        writer.flush();
    }

    public void keyReleased(KeyEvent e) 
    {
        writer.println(-4);
        writer.println(e.getKeyCode());
        writer.flush();
    }
    
    public void mouseDragged(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void keyTyped(KeyEvent e) {}

}
