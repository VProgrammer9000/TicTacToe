package test;

import javax.swing.*;
import program.Game;

public class GameTest{
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setSize(600,600);

        int gameBoardSize = 3;
        boolean specialMode = false;
        char[] players={'X','O'};

        frame.add(new Game(gameBoardSize,specialMode,players, frame));

        frame.setVisible(true);
    }
}
