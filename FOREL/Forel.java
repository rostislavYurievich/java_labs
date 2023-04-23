import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import java.awt.BorderLayout;
class Forel{

    List<Point2D> init_data = new ArrayList<Point2D>();
    List<Point2D> data = new ArrayList<Point2D>();
    List<Taxon> taxons = new ArrayList<Taxon>();
    JSpinner spinR;
    JSpinner spinN;
    private JImageDisplay img_disp;
    private int size;
    private int radius;
    private int length;
    
    Forel(int size){
        this.size = size;
        init_data = new RandomSet(size,(int)size/4).getPoints();
        radius = (int)size/3;
        length = 10;
    }

    private void createAndShowGUI(){

        JFrame frame = new JFrame("FOREL");

        JPanel btmPanel = new JPanel();
        JLabel labelN = new JLabel("N:");
        JLabel labelR = new JLabel("R:");
        spinR = new JSpinner(new SpinnerNumberModel(5,1,size,1));
        spinN = new JSpinner(new SpinnerNumberModel(20,1,size*2,1));
        ALClass al = new ALClass();

        JButton resetButton = new JButton("Reset");
        JButton runButton = new JButton("OK");
        JButton stepButton = new JButton("Step");

        resetButton.setActionCommand("reset");
        resetButton.addActionListener(al);
        runButton.setActionCommand("run");
        runButton.addActionListener(al);
        stepButton.setActionCommand("step");
        stepButton.addActionListener(al);

        btmPanel.add(labelN, BorderLayout.CENTER);
        btmPanel.add(spinN, BorderLayout.CENTER);
        btmPanel.add(resetButton, BorderLayout.CENTER);

        btmPanel.add(labelR, BorderLayout.CENTER);
        btmPanel.add(spinR, BorderLayout.CENTER);      
        btmPanel.add(stepButton, BorderLayout.CENTER);
        btmPanel.add(runButton, BorderLayout.CENTER);

        img_disp = new JImageDisplay(size*10, size*10,BufferedImage.TYPE_INT_RGB);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new java.awt.BorderLayout());
        frame.add(img_disp, BorderLayout.CENTER);
        frame.add(btmPanel, BorderLayout.SOUTH);

        frame.pack(); frame.setVisible(true);
        frame.setResizable(false);
        img_disp.clearImage();
    }

    private void run(){
        if (data.size()>0){
            Point2D centerPoint = RandomSet.getRandomPoint(data);
            Taxon currentTaxon = new Taxon(centerPoint,data,radius);
            taxons.add(currentTaxon);
            data.removeAll(currentTaxon.getPoints());
        } 
        else{
            taxons = new ArrayList<Taxon>();
            data.addAll(init_data);
        }
    }

    private void drawTaxons(){
        float colorStep = 1f/taxons.size();
        for (Point2D p: data){
            img_disp.drawCircle((int)(p.getX()*10),(int)(p.getY()*10),size/20,0,true);
        }
        for (Taxon taxon: taxons){
            float hue = taxons.indexOf(taxon)*colorStep;
            Point2D COM = taxon.getCOM();
            int color = Color.HSBtoRGB(hue,0.7f,1f);
            img_disp.drawCircle((int)(COM.getX()*10),(int)(COM.getY()*10),(int)(taxon.radius*10),color,false);
            
        }
        for (Taxon taxon: taxons){
            float hue = taxons.indexOf(taxon)*colorStep;
            Point2D COM = taxon.getCOM();
            int color = Color.HSBtoRGB(hue,1f,1f);
            for (Point2D p: taxon.getPoints()){
                img_disp.drawCircle((int)(p.getX()*10),(int)(p.getY()*10),size/20,color,true);
            }
            color = Color.HSBtoRGB(hue,0.5f,1f);
            img_disp.drawCircle((int)(COM.getX()*10),(int)(COM.getY()*10),size/30,color,true);
            
        }
        img_disp.repaint();
        
    }

    

    private class ALClass implements java.awt.event.ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            taxons = new ArrayList<Taxon>();
            if (e.getActionCommand().equals("run")) {
                radius = (int)spinR.getValue();
                data.addAll(init_data);
                while (data.size()>0){
                    run();
                }
            }
            if (e.getActionCommand().equals("reset")) {
                data.removeAll(data);
                length = (int)spinN.getValue();
                init_data = new RandomSet(size,length).getPoints();
                data.addAll(init_data);
            }
            if (e.getActionCommand().equals("step")) {
                run();
            }
            img_disp.clearImage();
            
            drawTaxons();
        }

    };

    public static void main(String[] args){
        Forel f = new Forel(80);
        f.createAndShowGUI();
    }

}


