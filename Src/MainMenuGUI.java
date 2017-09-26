import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Igor Gridin on 05.05.17.
 * <p>
 * MainMenuGUI class draws the main menu GUI of the game and provides to select game mode (1 Player or 2 Players),
 * as well as to choose "Game rules" and "Exit" menu.
 **/

class MainMenuGUI extends JPanel {
    private int menuSelect = 1;
    private int info;
    private JLabel infoLabel;
    private ArrayList<ImageIcon> infoList = new ArrayList<>();

    MainMenuGUI() {

        MyFont myFont = new MyFont();
        Music music = new Music();

        music.mainMenuPlay();
        JLabel labelGameName = new JLabel("Classic Snake");
        labelGameName.setFont(myFont.getMyFont(80f));

        JLabel labelPlayer1 = new JLabel("  1 Player");
        JLabel labelPlayer2 = new JLabel("  2 Players");
        JLabel labelRules = new JLabel("  Game rules");
        JLabel labelExit = new JLabel("  Exit");

        ImageIcon rays = new ImageIcon(getClass().getResource("/Res/Img/Title.png"));
        JLabel rayLabel = new JLabel(rays);

        rayLabel.setBounds(0, 0, 1050, 750);
        labelGameName.setBounds(320, 125, 400, 80);
        labelPlayer1.setBounds(370, 180, 300, 200);
        labelPlayer2.setBounds(370, 260, 300, 200);
        labelRules.setBounds(370, 330, 300, 200);
        labelExit.setBounds(370, 400, 300, 200);

        labelPlayer1.setFont(myFont.getMyFont(50f));
        labelPlayer2.setFont(myFont.getMyFont(50f));
        labelRules.setFont(myFont.getMyFont(50f));
        labelExit.setFont(myFont.getMyFont(50f));

        Color color = new Color(255, 255, 255);

        labelGameName.setForeground(color);
        labelPlayer1.setForeground(color);
        labelPlayer2.setForeground(color);
        labelRules.setForeground(color);
        labelExit.setForeground(color);

        ImageIcon menuIcon = new ImageIcon(getClass().getResource("/Res/Img/Snake_menu.gif"));
        ImageIcon menuIconWhite = new ImageIcon(getClass().getResource("/Res/Img/MenuIconWhite.png"));

        labelPlayer1.setIcon(menuIcon);
        labelPlayer2.setIcon(menuIconWhite);
        labelRules.setIcon(menuIconWhite);
        labelExit.setIcon(menuIconWhite);

        setPreferredSize(new Dimension(1000, 700));
        setLayout(null);

        infoList.add(new ImageIcon(getClass().getResource("/Res/Img/Info1.png")));
        infoList.add(new ImageIcon(getClass().getResource("/Res/Img/Info2.png")));
        infoList.add(new ImageIcon(getClass().getResource("/Res/Img/Info3.png")));

        infoLabel = new JLabel();
        infoLabel.setBounds(244, 109, 533, 450);
        infoLabel.setVisible(false);
        add(infoLabel);

        add(labelExit);
        add(labelGameName);
        add(labelPlayer1);
        add(labelPlayer2);
        add(labelRules);
        add(rayLabel);

        Main.frame.getContentPane().removeAll();
        Main.frame.getContentPane().invalidate();
        Main.frame.getContentPane().add(this);
        Main.frame.getContentPane().revalidate();
        Main.frame.pack();

        Main.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_ENTER && menuSelect == 1) {
                    music.mainMenuStop();
                    new Handler(true);
                    Main.frame.removeKeyListener(this);
                    return;
                }

                if (key == KeyEvent.VK_ENTER && menuSelect == 2) {
                    music.mainMenuStop();
                    new Handler2Players();
                    Main.frame.removeKeyListener(this);
                    return;
                }

                if (key == KeyEvent.VK_ENTER && menuSelect == 3) {
                    info = 0;
                    infoLabel.setVisible(true);
                    infoLabel.setIcon(infoList.get(info));
                    Main.frame.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            int key = e.getKeyCode();

                            if (key == KeyEvent.VK_LEFT) {
                                if (info != 0)
                                    info--;
                                infoLabel.setIcon(infoList.get(info));
                            }
                            if (key == KeyEvent.VK_RIGHT) {
                                if (info != 2)
                                    info++;
                                infoLabel.setIcon(infoList.get(info));
                            }
                            if (key == KeyEvent.VK_ESCAPE) {
                                infoLabel.setVisible(false);
                                Main.frame.removeKeyListener(this);
                            }
                        }
                    });
                }

                if (key == KeyEvent.VK_ENTER && menuSelect == 4) {
                    System.exit(0);
                }

                if (key == KeyEvent.VK_UP) {

                    if (menuSelect == 2) {
                        labelPlayer1.setIcon(menuIcon);
                        labelPlayer2.setIcon(menuIconWhite);
                        labelRules.setIcon(menuIconWhite);
                        labelExit.setIcon(menuIconWhite);
                        menuSelect = 1;
                    }

                    if (menuSelect == 3) {
                        labelPlayer1.setIcon(menuIconWhite);
                        labelPlayer2.setIcon(menuIcon);
                        labelRules.setIcon(menuIconWhite);
                        labelExit.setIcon(menuIconWhite);
                        menuSelect = 2;
                    }

                    if (menuSelect == 4) {
                        labelPlayer1.setIcon(menuIconWhite);
                        labelPlayer2.setIcon(menuIconWhite);
                        labelRules.setIcon(menuIcon);
                        labelExit.setIcon(menuIconWhite);
                        menuSelect = 3;
                    }
                }

                if (key == KeyEvent.VK_DOWN) {
                    if (menuSelect == 3) {
                        labelPlayer1.setIcon(menuIconWhite);
                        labelPlayer2.setIcon(menuIconWhite);
                        labelRules.setIcon(menuIconWhite);
                        labelExit.setIcon(menuIcon);
                        menuSelect = 4;
                    }

                    if (menuSelect == 2) {
                        labelPlayer1.setIcon(menuIconWhite);
                        labelPlayer2.setIcon(menuIconWhite);
                        labelRules.setIcon(menuIcon);
                        labelExit.setIcon(menuIconWhite);
                        menuSelect = 3;
                    }

                    if (menuSelect == 1) {
                        labelPlayer1.setIcon(menuIconWhite);
                        labelPlayer2.setIcon(menuIcon);
                        labelRules.setIcon(menuIconWhite);
                        labelExit.setIcon(menuIconWhite);
                        menuSelect = 2;
                    }
                }
            }
        });
        Main.frame.requestFocus();
    }
}

