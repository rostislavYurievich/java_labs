import java.awt.geom.Rectangle2D;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

class FractalExplorer {
    private int disp_size;
    private JImageDisplay img_disp;
    private FractalGenerator fg;
    private Rectangle2D.Double rng = new Rectangle2D.Double();

    FractalExplorer(int screen_size){
        this.disp_size = screen_size;
        fg = new Mandelbrot();
        fg.getInitialRange(rng);
    }

    private void createAndShowGUI(){

        JFrame frame = new JFrame("Генератор фракталов");
        JButton button = new JButton("Сброс");

        img_disp = new JImageDisplay(disp_size, disp_size,BufferedImage.TYPE_INT_RGB);

        img_disp.addMouseListener(new MLClass());
        button.addActionListener(new ALClass());

        frame.setLayout(new java.awt.BorderLayout());
        frame.add(img_disp, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); frame.setVisible(true); frame.setResizable(false);
    }

    private void drawFractal(){
        for (int i = 0; i<disp_size; i++){
            for (int j = 0; j<disp_size; j++){
                double x = FractalGenerator.getCoord(rng.getX(),rng.getX()+rng.getWidth(), disp_size,j);
                double y = FractalGenerator.getCoord(rng.getY(),rng.getY()+rng.getHeight(), disp_size,i);
                int numIters = fg.numIterations(x,y);
                if (numIters == -1) numIters = 0;
                float hue = 0.7f + (float) numIters/200f;
                int rgbColor = Color.HSBtoRGB(hue,1f,1f);
                img_disp.drawPixel(j,i,rgbColor);

            }
        }
        img_disp.repaint();
    }
    class ALClass implements java.awt.event.ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            fg.getInitialRange(rng);
            drawFractal();
        }

    };

    class MLClass implements java.awt.event.MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e){
            double x = FractalGenerator.getCoord(rng.getX(),rng.getX()+rng.getWidth(), disp_size,e.getX());
            double y = FractalGenerator.getCoord(rng.getY(),rng.getY()+rng.getHeight(), disp_size,e.getY());
            fg.recenterAndZoomRange(rng, x, y, 0.5);
            drawFractal();
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        }
        @Override
        public void mouseExited(MouseEvent arg0) {
            // TODO Auto-generated method stub
        }
        @Override
        public void mouseReleased(MouseEvent arg0) {
            // TODO Auto-generated method stub
        }
        @Override
        public void mousePressed(MouseEvent arg0) {
            // TODO Auto-generated method stub
        }

    }

    public static void main(String[] args){
        FractalExplorer frex = new FractalExplorer(640);
        frex.createAndShowGUI();
        frex.drawFractal();
    }

}
