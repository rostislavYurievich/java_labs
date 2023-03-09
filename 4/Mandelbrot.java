import java.awt.geom.Rectangle2D.Double;

class Mandelbrot extends FractalGenerator{
    @Override
    public int numIterations(double x, double y) {
	// TODO Auto-generated method stub
	return 0;
    }
    @Override
    public void getInitialRange(Double range) {
        range.x = -2;
        range.y = -1.5;
        range.width = range.height = 3;
    }
}
