package com.ships.player;

import com.ships.input.ArrayPoints;
import com.ships.input.ReadCord;
import com.ships.ship.Ship;

import java.awt.Point;
import java.util.ArrayList;

public class HumanPlayer extends AbstractPlayer {

    private ReadCord reader = new ReadCord();

    public HumanPlayer(int sizeMap) {
        super(sizeMap);
    }

    @Override
    public void putShips() {
        int i = 1;
        ArrayList<Point> pointsShip;
        ArrayPoints pointsChecker = new ArrayPoints();
        while (true) {
            board.printBoard(ships);
            System.out.print("Type cords for ship nr " + i + " : ");
            pointsShip = reader.readPoints();
            if (pointsShip == null) {
                System.out.println("Bad imput :[");
                continue;
            }
            if (pointsShip.get(0).getX() == 777) {
                super.putShips();
                break;
            }
            if (pointsShip.get(0).getX() == 666 && !ships.isEmpty()) {
                return;
            }
            if (pointsExist(pointsShip)) {
                System.out.println("\tYou already typed this field/fields. Type another one :)");
                continue;
            }
            if (pointsChecker.areBordering(pointsShip, ships)) {
                System.out.println("\tShips can't touch each other, there must be at least one free field");
                continue;
            }
            ships.add(new Ship(pointsShip));
            i++;
            pointsShip.clear();
        }
        board.printBoard(ships);

    }


    @Override
    public Point selectPointToShoot() {
        if (sunkStatus) {
            pointsGenerator.resetDependency();
            sunkStatus = false;
            // lastShotStatus = false;
        }
        while (true) {
            if (lastShotStatus) {
                System.out.print("You hit " + opponent.getName() + " ship ,type next point:");
            } else {
                System.out.print("Type Cord: ");
            }
            Point point = reader.readPoint();
            if (point == null) {
                System.out.println("Bad imput :[");
                continue;
            }
            if (hitPoints.contains(point) || missPoints.contains(point)) {
                System.out.println("You already typed this point.Type another one :)");
                continue;
            }
            return point;
        }
    }

    @Override
    public void drawBoard() {
        System.out.println("\tYour Board:");
        System.out.flush();
        super.drawBoard();
    }

    @Override
    public void wins() {
        System.out.println("\t You won this game!!" +
                System.lineSeparator() + "\tYour board:\t\t\t\t" + opponent.getName() +
                " board:");

        board.printTwoBoards(missPoints, opponent.getShips(), opponent.getMissPoints(), ships);
    }
}




