package com.ships.player;

import com.ships.ship.Ship;
import com.ships.ship.ShipsGenerator;

import java.util.ArrayList;

public class ComputerPlayer extends AbstractPlayer {

    public ComputerPlayer(int sizeMap) {
        super(sizeMap);
    }

    @Override
    public void putShips() {
        ShipsGenerator shipsGenerator = new ShipsGenerator(sizeMap);
        ArrayList<Ship> shipsUser = new ArrayList<>(opponent.getShips());
        shipsUser.sort((Ship s1, Ship s2) -> s2.getPoints().size() - s1.getPoints().size());
        for (Ship ship : shipsUser) {
            shipsGenerator.addShipToList(ships, ship.getPoints().size());
        }
    }

    @Override
    public void drawBoard() {
        System.out.println("\t" + this.name + " board:");
        System.out.flush();
        super.drawBoard();
    }

    @Override
    public void wins() {
        System.out.println("\t" + this.name + " won this game !!" +
                System.lineSeparator() + "\tYour board:\t\t\t\t\t\t\t" + this.name +
                " board:");

        board.printTwoBoards(opponent.getMissPoints(), ships, missPoints, opponent.getShips());
    }

}