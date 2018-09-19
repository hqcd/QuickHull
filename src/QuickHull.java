import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class QuickHull
{
    static ArrayList<Point> data = new ArrayList<>();
    static ArrayList<Point> hull = new ArrayList<>();

    public static void main(String[] args)
    {
        getData();
        //Find left and right most points, A & B, and add them to the convex hull
        Point A = getLeft(data);
        Point B = getRight(data);

        hull.add(A);
        hull.add(B);
        //Segment AB divides the remaining points into 2 groups, S1 and S2
        //If the point is on the left side of the line, add it to S1
        //If it is on the right side, add it to S2
        ArrayList<Point> S1 = new ArrayList<>();
        ArrayList<Point> S2 = new ArrayList<>();

        for(int i = 0; i < data.size();i++)
        {
            int position = getPosition(A,B,data.get(i));
            if(position > 0)
            {
                S1.add(data.get(i));
            }
            else if(position < 0) {
                S2.add(data.get(i));
            }
        }
        System.out.println("S1: " + S1);
        System.out.println("S2: " + S2);

        findHull(S1, A, B);
        findHull(S2, B, A);

        System.out.println("The hull consists of " + hull);

    }
    public static void findHull(ArrayList<Point> Sk, Point P, Point Q)
    {
        //Find points from Sk that are on the right side of the line from P to Q
        //If no points, return
        boolean hasPoints = false;
        for(int i =0; i<Sk.size();i++)
        {
            if(getPosition(P,Q,Sk.get(i)) > 0)
            {
                hasPoints = true;
            }
        }
        if(!hasPoints)
        {
            return;
        }
        //From the set of points, find the farthest point C
        double distance = 0;
        Point C = new Point();
        for(int i = 0; i < Sk.size();i++)
        {
            double d = findDistance(P, Q, Sk.get(i));
            if(d > distance)
            {
                distance = d;
                C = Sk.get(i);
            }
        }
        //Add C to the convex hull
        hull.add(C);
        System.out.println("Point added to hull: " + C);
        //Add points to left of C to S1 && points right of C to S2
        ArrayList<Point> S3 = new ArrayList<>();
        ArrayList<Point> S4 = new ArrayList<>();
        for(int i=0; i<Sk.size();i++)
        {
            if(getPosition(P,Q,C) > 0)
            {
                S3.add(Sk.get(i));
            }
            else if(getPosition(P,Q,C) < 0)
            {
                S4.add(Sk.get(i));
            }
        }
        System.out.println("S3" + S3);
        System.out.println("S4" + S4);

        findHull(S3, P, C);
        findHull(S4, C, Q);

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

    public static Point getLeft(ArrayList<Point> s)
    {
        Point min = s.get(0);
        for(int i=0;i<s.size();i++)
        {
            if(s.get(i).getX() < min.getX())
            {
                min = s.get(i);
            }
        }

        return min;
    }

    public static Point getRight(ArrayList<Point> s)
    {
        Point max = s.get(0);
        for(int i=0;i<s.size();i++)
        {
            if(s.get(i).getX() > max.getX())
            {
                max = s.get(i);
            }
        }

        return max;
    }

    public static int getPosition(Point A, Point B, Point S)
    {
        int position = (int)((B.getX() - A.getX()) * (S.getY() - A.getY()) - (B.getY() - A.getY()) * (S.getX() - A.getX()));
        return position;
    }

    public static double findDistance(Point A, Point B, Point S)
    {
        int Ax = (int)A.getX();
        int Ay = (int)A.getY();
        int Bx = (int)B.getX();
        int By = (int)B.getY();
        int Sx = (int)S.getX();
        int Sy = (int)S.getY();

        double d = Math.abs(((Bx - Ax)*(Ay - Sy) - (Ax -Sx)*(By -Ay))/Math.sqrt(Math.pow(Bx - Ax,2) + Math.pow(By - Ay, 2)));
        return d;
    }
}