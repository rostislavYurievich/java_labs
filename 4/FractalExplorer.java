import java.awt.geom.Rectangle2D;
import java.awt.BorderLayout;

class FractalExplorer {
    //TODO Refactor screen_size to disp_size
    private int screen_size;
    //TODO Refactor jid to img_disp
    private JImageDisplay jid;
    private FractalGenerator fg;
    private Rectangle2D.Double rng;

    FractalExplorer(int screen_size){
        this.screen_size = screen_size;
        rng = new Rectangle2d.Double(0,0,screen_size,screen_size);
        fg = new Mandelbrot();
        fg.getInitialRange(rng);
    }

    private void createAndShowGUI(){

        JFrame frame = new JFrame("Генератор фракталов");
        JButton button = new JButton("Сброс");

        jid = new JImageDisplay(screen_size, screen_size);
        jid.addMouseListener(new ActionHandler());

        frame.setLayout(new java.awt.BorderLayout());
        frame.add(jid, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);
        //TODO Add corresponding window header
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack(); frame.setVisible(true); frame.setResizable(false);
    }

    private void drawFractal(){
        for (int i = 0; i<screen_size; i++){
            for (int j = 0; j<screen_size; j++){

                double x = FractalGenerator.getCoord(rng.x,rng.x+rng.width, screen_size,j);
                double y = FractalGenerator.getCoord(rng.y,rng.y+rng.height, screen_size,i);
                numIters = numIterations(x,y);
                if (numIters == -1) numIters = 0;
                float hue = 0.7f + (float) numIters/200f;
                int rgbColor = Color.HSBtoRGB(hue,1f,1f);
                jid.drawPixel(x,y,rgbColor);

            }
        }
    }

}
