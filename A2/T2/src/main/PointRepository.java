package main;

import java.util.ArrayList;

/**
 * PointRepository
 * <p>
 * This class instance is used to keep the list of Point objects and
 * provides necessary methods to get point their index number
 *
 * @author Roshan Kharel
 */
public class PointRepository {
    /**
     * Holds reference to self instance
     */
    private static PointRepository pointRepository;
    /**
     * Holds list of points
     */
    protected ArrayList<Point> points;

    /**
     * private class constructor, follows singleton pattern
     */
    private PointRepository() {
        points = new ArrayList<>();
    }

    /**
     * The method to let access to same instance object of this class
     *
     * @return PointRepository instance
     */
    public static PointRepository getInstance() {
        // check if self object exists
        if (pointRepository == null)
            // create new instance
            pointRepository = new PointRepository();

        return pointRepository;
    }

    /**
     * The method to add list of points to the repository
     *
     * @param points ArrayList of Point objects
     */
    public void addPoints(ArrayList<Point> points) {
        for (Point point : points) {
            addPoint(point);
        }
    }

    /**
     * The method to add single point to the repository
     *
     * @param point Point object
     */
    private void addPoint(Point point) {
        points.add(point);
    }

    /**
     * The method to get a point from the list based on
     * the index number
     *
     * @param index index of a point in the list
     */
    public Point getPoint(int index) {
        return points.get(index);
    }

    /**
     * The method to get all the points
     *
     * @return ArrayList of Point objects
     */
    public ArrayList<Point> getPoints() {
        return points;
    }
}
