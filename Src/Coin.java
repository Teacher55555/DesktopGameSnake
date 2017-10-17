import java.awt.*;
/**
 * Created by Igor Gridin on 05.05.17.
 * <p>
 * The Coin class extends the Dot class and controls the coordinates and frequency of respawn of its elements
 **/

class Coin extends Dot {
    Coin (Snake Player1snake, Snake Player2Snake, int sleeptime, int count, int frequency ){
        super(Player1snake, Player2Snake);
        super.count = count;
        super.sleepTime = sleeptime;
        super.frequency = frequency;
}
    Dimension makeCoin () {
        return super.makeDot();
    }

}
