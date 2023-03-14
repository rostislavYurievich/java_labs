import java.awt.geom.Rectangle2D;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.event.MouseListener;
import org.w3c.dom.events.MouseEvent;

class FractalExplorer {
    private int disp_size;
    private JImageDisplay img_disp;
    private FractalGenerator fg;
    private Rectangle2D.Double rng;

    FractalExplorer(int screen_size){
        this.disp_size = screen_size;
        Rectangle2D.Double rng = new Rectangle2D.Double(0,0,screen_size,screen_size);
        fg = new Mandelbrot();
        fg.getInitialRange(rng);
    }

    private void createAndShowGUI(){

        JFrame frame = new JFrame("Генератор фракталов");
        JButton button = new JButton("Сброс");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        img_disp = new JImageDisplay(disp_size, disp_size,BufferedImage.TYPE_INT_RGB);
        img_disp.addMouseListener(new ALClass());
        frame.setLayout(new java.awt.BorderLayout());
        frame.add(img_disp, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);

        frame.pack(); frame.setVisible(true); frame.setResizable(false);
    }

    private void drawFractal(){
        for (int i = 0; i<disp_size; i++){
            for (int j = 0; j<disp_size; j++){

                double x = FractalGenerator.getCoord(rng.x,rng.x+rng.width, disp_size,j);
                double y = FractalGenerator.getCoord(rng.y,rng.y+rng.height, disp_size,i);
                int numIters = fg.numIterations(x,y);
                if (numIters == -1) numIters = 0;
                float hue = 0.7f + (float) numIters/200f;
                int rgbColor = Color.HSBtoRGB(hue,1f,1f);
                img_disp.drawPixel(j,i,rgbColor);
                img_disp.repaint();
            }
        }
    }
    class ALClass implements java.awt.event.ActionListener
    {

    };

    class MLClass implements java.awt.event.MouseListener
    {
            public void mouseClicked(MouseEvent e)
        {
            disp_size /=2;
            rng.
        }

    }

}
