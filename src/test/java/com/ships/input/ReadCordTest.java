package com.ships.input;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

class ReadCordTest {
    static ReadCord readCord;
    static HashMap<Point, LinkedList<String>> goodSingleInput;
    static LinkedList<String> badSingleInput;
    static HashMap<LinkedList<Point>, LinkedList<String>> goodMultipleInput;
    static LinkedList<String> badMultipleInput;

    @BeforeAll
    static void setUp() {
        readCord = new ReadCord();
        goodSingleInput = new HashMap<>();
        putGoodInputToHashMap();
        badSingleInput = putBadSingleInputToList();

        goodMultipleInput = new HashMap<>();
        putGoodMultipleInputToHashMap();
        badMultipleInput = putBadMultipleInputToList();

    }

    @Test
    void changePointTest() {
        assertEquals("7A", ReadCord.changePoint(new Point(6, 0)));
        assertEquals("1A", ReadCord.changePoint(new Point(0, 0)));
        assertEquals("5C", ReadCord.changePoint(new Point(4, 2)));
        assertEquals("9A", ReadCord.changePoint(new Point(8, 0)));
        assertEquals("9J", ReadCord.changePoint(new Point(8, 9)));
        assertEquals("10J", ReadCord.changePoint(new Point(9, 9)));
    }

    @Test
    void readPointMethodShouldAssertTrueForUserInput() {
//        goodSingleInput.forEach((point, stringLinkedList) -> {
//            stringLinkedList.forEach(input -> {
//                putToSystemInput(input);
//                assertEquals(point, readCord.readPoint());
//            });
//        });
        for (Map.Entry<Point, LinkedList<String>> entry : goodSingleInput.entrySet()) {
            for (String input : entry.getValue()) {
                putToSystemInput(input);
                Point result = readCord.readPoint();
                Point expected = entry.getKey();
                assertEquals(expected, result);
            }
        }
    }

    @Test
    void readPointMethodShouldReturnNullForBadUserInput() {
        badSingleInput.forEach(input -> {
            putToSystemInput(input);
            assertNull(readCord.readPoint());
        });
    }

    @Test
    void readMorePointsGoodInputTest() {
        goodMultipleInput.forEach((pointLinkedList, stringLinkedList) -> {
            stringLinkedList.forEach(input -> {
                putToSystemInput(input);
                ArrayList<Point> list = readCord.readPoints();
                list.removeAll(pointLinkedList);
                assertTrue(list.isEmpty());
            });
        });
//        for (Map.Entry<LinkedList<Point>,LinkedList<String>> entry : goodMultipleInput.entrySet()){
//            for(String input:entry.getValue()){
//                putToSystemInput(input);
//                ArrayList<Point> list = readCord.readPoints();
//                list.removeAll(entry.getKey());
//                assertTrue(list.isEmpty());
//            }
//        }
    }


    @Test
    void readMorePointsShouldReturnNullForBadMultipleInput() {
        for (String input : badMultipleInput) {
            putToSystemInput(input);
            ArrayList<Point> arrayList = readCord.readPoints();
            assertNull(arrayList);
        }
    }

    private static void putGoodInputToHashMap() {
        String[] input = new String[]{"1a", "a1", "  1A", "A1  "};
        LinkedList<String> list = new LinkedList<>(Arrays.asList(input));
        goodSingleInput.put(new Point(0, 0), list);

        input = new String[]{"7G", "G7", "    7g", "7g  ", "7g"};
        list = new LinkedList<>(Arrays.asList(input));
        goodSingleInput.put(new Point(6, 6), list);

        input = new String[]{" 6a ", "a6", "A6", "6A"};
        list = new LinkedList<>(Arrays.asList(input));
        goodSingleInput.put(new Point(5, 0), list);

        input = new String[]{"c4", "C4", "4c", "4C"};
        list = new LinkedList<>(Arrays.asList(input));
        goodSingleInput.put(new Point(3, 2), list);

        input = new String[]{"10J", "J10", " j10 ", "10J  "};
        list = new LinkedList<>(Arrays.asList(input));
        goodSingleInput.put(new Point(9, 9), list);

        input = new String[]{" 9J", "9j ", " j9 ", "9J  "};
        list = new LinkedList<>(Arrays.asList(input));
        goodSingleInput.put(new Point(8, 9), list);

        input = new String[]{" 10I", "I10 ", " i10 ", " 10i  "};
        list = new LinkedList<>(Arrays.asList(input));
        goodSingleInput.put(new Point(9, 8), list);
    }

    private static LinkedList<String> putBadSingleInputToList() {
        String[] badUserInput = {
                "0a", "v1", "5 g",
                "gg", "11", "12",
                "AA", " B 5", "11C",
                "54", "ab", "abc",
                "66", "cc"
        };
        return new LinkedList<>(Arrays.asList(badUserInput));
    }

    private static void putGoodMultipleInputToHashMap() {
        LinkedList<Point> listPoints = new LinkedList<>();
        listPoints.add(new Point(2, 2));
        listPoints.add(new Point(2, 3));
        listPoints.add(new Point(2, 4));
        String[] goodInputPoints = new String[]{"3C 3D 3E", "3E  3d c3 ", "  d3   c3 e3"};
        LinkedList<String> listUserInput = new LinkedList<>(Arrays.asList(goodInputPoints));
        goodMultipleInput.put(listPoints, listUserInput);

        listPoints = new LinkedList<>();
        listPoints.add(new Point(0, 0));
        listPoints.add(new Point(0, 1));
        listPoints.add(new Point(0, 2));
        goodInputPoints = new String[]{"1A 1B 1C", " 1c b1 1A", " b1  c1 1a"};
        listUserInput = new LinkedList<>(Arrays.asList(goodInputPoints));
        goodMultipleInput.put(listPoints, listUserInput);

        listPoints = new LinkedList<>();
        listPoints.add(new Point(0, 7));
        goodInputPoints = new String[]{"1h "};
        listUserInput = new LinkedList<>(Arrays.asList(goodInputPoints));
        goodMultipleInput.put(listPoints, listUserInput);

        listPoints = new LinkedList<>();
        listPoints.add(new Point(9, 6));
        listPoints.add(new Point(9, 7));
        listPoints.add(new Point(9, 8));
        listPoints.add(new Point(9, 9));
        goodInputPoints = new String[]{"10G 10H 10I 10J", " 10H G10  I10  10J", "J10 I10 H10 10G"};
        listUserInput = new LinkedList<>(Arrays.asList(goodInputPoints));
        goodMultipleInput.put(listPoints, listUserInput);

        listPoints = new LinkedList<>();
        listPoints.add(new Point(9, 9));
        listPoints.add(new Point(8, 9));
        listPoints.add(new Point(7, 9));
        listPoints.add(new Point(6, 9));
        listPoints.add(new Point(5, 9));
        goodInputPoints = new String[]{"7J 8J 9J 10J 6J", " 6J 7j 8j  9j j10  ", "j10 9j j8 6j 7j  "};
        listUserInput = new LinkedList<>(Arrays.asList(goodInputPoints));
        goodMultipleInput.put(listPoints, listUserInput);
    }

    private static LinkedList<String> putBadMultipleInputToList() {
        String[] badUserInPut = {"a1 a2 a 3", "a1a1a1", "a1 b1 c2",
                "c2 d2 b", "c4 44 c5", " _", "a1 a1 a1", " "};
        return new LinkedList<>(Arrays.asList(badUserInPut));
    }

    private void putToSystemInput(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }
}