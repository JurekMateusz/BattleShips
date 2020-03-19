package com.ships;

public class ShipGame {
    public static void main(String[] args) {
        String newLine = System.lineSeparator();
        MainGame mainGame = new MainGame();

        System.out.println("Game started! Enjoy" +
                newLine + "Enter imput like: \"2G\" or \"5F\"" +
                newLine + "'1' = hit ship\t\t'x' = mishit");

        mainGame.play();
    }
}
