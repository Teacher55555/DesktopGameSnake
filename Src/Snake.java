
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Igor Gridin on 05.05.17.
 * <p>
 * Snake class is the class witch manages snake movement, body size, checks when snake eat itself
 * or eat another snake (2 Players game)
 **/

public class Snake {

    private volatile ArrayList<Integer[]> body = new ArrayList<>();  // body of the snake. Each element keeps X and Y coordinates

    ArrayList<Integer[]> getBody() { // returns Snake body
        return body;
    }

    void addBody(int x, int y) {
        body.add(new Integer[]{x, y});   // adds 1 body element to the end of the snake body
    }

    void deleteBody(int count) {    // removes body elements. If count == -1 => clear all body
        if (count == -1) {
            body.clear();
        } else {
            for (int i = 0; i < count; i++) {
                body.remove(body.size() - 1);
            }
        }
    }

    int getBodySize() {      // returns size of the body
        return body.size();
    }

    void start(int x, int y, boolean isPlayer1Game, int bodyCount) { // sets up snake's coordinates and body amount when the game starts or when the snake has respawn
        body.clear();
        int count = 0;

        if (isPlayer1Game) {
            for (int i = 0; i < bodyCount; i++) {
                body.add(new Integer[]{x - count, y});
                count += 20;
            }
        } else {
            for (int i = 0; i < bodyCount; i++) {
                body.add(new Integer[]{x + count, y});
                count += 20;
            }
        }
    }

    void moveBody(int x, int y) {               // Moves the snake by 1 step, adding the received coordinates to the beginning of the body and simultaneously removing the last coordinates from the end of the body
        body.add(0, new Integer[]{x, y});
        body.remove(body.size() - 1);
    }

    boolean selfEated(int x, int y) {     // Checks, if the head of the snake has collision with it's body
        Boolean gameOver = false;

        for (int i = 1; i < body.size(); i++) {
            if (x == body.get(i)[0] && y == body.get(i)[1]) {
                gameOver = true;
            }
        }
        return gameOver;
    }

    synchronized int oneSnakeEatAnother(Snake snake) {         // For 2 Players game. Checks, if snake has collision with another snake.
        int result = 0; // Returns 0 - no collision, 1 - collision, 2 - heads collision


        if (body.size() == 0 || snake.body.size() == 0) {
            return result;
        }

        Point headOneSnake = new Point(this.body.get(0)[0], this.body.get(0)[1]);
        Point headAnotherSnake = new Point(snake.body.get(0)[0], snake.body.get(0)[1]);

        if (headOneSnake.equals(headAnotherSnake)) {
            return 2;
        }

        for (int i = 1; i < snake.body.size(); i++) {
            Point bodyAnotherSnake = new Point(snake.body.get(i)[0], snake.body.get(i)[1]);
            if (headOneSnake.equals(bodyAnotherSnake)) {
                return 1;
            }
        }
        return result;
    }
}

