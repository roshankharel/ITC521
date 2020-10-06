package main.entities;

import java.util.ArrayList;
import java.util.HashMap;

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

    public static State make(String stateName) {
        return cache.get(stateName);
    }

    public static String[] getKeys() {
        return keys.toArray(new String[0]);
    }

    static {
        for (State state : values()) {
            keys.add(state.toString());
            cache.put(state.toString(), state);
        }
    }
}
