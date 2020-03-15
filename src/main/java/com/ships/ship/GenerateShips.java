package com.ships.ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.function.Predicate;


public class GenerateShips {
    private final int sizeMap;
    private final Random generator;

    public GenerateShips(int sizeMap) {
        this.sizeMap = sizeMap;
        generator = new Random(System.currentTimeMillis());
    }

    public void addShipToList(ArrayList<Ship> ships, int lengthShip) {
        int[] cordsInTab = new int[4];
        while (true) {
            cordsInTab[0] = generator.nextInt(sizeMap); // x
            cordsInTab[1] = generator.nextInt(sizeMap); //y
            cordsInTab[2] = generator.nextInt(4); //direction
            cordsInTab[3] = lengthShip;

            int[] goodCords = didCordsCanBeIfNotChangeDirect(cordsInTab);
            if (goodCords[2] == -1) {
                continue;
            }
            ArrayList<Point> Points = makePoints(goodCords);

            if (!isPointsExist(ships, Points)) {
                continue;
            }
            ships.add(new Ship(Points));
            return;
        }
    }

    private int[] didCordsCanBeIfNotChangeDirect(int[] cordsInTab) {
        switch (cordsInTab[2]) {
            case 0:
                if (cordsInTab[1] + cordsInTab[3] - 1 >= sizeMap) {
                    cordsInTab[2] = 1;
                }
                if (isAlsoBadDirection(cordsInTab)) {
                    cordsInTab[2] = -1;
                }
                break;
            case 1:
                if (cordsInTab[1] - cordsInTab[3] + 1 < 0) {
                    cordsInTab[2] = 0;
                }
                if (isAlsoBadDirection(cordsInTab)) {
                    cordsInTab[2] = -1;
                }
                break;
            case 2:
                if (cordsInTab[0] + cordsInTab[3] - 1 >= sizeMap) {
                    cordsInTab[2] = 3;
                }
                if (isAlsoBadDirection(cordsInTab)) {
                    cordsInTab[2] = -1;
                }
                break;
            case 3:
                if (cordsInTab[0] - cordsInTab[3] + 1 < 0) {
                    cordsInTab[2] = 2;
                }
                if (isAlsoBadDirection(cordsInTab)) {
                    cordsInTab[2] = -1;
                }
                break;
        }
        return cordsInTab;
    }

    private ArrayList<Point> makePoints(int[] tab) {
        ArrayList<Point> result = new ArrayList<>();
        for (int i = 0; i < tab[3]; i++) {
            result.add(new Point(tab[0], tab[1]));
            changeCordByDirect(tab);
        }
        return result;
    }

    private void changeCordByDirect(int[] bufTab) {
        switch (bufTab[2]) {
            case 0:
                bufTab[1] += 1;
                break;
            case 1:
                bufTab[1] -= 1;
                break;
            case 2:
                bufTab[0] += 1;
                break;
            case 3:
                bufTab[0] -= 1;
                break;
        }
    }

    private boolean isPointsExist(ArrayList<Ship> allShips, ArrayList<Point> pointsToCheck) {
        for (Ship ship : allShips) {
            ArrayList<Point> pointsOfShip = ship.getPoints();
            if (!Collections.disjoint(pointsOfShip, pointsToCheck)) {
                return true;
            }
        }
        return false;
    }

    private boolean isAlsoBadDirection(int[] cordsInTab) {
        switch (cordsInTab[2]) {
            case 0:
                if (cordsInTab[1] + cordsInTab[3] - 1 >= sizeMap) {
                    return true;
                }
            case 1:
                if (cordsInTab[1] - cordsInTab[3] + 1 < 0) {
                    return true;
                }
            case 2:
                if (cordsInTab[0] + cordsInTab[3] - 1 >= sizeMap) {
                    return true;
                }
            case 3:
                if (cordsInTab[0] - cordsInTab[3] + 1 < 0) {
                    return true;
                }
            default:
                return false;
        }
    }
}
