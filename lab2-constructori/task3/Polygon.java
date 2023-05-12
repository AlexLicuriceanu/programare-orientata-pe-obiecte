package task3;

public class Polygon {
    private int nrCorners;
    private Point[] polygon;

    public Polygon(int n) {
        nrCorners = n;
        polygon = new Point[n];
    }

    public Polygon(float[] points) {
        nrCorners = points.length/2;
        polygon = new Point[points.length/2];

        int k = 0;
        for(int i=0; i<points.length; i+=2) {
            polygon[k++] = new Point(points[i], points[i+1]);
        }
    }

    public void showPolygon() {
        for (int i = 0; i < nrCorners; i++) {
            polygon[i].showPoint();
        }
    }
}
