import com.sun.deploy.ui.ImageLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by linhonggu on 4/8/17.
 */
public class DrawTest extends JComponent implements Runnable, MouseMotionListener{
    JFrame jFrame;
    int x = 0;
    int y = 0;

    public DrawTest(){
        SwingUtilities.invokeLater(this);
    }

    public static void main(String[] args) {
        new DrawTest();
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        //some basic drawing, line,rectangular,oval and set color
        g.setColor(Color.CYAN);
        g.fillRect(50,50, 50,50);
        g.fillOval(100,100, 50,50);

        g.setColor(new Color(1.0f,0.0f,0.0f));
        g.drawLine(500,500,600,600);
        g.draw3DRect(200,200,60,60,false);

        //draw text
        int h=20;
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD,20));
        String str = "Helloooo";
        g.drawString(str,600,600);

        //load and draw an image
        BufferedImage image;
        try {
            image= ImageIO.read(new File("src/tiger.jpg"));
            Graphics2D gi = image.createGraphics();
            //gi.draw(new Line2D.Double(0.0, 0.0, 20.0, 20.0));  // draw onto the image
            //g.drawImage(image, 10, 50, null); // draw the image onto the main Graphics object
        }catch (IOException e){
            e.printStackTrace();
        }

        //in graphic2D we can set the width of line
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL));


        //create a path
        Path2D p = new Path2D.Double();
        p.moveTo(300,400);
        p.quadTo(40,50,60,80);
        p.moveTo(200,100);
        p.curveTo(23,33,44,55,66,77);
        g2.draw(p);



        //fill a path
        Path2D p2 = new Path2D.Double();
        p2.moveTo(300, 150);
        p2.lineTo(100, 150);
        p2.lineTo(80, 150);
        p2.lineTo(70, 100);
        p2.lineTo(170, 140);
        p2.lineTo(x, y);
        p2.lineTo(80, 150);
        g2.setColor(Color.red);
        g2.fill(p2);
        g2.setColor(Color.green);
        g2.draw(p2);







    }


    @Override
    public void run() {
        jFrame = new JFrame("Hello, GUI");
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);

        jFrame.getContentPane().add(this);
        this.addMouseMotionListener(this);


        jFrame.setVisible(true);
        jFrame.pack();


    }


    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        this.repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();

        this.repaint();

    }
}
