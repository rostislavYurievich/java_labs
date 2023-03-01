public class Point3d extends Point2d{
    private double zCoord;
    public Point3d(double x, double y, double z){
        super(x,y);
        zCoord = z;
    }
    //конструктор по умолчанию
    public Point3d(){
        super();
        zCoord = 0;
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
    public boolean equals(Point3d p){
        return (
        (this.getX()==p.getX())&&
        (this.getY()==p.getY())&&
        (this.getZ()==p.getZ())
        );
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
