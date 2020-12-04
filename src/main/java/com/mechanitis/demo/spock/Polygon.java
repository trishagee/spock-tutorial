package com.mechanitis.demo.spock;

public class Polygon {
    private final int numberOfSides;
    private Renderer renderer;

    public Polygon(int numberOfSides) {
        if (numberOfSides <= 2) {
            throw new TooFewSidesException("You can't have fewer than 3 sides for a polygon", numberOfSides);
        }
        this.numberOfSides = numberOfSides;
    }

    public Polygon(int numberOfSides, Renderer renderer) {
        this.numberOfSides = numberOfSides;
        this.renderer = renderer;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }

    public void draw() {
        for (int i = 0; i < numberOfSides; i++) {
            renderer.drawLine();
        }
    }
}
