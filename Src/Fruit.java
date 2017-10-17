

import java.awt.*;
/**
 * Created by Igor Gridin on 05.05.17.
 * <p>
 * The Fruit class extends the Dot class and controls the coordinates and frequency of respawn of its elements
 **/

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
