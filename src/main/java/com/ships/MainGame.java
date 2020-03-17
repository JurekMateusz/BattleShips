package com.ships;

import com.ships.board.Board;
import com.ships.player.ComputerPlayer;
import com.ships.player.HumanPlayer;
import com.ships.player.AbstractPlayer;

import java.awt.*;


public class MainGame {
    private final static int SIZE_MAP = 10;
    private final AbstractPlayer human;
    private final AbstractPlayer computer;
    private final Board board;

    public MainGame() {
        human = new HumanPlayer(SIZE_MAP);
        computer = new ComputerPlayer(SIZE_MAP);
        board = new Board(SIZE_MAP);
        human.setOpponent(computer);
        computer.setOpponent(human);
    }


    public void play() {
        human.putShips();
        computer.putShips();

        AbstractPlayer currentPlayer = human;
        AbstractPlayer secondPlayer = computer;

        do {
            System.out.flush();
            // board.printBoard(currentAbstractPlayer.getAllShottedPoints);// TODO not currentPlayyer ,only board/map
            boolean currentHit = doTurn(currentPlayer, secondPlayer);
            if (currentHit == false) { //TODO currentHit => enum ShotResult
                AbstractPlayer sup = currentPlayer;
                currentPlayer = secondPlayer;
                secondPlayer = sup;
            }
        } while (!secondPlayer.loose());
        currentPlayer.wins();
    }

    private boolean doTurn(AbstractPlayer current, AbstractPlayer other) {
        Point point = current.selectPointToShoot();
        return current.shootAt(point, other);
    }

}
