import java.awt.geom.*; 
import java.util.ArrayList; 
import gpdraw.*; 

public class IrregularPolygon {
    private ArrayList<Point2D.Double> vertices = new ArrayList<>();

    public IrregularPolygon() {}

    public void add(Point2D.Double vertex) {
        vertices.add(vertex);
    }

    public double perimeter() {
        if (vertices.size() < 2) return 0.0; 

        double totalPerimeter = 0.0;
        for (int i = 0; i < vertices.size() - 1; i++) {
            Point2D.Double currentVertex = vertices.get(i);
            Point2D.Double nextVertex = vertices.get(i + 1);
            totalPerimeter += currentVertex.distance(nextVertex);
        }
        totalPerimeter += vertices.get(vertices.size() - 1).distance(vertices.get(0));
        return totalPerimeter;
    }

    public double area() {
        if (vertices.size() < 3) return 0.0; 

        double sum1 = 0.0;
        double sum2 = 0.0;
        for (int i = 0; i < vertices.size() - 1; i++) {
            Point2D.Double currentVertex = vertices.get(i);
            Point2D.Double nextVertex = vertices.get(i + 1);
            sum1 += currentVertex.x * nextVertex.y;
            sum2 += currentVertex.y * nextVertex.x;
        }
        sum1 += vertices.get(vertices.size() - 1).x * vertices.get(0).y;
        sum2 += vertices.get(vertices.size() - 1).y * vertices.get(0).x;

        double polygonArea = 0.5 * Math.abs(sum1 - sum2);
        return polygonArea;
    }

    public void draw() {
        try {
            if (vertices.size() < 2) return; 

            DrawingTool pen = new DrawingTool(new SketchPad(500, 500));
            Point2D.Double firstVertex = vertices.get(0);
            pen.move(firstVertex.getX(), firstVertex.getY());
            pen.down();

            for (int i = 1; i < vertices.size(); i++) {
                Point2D.Double nextVertex = vertices.get(i);
                pen.move(nextVertex.getX(), nextVertex.getY());
            }
            pen.move(firstVertex.getX(), firstVertex.getY());
            pen.up();
        } catch (java.awt.HeadlessException e) {
            System.out.println("Exception: No graphics support available.");
        }
    }
}
