import java.awt.*;


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
