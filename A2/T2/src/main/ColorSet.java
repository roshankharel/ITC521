package main;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Random;

public class ColorSet {
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

    private static final Random RANDOM = new Random();

    public static Paint random() {
        int colorIndex = RANDOM.nextInt(HEX_COLORS.length);
        String hex = HEX_COLORS[colorIndex];

        return Color.web(hex);
    }
}
