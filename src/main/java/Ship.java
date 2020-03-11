import java.awt.*;
import java.util.ArrayList;

public class Ship {
    private ArrayList<Point> points;

    Ship(ArrayList<Point> arrayPoints) {
        points = new ArrayList<>(arrayPoints);
    }

    ArrayList<Point> getListOfPoints() {
        return points;
    }

}
