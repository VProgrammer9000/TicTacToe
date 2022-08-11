import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenu {
    JFrame frame=new JFrame();
    JPanel selectPanel=new JPanel();
    JButton defaultGameButton=new JButton();
    JButton customizedGameButton=new JButton();
    JButton looksButton=new JButton();

    public GameMenu() {
        frame.setSize(500,500);

        //UIManager
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        //init defaultGameButton
        defaultGameButton.addActionListener(defaultGameActionListener);
        defaultGameButton.setText("Default");
        //TODO ADD Picture
        selectPanel.add(defaultGameButton);

        //init customizedGameButton
        customizedGameButton.addActionListener(customizedGameActionListener);
        customizedGameButton.setText("Customized");
        //TODO ADD Picture
        selectPanel.add(customizedGameButton);

        //init looksButton
        looksButton.addActionListener(looksActionListener);
        looksButton.setText("Looks");
        //TODO ADD Picture
        selectPanel.add(looksButton);

        frame.add(selectPanel);
        frame.setVisible(true);
    }


    ActionListener defaultGameActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int gameBoardSize = 3;
            boolean specialMode = false;
            char[] players={'X','O'};
            new Game(gameBoardSize,specialMode,players);        }
    };

    ActionListener customizedGameActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        int gameBoardSize = 7; //3-7
            boolean specialMode = false;
        char[] players={'O','X'};
        new Game(gameBoardSize,specialMode,players);
        frame.dispose();
        }
    };

    ActionListener looksActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };
}
