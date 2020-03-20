package com.ships.input;

import java.awt.Point;
import java.util.*;

public class ReadCord {

    public static String changePoint(Point point) {
        return ("" + (point.x + 1) + changeNumberToCharacter(point.y));
    }

    public Point readPoint() {
        int[] cordsInTab = new int[4];

        Scanner scanner = new Scanner(System.in);
        String txtBufor = scanner.nextLine();

        txtBufor = txtBufor.trim().toUpperCase();
        makeAndCheckInput(txtBufor, cordsInTab);

        if (correctInput(cordsInTab)) {
            return null;
        }
        return new Point(cordsInTab[1], cordsInTab[0]);
    }

    public ArrayList<Point> readPoints() {
        ArrayPoints arrayPoints = new ArrayPoints();
        ArrayList<Point> result = new ArrayList<>();
        int[] cordsInTab = new int[4];
        Scanner scanner = new Scanner(System.in);

        String bufor = scanner.nextLine();
        bufor = bufor.trim().toUpperCase();

        if (bufor.equals("END")) {
            result.add(new Point(666, 0));
            return result;
        }
        if (bufor.equals("GENERATE")) {
            result.add(new Point(777, 0));
            return result;
        }

        String[] splitTxt = bufor.split(" ");

        for (int i = 0; i < splitTxt.length; i++) {
            if (splitTxt[i].isEmpty()) {
                continue;
            }
            String txt = splitTxt[i];

            makeAndCheckInput(txt, cordsInTab);

            if (correctInput(cordsInTab)) {
                return null;
            }
            Point point = new Point(cordsInTab[1], cordsInTab[0]);
            if(result.contains(point)){
                return null;
            };//added
            result.add(point);
            resetTable(cordsInTab);
        }
        if (arrayPoints.areString(result) && arrayPoints.areInOneLine(result)) {
            return result;
        }
        return null;
    }

    private void makeAndCheckInput(String txt, int[] tabCharacter) {
        for (int i = 0; i < txt.length(); i++) {
            int buf = txt.charAt(i);
            tabCharacter[3]++;
            if (isCharacter(buf)) {
                tabCharacter[0] = changeCharacterToNumber(txt.charAt(i)); //y
                tabCharacter[2] += 2;
            }
            if (isNumber(buf)) {
                if ((i + 1) < txt.length() && txt.charAt(i + 1) == '0') {
                    String number10 = "" + txt.charAt(i) + txt.charAt(i + 1);
                    tabCharacter[1] = Integer.parseInt(number10);
                    i++;
                } else {
                    tabCharacter[1] = Integer.parseInt(String.valueOf(txt.charAt(i))); //x
                }
                tabCharacter[1] -= 1; //bicause map start form 0,user only think he put "1"
                tabCharacter[2] += 1;
            }
        }
    }

    private int changeCharacterToNumber(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            case 'I':
                return 8;
            case 'J':
                return 9;
            default:
                return -1;
        }
    }

    private static char changeNumberToCharacter(int number) {
        switch (number) {
            case 0:
                return 'A';
            case 1:
                return 'B';
            case 2:
                return 'C';
            case 3:
                return 'D';
            case 4:
                return 'E';
            case 5:
                return 'F';
            case 6:
                return 'G';
            case 7:
                return 'H';
            case 8:
                return 'I';
            case 9:
                return 'J';
            default:
                return '?';
        }
    }

    private boolean correctInput(int[] cordsInTab) {
        return cordsInTab[2] != 3 || cordsInTab[3] != 2;
    }

    private boolean isCharacter(int c) {
        return c >= 65 && c <= 74;
    }

    private boolean isNumber(int c) {
        return c >= 49 && c <= 58;
    }

    private void resetTable(int[] cordsInTab) {
        for (int j = 0; j < cordsInTab.length; j++) {
            cordsInTab[j] = 0;
        }
    }
}
