import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.beans.Transient;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class BasicDrawing extends JComponent implements Runnable,
        MouseMotionListener {

    /**
     * BasicDrawing - a simple program to demo Graphics and Graphics2D 
     * Eric McCreath 2015 - GPL
     */

    JFrame jframe;
    int x = 0;
    int y = 0;

    public BasicDrawing() {
        SwingUtilities.invokeLater(this);
    }

    public static void main(String[] args) {
        new BasicDrawing();
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        // g.setClip(10, 10, 50, 50);

        // some basic drawing, a line, a rectangle, an oval ..
        g.setColor(new Color(0.5f, 0.1f, 1.0f));
        g.fillRect(20, 50, 100, 20);
        // g.setXORMode(Color.red);
        g.setColor(Color.red);
        g.drawLine(10, 20, 30, 50);
        g.drawRoundRect(30, 30, 100, 100, 15, 15);

        g.drawOval(50, 40, 30, 30);

        g.setColor(Color.green);

        g.drawArc(50, 40, 30, 30, 0, 45);

        g.setColor(Color.black);

        // drawing text
        int h = 22;
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, h));
        String str = "Hello!!!!!";
        int w = g.getFontMetrics().stringWidth(str);
        g.drawString(str, 50, 40);
        g.drawRect(50, 40 - h, w, h);

        // g.setPaintMode();

        // load and draw an image
        BufferedImage image;
        try {
            image = ImageIO.read(new File(
                    "/home/ericm/courses/cg/comp4610/compgraphicslogo.png"));

            Graphics2D gi = image.createGraphics();
            gi.draw(new Line2D.Double(0.0, 0.0, 20.0, 20.0));  // draw onto the image

            g.drawImage(image, 10, 50, null); // draw the image onto the main Graphics object
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Graphics2D g2 = (Graphics2D) g;

        // in Graphics2D we can set the width of lines
        g2.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER));

        // create paths
        Path2D p = new Path2D.Double();
        p.moveTo(70, 20);
        p.quadTo(x, y, 130, 130);
        p.moveTo(70, 20);
        p.curveTo(39, 40, 39, 40, 130, 130);
        g2.draw(p);



        // filling a path
        Path2D p2 = new Path2D.Double();
        p2.moveTo(10, 150);
        p2.lineTo(100, 150);
        p2.lineTo(80, 150);
        p2.lineTo(70, 100);
        p2.lineTo(170, 140);
        p2.lineTo(x, y);
        p2.lineTo(10, 150);
        g2.setColor(Color.red);
        g2.fill(p2);
        g2.setColor(Color.green);
        g2.draw(p2);



        // For a good summary of alpha blending see : https://docstore.mik.ua/orelly/java-ent/jfc/ch04_07.htm
        // change the way the pen combines with the image it is drawing to using setComposite 
//		g2.setComposite(AlphaComposite.SrcAtop);
//		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2.setColor(new Color(0.5f, 1.0f, 0.0f, 0.8f));
        g2.fill(new Rectangle2D.Double(10.0, 10.10, 150.0, 60.0));

    }

    @Override
    @Transient
    public Dimension getPreferredSize() {
        // TODO Auto-generated method stub
        return new Dimension(200, 200);
    }

    public void run() {
        jframe = new JFrame("HelloGUI");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jframe.getContentPane().add(this);
        this.addMouseMotionListener(this);

        jframe.setVisible(true);
        jframe.pack();
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent me) {
        x = me.getX();
        y = me.getY();
        this.repaint();
    }
}