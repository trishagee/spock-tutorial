package com.mechanitis.demo.spock;

public class TooFewSidesException extends IllegalArgumentException {
    private final int numberOfSides;

    public TooFewSidesException(String s, int numberOfSides) {
        super(s);
        this.numberOfSides = numberOfSides;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }
}
