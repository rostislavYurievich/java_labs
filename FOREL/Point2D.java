/*
двумерный класс точки.
*/
public class Point2D {
    /** координата X **/
    private double xCoord;
    /** координата Y **/
    private double yCoord;
    /** Конструктор инициализации **/
    public Point2D ( double x, double y) {
        xCoord = x;
        yCoord = y;
    }
    /** Конструктор по умолчанию. **/
    public Point2D () {
        //Вызовите конструктор с двумя параметрами и определите источник.
        this(0, 0);
    }
        /** Возвращение координаты X **/
    public double getX () {
        return xCoord;
    }
    /** Возвращение координаты Y **/
    public double getY () {
        return yCoord;
    }
    /** Установка значения координаты X. **/
    public void setX ( double val) {
        xCoord = val;
    }
    /** Установка значения координаты Y. **/
    public void setY ( double val) {
        yCoord = val;
    }
    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        Point2D p = (Point2D) o;
        return (this.getY()==p.getY())&&(this.getX()==p.getX());
    }
    public double distanceTo(Point2D p){
        return Math.sqrt((this.getX()-p.getX())*(this.getX()-p.getX())+(this.getY()-p.getY())*(this.getY()-p.getY()));
    }
}
