import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

class JImageDisplay extends javax.swing.JComponent {
    public BufferedImage img;

    JImageDisplay(int width, int height, int TYPE_INT_RGB)
    {
        img = new BufferedImage(width, height, TYPE_INT_RGB);
        super.setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
         g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
    }

    public void clearImage(){
        int w = img.getWidth(), h = img.getHeight();
        for (int i = 0; i<h;i++){
            for (int j = 0; j<w; j++){
                img.setRGB(i,j,2147483647);
            }
        }
    }

    public void drawPixel(int x, int y, int rgb){
        img.setRGB(x,y,rgb);
    }

    public void drawCircle(int x, int y, int radius, int rgb, boolean fill){
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setColor(new Color(rgb));
        Ellipse2D e = new Ellipse2D.Double(x-radius,y-radius,radius*2,radius*2);
        g.draw(e);
        if (fill){
            g.fill(e);
        }
    }
}
