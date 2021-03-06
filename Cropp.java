import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;

import javax.imageio.*;
import javax.swing.*;

public class Cropp
{

    public static void main(String[] args) {
        new Cropp();
    }

    public Cropp() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new CropPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class CropPane extends JPanel {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private BufferedImage background;
        private Rectangle cropBounds;

        public CropPane() {
            try {
                background = ImageIO.read(new File("Temp_0.jpg"));
            } catch (IOException exp) {
                exp.printStackTrace();
            }

            MouseHandler handler = new MouseHandler();

            addMouseListener((MouseListener) handler);
            addMouseMotionListener((MouseMotionListener) handler);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(background.getWidth(), background.getHeight());
        }

        protected Rectangle getCropBounds() {
            Rectangle actualBounds = null;
            if (cropBounds != null) {
                int x = cropBounds.x;
                int y = cropBounds.y;
                int width = cropBounds.width;
                int height = cropBounds.height;

                if (width < 0) {
                    x += width;
                    width -= (width * 2);
                }
                if (height < 0) {
                    y += height;
                    height -= (height * 2);
                }

                actualBounds = new Rectangle(x, y, width, height);
                System.out.println(actualBounds);
            }
            return actualBounds;
        }

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();
            if (background != null) {
                int x = (getWidth() - background.getWidth()) / 2;
                int y = (getHeight() - background.getHeight()) / 2;
                g2d.drawImage(background, x, y, this);
            }

            Rectangle drawCrop = getCropBounds();
            if (drawCrop != null) {
                Color color = UIManager.getColor("List.selectionBackground");
                g2d.setColor(color);
                Composite composite = g2d.getComposite();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
                g2d.fill(drawCrop);
                g2d.setComposite(composite);
                g2d.draw(drawCrop);
            }
        }

        public class MouseHandler extends MouseAdapter 
        {

            @Override
            public void mouseReleased(MouseEvent e) 
            {
            	BufferedImage bff = background.getSubimage(cropBounds.x, cropBounds.y, cropBounds.width, cropBounds.height);
            	
            	JLabel picLabel = new JLabel(new ImageIcon(bff));
            	int YesNo = JOptionPane.showConfirmDialog(null, picLabel, "About", JOptionPane.YES_NO_OPTION);
            	
            	if (YesNo == JOptionPane.YES_OPTION)
            	{		
	            	File outputfile = new File("image.jpg");
	            	try
	            	{
						ImageIO.write(bff, "jpg", outputfile);
					}
	            	catch (IOException e1)
	            	{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            	cropBounds = null;
	                repaint();
	                // JPanel p = (JPanel)(e.getSource());
	                // p.getF
	                System.exit(0);
            	}
            }

            @Override
            public void mousePressed(MouseEvent e) {
                cropBounds = new Rectangle();
                cropBounds.setLocation(e.getPoint());
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (cropBounds != null) {
                    Point p = e.getPoint();
                    int width = p.x - cropBounds.x;
                    int height = p.y - cropBounds.y;
                    cropBounds.setSize(width, height);
                    repaint();
                }
            }
        }
    }
}