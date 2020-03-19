package com.ships.input;

import com.ships.ship.Ship;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArrayPointsTest {
    private static ArrayPoints arrayPoints;

    @BeforeAll
    static void setUp() {
        arrayPoints = new ArrayPoints();
    }

    @Test
    void areInOneLine() {
        ArrayList<Point> arrayList = new ArrayList<>();
        arrayList.add(new Point(0, 0));
        arrayList.add(new Point(0, 7));
        arrayList.add(new Point(0, 2));
        assertTrue(arrayPoints.areInOneLine(arrayList));

        arrayList.clear();

        arrayList.add(new Point(6, 9));
        arrayList.add(new Point(0, 9));
        assertTrue(arrayPoints.areInOneLine(arrayList));


        arrayList.add(new Point(0, 9));
        arrayList.add(new Point(6, 9));
        arrayList.add(new Point(1, 9));
        assertTrue(arrayPoints.areInOneLine(arrayList));

        arrayList.clear();
        arrayList.add(new Point(5, 5));
        arrayList.add(new Point(6, 6));
        arrayList.add(new Point(7, 7));
        assertFalse(arrayPoints.areInOneLine(arrayList));
    }

    @Test
    void areString() {
        ArrayList<Point> arrayList = new ArrayList<>();
        arrayList.add(new Point(0, 9));
        arrayList.add(new Point(2, 9));
        arrayList.add(new Point(1, 9));
        assertTrue(arrayPoints.areString(arrayList));

        arrayList.add(new Point(3, 9));
        arrayList.add(new Point(4, 9));
        arrayList.add(new Point(5, 9));
        assertTrue(arrayPoints.areString(arrayList));

        arrayList.add(new Point(7, 9));
        assertFalse(arrayPoints.areString(arrayList));

        arrayList.clear();
        arrayList.add(new Point(5, 9));
        arrayList.add(new Point(5, 7));
        arrayList.add(new Point(5, 8));
        arrayPoints.areString(arrayList);
        assertTrue(arrayPoints.areString(arrayList));
    }

    @Test
    void areNotBordering() {
        ArrayList<Point> shipPoints = new ArrayList<>();
        shipPoints.add(new Point(5, 7));
        shipPoints.add(new Point(5, 6));
        shipPoints.add(new Point(5, 8));
        Ship ship = new Ship(shipPoints);
        ArrayList<Ship> listShips = new ArrayList<>();
        listShips.add(ship);

        ArrayList<Point> testedPoints = new ArrayList<>();
        testedPoints.add(new Point(3, 6));
        testedPoints.add(new Point(2, 3));
        testedPoints.add(new Point(5, 10));
        testedPoints.add(new Point(7, 9));
        testedPoints.add(new Point(7, 8));
        testedPoints.add(new Point(8, 4));
        assertTrue(arrayPoints.areNotBordering(testedPoints, listShips));

        shipPoints.clear();
        shipPoints.add(new Point(6, 4));
        shipPoints.add(new Point(5, 4));
        shipPoints.add(new Point(4, 4));
        listShips.add(new Ship(shipPoints));
        arrayPoints.areNotBordering(testedPoints, listShips);
        assertTrue(arrayPoints.areNotBordering(testedPoints, listShips));

        testedPoints.add(new Point(6, 8));

        assertFalse(arrayPoints.areNotBordering(testedPoints, listShips));


    }
}