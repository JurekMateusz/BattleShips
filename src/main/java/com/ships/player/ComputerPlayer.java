package com.ships.player;

import com.ships.ship.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class ComputerPlayer extends Player {
    Random generator = new Random(System.currentTimeMillis());
    private boolean secondStage = false;
    private int firstTimeComeToSecondStage = 1;
    private boolean wasNOTShottedLastTime = false;
    private boolean wasShottedLastTime = false;
    private boolean[] helpDirectionShot = {true, true, true, true};
    private LinkedList<Point>[] allGeneratedPointsInSecondStage = new LinkedList[4];
    private ArrayList<Point> allShottedPoints = new ArrayList<>();
    private Point lastPoint = new Point();
    private int lastChoosenDirection;

    private int functionCallNumber = 0;
    private int[] oddNumbers = {1, 3, 5};
    private int[] evenNumbers = {0, 2, 4, 6};


    public ComputerPlayer(int sizeMap) {
        super(sizeMap);
    }

    public void setAllDrectionsTrue() {
        helpDirectionShot[0] = true;
        helpDirectionShot[1] = true;
        helpDirectionShot[2] = true;
        helpDirectionShot[3] = true;
    }

    public void setFirstTimeComeToSecondStage() {
        firstTimeComeToSecondStage = 1;
    }

    public boolean getSecondStage() {
        return secondStage;
    }

    public void setSecondStage(boolean bool) {
        secondStage = bool;
    }

    public void setWasNOTShottedLastTime(boolean bool) {
        wasNOTShottedLastTime = bool;
    }

    public void setWasShottedLastTime(boolean bool) {
        wasShottedLastTime = bool;
    }

    public void chooseCordsShips(int numbersOfShips) {
        int i = 0;
        int[] cordsInTab = new int[4];

        while (i < numbersOfShips) {
            cordsInTab[0] = generator.nextInt(sizeMap); // x
            cordsInTab[1] = generator.nextInt(sizeMap); //y
            cordsInTab[2] = generator.nextInt(4); //direction
            cordsInTab[3] = 3; //temporary variable ,length ship,change in future

            didCordsCanBeIfNotChangeDirect(cordsInTab);
            ArrayList<Point> Points = makePoints(cordsInTab);

            if (!isPointsExist(Points)) {
                ships.add(new Ship(Points));
                i++;
            }
        }
        addPointsToMap();
    }

    public Point shotShip() {
        sunkShip = false;
        Point result;
        if (secondStage) {
            result = shotShipSecondStage();
        } else {
            result = shotShipFirstStage();
        }
        lastPoint.x = result.x;
        lastPoint.y = result.y;
        allShottedPoints.add(new Point(result));
        return result;
    }

    private void didCordsCanBeIfNotChangeDirect(int[] cordsInTab) {
        switch (cordsInTab[2]) {
            case 0:
                if (cordsInTab[1] + cordsInTab[3] - 1 >= sizeMap) {
                    cordsInTab[2] = 1;
                }
                break;
            case 1:
                if (cordsInTab[1] - cordsInTab[3] + 1 < 0) {
                    cordsInTab[2] = 0;
                }
                break;
            case 2:
                if (cordsInTab[0] + cordsInTab[3] - 1 >= sizeMap) {
                    cordsInTab[2] = 3;
                }
                break;
            case 3:
                if (cordsInTab[0] - cordsInTab[3] + 1 < 0) {
                    cordsInTab[2] = 2;
                }
                break;
        }
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

    private ArrayList<Point> makePoints(int[] tab) {
        ArrayList<Point> result = new ArrayList<>();
        for (int i = 0; i < tab[3]; i++) {
            result.add(new Point(tab[0], tab[1]));
            changeCordByDirect(tab);
        }
        return result;
    }

    public void clearTableOfGeneratetPoints() {
        for (int i = 0; i < allGeneratedPointsInSecondStage.length; i++) {
            allGeneratedPointsInSecondStage[i] = null;
        }
    }

    private void makeListOfPointsIn4Directions() {
        //0 -up,1-down,2-right,3-left;
        Point point = new Point(lastPoint);
        for (int i = 0; i < allGeneratedPointsInSecondStage.length; i++) {
            for (int j = 0; j < sizeMap; j++) {
                changeCord(i, point);
                if (point.y < 0 || point.y > sizeMap - 1) {
                    break;
                }
                if (point.x < 0 || point.x > sizeMap - 1) {
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
                point.y--; // w góre chce iść to y muszę zmiejszyć aby w tablicy się zgadzało.
                break;
            case 1:
                point.y++; //w gół to y muszę zwiększyć aby się zgadzało na tablicy
                break;
            case 2:
                point.x++; // x to już normalnie ,nie ma odwróconej logiki
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
        if (point.x == sizeMap - 1) {
            helpDirectionShot[2] = false;
        }
        if (point.y == 0) {
            helpDirectionShot[0] = false;
        }
        if (point.y == sizeMap - 1) {
            helpDirectionShot[1] = false;
        }
    }

    private boolean isAllDirectionFalse() {
        for (int i = 0; i < helpDirectionShot.length; i++) {
            if (helpDirectionShot[i] != false) {
                return false;
            }
        }
        return true;
    }

    private void unbanTwoDirections() {
        if (lastChoosenDirection == 0 || lastChoosenDirection == 1) {
            helpDirectionShot[2] = true;
            helpDirectionShot[3] = true;
        } else {
            helpDirectionShot[0] = true;
            helpDirectionShot[1] = true;
        }
    }

    private void banTwoDirections() {
        if (lastChoosenDirection == 0 || lastChoosenDirection == 1) {
            helpDirectionShot[2] = false;
            helpDirectionShot[3] = false;
        }
        if (lastChoosenDirection == 2 || lastChoosenDirection == 3) {
            helpDirectionShot[0] = false;
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

    private Point shotShipSecondStage() {
        Point result;
        if (firstTimeComeToSecondStage++ == 1) {
            makeListOfPointsIn4Directions();
        }
        if (wasNOTShottedLastTime) {
            banDirection(lastChoosenDirection);
        }
        if (wasShottedLastTime) {
            banTwoDirections();
        }
        if (wasNOTShottedLastTime && isAllDirectionFalse()) {
            unbanTwoDirections();
        }
        wasShottedLastTime = false;
        wasNOTShottedLastTime = false;

        secureFromExtremePoints(lastPoint);

        while (true) {
            int i = generator.nextInt(4);
            if (helpDirectionShot[i] == true) {
                result = allGeneratedPointsInSecondStage[i].removeFirst();
                if (allShottedPoints.contains(result)) {
                    banDirection(i);
                    continue;
                }
                lastChoosenDirection = i;
                return result;
            }
        }
    }

    private Point shotShipFirstStage(){
        Point result = new Point();
        do {
            if ((++functionCallNumber % 2) == 0) {
                result.x = evenNumbers[generator.nextInt(evenNumbers.length)];
                result.y = oddNumbers[generator.nextInt(oddNumbers.length)];
            } else {
                result.x = oddNumbers[generator.nextInt(oddNumbers.length)];
                result.y = evenNumbers[generator.nextInt(evenNumbers.length)];
            }
        } while (allShottedPoints.contains(result));
        return result;
    }
}