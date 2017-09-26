import java.awt.*;
import java.awt.geom.Rectangle2D;


/**
 * Created by Igor Gridin on 05.05.17.
 * <p>
 * This class is base abstract class for next game elements: fruits, coin, scissors, snail, stumps.
 * It's manages coordinates and frequency of respawn elements .
 **/

abstract public class Dot extends Thread {
    private Dimension coordinates; //Keeps coordinates of the element;
    int sleepTime; //time, after which the element will change coordinates or will no show
    int x; //random generated x position of the element
    int y; //random generated y position of the element
    int startX; // x = x + startX // Some elements (like stumps) mustn't show up in the specific range of the game coordinates
    int startY; // y = y + startY // Some elements (like stumps) mustn't show up in the specific range of the game coordinates
    int edgeX = 960; // The edge of the game coordinate X
    int edgeY = 560; // The edge of the game coordinate X
    int objectWidth = 40; // Object Width
    int objectHight = 40; // Object Hight
    int count; // Every new coordinates increase the count by 1
    int frequency; // Each element has its own frequency level. If count % frequency == 0 => The element will get new coordinates

    //Thus, sleepTime, count and frequency control how long the element will be displayed, not displayed, or when it will change the coordinates

    private Rectangle restrictedArea; // A special area where the element will not appear
    private Snake snakePlayer1; // This is necessary to check the new coordinates of the element so that the element does not appear on the snake's body
    private Snake snakePlayer2; // This is necessary to check the new coordinates of the element so that the element does not appear on the snake's body
    private int fruittype = 6;// This is for the random creation of fruit types: 0 - apple, 1 - cherry, 2 - banana, 3 - grapes, 4 - strawberry, 5 - kiwi, 6 = null

    @Override
    public void run() { // Generate new coordinates and wait sleepTime
        while (isAlive()) {
            try {
                makeDot();
                Thread.sleep(sleepTime);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    Dot(Snake snakePlayer1, Snake snakePlayer2) { //receive snake bodies
        this.snakePlayer1 = snakePlayer1;
        this.snakePlayer2 = snakePlayer2;
        restrictedArea = new Rectangle();
        start();
    }

    void setRestrictedArea(Rectangle restrictedArea) { //setter
        this.restrictedArea = restrictedArea;
    }

    boolean checkRestrictedArea() { //Check if the element in the restricted area
        return new Rectangle(x, y, objectWidth, objectHight).intersects(restrictedArea);
    }

    Dimension makeDot() { // generates new coordinates
        int fruitVariations = 7;
        if (count % frequency == 0) {
            do {
                x = startX + (int) (Math.random() * edgeX);
                y = startY + (int) (Math.random() * edgeY);
            } while (newFruitInBodyCheck() || checkRestrictedArea());
        } else {
            count++;
            return coordinates = new Dimension(-500, -500); // return coordinates outside the game area
        }
        count++;
        fruittype = (int) (Math.random() * fruitVariations); // generate random fruitType
        return coordinates = new Dimension(x, y);
    }

    Dimension getCoordinates() {
        return this.coordinates;
    } // Getter

    int getFruittype() {
        return fruittype;
    } // Getter

    private boolean newFruitInBodyCheck() { // Check the new coordinates of the element so that the element does not appear on the snake's body
        for (Integer[] body : snakePlayer1.getBody()) {
            Rectangle2D bodyRec = new Rectangle(body[0], body[1], 30, 30);
            Rectangle2D fruitRec = new Rectangle(x, y, objectWidth, objectHight);

            if (bodyRec.intersects(fruitRec)) {
                return true;
            }
        }

        if (snakePlayer2 != null) {
            for (Integer[] body : snakePlayer2.getBody()) {
                Rectangle2D bodyRec = new Rectangle(body[0], body[1], 30, 30);
                Rectangle2D fruitRec = new Rectangle(x, y, objectWidth, objectHight);

                if (bodyRec.intersects(fruitRec)) {
                    return true;
                }
            }
        }
        return false;
    }
}
