package GUI;

import logic.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    GameButton[] allButton=new GameButton[9];
    GameFrame frame;



    public GamePanel(GameFrame frame){
        this.frame=frame;

        setLayout(new GridLayout(3,3,5,5));

        for (int i = 0; i < allButton.length; i++) {
            allButton[i]=new GameButton(this);
            add(allButton[i]);
        }
    }

    public Player getCurrentPlayer(){
        return frame.getCurrentPlayer();
    }

    public void gameOver(){
        boolean finished=false;
        //Check if there is a row with the same symbol vertically or horizontally
        for (int i = 0; i < 3; i++) {
            if (((allButton[i*3].getValue()==allButton[i*3+1].getValue())&&
                    (allButton[i*3].getValue()==allButton[i*3+2].getValue())&&
                    (allButton[i*3].getValue()!=' '))
                    ||
                    (allButton[i].getValue()==allButton[i+3].getValue())&&
                            (allButton[i].getValue()==allButton[i+6].getValue())&&
                            (allButton[i].getValue()!=' ')){
                finished=true;
            }
        }

        //Check if there is a row with the same symbol sideways
        if (((allButton[0].getValue()==allButton[4].getValue())&&(allButton[0].getValue()==allButton[8].getValue())&&(allButton[0].getValue()!=' '))
                ||
                ((allButton[2].getValue()==allButton[4].getValue())&&(allButton[2].getValue()==allButton[6].getValue())&&(allButton[2].getValue()!=' '))){
            finished =true;
        }

        //What if there is an Winner
        if (finished){
            new JFrame().setVisible(true);
        }
    }

}
