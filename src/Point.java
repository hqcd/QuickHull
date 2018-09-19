//Class for defining a point on the XY plane

class Point{

    int x;
    int y;

    public Point()
    {

    }

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public String toString()
    {
        String s = ("(" + this.x + "," + this.y + ")");
        return s;
    }
}
