package com.ships.player;

import com.ships.input.ReadCord;

import java.awt.Point;

public class HumanPlayer extends AbstractPlayer {

    private ReadCord reader = new ReadCord();

    public HumanPlayer(int sizeMap) {
        super(sizeMap);
    }

    @Override
    public void putShips() {
        super.putShips();    //TODO user can put his configuration of ship,computer will adopt
        System.out.println("   Your generated ships on board:");
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

        board.printTwoBoards(missPoints,opponent.getShips() , opponent.getMissPoints(), ships);
    }
}




