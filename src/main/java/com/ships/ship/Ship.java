package com.ships.ship;

import java.awt.*;
import java.util.ArrayList;

public class Ship {
    private ArrayList<Point> points;
    private boolean shottedStatus;

    public void setShottedStatus(boolean status) {
        shottedStatus = status;
    }

    public boolean getShottedStatus() {
        return shottedStatus;
    }

    public Ship(ArrayList<Point> arrayPoints) {
        points = new ArrayList<>(arrayPoints);
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

}
