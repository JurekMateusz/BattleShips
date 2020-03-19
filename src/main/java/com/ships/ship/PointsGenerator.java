package com.ships.ship;

import com.ships.input.ArrayPoints;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class PointsGenerator {
    private Random generator;
    private final int sizeBoard;

    private HashSet<Point> allGeneratedPoints;
    private ArrayList<Point> hitPoints;

    private int functionCallNumber = 0;
    private int[] oddNumbers = {1, 3, 5,7,9};
    private int[] evenNumbers = {0, 2, 4, 6,8};

    private Point result;

    private int lastChoosenDirection;
    private boolean firstTimeComeToSecondStage = true;
    private boolean[] helpDirectionShot = {true, true, true, true};

    private LinkedList<Point>[] allGeneratedPointsInSecondStage = new LinkedList[4];

    public PointsGenerator(int sizeBoard) {
        this.sizeBoard = sizeBoard;
        generator = new Random(System.currentTimeMillis());
        allGeneratedPoints = new HashSet<>();
        hitPoints = new ArrayList<>();
    }

    public Point generatePoint(boolean flagShottedLastTime) {
        if (!flagShottedLastTime && !hitPoints.isEmpty()) {
            hitPoints.remove(hitPoints.size() - 1);
        }
        if (!hitPoints.isEmpty()) {
            result = choosePointSecondStage(flagShottedLastTime);
        } else {
            result = choosePointFirstStage();
        }
        hitPoints.add(result);
        allGeneratedPoints.add(result);
        return result;
    }

    public void resetDependency() {
        hitPoints.clear();
        firstTimeComeToSecondStage = true;
        setAllDirectionsTrue();
        clearTableOfGeneratePoints();
    }

    private Point choosePointFirstStage() {
        result = new Point();
        do {
//            result.x =generator.nextInt(sizeBoard);
//            result.y =generator.nextInt(sizeBoard); //TODO
            if ((++functionCallNumber % 2) == 0) {
                result.x = evenNumbers[generator.nextInt(evenNumbers.length)];
                result.y = oddNumbers[generator.nextInt(oddNumbers.length)];
            } else {
                result.x = oddNumbers[generator.nextInt(oddNumbers.length)];
                result.y = evenNumbers[generator.nextInt(evenNumbers.length)];
            }
        } while (allGeneratedPoints.contains(result));
        return result;
    }

    private Point choosePointSecondStage(boolean flagShottedLastTime) {
        if (firstTimeComeToSecondStage) {
            makeListOfPointsIn4Directions();
        }
        firstTimeComeToSecondStage = false;

        if (!flagShottedLastTime) {
            banDirection(lastChoosenDirection);
        }
        if (hitPoints.size() == 2) {
            ArrayPoints points = new ArrayPoints();
            char c = points.pointsInLine(hitPoints);
            if (c == 'X') {
                banDirection(2);
                banDirection(3);
            } else {
                banDirection(0);
                banDirection(1);
            }
        }
        secureFromExtremePoints(hitPoints.get(hitPoints.size() - 1));
        while (true) {
            int i = generator.nextInt(4);
            if (helpDirectionShot[i]) {
                result = allGeneratedPointsInSecondStage[i].removeFirst();
                if (allGeneratedPoints.contains(result)) {
                    banDirection(i);
                    continue;
                }
                lastChoosenDirection = i;
                return result;
            }
        }
    }

    private void setAllDirectionsTrue() {
        helpDirectionShot[0] = true;
        helpDirectionShot[1] = true;
        helpDirectionShot[2] = true;
        helpDirectionShot[3] = true;
    }


    private void clearTableOfGeneratePoints() {
        for (int i = 0; i < allGeneratedPointsInSecondStage.length; i++) {
            allGeneratedPointsInSecondStage[i] = null;
        }
    }

    private void makeListOfPointsIn4Directions() {
//        0 -up,1-down,2-right,3-left;
        Point lastPoint = hitPoints.get(hitPoints.size() - 1);
        Point point = new Point(lastPoint);
        for (int i = 0; i < allGeneratedPointsInSecondStage.length; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                changeCord(i, point);
                if (point.y < 0 || point.y > sizeBoard - 1) {
                    break;
                }
                if (point.x < 0 || point.x > sizeBoard - 1) {
                    break;
                }
                if (allGeneratedPointsInSecondStage[i] == null) {
                    allGeneratedPointsInSecondStage[i] = new LinkedList<>();
                }
                allGeneratedPointsInSecondStage[i].addLast(new Point(point.x, point.y));
            }
            point.x = lastPoint.x;
            point.y = lastPoint.y;
        }
    }

    private void changeCord(int direction, Point point) {
        switch (direction) {
            case 0:
                point.y--;
                break;
            case 1:
                point.y++;
                break;
            case 2:
                point.x++;
                break;
            case 3:
                point.x--;
                break;
        }
    }

    private void secureFromExtremePoints(Point point) {
        if (point.x == 0) {
            helpDirectionShot[3] = false;
        }
        if (point.x == sizeBoard - 1) {
            helpDirectionShot[2] = false;
        }
        if (point.y == 0) {
            helpDirectionShot[0] = false;
        }
        if (point.y == sizeBoard - 1) {
            helpDirectionShot[1] = false;
        }
    }

    private void banDirection(int direction) {
        switch (direction) {
            case 0:
                helpDirectionShot[0] = false;
                break;
            case 1:
                helpDirectionShot[1] = false;
                break;
            case 2:
                helpDirectionShot[2] = false;
                break;
            case 3:
                helpDirectionShot[3] = false;
        }
    }
}
