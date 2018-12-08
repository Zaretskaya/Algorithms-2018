package lesson6;
public class Point{
    int x, y;
    Point() {
        this(0, 0);
    }
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    Point(Point otherPoint) {
        this.x = otherPoint.getX();
        this.y = otherPoint.getY();
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean equals(Point otherPoint) {
        if (otherPoint == this)
            return true;
        if (otherPoint == null)
            return false;
        return this.x == otherPoint.getX() && this.y == otherPoint.getY();
    }
}


