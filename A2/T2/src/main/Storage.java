package main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {
    private static final String NUMBER_REGEX = "([0-9]*[.])?[0-9]+";
    private static final String LINE_REGEX = "x=" + NUMBER_REGEX + "; y=" + NUMBER_REGEX;
    public static final Path STORAGE_FILE = Paths.get("points.txt");

    private static Storage storage;

    private Storage() {}

    public static Storage getInstance() {
        if(storage == null)
            storage = new Storage();

        return storage;
    }

    public ArrayList<Point> loadPoints() {
        ArrayList<Point> points = new ArrayList<>();

        try {
            File file = makeOrGetFile();
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            while (line != null) {

                Point point = makePointFrom(line);

                if (point != null)
                    points.add(point);

                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return points;
    }

    public void savePoints(ArrayList<Point> points) {
        try {
            FileOutputStream fileOutputStream;
            BufferedWriter writer;
            File file;

            file = makeOrGetFile();
            fileOutputStream = new FileOutputStream(file);
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

            for (Point point : points) {
                writer.write(point.toString());
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static File makeOrGetFile() throws IOException {
        if (!Files.exists(STORAGE_FILE)) {
            Files.createFile(STORAGE_FILE);
        }

        return STORAGE_FILE.toFile();
    }

    protected static Point makePointFrom(String line) {
        if (!line.matches(LINE_REGEX)) return null; // line is malformed

        Pattern p = Pattern.compile(NUMBER_REGEX);
        Matcher m = p.matcher(line);
        double[] xy = new double[2];
        int idx = 0;

        while (m.find()) {
            xy[idx++] = Double.parseDouble(m.group());
        }

        return new Point(xy[0], xy[1]);
    }
}
