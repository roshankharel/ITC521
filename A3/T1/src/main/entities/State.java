package main.entities;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * State
 * Enum values of available staff geographical Australian states
 *
 * @author Roshan Kharel
 */
public enum State {
    ACT("ACT"),
    NSW("NSW"),
    NT("NT"),
    QLD("QLD"),
    SA("SA"),
    TAS("TAS"),
    VIC("VIC"),
    WA("WA");

    private final String value;
    private static final HashMap<String, State> cache = new HashMap<>();
    private static final ArrayList<String> keys = new ArrayList<>();

    State(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * returns enum State from a string
     */
    public static State make(String stateName) {
        return cache.get(stateName);
    }

    /**
     * Gets all the keys as an array of strings of the State enum
     */
    public static String[] getKeys() {
        return keys.toArray(new String[0]);
    }

    /**
     * It is run statically without initialization to create HashMap of String state and State enum
     */
    static {
        for (State state : values()) {
            keys.add(state.toString());
            cache.put(state.toString(), state);
        }
    }
}
