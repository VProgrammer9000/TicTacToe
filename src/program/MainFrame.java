package program;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.CardLayout;

public class MainFrame {
    JFrame frame=new JFrame();
    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel=new JPanel(cardLayout);
    JPanel startMenu;
    JPanel gameMenu;

    public MainFrame() {

        initFrame();

        startMenu=new StartMenu(this);

        gameMenu=new GameMenu(this);

        cardLayout.show(mainPanel,"startMenu");


        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    private void initFrame(){
        frame.setSize(500,500);
        frame.setLayout(null);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //UIManager
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // sets Title
        JLabel title=new JLabel("Tic Tac Toe");
        title.setBounds(50, 0,100,20);
        frame.add(title);

        mainPanel.setBounds(20,30,460,440);
        frame.add(mainPanel);

        cardLayout.addLayoutComponent(startMenu,"startMenu");
        cardLayout.addLayoutComponent(gameMenu,"gameMenu");

    }


    ActionListener gameSelectionActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO Remove
            int gameBoardSize = 3;
            boolean specialMode = false;
            char[] players={'X','O'};
            new Game(gameBoardSize,specialMode,players, frame);
        }
    };

    public void startMenuGameButtonPressed(){
        //TODO
        cardLayout.show(mainPanel,"gameMenu");
        frame.repaint();
    }

    public void startMenuLookButtonPressed(){
        //TODO
        cardLayout.show(mainPanel,"game");
        frame.repaint();
    }

    public void gameMenuBackButtonPressed(){
        //TODO
        cardLayout.show(mainPanel,"startMenu");
        frame.repaint();
    }

    public void gameMenuGameButtonPressed(int gameSize, boolean specialMode){
        //TODO

    }
}
