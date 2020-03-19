package com.ships.player;


public class ComputerPlayer extends AbstractPlayer {

    public ComputerPlayer(int sizeMap) {
        super(sizeMap);
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