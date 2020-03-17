package com.ships.player;

import com.ships.board.Board;
import com.ships.ship.Ship;
import com.ships.input.ReadCord;

import java.awt.Point;
import java.util.ArrayList;

public class HumanPlayer extends AbstractPlayer {
    private int lengthShip = 3;
    private ReadCord reader = new ReadCord();

    public HumanPlayer(int sizeMap) {
        super(sizeMap);
    }

    @Override
    public Point selectPointToShoot() {
        while (true) {
            System.out.print("Type Cord: ");
            Point point = reader.readPoint();
            if (point == null) {
                System.out.println("Bad imput :[");
                continue;
            }
            //TODO if point repeat :continue
            return point;
        }
    }
    @Override
    public void wins() {

    }
    @Override
    public void putShips() {
        int numbersOfShips = 3;
        int i = 0;
        ArrayList<Point> points;

         Board board = new Board(sizeMap);
//         board.printBoard();
        while (i < numbersOfShips) {
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
            if (!isPointsExist(points)) {//add not bordering method
                ships.add(new Ship(points));
                i++;
            } else {
                System.out.print("\tYou already typed this field/fields. Type another one :)");
            }
            points.clear();
            System.out.print("   Your Map:");
            board.printBoard(ships);
        }
    }

    @Override
    public void drawBoards() {

    }


}




