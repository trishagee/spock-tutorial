package com.mechanitis.demo.spock;

public class Polygon {
    private final int numberOfSides;

    public Polygon(int numberOfSides) {
        if (numberOfSides <= 2) {
            throw new TooFewSidesException("You can't have fewer than 3 sides for a polygon", numberOfSides);
        }
        this.numberOfSides = numberOfSides;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }
}
