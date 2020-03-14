package com.ships.player;

import com.ships.ship.Ship;
import com.ships.input.ReadCord;

import java.awt.Point;
import java.util.ArrayList;

public class HumanPlayer extends Player {
    private int lengthShip = 3;
    private ReadCord reader = new ReadCord();

    public HumanPlayer(int sizeMap) {
        super(sizeMap);
    }

    public Point shotShip() {
        sunkShip = false;
        Point point;
        while (true) {
            System.out.print("Type Cord: ");
            point = reader.readPoint();
            if (point == null) {
                System.out.println("Bad imput :[");
                continue;
            }
            return point;
        }
    }


    public void chooseCordsShips(int NumbersOfShips) {
        int i = 0;
        ArrayList<Point> points;

        printMap();
        while (i < NumbersOfShips) {
            System.out.print(System.lineSeparator() + "Type cords for ship nr " + (i + 1) + " : ");
            points = reader.readPoints();
            if (points == null) {
                System.out.print("Bad imput :[ try something like this:\"A1 B1 C1\" ");
                continue;
            }
            if (points.size() < lengthShip) {
                System.out.print("too short imput, missing " + (lengthShip - points.size()) + " point/s");
                continue;
            }
            if (!isPointsExist(points)) {
                ships.add(new Ship(points));
                i++;
            } else {
                System.out.print("\tYou already typed this field/fields. Type another one :)");
            }
            points.clear();
            addPointsToMap();
            System.out.print("   Your Map:");
            printMap();
        }
    }
}




