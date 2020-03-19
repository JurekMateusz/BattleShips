package com.ships.board;

import com.ships.ship.Ship;

import java.awt.*;
import java.util.ArrayList;

public class Board {
    private final char[] lettersInMap = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    private char[][] board;
    private int sizeBoard;

    public Board(int sizeBoard) {
        this.sizeBoard = sizeBoard;
        board = new char[sizeBoard][sizeBoard];

    }

    public void printBoard(ArrayList<?> shipsPoints) {
        if (shipsPoints.isEmpty()) {
            return;
        }
        Object firstElement = shipsPoints.get(0);
        if (firstElement instanceof Point ) {
            ArrayList<Point> pointsArr = (ArrayList<Point>)shipsPoints;
            printBoard(new ArrayList<Point>(), pointsArr);
        }else{
            ArrayList<Point> allPointShip = new ArrayList<>();
            ArrayList<Ship> ships = (ArrayList<Ship>) shipsPoints;
            ships.forEach(ship -> {
                ArrayList<Point> points = ship.getPoints();
                points.forEach(point -> {
                    allPointShip.add(point);
                });

            });
            printBoard(new ArrayList<Point>(), allPointShip);
        }
    }

    public void printBoard(ArrayList<Point> missPoints, ArrayList<Point> hitPoints) {
        initiateBoard(board);
        putPointsToBoard(missPoints, board, 'x');
        putPointsToBoard(hitPoints, board, '1');

        print();
    }

    public void printTwoBoards(ArrayList<Point> playerMissPoints, ArrayList<Ship> playerShips,
                               ArrayList<Point> computerMissPoints, ArrayList<Ship> computerShips) {
        char[][] helpBoard = new char[sizeBoard][sizeBoard];

        initiateBoard(board);
        putPointsToBoard(playerMissPoints, board, 'x');
        playerShips.forEach(ship -> {
            putPointsToBoard(ship.getPoints(), board, '1');
        });


        initiateBoard(helpBoard);
        putPointsToBoard(computerMissPoints, helpBoard, 'x');
        computerShips.forEach(ship -> {
            putPointsToBoard(ship.getPoints(), helpBoard, '1');
        });
        print(helpBoard);
    }

    private void initiateBoard(char[][] board) {
        for (int i = 0; i < sizeBoard; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                board[i][j] = '-';
            }
        }
    }

    private void putPointsToBoard(ArrayList<Point> points, char[][] board, char character) {
        points.forEach(point -> {
            board[point.y][point.x] = character;
        });
    }

    private void print() {
        System.out.print("   ");
        for (int i = 1; i <= sizeBoard; i++) {
            System.out.print(i + "  ");
        }

        for (int i = 0; i < sizeBoard; i++) {
            System.out.print(System.lineSeparator() + lettersInMap[i] + "  ");
            for (int j = 0; j < sizeBoard; j++) {
                System.out.print(board[i][j] + "  ");
            }
        }
        System.out.println();
    }

    private void print(char[][] helpBoard) {
        System.out.print(System.lineSeparator() + "   ");
        for (int i = 1; i <= sizeBoard; i++) {
            System.out.print(i + "  ");
        }
        System.out.print("\t\t   ");
        for (int i = 1; i <= sizeBoard; i++) {
            System.out.print(i + "  ");
        }

        for (int i = 0; i < sizeBoard; i++) {
            System.out.print(System.lineSeparator() + lettersInMap[i] + "  ");
            for (int j = 0; j < sizeBoard; j++) {
                System.out.print(board[i][j] + "  ");
            }
            System.out.print("\t\t");

            System.out.print(lettersInMap[i] + "  ");
            for (int j = 0; j < sizeBoard; j++) {
                System.out.print(helpBoard[i][j] + "  ");
            }
        }
        System.out.println();
    }
}

