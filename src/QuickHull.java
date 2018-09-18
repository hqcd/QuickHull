import java.awt.*;
import java.util.ArrayList;


public class QuickHull
{
    static ArrayList<Point> data = new ArrayList<>();
    static ArrayList<Point> hull = new ArrayList<>();
    public static void main(String[] args)
    {
        getData();
        getFirstPoints();
        System.out.println(hull);

    }

    public static void getData()
    {
        data.add(new Point(1,6));
        data.add(new Point(4,15));
        data.add(new Point(7,7));
        data.add(new Point(10,13));
        data.add(new Point(11,6));
        data.add(new Point(11,18));
        data.add(new Point(11,21));
        data.add(new Point(12,10));
        data.add(new Point(15,18));
        data.add(new Point(16,6));
        data.add(new Point(18,3));
        data.add(new Point(18,12));
        data.add(new Point(19,15));
        data.add(new Point(22,19));
    }

    public static void getFirstPoints()
    {
        Point max = data.get(0);
        Point min = data.get(0);
        for(int i = 0; i < data.size(); i++)
        {
            if(data.get(i).getX() < max.getX())
            {
                max = data.get(i);
            }
        }

        for(int i = 0; i < data.size(); i++)
        {
            if(data.get(i).getX() > max.getX())
            {
                max = data.get(i);
            }
        }

        hull.add(max);
        hull.add(min);
    }

    public static double getDistance(Point a, Point b, Point c)
    {

    }
}