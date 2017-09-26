

import java.awt.*;

class Fruit extends Dot {

    Fruit (Snake Player1snake,Snake Player2snake, int sleeptime, int count, int frequency){
        super(Player1snake,Player2snake);
        super.count = count;
        super.frequency = frequency;
        super.sleepTime = sleeptime;
    }

    Dimension makeFruit () {
       return super.makeDot();
    }

}
