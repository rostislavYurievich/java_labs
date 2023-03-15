import java.awt.geom.Rectangle2D;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.SwingWorker;

class FractalExplorer {
    private int disp_size;
    private JImageDisplay img_disp;
    private JComboBox<FractalGenerator> combo;
    private FractalGenerator fg;
    private FractalGenerator[] fractalList = {
        new Mandelbrot(),
        new Tricorn(),
        new BurningShip()
        };


    private Rectangle2D.Double rng = new Rectangle2D.Double();
    private int rowsRemaining;

    FractalExplorer(int screen_size){
        this.disp_size = screen_size;
        fg = fractalList[0];
        fg.getInitialRange(rng);
    }

    private void createAndShowGUI(){

        JFrame frame = new JFrame("Генератор фракталов");

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Фрактал:");
        combo = new JComboBox<FractalGenerator>();

        JButton button = new JButton("Сброс");

        for (FractalGenerator fractal: fractalList){
            combo.addItem(fractal);
        }
        combo.addActionListener(new ALClass());

        panel.add(label, BorderLayout.CENTER);
        panel.add(combo, BorderLayout.CENTER);

        img_disp = new JImageDisplay(disp_size, disp_size,BufferedImage.TYPE_INT_RGB);
        img_disp.addMouseListener(new MLClass());
        button.addActionListener(new ALClass());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new java.awt.BorderLayout());
        frame.add(panel,BorderLayout.NORTH);
        frame.add(img_disp, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);

        frame.pack(); frame.setVisible(true);
        frame.setResizable(false);
    }

    private void drawFractal(){
        for (int i = 0; i<disp_size; i++){
            new FractalWorker(i).execute();
        }
    }

    private void enableUI(boolean val){

    }
    private class ALClass implements java.awt.event.ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == combo){
                fg = (FractalGenerator)combo.getSelectedItem();
                }
            fg.getInitialRange(rng);
            drawFractal();
        }

    };

    private class MLClass implements java.awt.event.MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e){
            double x = FractalGenerator.getCoord(rng.getX(),rng.getX()+rng.getWidth(), disp_size,e.getX());
            double y = FractalGenerator.getCoord(rng.getY(),rng.getY()+rng.getHeight(), disp_size,e.getY());
            fg.recenterAndZoomRange(rng, x, y, 0.5);
            drawFractal();
        }

        @Override
        public void mouseExited(MouseEvent e){};
        @Override
        public void mouseEntered(MouseEvent e){};
        @Override
        public void mouseReleased(MouseEvent e){};
        @Override
        public void mousePressed(MouseEvent e){};
    }

    private class FractalWorker extends SwingWorker<Object, Object>{
        private int yCoord;
        private int[] row;

        FractalWorker(int yCoord){
            this.yCoord = yCoord;
        }
        @Override
        protected Object doInBackground(){
            row = new int[disp_size];
            double y = FractalGenerator.getCoord(rng.getY(),rng.getY()+rng.getHeight(), disp_size, yCoord);
            for (int j = 0; j<disp_size; j++){
                double x = FractalGenerator.getCoord(rng.getX(),rng.getX()+rng.getWidth(), disp_size, j);
                int numIters = fg.numIterations(x,y);
                if (numIters == -1) numIters = 0;
                float hue = 0.7f + (float) numIters/200f;
                int rgbColor = Color.HSBtoRGB(hue,1f,1f);
                row[j] = rgbColor;
            }
            return null;
        }
        @Override
        protected void done(){
            for (int j = 0; j<disp_size; j++){
                img_disp.drawPixel(j, yCoord, row[j]);
            }
            img_disp.repaint(0,0,yCoord,disp_size,1);
        }
    }

    public static void main(String[] args){
        FractalExplorer frex = new FractalExplorer(640);
        frex.createAndShowGUI();
        frex.drawFractal();
    }

}
