package com.ships.input;

import com.ships.ship.PointsGenerator;
import com.ships.ship.Ship;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArrayPointsTest {
    private static ArrayPoints arrayPoints;

    private static ArrayList<ArrayList<Point>> pointsInLine;
    private static ArrayList<ArrayList<Point>> pointsNotInLine;
    private static ArrayList<ArrayList<Point>> pointsAreString;
    private static ArrayList<ArrayList<Point>> pointsAreNotString;

    private static ArrayList<Ship> shipsGoodLayed;

    @BeforeAll
    static void setUp() {
        arrayPoints = new ArrayPoints();

        pointsInLine = new ArrayList<>();
        pointsNotInLine = new ArrayList<>();
        pointsAreString = new ArrayList<>();
        pointsAreNotString = new ArrayList<>();
        shipsGoodLayed = new ArrayList<>();
        setPointsInLine();
        setPointsNotInLine();
        setPointsWithAreString();
        setPointsAreNotString();
        setShipsGoodLayed();
    }

    @Test
    void areInOneLineAssertTrue() {
        for (ArrayList<Point> arrayList : pointsInLine) {
            boolean status = arrayPoints.areInOneLine(arrayList);
            assertTrue(status);
        }
    }

    @Test
    void areNotInOneLineAssertFalse() {
        for (ArrayList<Point> arrayList : pointsNotInLine) {
            boolean status = arrayPoints.areInOneLine(arrayList);
            assertFalse(status);
        }
    }

    @Test
    void areStringAssertTrue() {
        for (ArrayList<Point> arrayList : pointsAreString) {
            boolean status = arrayPoints.areString(arrayList);
            assertTrue(status);
        }
    }

    @Test
    void areStringAssertFalse() {
        for (ArrayList<Point> arrayList : pointsAreNotString) {
            boolean status = arrayPoints.areString(arrayList);
            assertFalse(status);
        }
    }

    @Test
    void areBordering() {
        ArrayList<Point> arrayList = new ArrayList<>();
        arrayList.add(new Point(4, 0));
        arrayList.add(new Point(3, 0));
        assertFalse(arrayPoints.areBordering(arrayList,shipsGoodLayed));

        arrayList.add(new Point(3,1));
        assertTrue(arrayPoints.areBordering(arrayList,shipsGoodLayed));
    }

    private static void setPointsInLine() {
        ArrayList<Point> arrayList = new ArrayList<>();
        arrayList.add(new Point(0, 0));
        arrayList.add(new Point(0, 7));
        arrayList.add(new Point(0, 2));
        pointsInLine.add(arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Point(6, 9));
        arrayList.add(new Point(0, 9));
        pointsInLine.add(arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Point(0, 9));
        pointsInLine.add(arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Point(1, 3));
        pointsInLine.add(arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Point(5, 5));
        pointsInLine.add(arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Point(8, 5));
        arrayList.add(new Point(8, 6));
        arrayList.add(new Point(8, 9));
        arrayList.add(new Point(8, 1));
        arrayList.add(new Point(8, 0));
        pointsInLine.add(arrayList);
    }

    private static void setPointsNotInLine() {
        ArrayList<Point> arrayList = new ArrayList<>();
        arrayList.add(new Point(0, 0));
        arrayList.add(new Point(1, 7));
        arrayList.add(new Point(0, 2));
        pointsNotInLine.add(arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Point(6, 9));
        arrayList.add(new Point(1, 8));
        pointsNotInLine.add(arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Point(8, 5));
        arrayList.add(new Point(6, 6));
        arrayList.add(new Point(9, 9));
        arrayList.add(new Point(1, 1));
        arrayList.add(new Point(0, 0));
        pointsNotInLine.add(arrayList);
    }

    private static void setPointsWithAreString() {
        //this points need agree with method areBordering()
        ArrayList<Point> arrayList = new ArrayList<>();
        arrayList.add(new Point(0, 1));
        pointsAreString.add(arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Point(2, 7));
        arrayList.add(new Point(1, 7));
        pointsAreString.add(arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Point(0, 4));
        arrayList.add(new Point(0, 3));
        arrayList.add(new Point(0, 5));
        pointsAreString.add(arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Point(4, 5));
        arrayList.add(new Point(4, 4));
        arrayList.add(new Point(4, 3));
        pointsAreString.add(arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Point(5, 2));
        arrayList.add(new Point(4, 2));
        arrayList.add(new Point(3, 2));
        arrayList.add(new Point(1, 2));
        arrayList.add(new Point(2, 2));
        pointsAreString.add(arrayList);
    }

    public static void setPointsAreNotString() {
        ArrayList<Point> arrayList = new ArrayList<>();
        arrayList.add(new Point(0, 0));
        arrayList.add(new Point(1, 1));
        pointsAreNotString.add(arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Point(0, 0));
        arrayList.add(new Point(0, 1));
        arrayList.add(new Point(0, 3));
        pointsAreNotString.add(arrayList);

        arrayList = new ArrayList<>();
        arrayList.add(new Point(5, 5));
        arrayList.add(new Point(7, 5));
        arrayList.add(new Point(8, 5));
        pointsAreNotString.add(arrayList);
    }

    private static void setShipsGoodLayed() {
        //pointsAreString are put well
        pointsAreString.forEach(points -> shipsGoodLayed.add(new Ship(points)));
    }
}