import java.util.Scanner;

public class Lab1{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        double x, y, z;
        System.out.print("Введите координаты первой точки.\n>>> ");
        x = in.nextDouble();
        y = in.nextDouble();
        z = in.nextDouble();
        Point3d a = new Point3d(x,y,z);
        System.out.print("Введите координаты второй точки.\n>>> ");
        x = in.nextDouble();
        y = in.nextDouble();
        z = in.nextDouble();
        Point3d b = new Point3d(x,y,z);
        System.out.print("Введите координаты третьей точки.\n>>> ");
        x = in.nextDouble();
        y = in.nextDouble();
        z = in.nextDouble();
        Point3d c = new Point3d(x,y,z);
        System.out.printf("Площадь треугольника: %.5f %n",computeArea(a,b,c));

    }
    //считает площадь по ф. герона
    public static double computeArea(Point3d a, Point3d b, Point3d c){
        double l1 = a.distanceTo(b);
        double l2 = c.distanceTo(b);
        double l3 = a.distanceTo(c);
        double p = 0.5*(l1+l2+l3);
        return Math.sqrt(p*(p-l1)*(p-l2)*(p-l3));
    }
}
