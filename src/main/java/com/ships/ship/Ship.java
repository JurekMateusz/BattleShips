package com.ships.ship;

import java.awt.*;
import java.util.ArrayList;

public class Ship {
    private ArrayList<Point> points;

    public Ship(ArrayList<Point> arrayPoints) {
        points = new ArrayList<>(arrayPoints);
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

}
