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
        human.putShips();
        computer.putShips();

        Player currentPlayer = human;
        Player secondPlayer = computer;

        currentPlayer.drawBoard();
        do {
            System.out.flush();
            boolean currentHit = doTurn(currentPlayer);
            currentPlayer.drawBoard();
            if (currentPlayer.didShipWasSunk()) {
                System.out.println(currentPlayer.getName() + " sunk " + secondPlayer.getName()
                        + "'s ship!!!"+System.lineSeparator());
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
