package com.github.mushroommif.morespeeds;

public class Config {
    private final int[] speeds;

    public Config(int[] speeds) {
        this.speeds = speeds;
    }

    public static Config createDefault() {
        return new Config(new int[]{
                1000, 500, 250, 188, 125, 105, 85, 65, 45, 25
        });
    }

    public int[] getSpeeds() {
        return speeds;
    }

    public void validate() {
        if (speeds.length < 1) {
            throw new IllegalStateException("At least one speed must be provided in more speeds mod config");
        }

        for (int value : speeds) {
            if (value <= 1) {
                throw new IllegalStateException("All speed values should be positive in more speeds mod config");
            }
        }

        if (speeds.length < 2) {
            return;
        }

        int previous = speeds[0];
        for (int i = 1; i < speeds.length; i++) {
            int value = speeds[i];
            if (previous <= value) {
                throw new IllegalStateException("Speeds must be specified in descending order in more speeds mod config");
            }

            previous = value;
        }
    }
}
