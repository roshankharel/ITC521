package main;

import java.util.ArrayList;

public class Repository {
    protected ArrayList<Point> points;
    private static Repository repository;

    private Repository() {
        points = new ArrayList<>();
    }

    public static Repository getInstance() {
        if(repository == null)
            repository = new Repository();

        return repository;
    }

    public void addPoints(ArrayList<Point> points) {
        for (Point point : points) {
            addPoint(point);
        }
    }

    private void addPoint(Point point) {
        points.add(point);
    }

    public Point getPoint(int index) {
        return points.get(index);
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public static void main(String[] args) {
        Window.launch(args);
    }
}
