
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Igor Gridin on 05.05.17.
 * <p>
 * This class extends Handler and controls the game for 2 players.
 * It overrides some methods of the Handler class considering movement, graphics and etc of the snake of the second player
 *
 * The goal is to get >=40 score points first. Fruit gets 1 score points. Coin gets 5 score points. Scissors reduced
 * 5 score points.
 **/

public class Handler2Players extends Handler {
    volatile private Snake Player2snake; // keeps player 2 snake's body coordinates
    volatile private Snake Player1snake; // keeps player 1 snake's body coordinates
    private int Player2DotEatenCount; //player 2 Score. if it >= 40 Player 2 win.
    private int fruitEatenCount; ////it's needs for control game speed
    private int player2SkipMoveCount = 61; // it determines how many steps player 2 will stand idle if he did a mistake
    private int player1SkipMoveCount = 61; // it determines how many steps player 1 will stand idle if he did a mistake
    private int Player2X = 920; // player 2 X position
    private int Player2Y = 540; // player 2 Y position
    private int Player2stepX; // player 2 X step
    private int Player2stepY; // player 2 Y step
    private boolean isPlayer2Dead; //if true, will be drew dead head of the snake

    // Game objects
    private GameGUI gameGUI;
    private Music music = new Music();
    private MyFont myFont = new MyFont();

    //Images
    private Image headImagePlayer2;
    private Image headImageUp2;
    private Image headImageDown2;
    private Image headImageLeft2;
    private Image headImageRight2;
    private Image headImageRightDead2;
    private Image headImageLeftDead2;
    private Image headImageDownDead2;
    private Image headImageUpDead2;
    private Image bodyImageHorizontal2;
    private Image bodyImageVertical2;
    private Image bodyImageUpLeft2;
    private Image bodyImageUpRight2;
    private Image bodyImageDownRight2;
    private Image bodyImageDownLeft2;
    private Image tailImageUp2;
    private Image tailImageDown2;
    private Image tailImageLeft2;
    private Image tailImageRight2;
    private JLabel birdsLabelPlayer1;
    private JLabel birdsLabelPlayer2;
    private JLabel centreLabel;
    private JLabel kissLabel;
    private ImageIcon birdsIcon;
    private ImageIcon loveIcon;


    @Override
    void imageInitializer() {  // loads graphics
        super.imageInitializer();
        try {
            // Player 2 head images
            headImageUp2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleHeadUp.png").getFile()));
            headImageDown2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleHeadDown.png").getFile()));
            headImageLeft2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleHeadLeft.png").getFile()));
            headImageRight2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleHeadRight.png").getFile()));
            headImagePlayer2 = headImageLeft2; // The default head position for player 1 snake

            // Player 2 dead head images
            headImageRightDead2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleHeadRightDead.png").getFile()));
            headImageLeftDead2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleHeadLeftDead.png").getFile()));
            headImageUpDead2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleHeadUpDead.png").getFile()));
            headImageDownDead2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleHeadDownDead.png").getFile()));

            // Player 2 body images
            bodyImageHorizontal2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleBodyHorizontal.png").getFile()));
            bodyImageVertical2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleBodyVertical.png").getFile()));

            // Player 2 corner body images
            bodyImageUpLeft2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleBodyUpLeft.png").getFile()));
            bodyImageUpRight2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleBodyUpRight.png").getFile()));
            bodyImageDownRight2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleBodyDownRight.png").getFile()));
            bodyImageDownLeft2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleBodyDownLeft.png").getFile()));

            // Player 2 tail images
            tailImageUp2= ImageIO.read(new File(getClass().getResource("Res/Img/PurpleTailUp.png").getFile()));
            tailImageDown2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleTailDown.png").getFile()));
            tailImageLeft2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleTailLeft.png").getFile()));
            tailImageRight2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleTailRight.png").getFile()));

            // when player stands idle, will be drew birds over snake head
            birdsIcon = new ImageIcon(getClass().getResource("Res/Img/Stars.gif"));

            //if two players will have collision by the heads of the snakes, will be drew heart over their heads
            loveIcon = new ImageIcon(getClass().getResource("Res/Img/Love.gif"));

        } catch (Exception ex){ex.printStackTrace();}
    }

    // Constructor: invokes Handler constructor but resets new values for fruit, scissors, snail, stumps, coin and adds
    // 2 player's snake and some new graphics (birds over the head and heart)
    Handler2Players () {
        super(false);
        this.Player1snake = getPlayer1snake();
        this.gameGUI = getGameGUI();
        Player2snake = new Snake();
        Player2snake.start(Player2X, Player2Y, false,5);
        setLayout(null);

        kissLabel = new JLabel(loveIcon);
        birdsLabelPlayer1 = new JLabel(birdsIcon);
        birdsLabelPlayer2 = new JLabel(birdsIcon);
        birdsLabelPlayer1.setVisible(false);
        birdsLabelPlayer2.setVisible(false);
        kissLabel.setVisible(false);
        birdsLabelPlayer1.setBounds(40,10,50,50);
        birdsLabelPlayer2.setBounds(910,510,50,50);

        add(kissLabel);
        add(birdsLabelPlayer1);
        add(birdsLabelPlayer2);
        add(centreLabel = new JLabel());
        centreLabel.setFont((myFont.getMyFont(150f)));

        setScissors(new Scissors(Player1snake, Player2snake,3000,1,9));
        setCoin(new Coin(Player1snake, Player2snake,3000,1,6));
        setSnail(new Snail(Player1snake, Player2snake,3000,1,10));
        setStump(new Stump(Player1snake, Player2snake,11000,2,3));
        setStump2(new Stump(Player1snake, Player2snake,26000,1,2));

        getStump().setAnotherStump(getStump2());
        getStump2().setAnotherStump(getStump());

        ArrayList <Fruit> fruits = new ArrayList<>();

        fruits.add(new Fruit(Player1snake,Player2snake,9000,1,1));
        fruits.add(new Fruit(Player1snake,Player2snake,5000,1,3));

        setFruits(fruits);

      }

    // increases player score by 1 and counter of eaten fruit by 1. Sets game speed to maximum if eaten fruit by
    // player 1 and player 2 equal 10
    @Override
    void eatFruitCheck() {
        boolean fruitEaten = false;
        for (Fruit fruit : getFruits()) {
            if (fruit.getCoordinates() != null) {
                Rectangle2D fruitRectangle = new Rectangle(fruit.getCoordinates().width, fruit.getCoordinates().height, 40, 40);
                Rectangle2D snake1Rectangle = new Rectangle(getPlayer1X(), getPlayer1Y(), 20, 20);
                Rectangle2D snake2Rectangle = new Rectangle(Player2X, Player2Y, 20, 20);

                if (fruit.getCoordinates() != null && fruitRectangle.intersects(snake1Rectangle)) {
                    Player1snake.addBody(getPlayer1X(), getPlayer1Y());
                    music.eatFruitPlay();
                    fruit.interrupt();
                    setPlayer1dotEatenCount(getPlayer1dotEatenCount() + 1);
                    gameGUI.setPlayer1Score(getPlayer1dotEatenCount());
                    fruit.makeFruit();
                    fruitEatenCount++;
                    fruitEaten = true;
                }
                    if (fruit.getCoordinates() != null && fruitRectangle.intersects(snake2Rectangle)) {
                        Player2snake.addBody(Player2X, Player2Y);
                        music.eatFruitPlay();
                        fruit.interrupt();
                        Player2DotEatenCount++;
                        gameGUI.setPlayer2Score(Player2DotEatenCount);
                        fruit.makeFruit();
                        fruitEatenCount++;
                        fruitEaten = true;
                    }
                    if (fruitEaten){
                        if (fruitEatenCount != 0 && fruitEatenCount == 10 && getPlayer1Speed() < 3) {
                            music.speedUpPlay();
                            setPlayer1Speed(4);
                            speedControl();
                            fruitEatenCount = 0;
                        }
                    }
                }
            }
    }

    // increases player score by 5 points
    @Override
    void coinTakeCheck() {
        if (getCoin() != null) {
            Rectangle2D coinRec = new Rectangle(getCoin().getCoordinates().width, getCoin().getCoordinates().height, 40, 40);
            Rectangle2D snake1Rectangle = new Rectangle(getPlayer1X(), getPlayer1Y(), 20, 20);
            Rectangle2D snake2Rectangle = new Rectangle(Player2X, Player2Y, 20, 20);
            if (coinRec.intersects(snake1Rectangle)) {
                music.coinTakePlay();
                setPlayer1dotEatenCount(getPlayer1dotEatenCount() + 5);
                gameGUI.setPlayer1Score(getPlayer1dotEatenCount());
                getCoin().makeCoin();
            }
            if (coinRec.intersects(snake2Rectangle)) {
                Player2DotEatenCount += 5;
                gameGUI.setPlayer2Score(Player2DotEatenCount);
                music.coinTakePlay();
                getCoin().makeCoin();
            }
        }
    }

    // reduces the opponent player score by 5 points
    @Override
    void scissorsTakeCheck() {
            if (getScissors() != null) {
                Rectangle2D scissorsRec = new Rectangle(getScissors().getCoordinates().width, getScissors().getCoordinates().height, 40, 60);
                Rectangle2D snake1Rectangle = new Rectangle(getPlayer1X(), getPlayer1Y(), 20, 20);
                Rectangle2D snake2Rectangle = new Rectangle(Player2X, Player2Y, 20, 20);
                if (scissorsRec.intersects(snake1Rectangle)) {
                    if (Player2DotEatenCount < 5){
                        Player2DotEatenCount = 0;
                    } else {
                        Player2DotEatenCount -= 5;
                    }
                        gameGUI.setPlayer2Score(Player2DotEatenCount);
                        Player2snake.deleteBody(Player2snake.getBodySize()-5);
                    getScissors().makeScissors();
                    music.scissorsPlay();
                }
                if (scissorsRec.intersects(snake2Rectangle)) {
                    if (getPlayer1dotEatenCount() < 5){
                        setPlayer1dotEatenCount(0);
                    } else {
                        setPlayer1dotEatenCount(getPlayer1dotEatenCount() - 5);
                    }
                        gameGUI.setPlayer1Score(getPlayer1dotEatenCount());
                        Player1snake.deleteBody(Player1snake.getBodySize()-5);
                    getScissors().makeScissors();
                    music.scissorsPlay();
                }
            }
        }

    // sets game speed to minimum
    @Override
    void snailTakeCheck() {
        if (getSnail() != null) {
            Rectangle2D snailRec = new Rectangle(getSnail().getCoordinates().width, getSnail().getCoordinates().height, 50, 50);
            Rectangle2D snake1Rectangle = new Rectangle(getPlayer1X(), getPlayer1Y(), 20, 20);
            Rectangle2D snake2Rectangle = new Rectangle(Player2X, Player2Y, 20, 20);
            if (snailRec.intersects(snake1Rectangle) || snailRec.intersects(snake2Rectangle)) {
                setPlayer1Speed(0);
                speedControl();
                fruitEatenCount = 0;
                music.speedDownPlay();
                getSnail().makeSnail();
            }
        }
    }

    // if player crashed by himself, borders of the game, stumps, he will stand idle for awhile.
    // if players will have collision by heads. They will have no penalty, they will begin from their start places.
    // if one of the player will gain >=40 score points, he will win
    @Override
    void gameOverCheck() {
        boolean isPlayer1Crash = false;
        boolean isPlayer2Crash = false;
        Rectangle2D stumpRec = new Rectangle(getStump().getCoordinates().width, getStump().getCoordinates().height, 120, 80);
        Rectangle2D stump2Rec = new Rectangle(getStump2().getCoordinates().width, getStump2().getCoordinates().height, 120, 80);
        Rectangle2D snake1Rec = new Rectangle(getPlayer1X(), getPlayer1Y(), 20, 20);
        Rectangle2D snake2Rec = new Rectangle(Player2X, Player2Y, 20, 20);

        // if player 1 crashed
        if (getPlayer1X() > 980 || getPlayer1X() < 0 || getPlayer1Y() > 580 || getPlayer1Y() < 0
                || Player1snake.selfEated(getPlayer1X(), getPlayer1Y()) || snake1Rec.intersects(stumpRec) || snake1Rec.intersects(stump2Rec)
                || Player1snake.oneSnakeEatAnother(Player2snake) == 1) {
            isPlayer1Crash = true;
            setPlayer1Dead(true);
            player1SkipMoveCount = 0;
            player2SkipMoveCount = 61;

        // if player 2 crashed
        } else if (Player2X > 980 || Player2X < 0 || Player2Y > 580 || Player2Y < 0
                || Player2snake.selfEated(Player2X, Player2Y) || snake2Rec.intersects(stumpRec) || snake2Rec.intersects(stump2Rec)
                || Player2snake.oneSnakeEatAnother(Player1snake) == 1) {
            isPlayer2Crash = true;
            isPlayer2Dead = true;
            player2SkipMoveCount = 0;
            player1SkipMoveCount = 61;

        // if snakes have collision by heads
        } else if (Player1snake.oneSnakeEatAnother(Player2snake) == 2) {
            isPlayer1Crash = true;
            isPlayer2Crash = true;
            player2SkipMoveCount = 61;
            player1SkipMoveCount = 61;
            music.kissPlay();
            int Y = 100;
            int X = 30;
            if (Player2Y < 70){
                Y = - 40;}
            if (Player2X < 20){
                X = 0;
            }
            if (Player2X > 960){
                X = 70;
            }
            kissLabel.setBounds(Player2X-X,Player2Y-Y,100,100);
            kissLabel.setVisible(true);
        }

        // if someone crashed the snakes will respawn
        if (isPlayer1Crash || isPlayer2Crash) {
            this.repaint();
            if (!isPlayer1Crash || !isPlayer2Crash)
            music.crashPlay();
                try {
                    centreLabel.setBounds(Main.frame.getWidth() / 2 - 200, 190, 1000, 170);
                    centreLabel.setText("Ready?");
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            kissLabel.setVisible(false);
            setPlayer1Dead(false);
            setPlayer1X(60);
            setPlayer1Y(40);
            setPlayer1stepX(20);
            setPlayer1stepY(0);
            isPlayer2Dead = false;
            Player2X = 920;
            Player2Y = 540;
            Player2stepX = -20;
            Player2stepY = 0;

            if (isPlayer1Crash && isPlayer2Crash) {
                Player1snake.start(getPlayer1X(), getPlayer1Y(), true, Player1snake.getBodySize());
                Player2snake.start(Player2X, Player2Y, false, Player2snake.getBodySize());
            } else if (isPlayer1Crash) {
                Player1snake.start(getPlayer1X(), getPlayer1Y(), true, 5);
                Player2snake.start(Player2X, Player2Y, false, Player2snake.getBodySize());
                birdsLabelPlayer1.setVisible(true);
                music.birdsPlay();
            } else  {
                Player2snake.start(Player2X, Player2Y, false, 5);
                Player1snake.start(getPlayer1X(), getPlayer1Y(), true, Player1snake.getBodySize());
                birdsLabelPlayer2.setVisible(true);
                music.birdsPlay();
            }
            try {
                centreLabel.setFont((myFont.getMyFont(150f)));
                centreLabel.setBounds(Main.frame.getWidth() / 2 - 100, 190, 1000, 170);
                centreLabel.setText("Go!");
                this.repaint();
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            centreLabel.setText("");
        }

        // if someone will gain >=40 score points, he will win
        if (getPlayer1dotEatenCount() > 40 || Player2DotEatenCount > 40){
            setStump(null);
            setStump2(null);
            setIsEndTrue();
            music.gameMusicStop();
            birdsLabelPlayer1.setVisible(false);
            birdsLabelPlayer1.setVisible(false);
            Player2snake.deleteBody(-1); //0
            if (getPlayer1dotEatenCount() > 40) {
                super.gameOverGUI(false, "GREEN");
            } else {
                super.gameOverGUI(false, "PURPLE");
            }
            }
        }


    // Main thread of the game. Listens buttons that the players pressed, and runs infinitive cycle. On each iteration
    // it makes player move and checks if snake touched fruit, snail, borders of the game and etc.

    @Override
    public void run() {
        music.gameMusicPlay();
        int countDown = 2;
        centreLabel = new JLabel("3");
        centreLabel.setBounds(Main.frame.getWidth() / 2 - 45, 190, 1000, 170);
        centreLabel.setFont((myFont.getMyFont(150f)));
        setLayout(null);
        add(centreLabel);

        while (countDown > 0) {
            try {
                Thread.sleep(500);
                centreLabel.setText(String.valueOf(countDown));
                countDown--;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        try {
            Thread.sleep(500);
            centreLabel.setBounds(Main.frame.getWidth() / 2 - 100, 190, 1000, 170);
            centreLabel.setText("Go!");
            Thread.sleep(300);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        centreLabel.setText(null);

        setPlayer1stepX(20);
        Player2stepX = -20;

        Main.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                setMoveKey(e.getKeyCode());
                if (getMoveKey() == KeyEvent.VK_UP) {
                    setPlayer1stepX(0);
                    setPlayer1stepY(-20);
                }
                if (getMoveKey() == KeyEvent.VK_DOWN) {
                    setPlayer1stepX(0);
                    setPlayer1stepY(20);
                }
                if (getMoveKey() == KeyEvent.VK_LEFT) {
                    setPlayer1stepX(-20);
                    setPlayer1stepY(0);
                }
                if (getMoveKey() == KeyEvent.VK_RIGHT) {
                    setPlayer1stepX(20);
                    setPlayer1stepY(0);
                }
                if (getMoveKey() == KeyEvent.VK_W) {
                    Player2stepX = 0;
                    Player2stepY = -20;
                }
                if (getMoveKey() == KeyEvent.VK_S) {
                    Player2stepX = 0;
                    Player2stepY = 20;
                }
                if (getMoveKey() == KeyEvent.VK_A) {
                    Player2stepX = -20;
                    Player2stepY = 0;
                }
                if (getMoveKey() == KeyEvent.VK_D) {
                    Player2stepX = 20;
                    Player2stepY = 0;
                }
            }
        });
        Main.frame.requestFocus();

        // if one of the players is dead, he will wait 60 iteration of the cycle below
        while (!getIsEnd()) {
            try {
                if (player1SkipMoveCount > 60) {
                    setPlayer1X(getPlayer1X() + getPlayer1stepX());
                    setPlayer1Y(getPlayer1Y() + getPlayer1stepY());
                }

                if (player2SkipMoveCount > 60) {
                    Player2X += Player2stepX;
                    Player2Y += Player2stepY;
                }

                gameOverCheck();

                if (player1SkipMoveCount <= 60){
                    player1SkipMoveCount++;

                } else {
                    birdsLabelPlayer1.setVisible(false);
                    Player1snake.moveBody(getPlayer1X(), getPlayer1Y());
                }

                if (player2SkipMoveCount <= 60){
                    player2SkipMoveCount++;

                } else {
                    Player2snake.moveBody(Player2X, Player2Y);
                    birdsLabelPlayer2.setVisible(false);
                }

                eatFruitCheck();
                coinTakeCheck();
                scissorsTakeCheck();
                snailTakeCheck();
                this.repaint();
                super.getGameGUI().repaint();
                Thread.sleep(getSpeed());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    //*************Painting elements***************************

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //  ***************Player2 Body painting *********************
        if (Player2snake.getBodySize() > 0){

            Image bodyImage = bodyImageHorizontal2;

            for (int i = 1; i < Player2snake.getBodySize() - 1; i++) {

                int nextX = Player2snake.getBody().get(i + 1)[0];
                int nextY = Player2snake.getBody().get(i + 1)[1];

                int currentX = Player2snake.getBody().get(i)[0];
                int currentY = Player2snake.getBody().get(i)[1];

                int previosX = Player2snake.getBody().get(i - 1)[0];
                int previosY = Player2snake.getBody().get(i - 1)[1];

                Point prevBody = new Point(previosX, previosY);
                Point nextBody = new Point(nextX, nextY);

                Point up = new Point(currentX, currentY - 20);
                Point right = new Point(currentX + 20, currentY);
                Point down = new Point(currentX, currentY + 20);
                Point left = new Point(currentX - 20, currentY);

                if ((nextBody.equals(down) && prevBody.equals(right)) || (nextBody.equals(right) && prevBody.equals(down))) {

                    bodyImage = bodyImageUpLeft2;
                } else if ((nextBody.equals(down) && prevBody.equals(left)) || (nextBody.equals(left) && prevBody.equals(down))) {
                    bodyImage = bodyImageUpRight2;
                } else if ((nextBody.equals(up) && prevBody.equals(right)) || (nextBody.equals(right) && prevBody.equals(up))) {
                    bodyImage = bodyImageDownLeft2;
                } else if ((nextBody.equals(left) && prevBody.equals(up)) || (nextBody.equals(up) && prevBody.equals(left))) {
                    bodyImage = bodyImageDownRight2;
                } else if (currentY == previosY) {
                    bodyImage = bodyImageHorizontal2;
                } else if (currentX == previosX) {
                    bodyImage = bodyImageVertical2;
                }

                g.drawImage(bodyImage, Player2snake.getBody().get(i)[0], Player2snake.getBody().get(i)[1],
                        20, 20, null);
            }

            // ***************Player2 Tail paint*****************
            Image tailImage = tailImageRight2;

            int tailX = Player2snake.getBody().get(Player2snake.getBodySize() - 1)[0];
            int tailY = Player2snake.getBody().get(Player2snake.getBodySize() - 1)[1];

            int bodyX = Player2snake.getBody().get(Player2snake.getBodySize() - 2)[0];
            int bodyY = Player2snake.getBody().get(Player2snake.getBodySize() - 2)[1];

            if (tailX == bodyX && bodyY > tailY) {
                tailImage = tailImageDown2;}
            else if (tailX == bodyX && tailY > bodyY) {
                tailImage = tailImageUp2;}
            else if (tailY == bodyY && tailX > bodyX) {
                tailImage = tailImageLeft2;}
            else if (tailY == bodyY && bodyX > tailX) {
                tailImage = tailImageRight2;
            }

            g.drawImage(tailImage, Player2snake.getBody().get(Player2snake.getBodySize() - 1)[0],
                    Player2snake.getBody().get(Player2snake.getBodySize() - 1)[1], 20, 20, null);

            // ***********Player2 Head paint***************

            if (Player2stepX == 0 && Player2stepY == -20) {
                if (isPlayer2Dead) headImagePlayer2 = headImageUpDead2;
                else headImagePlayer2 = headImageUp2;
            }
            if (Player2stepX == 0 && Player2stepY == 20) {
                if (isPlayer2Dead) headImagePlayer2 = headImageDownDead2;
                else headImagePlayer2 = headImageDown2;
            }
            if (Player2stepX == -20 && Player2stepY == 0) {
                if (isPlayer2Dead) headImagePlayer2 = headImageLeftDead2;
                else headImagePlayer2 = headImageLeft2;
            }
            if (Player2stepX == 20 && Player2stepY == 0) {
                if (isPlayer2Dead) headImagePlayer2 = headImageRightDead2;
                else headImagePlayer2 = headImageRight2;
            }

            g.drawImage(headImagePlayer2, Player2snake.getBody().get(0)[0] - 20, Player2snake.getBody().get(0)[1] - 20,
                    60, 60, null);
        }

    }

}


