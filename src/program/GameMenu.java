package program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenu extends JPanel {
    MainFrame parentframe;

    JButton back=new JButton();
    JButton game3x3=new JButton();
    JButton game5x5=new JButton();
    JButton game7x7=new JButton();
    JCheckBox specialModeCheckBox =new JCheckBox();

    public GameMenu(MainFrame parentFrame){
        this.parentframe=parentFrame;

        this.add(back);
        this.add(game3x3);
        this.add(game5x5);
        this.add(game7x7);
        this.add(specialModeCheckBox);

        back.addActionListener(backActionListener);
        game3x3.addActionListener(gameActionListener);
        game5x5.addActionListener(gameActionListener);
        game7x7.addActionListener(gameActionListener);

        //TODO Remove
        this.setBackground(Color.BLUE);
    }
    ActionListener backActionListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    };
    ActionListener gameActionListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int gameSize=0;
            if (e.getSource()==game3x3){
                gameSize=3;
            }else if (e.getSource()==game5x5){
                gameSize=5;
            }else if (e.getSource()==game7x7){
                gameSize=7;
            }
            boolean specialMode=specialModeCheckBox.isSelected();
            parentframe.gameMenuGameButtonPressed(gameSize,specialMode);
        }
    };

}
