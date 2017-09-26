import java.awt.*;

 class Scissors extends Dot {
    Scissors (Snake Player1snake, Snake Player2Snake, int sleeptime, int count, int frequency){
        super(Player1snake,Player2Snake);
        super.edgeX = 930;
        super.edgeY = 520;
        super.count = count;
        super.frequency = frequency;
        super.sleepTime = sleeptime;
    }

    Dimension makeScissors () {
        return super.makeDot();
    }
}
