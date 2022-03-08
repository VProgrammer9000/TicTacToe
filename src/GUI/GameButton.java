package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GameButton extends JButton {
    private char value=' ';
    private GamePanel panel;

    public GameButton(GamePanel panel){
        this.panel=panel;

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (value==' '){
                    setValue(panel.getCurrentPlayer().getSymbol());
                }
                panel.gameOver();


            }
        });

    }

    public void setValue(char value) {
        this.value = value;
        setText(value+"");
    }

    public char getValue() {
        return value;
    }
}
