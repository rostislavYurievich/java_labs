import java.awt.geom.Rectangle2D;

class BurningShip extends FractalGenerator{

    public static final int MAX_ITERATIONS = 2000;

    @Override
    public int numIterations(double x, double y) {
        double temp, re = x, im = y;
        int i;
        for (i = 0;(i < MAX_ITERATIONS)&&!(re*re+im*im>4); i++){
            temp = re*re-im*im+x;
            im = Math.abs(2 * re * im) + y;;
            re = temp;
        }
        if (i>=MAX_ITERATIONS) return -1;

        return i;
    }
    @Override
    public void getInitialRange(Rectangle2D.Double range) {
        range.x = -2;
        range.y = -2.5;
        range.width = range.height = 4;
    }
}


