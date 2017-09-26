import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

   class Music {
     private  Clip gameMusic;
     private  Clip mainMenuMusic;
     private  Clip gameOverMusic;
     private  Clip rain;
     private  Clip thunder;

    void gameMusicPlay () {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource( "Res/Sound/Game.wav").getFile()));
            gameMusic = AudioSystem.getClip();
            gameMusic.open(audioInputStream);
            gameMusic.start();
            gameMusic.loop(999);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

     void gameMusicStop () {
        gameMusic.stop();
    }



    void mainMenuPlay() {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource( "Res/Sound/Menu.wav").getFile()));
            mainMenuMusic = AudioSystem.getClip();
            mainMenuMusic.open(audioInputStream);
            mainMenuMusic.start();
            mainMenuMusic.loop(10);
        } catch (Exception ex)

        {
            ex.printStackTrace();
        }
    }

     void mainMenuStop() {
       mainMenuMusic.stop();
    }

     void gameOverPlay () {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource( "Res/Sound/GameOver.wav").getFile()));
            gameOverMusic = AudioSystem.getClip();
            gameOverMusic.open(audioInputStream);
            gameOverMusic.start();
            gameOverMusic.loop(10);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     void gameOverStop () {
        gameOverMusic.stop();
    }

     void rainPlay () {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource( "Res/Sound/Rain.wav").getFile()));
            rain = AudioSystem.getClip();
            rain.open(audioInputStream);
            rain.start();
            rain.loop(999);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

     void rainStop () {
        rain.stop();
    }

     void thunderPlay () {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource( "Res/Sound/Thunder.wav").getFile()));
            thunder = AudioSystem.getClip();
            thunder.open(audioInputStream);
            thunder.start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

     void thunderStop () {
        thunder.stop();
    }



    void newLifePlay() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource( "Res/Sound/1up.wav").getFile()));
            Clip newLifeSound = AudioSystem.getClip();
            newLifeSound.open(audioInputStream);
            newLifeSound.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    void scissorsPlay () {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource( "Res/Sound/Scissors.wav").getFile()));
            Clip scissorsSound = AudioSystem.getClip();
            scissorsSound.open(audioInputStream);
            scissorsSound.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

     void birdsPlay () {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource( "Res/Sound/Birds.wav").getFile()));
            Clip birdsSound = AudioSystem.getClip();
            birdsSound.open(audioInputStream);
            birdsSound.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void speedUpPlay () {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource( "Res/Sound/SpeedUp.wav").getFile()));
            Clip speedUpSound = AudioSystem.getClip();
            speedUpSound.open(audioInputStream);
            speedUpSound.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void speedDownPlay () {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource( "Res/Sound/SpeedDown.wav").getFile()));
            Clip speedDownSound = AudioSystem.getClip();
            speedDownSound.open(audioInputStream);
            speedDownSound.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void eatFruitPlay () {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource( "Res/Sound/Eat.wav").getFile()));
            Clip eatFruitSound = AudioSystem.getClip();
            eatFruitSound.open(audioInputStream);
            eatFruitSound.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

     void victoryPlay () {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource( "Res/Sound/Victory.wav").getFile()));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


     void kissPlay () {

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource( "Res/Sound/Kiss.wav").getFile()));
            Clip eatFruitSound = AudioSystem.getClip();
            eatFruitSound.open(audioInputStream);
            eatFruitSound.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


     void coinTakePlay () {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource( "Res/Sound/Coin.wav").getFile()));
            Clip getCoint = AudioSystem.getClip();
            getCoint.open(audioInputStream);
            getCoint.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

     void crashPlay () {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(getClass().getResource( "Res/Sound/Crash.wav").getFile()));
            Clip crashPlay = AudioSystem.getClip();
            crashPlay.open(audioInputStream);
            crashPlay.start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
