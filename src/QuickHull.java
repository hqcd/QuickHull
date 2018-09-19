// Course:     	CS4306- section #1
// Student name:  Quinten Whitaker
// Student ID:  	000546718
// Assignment #:   1
// Due Date:   	9/20/2018
// Signature:  	Quinten Whitaker
// Score:  	  	______________


import java.util.ArrayList;


public class QuickHull
{
    static ArrayList<Point> data = new ArrayList<>();
    static ArrayList<Point> hull = new ArrayList<>();

    public static void main(String[] args)
    {
        //Test with predefined data
        System.out.println("Predefined data:");
        getData();
        QuickHull(data);

        //Generate random data
        data.clear();
        hull.clear();

        System.out.println("\nRandomized Data:");
        getRandom();
        QuickHull(data);

    }

    public static void QuickHull(ArrayList<Point> data)
    {
        System.out.println("Input: " + data);

        //Find left and right most points, A & B, and add them to the convex hull
        Point A = getMin(data);
        Point B = getMax(data);

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
        findHull(S1, B, A); //Lower
        findHull(S2, A, B); //Upper

        System.out.println("The hull consists of " + hull);
    }
    public static void findHull(ArrayList<Point> Sk, Point P, Point Q)
    {
        //Find points from Sk that are on the right side of the line from P to Q
        //If no points, return
        boolean hasPoints = false;
        for(int i =0; i<Sk.size();i++)
        {
            if(getPosition(P,Q,Sk.get(i)) < 0)
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
        //Add points to left of C to S1 && points right of C to S2
        ArrayList<Point> S3 = new ArrayList<>();
        ArrayList<Point> S4 = new ArrayList<>();

        for(int i=0; i<Sk.size();i++)
        {
            if(getPosition(P,C,Sk.get(i)) < 0)
            {
                S3.add(Sk.get(i));
            }
            if(getPosition(C,Q,Sk.get(i)) < 0)
            {
                S4.add(Sk.get(i));
            }
        }


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

    public static void getRandom()
    {
        for(int i = 0; i < 15; i++)
        {
            data.add(new Point((int)(Math.random()*20), (int)(Math.random() * 20)));
        }
    }

    public static Point getMin(ArrayList<Point> s)
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

    public static Point getMax(ArrayList<Point> s)
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
        int x1 = (int)A.getX();
        int y1 = (int)A.getY();
        int x2 = (int)B.getX();
        int y2 = (int)B.getY();
        int x = (int)S.getX();
        int y = (int)S.getY();

        int position = (int)((x-x1)*(y2-y1) - (y-y1)*(x2-x1));
        return position;
    }

    public static double findDistance(Point A, Point B, Point S)
    {
        int X1 = (int)A.getX();
        int Y1 = (int)A.getY();
        int X2 = (int)B.getX();
        int Y2 = (int)B.getY();
        int X0 = (int)S.getX();
        int Y0 = (int)S.getY();

        double num = Math.abs(((Y2 - Y1)* X0) - ((X2 - X1) * Y0) + (X2*Y1) - (Y2 * X1));
        double denom = Math.sqrt(Math.pow((Y2-Y1),2) + Math.pow((X2-X1),2));

        return num / denom;
    }
}