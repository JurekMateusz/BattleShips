package com.ships;

import com.ships.player.ComputerPlayer;
import com.ships.player.HumanPlayer;
import com.ships.player.Player;

import java.awt.*;


public class MainGame {
    private final static int SIZE_MAP = 10;
    private final Player human;
    private final Player computer;

    public MainGame() {
        human = new HumanPlayer(SIZE_MAP);
        computer = new ComputerPlayer(SIZE_MAP);
        human.setOpponent(computer);
        computer.setOpponent(human);
        computer.setName("Computer");
    }

    public MainGame(String test) {
        human = new ComputerPlayer(SIZE_MAP);
        computer = new ComputerPlayer(SIZE_MAP);
        human.setOpponent(computer);
        human.setName("Stewie");
        computer.setOpponent(human);
        computer.setName("Meg");

    }


    public void play() {
        String lineSep = System.getProperty("line.separator");
        System.out.println("You can put your configuration of ships ,but there is some rules:" + lineSep
                + "\t-they need to be arranged either horizontally or vertically.Like A1 B1 C1,no - A1 B2 C3" + lineSep
                + "\t-The ships can't overlap" + lineSep
                + "\t-ships can't touch each other, there must be at least one free field" + lineSep +
                "\t\t A1 B1 C1 and C2 C3 C4 is a bad configuration because there is no free field between C1 and C2" +
                lineSep + "\tWhen you end,type \"END\" " +
                lineSep + "If you would like to skip this step,type \"GENERATE\".Computer will generate your ships");

        human.putShips();
        computer.putShips();

        System.out.println("Enjoy" +
                lineSep + "Enter imput like: \"2G\" or \"5F\"" +
                lineSep + "'1' = hit ship\t\t'x' = mishit");

        Player currentPlayer = human;
        Player secondPlayer = computer;

        currentPlayer.drawBoard();
        do {
            System.out.flush();
            boolean currentHit = doTurn(currentPlayer);
            currentPlayer.drawBoard();
            if (currentPlayer.didShipWasSunk()) {
                System.out.println(currentPlayer.getName() + " sunk " + secondPlayer.getName()
                        + "'s ship!!!" + System.lineSeparator());
            }
            if (currentHit == false || currentPlayer.didShipWasSunk()) {
                Player sup = currentPlayer;
                currentPlayer = secondPlayer;
                secondPlayer = sup;
            }
        } while (!secondPlayer.loose());
        currentPlayer.wins();
    }

    private boolean doTurn(Player current) {
        Point point = current.selectPointToShoot();
        return current.shootAt(point);
    }

}
