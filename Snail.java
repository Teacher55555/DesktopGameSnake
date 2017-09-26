import java.awt.*;

 class Snail extends Dot {
    Snail (Snake Player1snake, Snake Player2Snake, int sleeptime, int count, int frequency){
        super(Player1snake, Player2Snake);
        super.edgeX = 930;
        super.edgeY = 530;
        super.count = count;
        super.frequency = frequency;
        super.sleepTime = sleeptime;
    }

    Dimension makeSnail () {
        return super.makeDot();
    }

}
