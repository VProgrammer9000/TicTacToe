package program;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    JFrame frame=new JFrame();
    JPanel mainPanel=new JPanel();
    CardLayout cardLayout = new CardLayout();


    public MainFrame() {
        initFrame();

        mainPanel.setLayout(cardLayout);

        JPanel startMenu=new StartMenu(this);
        JPanel gameMenu=new GameMenu(this);
        JPanel settingsMenu=new SettingsMenu(this);

        mainPanel.add(startMenu,"startMenu");
        mainPanel.add(gameMenu,"gameMenu");
        mainPanel.add(settingsMenu,"settingMenu");


        cardLayout.show(mainPanel,"startMenu");

        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    private void initFrame(){
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Settings.getInstance().getBackground());

        //UIManager
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // sets Title
        JLabel title=new JLabel("Tic Tac Toe");
        frame.add(title, BorderLayout.NORTH);

        frame.add(mainPanel, BorderLayout.CENTER);
    }



    public void openStartMenu(){
        cardLayout.show(mainPanel,"startMenu");
    }

    public void openGameMenu(){
        cardLayout.show(mainPanel,"gameMenu");
    }

    public void openSettingMenu(){
        cardLayout.show(mainPanel,"settingMenu");
    }

    public void gameMenuGameButtonPressed(int gameSize, boolean specialMode){
        JPanel panel = new Game(gameSize,specialMode, this);
        mainPanel.add(panel,"gameBoard");
        cardLayout.show(mainPanel,"gameBoard");
    }

    public static void main(String[] args) {
        new MainFrame();
    }
    //TODO StartMenu finished
    //TODO GameMenu finished
    //TODO Font Size

    //TODO Looks Change game
    //TODO Looks Selection
}
