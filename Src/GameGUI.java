import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by Igor Gridin on 05.05.17.
 * <p>
 * The GameGUI class controls the game pad that is located above the playing field.
 * There are next elements for 1 Player: Score, game speed and number of lives.
 * For a game for two, each player's progress is displayed in the form of a growing snake and finish line that they need to achieve.
 **/

class GameGUI extends JPanel {
    private JLabel scorePlayer1;
    private boolean isPlayer1game;
    private int PlayerLife;
    private int PlayerSpeed;
    private int player1Score;
    private int player2Score;
    private Image backgroundGUI;
    private Image lifeImage;
    private Image speedometer20;
    private Image speedometer40;
    private Image speedometer60;
    private Image speedometer80;
    private Image speedometer100;
    private Image headImagePlayer1;
    private Image bodyImagePlayer1;
    private Image tailImagePlayer1;
    private Image headImagePlayer2;
    private Image bodyImagePlayer2;
    private Image tailImagePlayer2;

    // Getters and setters
    int getPlayer1Score() {
        return player1Score;
    }
    void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }
    void setPlayer2Score(int player2Score) {
        this.player2Score = player2Score;
    }
    void getPlayerLifes (int life){
        PlayerLife = life;
    }
    void getPlayerSpeed (int speed){
        PlayerSpeed = speed;
    }

    //Constructor
    GameGUI (boolean isPlayer1Game){
        MyFont myFont = new MyFont();
        this.isPlayer1game = isPlayer1Game;
        setPreferredSize(new Dimension(1000,150));
        Color color = new Color(11, 92, 140);
        setLayout(null);

        try {
            lifeImage = ImageIO.read(new File(getClass().getResource("Res/Img/SnakeIcon.png").getFile()));
            speedometer20 = ImageIO.read(new File(getClass().getResource("Res/Img/Speedometer20.png").getFile()));
            speedometer40 = ImageIO.read(new File(getClass().getResource("Res/Img/Speedometer40.png").getFile()));
            speedometer60 = ImageIO.read(new File(getClass().getResource("Res/Img/Speedometer60.png").getFile()));
            speedometer80 = ImageIO.read(new File(getClass().getResource("Res/Img/Speedometer80.png").getFile()));
            speedometer100 = ImageIO.read(new File(getClass().getResource("Res/Img/Speedometer100.png").getFile()));
        } catch (Exception ex) {ex.printStackTrace();}

        if (isPlayer1Game){ // constructor for 1 player mode

            try {
                backgroundGUI = ImageIO.read(new File(getClass().getResource("Res/Img/GameGUI1Player.png").getFile()));
            } catch (Exception ex) {ex.printStackTrace();}

            scorePlayer1 = new JLabel("Score: 0");
            JLabel lifeLabel = new JLabel("Life");
            scorePlayer1.setFont(myFont.getMyFont(25f));
            lifeLabel.setFont(myFont.getMyFont(25f));
            scorePlayer1.setBounds(85,10,500,100);
            lifeLabel.setBounds(840,-10,300,100);
            scorePlayer1.setText("Score: 0");
            scorePlayer1.setForeground(color);
            lifeLabel.setForeground(color);
            scorePlayer1.setFont(myFont.getMyFont(30f));
            lifeLabel.setFont(myFont.getMyFont(30f));
            add(scorePlayer1);
            add(lifeLabel);

        } else { // constructor for 2 player mode
            try {
                backgroundGUI = ImageIO.read(new File(getClass().getResource("Res/Img/GameGUI2Players.png").getFile()));
                headImagePlayer1 = ImageIO.read(new File(getClass().getResource("Res/Img/GreenHeadRight.png").getFile()));
                bodyImagePlayer1 = ImageIO.read(new File(getClass().getResource("Res/Img/GreenBodyHorizontal.png").getFile()));
                tailImagePlayer1 = ImageIO.read(new File(getClass().getResource("Res/Img/GreenTailRight.png").getFile()));
                headImagePlayer2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleHeadRight.png").getFile()));
                bodyImagePlayer2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleBodyHorizontal.png").getFile()));
                tailImagePlayer2 = ImageIO.read(new File(getClass().getResource("Res/Img/PurpleTailRight.png").getFile()));
            } catch (Exception ex) {ex.printStackTrace();}
        }
    }

    // draw graphics
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(backgroundGUI,0,0,null);

        // if 1 player mode, it will draw number of lives, speedometer and player's score.
        if (isPlayer1game) {

            // Player's score
            scorePlayer1.setText("Score: " + player1Score);

            // Number of lives
            int Xcoordinate = 940;
            for (int i = 0; i < PlayerLife; i++) {
                if (i == 5) {
                    return;
                }
                g.drawImage(lifeImage, Xcoordinate -= 31, 60, 25, 40, null);
            }

            //Speedometer painting
            Image speedImage;
            if (PlayerSpeed == 0) {
                speedImage = speedometer20;
            } else if (PlayerSpeed == 1) {
                speedImage = speedometer40;
            } else if (PlayerSpeed == 2) {
                speedImage = speedometer60;
            } else if (PlayerSpeed == 3) {
                speedImage = speedometer80;
            } else {
                speedImage = speedometer100;
            }
            g.drawImage(speedImage, 440, 25, 130, 65, null);

            // if 2 player mode, it will draw the tails of snakes according to the score of their players
        } else {

            // Player1ScoreDraw
            g.drawImage(tailImagePlayer1,25,20,20,20,null);
            int X = 45;
            for (int i = 0; i < player1Score; i++) {
                g.drawImage(bodyImagePlayer1,X,20,20,20,null);
                X += 20;
            }
            g.drawImage(headImagePlayer1,X-20,0,60,60,null);

            // Player2ScoreDraw
            g.drawImage(tailImagePlayer2,25,60,20,20,null);
            int X2 = 45;
            for (int i = 0; i < player2Score; i++) {
                g.drawImage(bodyImagePlayer2,X2,60,20,20,null);
                X2 += 20;
            }
            g.drawImage(headImagePlayer2,X2-20,40,60,60,null);
        }
    }
}