package com.ships.ship;

import org.junit.jupiter.api.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

class ShipsGeneratorTest {
    private static ShipsGenerator shipsGenerator;
    private static ArrayList<Ship> generatedShips;
    private char[][] map = new char[10][10];

    @BeforeAll
    static void setUp() {
        shipsGenerator = new ShipsGenerator(10);
        generatedShips = new ArrayList<>();
    }

    @AfterEach
    void clear() {
        generatedShips.clear();
        clearMap();
    }

    @RepeatedTest(10)
    void addShipToList() {
        shipsGenerator.addShipToList(generatedShips, 6);
        for (int lengthShips = 2; lengthShips < 5; lengthShips++) {
            int numberOfShipGivenLength = 2;
            while (numberOfShipGivenLength-- > 0) {
                shipsGenerator.addShipToList(generatedShips, lengthShips);
            }
        }
        testGenerateShips();
        return;
    }

    private void testGenerateShips() {
        try {
            putToMapCords(generatedShips);
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
            Assertions.fail("Points" + Arrays.toString(generatedShips.toArray()));
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
            Assertions.fail("Points repeated");
        }
    }

    private void putToMapCords(ArrayList<Ship> ships) throws IndexOutOfBoundsException, IllegalStateException {
        for (Ship ship : ships) {
            ArrayList<Point> points = ship.getPoints();
            for (Point point : points) {
                if (map[point.y][point.x] == '1') {
                    throw new IllegalStateException("Points repeated " + point.toString());
                }
                map[point.y][point.x] = '1';
            }
        }
    }

    private void clearMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = '-';
            }
        }
    }
}