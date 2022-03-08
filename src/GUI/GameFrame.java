package GUI;

import logic.Player;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    GamePanel gamePanel=new GamePanel(this);
    Player[] player=new Player[2];
    boolean currentPlayer=true;

    public GameFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(500,500));

        add(gamePanel);

        setPlayer(new Player('X'),0);
        setPlayer(new Player('O'),1);

        setVisible(true);
    }

    public void setPlayer(Player player,int index){
        this.player[index]=player;
    }

    public Player getCurrentPlayer() {
        if (currentPlayer) {
            currentPlayer=false;
            return player[0];
        }else {
            currentPlayer=true;
            return player[1];
        }
    }
}
