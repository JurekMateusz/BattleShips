import java.awt.*;

public class ShipGame {
    private final char[] lettersInMap = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
    private final static int SIZE_MAP = 7;
    private int maxNumbersOfShips = 3;

    ComputerPlayer computerPlayer;
    HumanPlayer humanPlayer;

    private Point pointShot;

    private void initiateMap() {
        computerPlayer = new ComputerPlayer(SIZE_MAP);
        computerPlayer.chooseCordsShips(maxNumbersOfShips);

        humanPlayer = new HumanPlayer(SIZE_MAP);
        humanPlayer.chooseCordsShips(maxNumbersOfShips);
    }

    private void printMaps() {
        System.out.print("    Your Map:\t\t\t\t\t    Computer Map:");
        System.out.print(System.lineSeparator() + "   ");
        for (int i = 1; i <= SIZE_MAP; i++) {
            System.out.print(i + "  ");
        }
        System.out.print("\t\t   ");
        for (int i = 1; i <= SIZE_MAP; i++) {
            System.out.print(i + "  ");
        }
        for (int i = 0; i < SIZE_MAP; i++) {
            System.out.print(System.lineSeparator() + lettersInMap[i] + "  ");
            for (int j = 0; j < SIZE_MAP; j++) {
                System.out.print(computerPlayer.getElementMap(i, j) + "  ");
            }
            System.out.print("\t\t");
            System.out.print(lettersInMap[i] + "  ");
            for (int j = 0; j < SIZE_MAP; j++) {
                System.out.print(humanPlayer.getElementMap(i, j) + "  ");
            }
        }
        System.out.println();
    }

    private void prepareMap() {
        computerPlayer.iniciateMap();
        humanPlayer.iniciateMap();
    }

    private void play() {
        while (true) {
            System.out.println(System.lineSeparator());
            while (true) {
                pointShot = humanPlayer.shotShip();
                if (computerPlayer.getElementMap(pointShot.y, pointShot.x) != '1' && computerPlayer.getElementMap(pointShot.y, pointShot.x) != 'x') {
                    break;
                }
                System.out.println("You already typed this field.Type another one :)");
            }
            if (computerPlayer.isShottedSetMapAndIsSunk(pointShot)) {
                if (computerPlayer.allDrowned()) {
                    printMaps();
                    System.out.println("\t\t\tYou won!!!");
                    break;
                }
                System.out.println("\t\t\tYou Hit Ship");
                if (computerPlayer.isSunkShip()) {
                    System.out.println("\t\t\tYou sank Ship!");
                }
            } else {
                System.out.println("\t\t\tYou mishit!");
            }

            pointShot = computerPlayer.shotShip();
            if (humanPlayer.isShottedSetMapAndIsSunk(pointShot)) {// is Sotted nie zrobioa
                System.out.println("\t\t\tComputer hit ship: " + ReadCord.changePoint(pointShot));
                if (computerPlayer.getSecondStage() == true) {
                    computerPlayer.setWasShottedLastTime(true);
                } else {
                    computerPlayer.setSecondStage(true);
                }
                if (humanPlayer.isSunkShip()) {
                    if (humanPlayer.allDrowned()) {
                        printMaps();
                        System.out.println("\t\t\tComputer Win");
                        break;
                    }
                    System.out.println("\t\t\tAnd sank your Ship!");
                    computerPlayer.setSecondStage(false);
                    computerPlayer.setWasShottedLastTime(false);
                    computerPlayer.setWasNOTShottedLastTime(false);
                    computerPlayer.setAllDrectionsTrue();
                    computerPlayer.setSunkShip(false);
                    computerPlayer.setFirstTimeComeToSecondStage();
                    computerPlayer.clearTableOfGeneratetPoints();
                }
            } else {
                System.out.println("\t\t\tComputer mishit: " + ReadCord.changePoint(pointShot));
                if (computerPlayer.getSecondStage()) {
                    computerPlayer.setWasNOTShottedLastTime(true);
                }
            }
            printMaps();
        }
    }


    public static void main(String[] args) {
        ShipGame shipGame = new ShipGame();

        System.out.println("Game started!" + System.lineSeparator() + "Now you will be put your " + shipGame.maxNumbersOfShips + " ships in map");
        System.out.println("Size ship is 3 filds");
        System.out.println("Type in number  \"1\"-\"7\" and charakter: \"A\"-\"G\"");
        System.out.println("For example 1A 2A 3A");

        shipGame.initiateMap();

        System.out.println(System.lineSeparator() + "you successfully put your ships on map.Now you can compete with Computer.");
        System.out.println("Enter imput like: \"2G\" or \"5F\"");
        System.out.println("'1' = hit ship\t\t'x' = mishit");

        shipGame.prepareMap();
        shipGame.play();
    }
}
