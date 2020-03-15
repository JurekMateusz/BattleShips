package com.ships.input;

import java.awt.Point;
import java.util.*;

public class ReadCord {
    public static String changePoint(Point point) {
        return ("" + (point.x + 1) + changeNumberTocharacter(point.y));
    }

    public Point readPoint() {
        int[] cordsInTab = new int[4]; //respectively x ,y ,tab[2] and tab[3] control good imput
        String txtBufor;
        Scanner scanner = new Scanner(System.in);
        txtBufor = scanner.nextLine();
        makeGoodInput(txtBufor, cordsInTab);
        if (cordsInTab[2] != 3 || cordsInTab[3] != 2) {
            return null;
        }
        return new Point(cordsInTab[1], cordsInTab[0]);
    }

    public ArrayList<Point> readPoints() {
        int[] cordsInTab = new int[4];//respectively x ,y ,tab[2] and tab[3] control good imput
        ArrayList<Point> result = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String bufor = scanner.nextLine();
        String[] splitBufor = bufor.split(" ");
        for (String txt : splitBufor) {
            if (txt.isEmpty()) {
                continue;
            }
            makeGoodInput(txt, cordsInTab);
            if (cordsInTab[2] != 3 || cordsInTab[3] != 2) {
                return null;
            }
            result.add(new Point(cordsInTab[1], cordsInTab[0]));
            //reset all table.
            for (int i = 0; i < cordsInTab.length; i++) {
                cordsInTab[i] = 0;
            }
        }
        if (result.size() == 1) {
            return result;
        }
        if (!correctPoints(result)) {
            return null;
        }
        return result;
    }

    private void makeGoodInput(String txt, int[] tabCharacter) {
        txt = txt.toUpperCase();
        txt = txt.trim();
        for (int i = 0; i < txt.length(); i++) {
            int buf = txt.charAt(i);
            tabCharacter[3]++;
            if (buf >= 65 && buf <= 71) {
                tabCharacter[0] = changeCharacterToNumber(txt.charAt(i)); //y
                tabCharacter[2] += 2;
            }
            if (buf >= 49 && buf <= 55) {
                tabCharacter[1] = Integer.parseInt(String.valueOf(txt.charAt(i))); //x
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

    private static char changeNumberTocharacter(int number) {
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

    private boolean correctPoints(ArrayList<Point> arrayList) {
        //this method check points is correct(in one line).  5A 5B 5C -correct , 5A 6D A1 -NOT
        boolean sortByX = true;
        boolean sortByY = true;

        int x = (int) arrayList.get(0).getX();
        int y = (int) arrayList.get(0).getY();
        for (Point point : arrayList) {
            if (x != point.getX()) {
                sortByY = false;
            }
            if (y != point.getY()) {
                sortByX = false;
            }
        }
        if (!sortByX && !sortByY) {
            return false;
        }
        if (sortByX && sortByY) {
            return false;
        }
        if (sortByX) {
            Collections.sort(arrayList, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    return Integer.compare((int) o1.getX(), (int) o2.getX());
                }
            });
        } else {
            Collections.sort(arrayList, new Comparator<Point>() {
                public int compare(Point o1, Point o2) {
                    return Integer.compare((int) o1.getY(), (int) o2.getY());
                }
            });
        }
        x = (int) arrayList.get(0).getX();
        y = (int) arrayList.get(0).getY();
        for (int i = 0; i < arrayList.size(); i++) {
            if (sortByX) {
                if (x++ != arrayList.get(i).getX()) {
                    return false;
                }
            } else {
                if (y++ != arrayList.get(i).getY()) {
                    return false;
                }
            }
        }
        return true;
    }
}
