import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

class RandomSet{
    long seed = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
    Random generator;
    List<Point2D> set = new ArrayList<Point2D>();

    RandomSet(int size,int length){
        generator = new Random(seed);
        for (int i=0; i<length; i++)
        {
            int x = generator.nextInt(size-size/10)+size/20;
            int y = generator.nextInt(size-size/10)+size/20;
            set.add(new Point2D((double)x,(double)y));
        }
    }

    List<Point2D> getPoints(){
        return set;
    }

    static Point2D getRandomPoint(List<Point2D> set){
        return set.get(new Random(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())).nextInt(set.size()));
    }

}
