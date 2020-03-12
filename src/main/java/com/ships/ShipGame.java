package com.ships;

public class ShipGame {
    public static void main(String[] args) {
        String newLine = System.lineSeparator();
        MainGame mainGame = new MainGame();

        System.out.println("Game started!" + newLine + "Now you will be put your 3 ships in map" +
                newLine + "Size ship is 3 filds" +
                newLine + "Type in number  \"1\"-\"7\" and charakter: \"A\"-\"G\"" +
                newLine + "For example 1A 2A 3A");

        mainGame.initiateMap();

        System.out.println(newLine + "you successfully put your ships on map.Now you can compete with Computer." +
                newLine + "Enter imput like: \"2G\" or \"5F\"" +
                newLine + "'1' = hit ship\t\t'x' = mishit");

        mainGame.prepareMap();
        mainGame.play();
    }
}
