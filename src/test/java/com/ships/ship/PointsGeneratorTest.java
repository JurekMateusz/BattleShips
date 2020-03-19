package com.ships.ship;

import com.ships.board.Board;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

class PointsGeneratorTest {
    private static PointsGenerator pointsGenerator;
    private static Board board;
    private static HashSet<Point> allGeneratedPoints;
    private static ArrayList<Ship> ships;

    char[][] tab = new char[10][10];

    @BeforeAll
    static void setUp() {
        pointsGenerator = new PointsGenerator(10);
        board = new Board(10);
        allGeneratedPoints = new HashSet<>();
        ships = new ArrayList<>();

        generateShips();

    }

//    @RepeatedTest(1)
    void generatePoint() {

        board.printBoard(ships);

        boolean flag = false;

        while (!ships.isEmpty()) {
            Point point = pointsGenerator.generatePoint(flag);
            allGeneratedPoints.add(point);

            for (Ship ship : ships) {
                ArrayList<Point> points = ship.getPoints();
                if (points.contains(point)) {
                    tab[point.y][point.x] = '1';
                    flag = true;
                    if (allGeneratedPoints.containsAll(points)) {
                        ships.remove(ship);
                        pointsGenerator.resetDependency();
                        flag = false;
                        break;
                    }
                    break;
                } else {
                    tab[point.y][point.x] = 'x';
                    flag = false;
                }
            }
//            print(tab);
        }
    }

    private static void generateShips() {
        ShipsGenerator shipsGenerator = new ShipsGenerator(10);

        shipsGenerator.addShipToList(ships, 4);

        shipsGenerator.addShipToList(ships, 3);
        shipsGenerator.addShipToList(ships, 3);

        shipsGenerator.addShipToList(ships, 2);
        shipsGenerator.addShipToList(ships, 2);
        shipsGenerator.addShipToList(ships, 2);

        shipsGenerator.addShipToList(ships, 1);
        shipsGenerator.addShipToList(ships, 1);
        shipsGenerator.addShipToList(ships, 1);
        shipsGenerator.addShipToList(ships, 1);

//        for (int lengthShips = 2; lengthShips < 5; lengthShips++) {
//            int numberOfShipGivenLength = 2;
//            while (numberOfShipGivenLength-- > 0) {
//                shipsGenerator.addShipToList(ships, lengthShips);
//            }
//        }
    }

    private void print(char[][] board) {
        System.out.print(System.lineSeparator() + "   ");
        for (int i = 0; i < 10; i++) {
            System.out.print(i + "  ");
        }

        for (int i = 0; i < 10; i++) {
            System.out.print(System.lineSeparator() + i + "  ");
            for (int j = 0; j < 10; j++) {
                System.out.print(board[i][j] + "  ");
            }
        }
        System.out.println();
    }
}