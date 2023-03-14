public class Point3d extends Point2d{
    private double zCoord;
    public Point3d(double x, double y, double z){
        super(x,y);
        zCoord = z;
    }
    //конструктор без аргументов
    public Point3d(){
        this(0,0,0);
    }

    //геттер
    public double getZ(){
        return zCoord;
    }
    //сеттер
    public void setZ(double c){
        zCoord = c;
    }
    //метод проверяющих равенство
    @Override
    public boolean equals(Object o){
        Point3d p = (Point3d) o;
        return super.equals(o)&&(this.getZ()==p.getZ()); //можно использовать super вот так!!!
    }
    //считает расстояние до точки
    public double distanceTo(Point3d p){
        return Math.sqrt(
            Math.pow(this.getX()-p.getX(), 2)+
            Math.pow(this.getY()-p.getY(), 2)+
            Math.pow(this.getZ()-p.getZ(), 2)
        );
    }
}
