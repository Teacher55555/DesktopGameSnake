
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

class Handler extends JPanel implements Runnable {

   volatile private Snake Player1snake;

    private int fruitTakeCount;
    private int Player1Lifes = 5;
    private ArrayList <Fruit> fruits = new ArrayList<>();
    private int lifeCheckPoint = 3000;
    private int menuSelect = 1;
    private JLabel coinLabel;
    private JLabel snailLabel;
    private JLabel scissorsLabel;
    private boolean isPlayer1Game;
    private GameGUI gameGUI;
    private Snail snail;
    private Scissors scissors;
    private boolean isPlayer1Dead;

     int getPlayer1Speed() {
        return player1Speed;
    }

     void setPlayer1Speed(int player1Speed) {
        this.player1Speed = player1Speed;
    }

    private int speed = 100; //100
    private boolean isEnd;
    private int Player1X = 60;
    private int Player1Y = 40;
    private int Player1stepX; // 20
    private int Player1stepY;

     int getPlayer1dotEatenCount() {
        return Player1dotEatenCount;
    }

     void setPlayer1dotEatenCount(int player1dotEatenCount) {
        Player1dotEatenCount = player1dotEatenCount;
    }

    private int Player1dotEatenCount;
    static private int moveKey;
    private JLabel centreLabel;

    private int player1Speed = 0; //0 - 100, 1 - 80, 2 - 70, 3 - 55, 4 - 40

    private Image headImagePlayer1;
    private Image headImageUp;
    private Image headImageDown;
    private Image headImageLeft;
    private Image headImageRight;
    private Image headImageRightDead;
    private Image headImageLeftDead;
    private Image headImageDownDead;
    private Image headImageUpDead;
    private Image tailImageUp;
    private Image tailImageDown;
    private Image tailImageLeft;
    private Image tailImageRight;
    private ImageIcon appleIcon;
    private ImageIcon bananaIcon;
    private ImageIcon cherryIcon;
    private ImageIcon grapesIcon;
    private ImageIcon strawberryIcon;
    private ImageIcon kiwiIcon;
    private ImageIcon watermelonIcon;
    private ImageIcon snailIcon;
    private ImageIcon scissorsIcon;
    private ImageIcon rainIcon;
    private ImageIcon coinIcon;
    private Image bodyImageHorizontal;
    private Image bodyImageVertical;
    private Image bodyImageUpLeft;
    private Image bodyImageUpRight;
    private Image bodyImageDownRight;
    private Image bodyImageDownLeft;
    private Image stumpImage;
    private Image background;
    private ArrayList<JLabel> fruitLabels = new ArrayList<>();
    private Stump stump;
    private Stump stump2;
    private Coin coin;
    private MyFont myFont = new MyFont();
    private Music music = new Music();



     Snake getPlayer1snake() {
        return Player1snake;
    }

     void setPlayer1Y(int player1Y) {
        Player1Y = player1Y;
    }

     void setPlayer1X(int player1X) {

        Player1X = player1X;
    }

    boolean getIsEnd () {
        return isEnd;
    }
    void setIsEndTrue () {
        isEnd = true;
    }

     ArrayList<Fruit> getFruits() {
        return fruits;
    }

     GameGUI getGameGUI() {
        return gameGUI;
    }

     Snail getSnail() {
        return snail;
    }

     Scissors getScissors() {
        return scissors;
    }

     int getSpeed() {
        return speed;
    }

     int getPlayer1X() {
        return Player1X;
    }

     int getPlayer1Y() {
        return Player1Y;
    }

     int getPlayer1stepX() {
        return Player1stepX;
    }

     int getPlayer1stepY() {
        return Player1stepY;
    }

     static int getMoveKey() {
        return moveKey;
    }

     void setPlayer1Dead(boolean player1Dead) {
        isPlayer1Dead = player1Dead;
    }

     void imageInitializer (){
        try {

            headImageUp = ImageIO.read(new File(getClass().getResource("Res/Img/GreenHeadUp.png").getFile()));
            headImageDown = ImageIO.read(new File(getClass().getResource("Res/Img/GreenHeadDown.png").getFile()));
            headImageLeft = ImageIO.read(new File(getClass().getResource("Res/Img/GreenHeadLeft.png").getFile()));
            headImageRight = ImageIO.read(new File(getClass().getResource("Res/Img/GreenHeadRight.png").getFile()));

            headImageRightDead = ImageIO.read(new File(getClass().getResource("Res/Img/GreenHeadRightDead.png").getFile()));
            headImageLeftDead = ImageIO.read(new File(getClass().getResource("Res/Img/GreenHeadLeftDead.png").getFile()));
            headImageUpDead = ImageIO.read(new File(getClass().getResource("Res/Img/GreenHeadUpDead.png").getFile()));
            headImageDownDead = ImageIO.read(new File(getClass().getResource("Res/Img/GreenHeadDownDead.png").getFile()));

            headImagePlayer1 = headImageRight;

            tailImageUp = ImageIO.read(new File(getClass().getResource("Res/Img/GreenTailUp.png").getFile()));
            tailImageDown = ImageIO.read(new File(getClass().getResource("Res/Img/GreenTailDown.png").getFile()));
            tailImageLeft = ImageIO.read(new File(getClass().getResource("Res/Img/GreenTailLeft.png").getFile()));
            tailImageRight = ImageIO.read(new File(getClass().getResource("Res/Img/GreenTailRight.png").getFile()));

            stumpImage = ImageIO.read(new File(getClass().getResource("Res/Img/Stump.png").getFile()));

            appleIcon = new ImageIcon(getClass().getResource("Res/Img/Apple.gif"));
            bananaIcon = new ImageIcon(getClass().getResource("Res/Img/Banana.gif"));
            cherryIcon = new ImageIcon(getClass().getResource("Res/Img/Cherry.gif"));
            grapesIcon = new ImageIcon(getClass().getResource("Res/Img/Grapes.gif"));
            strawberryIcon = new ImageIcon(getClass().getResource("Res/Img/Strawberry.gif"));
            kiwiIcon = new ImageIcon(getClass().getResource("Res/Img/Kiwi.gif"));
            watermelonIcon = new ImageIcon(getClass().getResource("Res/Img/Watermelon.gif"));
            coinIcon = new ImageIcon(getClass().getResource("Res/Img/Coin.gif"));
            snailIcon = new ImageIcon(getClass().getResource("Res/Img/Snail.gif"));
            scissorsIcon = new ImageIcon(getClass().getResource("Res/Img/Scissors.gif"));
            rainIcon = new ImageIcon(getClass().getResource("Res/Img/Rain.gif"));

            background = ImageIO.read(new File(getClass().getResource("Res/Img/Grass.png").getFile()));

            bodyImageHorizontal = ImageIO.read(new File(getClass().getResource("Res/Img/GreenBodyHorizontal.png").getFile()));
            bodyImageVertical = ImageIO.read(new File(getClass().getResource("Res/Img/GreenBodyVertical.png").getFile()));
            bodyImageUpLeft = ImageIO.read(new File(getClass().getResource("Res/Img/GreenBodyUpLeft.png").getFile()));
            bodyImageUpRight = ImageIO.read(new File(getClass().getResource("Res/Img/GreenBodyUpRight.png").getFile()));
            bodyImageDownRight = ImageIO.read(new File(getClass().getResource("Res/Img/GreenBodyDownRight.png").getFile()));
            bodyImageDownLeft = ImageIO.read(new File(getClass().getResource("Res/Img/GreenBodyDownLeft.png").getFile()));

        } catch (Exception ex){ex.printStackTrace();}
    }

    Handler(boolean isPlayer1Game) {

        this.isPlayer1Game = isPlayer1Game;

        Main.frame.getContentPane().removeAll();
        Main.frame.getContentPane().invalidate();

        imageInitializer();

        moveKey = KeyEvent.VK_RIGHT;

        Player1snake = new Snake();
        Player1snake.start(Player1X, Player1Y, true,5);

        scissors = new Scissors(Player1snake, null,3500,1,16);
        scissorsLabel = new JLabel();
        scissorsLabel.setIcon(scissorsIcon);
        this.add(scissorsLabel);


        snail = new Snail(Player1snake,null,2300,1,10);
        snailLabel = new JLabel();
        snailLabel.setIcon(snailIcon);
        this.add(snailLabel);

        coin = new Coin(Player1snake,null,4000,1,4);
        coinLabel = new JLabel();
        coinLabel.setIcon(coinIcon);
        this.add(coinLabel);

        stump = new Stump(Player1snake,null,11000,2,3);
        stump2 = new Stump(Player1snake,null,26000,1,2);

        stump.setAnotherStump(stump2);
        stump2.setAnotherStump(stump);

        fruits.add(new Fruit(Player1snake,null,9000,1,1));
        fruits.add(new Fruit(Player1snake,null,5000,1,3));
        JLabel label = new JLabel();
        JLabel label2 = new JLabel();
        fruitLabels.add(label);
        fruitLabels.add(label2);
        this.add(label);
        this.add(label2);

        setPreferredSize(new Dimension(1000,600));

        gameGUI = new GameGUI(isPlayer1Game);
        gameGUI.getPlayerLifes(Player1Lifes);

        Main.frame.getContentPane().add("Center", this);
        Main.frame.getContentPane().add("North", gameGUI);
        Main.frame.getContentPane().revalidate();
        Main.frame.pack();

        Thread snakeMove = new Thread(this);
        snakeMove.start();

    }


    private void newLifeCheck (){

        if (gameGUI.getPlayer1Score() >= lifeCheckPoint){
            Player1Lifes++;
            gameGUI.getPlayerLifes(Player1Lifes);
            lifeCheckPoint += 3000;
            music.newLifePlay();
        }
    }


     void speedControl (){
        if (player1Speed == 0) {
            speed = 100;
        }else if (player1Speed == 1){
            speed = 78;
        } else if (player1Speed == 2){
            speed = 65;
        } else if(player1Speed == 3) {
            speed = 53;
        } else {
            speed = 38;
        }
        gameGUI.getPlayerSpeed(player1Speed);
    }

     void scissorsTakeCheck () {
        if (scissors != null) {
            Rectangle2D scissorsRec = new Rectangle(scissors.getCoordinates().width, scissors.getCoordinates().height, 40, 60);
            Rectangle2D snakeRec = new Rectangle(Player1X, Player1Y, 20, 20);

            if (scissorsRec.intersects(snakeRec)) {
                if (Player1snake.getBodySize() > 8) {
                    Player1snake.deleteBody(5);
                }

                scissors.makeScissors();
                music.scissorsPlay();
            }

        }
    }

     void coinTakeCheck (){
         if (coin == null && fruitTakeCount == 5){
             coin = new Coin(Player1snake,null,4000,1,4);
             coin.makeCoin();
             System.out.println("coin is alive!");
         }

        if (coin != null) {
            Rectangle2D coinRec = new Rectangle(coin.getCoordinates().width, coin.getCoordinates().height, 40, 40);
            Rectangle2D snakeRec = new Rectangle(Player1X, Player1Y, 20, 20);

            if (coinRec.intersects(snakeRec)) {
                music.coinTakePlay();
                gameGUI.setPlayer1Score(gameGUI.getPlayer1Score() + 100);
//                gameGUI.scorePlayer1.setText("Score: " + gameGUI.player1Score);
                newLifeCheck();
                coin = null;
                fruitTakeCount = 0;
            }
        }
    }

     void snailTakeCheck () {
        if (snail != null) {
            Rectangle2D snailRec = new Rectangle(snail.getCoordinates().width, snail.getCoordinates().height, 50, 50);
            Rectangle2D snakeRec = new Rectangle(Player1X, Player1Y, 20, 20);

            if (snailRec.intersects(snakeRec)) {
                if (player1Speed > 0) {
                    player1Speed--;
                    speedControl();
                    System.out.println(speed);
                }
                Player1dotEatenCount = 0;
                music.speedDownPlay();
                snail.makeSnail();
            }
            gameGUI.repaint();
        }
    }


     void setFruits(ArrayList<Fruit> fruits) {
        this.fruits = fruits;
    }

     void setSnail(Snail snail) {
        this.snail = snail;
    }

     void setScissors(Scissors scissors) {
        this.scissors = scissors;
    }

     Stump getStump() {
        return stump;
    }

     void setStump(Stump stump) {
        this.stump = stump;
    }

     Stump getStump2() {
        return stump2;
    }

     void setStump2(Stump stump2) {
        this.stump2 = stump2;
    }

     Coin getCoin() {
        return coin;
    }

     void setCoin(Coin coin) {
        this.coin = coin;
    }

    void gameOverCheck() {

        Rectangle2D stumpRec = new Rectangle(stump.getCoordinates().width, stump.getCoordinates().height, 120, 80);
        Rectangle2D stump2Rec = new Rectangle(stump2.getCoordinates().width, stump2.getCoordinates().height, 120, 80);
        Rectangle2D snakeRec = new Rectangle(Player1X, Player1Y, 20, 20);

        if (Player1X > 980 || Player1X < 0 || Player1Y > 580 || Player1Y < 0
                || Player1snake.selfEated(Player1X, Player1Y) || snakeRec.intersects(stumpRec) || snakeRec.intersects(stump2Rec)) {

            music.crashPlay();
            Player1Lifes--;
            gameGUI.getPlayerLifes(Player1Lifes);
            isPlayer1Dead = true;
            this.repaint();
            gameGUI.repaint();
            if (Player1Lifes < 0) {
                music.gameMusicStop();
            }
            try {
                Thread.sleep(1000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (Player1Lifes >= 0) {
                try {
                    centreLabel.setBounds(Main.frame.getWidth() / 2 - 200, 190, 1000, 170);
                    centreLabel.setText("Ready?");
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                isPlayer1Dead = false;
                Player1X = 60;
                Player1Y = 40;
                Player1stepX = 20;
                Player1stepY = 0;
                moveKey = KeyEvent.VK_RIGHT;
                Player1snake.start(Player1X, Player1Y, true, Player1snake.getBodySize());
                try {
                    centreLabel.setBounds(Main.frame.getWidth() / 2 - 100, 190, 1000, 170);
                    centreLabel.setText("Go!");
                    this.repaint();
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                centreLabel.setText("");
            } else {
                gameOverGUI(isPlayer1Game,null);
            }
        }
    }

     void gameOverGUI (boolean isPlayer1Game, String winner) {
                isEnd = true;
                for (Fruit fruit : fruits) {
                    fruit.setRestrictedArea(new Rectangle(300, 50, 400, 400));
                    fruit.makeFruit();
                }
                coin = null;
                scissors = null;
                snail = null;
                stump = null;
                stump2 = null;
                Player1snake.deleteBody(-1); //0

                if (!isPlayer1Game){
                    repaint();
                    music.victoryPlay();

                    ImageIcon cupIcon = new ImageIcon(getClass().getResource("Res/Img/Cup.gif"));
                    JLabel label = new JLabel(winner +" WINS");
                    JLabel cupLabel = new JLabel(cupIcon);
                    cupLabel.setBounds(390,120,200,300);
                    label.setFont(myFont.getMyFont(70f));
                    label.setBounds(290, 60, 2000, 100);
                    add(label);
                    add(cupLabel);
                    repaint();
                    try {
                        Thread.sleep(6000);
                    } catch (Exception ex) {ex.printStackTrace();}
                }

                removeAll();


        repaint();

        music.gameOverPlay();
        music.rainPlay();
        music.thunderPlay();

        JLabel rainlabel = new JLabel();

        rainlabel.setIcon(rainIcon);
        rainlabel.setBounds(0,0,rainIcon.getIconWidth(),rainIcon.getIconHeight());

        JLabel puddleLabel1 = new JLabel();
        JLabel puddleLabel2 = new JLabel();
        JLabel puddleLabel3 = new JLabel();
        ImageIcon puddleIcon = new ImageIcon(getClass().getResource("Res/Img/Puddle.gif"));
        puddleLabel1.setIcon(puddleIcon);
        puddleLabel2.setIcon(puddleIcon);
        puddleLabel3.setIcon(puddleIcon);
        puddleLabel1.setBounds(850,400,puddleIcon.getIconWidth(),puddleIcon.getIconHeight());
        puddleLabel2.setBounds(100,100,puddleIcon.getIconWidth(),puddleIcon.getIconHeight());
        puddleLabel3.setBounds(250,520,puddleIcon.getIconWidth(),puddleIcon.getIconHeight());

        JLabel gameOverLabel = new JLabel("Game Over");
        JLabel menuSelect1Label = new JLabel(" New Game");
        JLabel menuSelect2Label = new JLabel(" Main menu");
        JLabel menuSelect3Label = new JLabel(" Exit");

//        Font font = new Font("Curlz MT",Font.PLAIN,50);

        gameOverLabel.setFont(myFont.getMyFont(80f));
        menuSelect1Label.setFont(myFont.getMyFont(50f));
        menuSelect2Label.setFont(myFont.getMyFont(50f));
        menuSelect3Label.setFont(myFont.getMyFont(50f));

        gameOverLabel.setBounds(getWidth() / 2 - 180, getY()/4, 1000, 150);
        menuSelect1Label.setBounds(getWidth() / 2 - 135, getY()/4 + 100, 1000, 150);
        menuSelect2Label.setBounds(getWidth() / 2 - 135, getY()/4 + 160, 1000, 150);
        menuSelect3Label.setBounds(getWidth() / 2 - 135, getY()/4 + 220, 1000, 150);

        add(gameOverLabel);
        add(menuSelect1Label);
        add(menuSelect2Label);
        add(menuSelect3Label);
        add(rainlabel);
        add(puddleLabel1);
        add(puddleLabel2);
        add(puddleLabel3);

        ImageIcon menuIcon = new ImageIcon(getClass().getResource("Res/Img/Snake_menu.gif"));
        ImageIcon menuIconWhite = new ImageIcon(getClass().getResource("/Res/Img/MenuIconWhite.png"));

        menuSelect1Label.setIcon(menuIcon);
        menuSelect2Label.setIcon(menuIconWhite);
        menuSelect3Label.setIcon(menuIconWhite);

        Main.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_ENTER && menuSelect == 1){
                    music.gameOverStop();
                    music.thunderStop();
                    music.rainStop();
                    Main.frame.removeKeyListener(this);
                    if (isPlayer1Game)
                    new Handler(true);
                    else
                        new Handler2Players();

                    return;
                }

                if (key == KeyEvent.VK_ENTER && menuSelect == 2){
                    music.gameOverStop();
                    music.thunderStop();
                    music.rainStop();
                    Main.frame.removeKeyListener(this);
                    new MainMenuGUI();
                    return;
                }

                if (key == KeyEvent.VK_ENTER && menuSelect == 3){
                    System.exit(0);
                }

                if (key == KeyEvent.VK_UP) {

                    if (menuSelect == 2) {
                        menuSelect1Label.setIcon(menuIcon);
                        menuSelect2Label.setIcon(menuIconWhite);
                        menuSelect3Label.setIcon(menuIconWhite);
                        menuSelect = 1;
                    }

                    if (menuSelect == 3) {
                        menuSelect1Label.setIcon(menuIconWhite);
                        menuSelect2Label.setIcon(menuIcon);
                        menuSelect3Label.setIcon(menuIconWhite);
                        menuSelect = 2;
                    }
                }

                if (key == KeyEvent.VK_DOWN) {
                    if (menuSelect == 2){
                        menuSelect1Label.setIcon(menuIconWhite);
                        menuSelect2Label.setIcon(menuIconWhite);
                        menuSelect3Label.setIcon(menuIcon);
                        menuSelect = 3;
                    }

                    if (menuSelect == 1){
                        menuSelect1Label.setIcon(menuIconWhite);
                        menuSelect2Label.setIcon(menuIcon);
                        menuSelect3Label.setIcon(menuIconWhite);
                        menuSelect = 2;
                    }
                }
            }
        });
    }



     void eatFruitCheck() {

        for (Fruit fruit : fruits) {
            if (fruit.getCoordinates() != null) {

                Rectangle2D fruitRectangle = new Rectangle(fruit.getCoordinates().width, fruit.getCoordinates().height, 40, 40);
                Rectangle2D snake1Rectangle = new Rectangle(Player1X, Player1Y, 20, 20);

                if (fruit.getCoordinates() != null && fruitRectangle.intersects(snake1Rectangle)) {
                    Player1snake.addBody(Player1X, Player1Y);
                    music.eatFruitPlay();
                    fruit.interrupt();

                        gameGUI.setPlayer1Score(gameGUI.getPlayer1Score() + 10);
//                        gameGUI.player1Score += 10;
                        Player1dotEatenCount++;
//                    gameGUI.scorePlayer1.setText("Score: " + gameGUI.player1Score);
                    newLifeCheck ();

                    if (Player1dotEatenCount != 0 && Player1dotEatenCount % 10 == 0 && player1Speed < 4) {
                        music.speedUpPlay ();
                        player1Speed++;
                        speedControl();
                        System.out.println(speed);

                    }
                    fruit.makeFruit();
                     fruitTakeCount ++;
                }
            }
        }
    }


     void setPlayer1stepX(int player1stepX) {
        Player1stepX = player1stepX;
    }

     void setPlayer1stepY(int player1stepY) {
        Player1stepY = player1stepY;
    }

     static void setMoveKey(int moveKey) {
        Handler.moveKey = moveKey;
    }

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

        Player1stepX = 20;

        Main.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                moveKey = e.getKeyCode();
                if (moveKey == KeyEvent.VK_UP) {
                    Player1stepX = 0;
                    Player1stepY = -20;
                }
                if (moveKey == KeyEvent.VK_DOWN) {
                    Player1stepX = 0;
                    Player1stepY = 20;
                }
                if (moveKey == KeyEvent.VK_LEFT) {
                    Player1stepX = -20;
                    Player1stepY = 0;
                }
                if (moveKey == KeyEvent.VK_RIGHT) {
                    Player1stepX = 20;
                    Player1stepY = 0;
                }


            }
        });
        Main.frame.requestFocus();

            while (!isEnd) {
                try {


                    Player1X += Player1stepX;
                    Player1Y += Player1stepY;

                    gameOverCheck();

                    Player1snake.moveBody(Player1X, Player1Y);
                    scissorsTakeCheck ();
                    eatFruitCheck();
                    coinTakeCheck();

                    snailTakeCheck ();
                    gameGUI.repaint();
                    this.repaint();
                    Thread.sleep(speed);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // *****************Background paint****************
        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);

//       ******************* Stump paint ******************
        if (stump != null && stump2 != null) {
            g.drawImage(stumpImage, stump.getCoordinates().width - 33, stump.getCoordinates().height - 25, 200, 120, null);
            g.drawImage(stumpImage, stump2.getCoordinates().width - 33, stump2.getCoordinates().height - 25, 200, 120, null);
        }

        //************Snail paint *********************

        if (snail != null) {
            snailLabel.setIcon(snailIcon);
            snailLabel.setBounds(snail.getCoordinates().width, snail.getCoordinates().height, 50, 50);}
        else snailLabel.setIcon(null);


        //************Scissors paint*************
        if (scissors != null) {
            scissorsLabel.setIcon(scissorsIcon);
            scissorsLabel.setBounds(scissors.getCoordinates().width, scissors.getCoordinates().height, 40, 60);
        }
        else scissorsLabel.setIcon(null);


        //*************Coin paint**********
        if (coin != null){
            coinLabel.setIcon(coinIcon);
            coinLabel.setBounds(coin.getCoordinates().width, coin.getCoordinates().height, 40, 40);}
        else coinLabel.setIcon(null);

        //*************Fruits paint***************
        int f = 0;
        for (JLabel label : fruitLabels) {
            Fruit fruit = fruits.get(f++);
            if (fruit.getFruittype() == 0) {
                label.setIcon(appleIcon);}
            else if (fruit.getFruittype() == 1) {
                label.setIcon(cherryIcon);}
            else if (fruit.getFruittype() == 2) {
                label.setIcon(bananaIcon);}
            else if (fruit.getFruittype() == 3) {
                label.setIcon(grapesIcon);}
            else if (fruit.getFruittype() == 4) {
                label.setIcon(strawberryIcon);}
            else if (fruit.getFruittype() == 5) {
                label.setIcon(kiwiIcon);}
            else if (fruit.getFruittype() == 6) {
                label.setIcon(watermelonIcon);
            }

            label.setBounds(fruit.getCoordinates().width, fruit.getCoordinates().height, 40, 40);
        }

        //  ***************Player1 Body painting *********************
        if (Player1snake.getBodySize() > 0){

        Image bodyImage = bodyImageHorizontal;

        for (int i = 1; i < Player1snake.getBodySize() - 1; i++) {

            int nextX = Player1snake.getBody().get(i + 1)[0];
            int nextY = Player1snake.getBody().get(i + 1)[1];

            int currentX = Player1snake.getBody().get(i)[0];
            int currentY = Player1snake.getBody().get(i)[1];

            int previosX = Player1snake.getBody().get(i - 1)[0];
            int previosY = Player1snake.getBody().get(i - 1)[1];

            Point prevBody = new Point(previosX, previosY);
            Point nextBody = new Point(nextX, nextY);

            Point up = new Point(currentX, currentY - 20);
            Point right = new Point(currentX + 20, currentY);
            Point down = new Point(currentX, currentY + 20);
            Point left = new Point(currentX - 20, currentY);

            if ((nextBody.equals(down) && prevBody.equals(right)) || (nextBody.equals(right) && prevBody.equals(down))) {

                bodyImage = bodyImageUpLeft;
            } else if ((nextBody.equals(down) && prevBody.equals(left)) || (nextBody.equals(left) && prevBody.equals(down))) {
                bodyImage = bodyImageUpRight;
            } else if ((nextBody.equals(up) && prevBody.equals(right)) || (nextBody.equals(right) && prevBody.equals(up))) {
                bodyImage = bodyImageDownLeft;
            } else if ((nextBody.equals(left) && prevBody.equals(up)) || (nextBody.equals(up) && prevBody.equals(left))) {
                bodyImage = bodyImageDownRight;
            } else if (currentY == previosY) {
                bodyImage = bodyImageHorizontal;
            } else if (currentX == previosX) {
                bodyImage = bodyImageVertical;
            }

            g.drawImage(bodyImage, Player1snake.getBody().get(i)[0], Player1snake.getBody().get(i)[1],
                    20, 20, null);
        }

        // ***************Player1 Tail paint*****************
        Image tailImage = tailImageLeft;

        int tailX = Player1snake.getBody().get(Player1snake.getBodySize() - 1)[0];
        int tailY = Player1snake.getBody().get(Player1snake.getBodySize() - 1)[1];

        int bodyX = Player1snake.getBody().get(Player1snake.getBodySize() - 2)[0];
        int bodyY = Player1snake.getBody().get(Player1snake.getBodySize() - 2)[1];

        if (tailX == bodyX && bodyY > tailY) {
            tailImage = tailImageDown;}
        else if (tailX == bodyX && tailY > bodyY) {
            tailImage = tailImageUp;}
        else if (tailY == bodyY && tailX > bodyX) {
            tailImage = tailImageLeft;}
        else if (tailY == bodyY && bodyX > tailX) {
            tailImage = tailImageRight;
        }

        g.drawImage(tailImage, Player1snake.getBody().get(Player1snake.getBodySize() - 1)[0],
                Player1snake.getBody().get(Player1snake.getBodySize() - 1)[1], 20, 20, null);

        // ***********Player1 Head pain***************

            if (Player1stepX == 0 && Player1stepY == -20) {
                if (isPlayer1Dead) headImagePlayer1 = headImageUpDead;
                else headImagePlayer1 = headImageUp;
            }
            if (Player1stepX == 0 && Player1stepY == 20) {
                if (isPlayer1Dead) headImagePlayer1 = headImageDownDead;
                else headImagePlayer1 = headImageDown;
            }
            if (Player1stepX == -20 && Player1stepY == 0) {
                if (isPlayer1Dead) headImagePlayer1 = headImageLeftDead;
                else headImagePlayer1 = headImageLeft;
            }
            if (Player1stepX == 20 && Player1stepY == 0) {
                if (isPlayer1Dead) headImagePlayer1 = headImageRightDead;
                else headImagePlayer1 = headImageRight;
            }

            g.drawImage(headImagePlayer1, Player1snake.getBody().get(0)[0] - 20, Player1snake.getBody().get(0)[1] - 20,
                    60, 60, null);
        }

    }

}




