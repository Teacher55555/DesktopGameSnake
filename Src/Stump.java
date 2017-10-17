import java.awt.*;


/**
 * Created by Igor Gridin on 05.05.17.
 * <p>
 * The Snail class extends the Dot class and controls the coordinates and frequency of respawn of its elements
 **/


class Stump extends Dot {
    private Stump anotherStump;

    Stump(Snake Player1snake, Snake Player2snake, int sleeptime, int count, int frequency) {
        super(Player1snake, Player2snake);
        super.sleepTime = sleeptime;
        super.objectWidth = 200;
        super.objectHight = 120;
        super.startX = 30;
        super.startY = 30;
        super.edgeX = 820;
        super.edgeY = 460;
        super.frequency = frequency;
        super.count = count;
        super.setRestrictedArea(new Rectangle(0, 0, 150, 100));
    }

    void setAnotherStump(Stump anotherStump) {
        this.anotherStump = anotherStump;
    } // Stumps mustn't overlap each other

    @Override
    boolean checkRestrictedArea() {
        if (anotherStump == null) {
            return super.checkRestrictedArea();
        }
        return new Rectangle(this.x, this.y, 200, 120).intersects(new Rectangle(anotherStump.x, anotherStump.y, 200, 120)) || super.checkRestrictedArea();
    }
}