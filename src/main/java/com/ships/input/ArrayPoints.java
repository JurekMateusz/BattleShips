package com.ships.input;

import com.ships.ship.Ship;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class ArrayPoints {
    public boolean areInOneLine(ArrayList<Point> points) {
        if (badGivenParametr(points)) {
            return false;
        }
        if (points.size() == 1) {
            return true;
        }
        ArrayList<Point> copyPoints = new ArrayList<>(points);
        if (isNecessary(copyPoints)) {
            if (isInLine(copyPoints, 'X')) {
                return true;
            }
            if (isInLine(copyPoints, 'Y')) {
                return true;
            }
        }
        return false;
    }

    public boolean areString(ArrayList<Point> points) {
        if (badGivenParametr(points)) {
            return false;
        }
        if (points.size() == 1) {
            return true;
        }
        ArrayList<Point> copyPoints = new ArrayList<>(points);
        if (!areInOneLine(copyPoints)) {
            return false;
        }
        sort(copyPoints, 'X');
        if (isGood(copyPoints, 'X')) {
            return true;
        }
        sort(copyPoints, 'Y');
        if (isGood(copyPoints, 'Y')) {
            return true;
        }
        return false;
    }

    public boolean areNotBordering(ArrayList<Point> points,ArrayList<Ship> ships) {
        if (badGivenParametr(points) || ships == null) {
            return false;
        }
        ArrayList<Ship> copyWithPoints = new ArrayList<>(ships);

        ArrayList<Point> borderingPoints = makeBorderingPointsToShip(copyWithPoints);
        for (Point point :points) {
            if(borderingPoints.contains(point)){
                return  false;
            }
        }
        return true;
    }
    public char pointsInLine(ArrayList<Point> points){
        if (isInLine(points, 'X')) {
            return 'X';
        }
        if (isInLine(points, 'Y')) {
            return 'Y';
        }
        return '?';
    }

    private boolean isNecessary(ArrayList<Point> points) {
        boolean sortByX = true;
        boolean sortByY = true;

        int x = (int) points.get(0).getX();
        int y = (int) points.get(0).getY();
        for (Point point : points) {
            if (x != point.getX()) {
                sortByY = false;
            }
            if (y != point.getY()) {
                sortByX = false;
            }
        }
        if ((!sortByX && !sortByY) || (sortByX && sortByY)) {
            return false;
        }
        return true;
    }

    private boolean badGivenParametr(ArrayList<Point> points) {
        return points == null || points.isEmpty();
    }

    private boolean isInLine(ArrayList<Point> points, char line) {
        line = changeToUpperCase(line);
        int valX = (int) points.get(0).getX();
        int valY = (int) points.get(0).getY();
        for (int i = 0; i < points.size(); i++) {
            if (line == 'Y') {
                if (valY != points.get(i).getY()) {
                    return false;
                }
            } else {
                if (valX != points.get(i).getX()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void sort(ArrayList<Point> pointsToSort, char sortBy) {
        sortBy = changeToUpperCase(sortBy);
        if (sortBy == 'X') {
            pointsToSort.sort(Comparator.comparing(Point::getX));
        } else {
            pointsToSort.sort(Comparator.comparing(Point::getY));
        }
    }

    private char changeToUpperCase(char character) {
        String st = "" + character;
        st.toUpperCase();
        return st.charAt(0);
    }

    private boolean isGood(ArrayList<Point> points, char checkBy) {
        checkBy = changeToUpperCase(checkBy);
        int valX = (int) points.get(0).getX();
        int valY = (int) points.get(0).getY();
        for (int i = 0; i < points.size(); i++) {
            if (checkBy == 'X') {
                if (valX++ != points.get(i).getX()) {
                    return false;
                }
            } else {
                if (valY++ != points.get(i).getY()) {
                    return false;
                }
            }
        }
        return true;
    }

    private ArrayList<Point> makeBorderingPointsToShip(ArrayList<Ship> ships) {
        ArrayList<Point> result = new ArrayList<>();
        char sortBy;
        for (Ship ship : ships) {
            ArrayList<Point> points = ship.getPoints();
            ArrayList<Point> copyPoints = new ArrayList<>(points);
            if (isInLine(copyPoints, 'X')) {
                sort(copyPoints, 'Y');
                sortBy = 'Y';
            } else {
                sort(copyPoints, 'X');
                sortBy = 'X';
            }
            Point minPoint = new Point(copyPoints.get(0));
            Point maxPoint = new Point(copyPoints.get(points.size() - 1));
            if (sortBy == 'X') {
                minPoint.setLocation(minPoint.getX()-1,minPoint.getY());
                result.add(new Point(minPoint));
                maxPoint.setLocation(maxPoint.getX()+1,maxPoint.getY());
                result.add(new Point(maxPoint));

                Point bufMinPoint = new Point(minPoint);
                minPoint.setLocation(minPoint.getX(),minPoint.getY()-1);
                while(minPoint.getX() <= maxPoint.getX()){
                    result.add(new Point(minPoint));
                    minPoint.setLocation(minPoint.getX()+1,minPoint.getY());
                }

                bufMinPoint.setLocation(bufMinPoint.getX(),bufMinPoint.getY()+1);
                while(bufMinPoint.getX() <= maxPoint.getX()){
                    result.add(new Point(bufMinPoint));
                    bufMinPoint.setLocation(bufMinPoint.getX()+1,bufMinPoint.getY() );
                }
            } else {
                minPoint.setLocation(minPoint.getX(),minPoint.getY()-1);
                result.add(new Point(minPoint));
                maxPoint.setLocation(maxPoint.getX(),maxPoint.getY()+1);
                result.add(new Point(maxPoint));

                Point bufMinPoint = new Point(minPoint);
                minPoint.setLocation(minPoint.getX()-1,minPoint.getY());
                while(minPoint.getY() <= maxPoint.getY()){
                    result.add(new Point(minPoint));
                    minPoint.setLocation(minPoint.getX(),minPoint.getY() +1);
                }

                bufMinPoint.setLocation(bufMinPoint.getX()+1,bufMinPoint.getY());
                while(bufMinPoint.getY() <= maxPoint.getY()){
                    result.add(new Point(bufMinPoint));
                    bufMinPoint.setLocation(bufMinPoint.getX(),bufMinPoint.getY() +1);
                }
            }
        }
        return result;
    }
}