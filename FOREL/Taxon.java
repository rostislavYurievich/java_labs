import java.util.ArrayList;
import java.util.List;

class Taxon {
    List<Point2D> points = new ArrayList<Point2D>();
    Point2D centerOfMass;
    double radius;


    Taxon(Point2D centerPoint, List<Point2D> data, double radius){
        centerOfMass = centerPoint;
        this.radius = radius;
        Point2D newCOM = new Point2D(-1,-1);
        int stationary = 0;
        while (stationary<2){
            points = new ArrayList<Point2D>();
            if (centerOfMass.equals(newCOM)){stationary++;}
            sievePoints(data);
            if (!newCOM.equals(new Point2D(-1,-1)))
                centerOfMass = newCOM;
            newCOM = calculateCentroid();
        }
        this.radius = maxDistance();
    }

    private void sievePoints(List<Point2D> data){
        for (Point2D p: data){
            if (centerOfMass.distanceTo(p)<radius){
                points.add(p);
            }
        }
    }

    private double maxDistance(){
        double max = 0;
        for (Point2D p: points){
            if (centerOfMass.distanceTo(p)>max) {max = centerOfMass.distanceTo(p);}
        }
        return max;
    }

    private Point2D calculateCentroid() {
        double x = 0.;
        double y = 0.;
        int pointCount = points.size();
        for (int i = 0;i < pointCount;i++){
            final Point2D point = points.get(i);
            x += point.getX();
            y += point.getY();
        }
        x = x/pointCount;
        y = y/pointCount;
        return new Point2D(x, y);
    }


    public List<Point2D> getPoints(){
        return points;
    }
    public Point2D getCOM() {return centerOfMass;}


}
