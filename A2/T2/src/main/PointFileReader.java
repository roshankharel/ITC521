package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * PointFileReader
 * The PointFileReader class simply provides abstractions to
 * reads point coordinates from a file and parses them to
 * List of Point class objects
 *
 * @author Roshan Kharel
 */
public class PointFileReader {

    /**
     * Holds reference to the path of the points file
     */
    private static final Path STORAGE_FILE = Paths.get("points.txt");

    /**
     * Holds reference to the instance object of this class
     */
    private static PointFileReader pointFileReader;

    /**
     * Constructor method
     * private visibility of the constructor makes object creation
     * prohibited from external source
     */
    private PointFileReader() {
    }

    /**
     * The method to let access to same instance object of this class
     *
     * @return PointFileReader instance
     */
    public static PointFileReader getInstance() {
        // check if this class has been constructed before
        if (pointFileReader == null)
            // create a new instance and assign
            // the object reference to pointFileReader static variable
            pointFileReader = new PointFileReader();

        // return the PointFileReader instance
        return pointFileReader;
    }

    /**
     * The method to read points file and make list of Point objects
     *
     * @return ArrayList of Point objects
     */
    public ArrayList<Point> readPoints() {
        // create an ArrayList to hold all the points
        ArrayList<Point> points = new ArrayList<>();

        try {
            // get the points file
            File file = createOrGetFile();
            // create the file reader for the points file
            FileReader fileReader = new FileReader(file);
            // Creates a buffering character-input stream that uses a default-sized * input buffer.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // read the first line
            String line = bufferedReader.readLine();

            // read file until end of file is reached
            while (line != null) {
                // convert line to Point object if line is valid
                Point point = getPointFromLine(line);

                if (point != null)
                    // line is valid
                    // add point object to points list
                    points.add(point);

                // read next line
                line = bufferedReader.readLine();
            }

            // file read completed
            // close the readers to release the resource(s)
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return points; // return the list of points
    }

    /**
     * The method gets the points file from the file system
     *
     * @return File points file
     * @throws IOException if points file does not exist
     *                     or unable to determine if file exists
     */
    protected File createOrGetFile() throws IOException {
        // check if file exists
        if (!Files.exists(STORAGE_FILE)) {
            // create new file if points file does not exists
            Files.createFile(STORAGE_FILE);
        }

        return STORAGE_FILE.toFile();
    }

    /**
     * The method creates Point object from the supplied string
     * if the supplied string is in format x=Double; y=Double
     * e.g. x=24.67; y=57.33
     *
     * @param line a line from points file
     * @return Point point object
     */
    protected Point getPointFromLine(String line) {
        // split the line by semicolon(;)
        String[] lineSplitXY = line.split(";");

        // check if split is exactly of length of 2
        if (lineSplitXY.length != 2) {
            // line is malformed
            return null;
        }

        // again split using equal(=) character to extract the x value
        String[] xLabelValuePair = lineSplitXY[0].split("=");
        // similarly split using equal(=) character to extract the y value
        String[] yLabelValuePair = lineSplitXY[1].split("=");

        // check if above splits are of exact length 2
        if (xLabelValuePair.length != 2 || yLabelValuePair.length != 2) {
            // line is malformed
            return null;
        }

        double x, y;

        try {
            // try parsing x and y value to a double type
            x = Double.parseDouble(xLabelValuePair[1]);
            y = Double.parseDouble(yLabelValuePair[1]);
        } catch (NumberFormatException e) {
            // line is malformed
            return null;
        }

        // line is valid and in the desired format
        // create a new point and return it
        return new Point(x, y);
    }
}
