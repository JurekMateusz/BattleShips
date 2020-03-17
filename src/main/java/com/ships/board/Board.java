package com.ships.board;

import com.ships.ship.PointsGenerator;
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

    public void printBoard(ArrayList<Ship> ships) {
        printBoard(new ArrayList<Point>(), ships);
    }

    public void printBoard(ArrayList<Point> allPoints, ArrayList<Ship> ships) {
        initiateBoard();
        putPointsToBoard(allPoints, 'x');

        ships.forEach(ship -> {
            putPointsToBoard(ship.getPoints(), '1');
        });

        System.out.print(System.lineSeparator() + "   ");
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

    private void initiateBoard() {
        for (int i = 0; i < sizeBoard; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                board[i][j] = '-';
            }
        }
    }

    private void putPointsToBoard(ArrayList<Point> points, char character) {
        points.forEach(point -> {
            board[point.y][point.x] = character;
        });
    }
}

