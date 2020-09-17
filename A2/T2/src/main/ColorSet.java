package main;

import javafx.scene.paint.Color;

import java.util.Random;

/**
 * ColorSet
 * The ColorSet class provides method to randomly
 * generate color from the list of colors
 *
 * @author Roshan Kharel
 */
public class ColorSet {
    /**
     * Holds predefined array of HEX color values
     */
    private static final String[] HEX_COLORS = {
            "#F44336",
            "#E91E63",
            "#9C27B0",
            "#673AB7",
            "#3F51B5",
            "#2196F3",
            "#2196F3",
            "#03A9F4",
            "#00BCD4",
            "#009688",
            "#4CAF50",
            "#8BC34A",
            "#CDDC39",
            "#FFEB3B",
            "#FFC107",
            "#FF9800",
            "#FF5722"
    };

    /**
     * Holds random number generator
     */
    private static final Random RANDOM = new Random();

    /**
     * The method allows to get random color from predetermined array of colors
     *
     * @return Color
     */
    public static Color getRandomColor() {

        // generate a random integer between 0 to the length of the colors array
        int colorIndex = RANDOM.nextInt(HEX_COLORS.length);

        // get the random hex color for the array
        String hex = HEX_COLORS[colorIndex];

        // parse to Color object and return it
        return Color.web(hex);
    }
}
