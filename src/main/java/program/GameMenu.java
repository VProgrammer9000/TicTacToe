package program;

import settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenu extends JPanel {
    MainFrame parentframe;

    JButton button3x3;
    JButton button5x5;
    JButton button7x7;
    JCheckBox specialModeCheckBox =new JCheckBox();

    public GameMenu(MainFrame parentFrame){
        this.parentframe=parentFrame;
        this.setBackground(Settings.getInstance().getBackground());
        this.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 70) );
        buttonPanel.setBackground(Settings.getInstance().getBackground());

        int picSize=400;

        ImageIcon pic3x3 = new ImageIcon("data/3x3.png");
        pic3x3 = new ImageIcon( pic3x3.getImage() .getScaledInstance( picSize, picSize,  java.awt.Image.SCALE_SMOOTH ) );
        button3x3 =new JButton(pic3x3);
        button3x3.setBackground(Settings.getInstance().getBackground());
        button3x3.setBorder(null);
        buttonPanel.add(button3x3);

        ImageIcon pic5x5 = new ImageIcon("data/5x5.png");
        pic5x5 = new ImageIcon( pic5x5.getImage() .getScaledInstance( picSize, picSize,  java.awt.Image.SCALE_SMOOTH ) );
        button5x5 =new JButton(pic5x5);
        button5x5.setBackground(Settings.getInstance().getBackground());
        button5x5.setBorder(null);
        buttonPanel.add(button5x5);

        ImageIcon pic7x7 = new ImageIcon("data/7x7.png");
        pic7x7 = new ImageIcon( pic7x7.getImage() .getScaledInstance( picSize, picSize,  java.awt.Image.SCALE_SMOOTH ) );
        button7x7 =new JButton(pic7x7);
        button7x7.setBackground(Settings.getInstance().getBackground());
        button7x7.setBorder(null);
        buttonPanel.add(button7x7);

        this.add(buttonPanel,BorderLayout.CENTER);

        JPanel specialModePanel = new JPanel();
        specialModePanel.setBackground(Settings.getInstance().getBackground());

        JLabel specialModeLabel = new JLabel("Special Mode");
        specialModeLabel.setForeground(Settings.getInstance().getForeground());
        specialModeLabel.setFont(new Font(Settings.getInstance().getTextFont().getFontName(), Font.PLAIN, Settings.getInstance().getTextFontSize()));
        specialModePanel.add(specialModeLabel);


        specialModeCheckBox.setBackground(Settings.getInstance().getBackground());
        specialModeCheckBox.setForeground(Settings.getInstance().getForeground());

        specialModePanel.add(specialModeCheckBox);

        this.add(specialModePanel,BorderLayout.NORTH);

        button3x3.addActionListener(gameActionListener);
        button5x5.addActionListener(gameActionListener);
        button7x7.addActionListener(gameActionListener);

    }


    ActionListener gameActionListener=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int gameSize=0;
            if (e.getSource()== button3x3){
                gameSize=3;
            }else if (e.getSource()==button5x5){
                gameSize=5;
            }else if (e.getSource()== button7x7){
                gameSize=7;
            }
            boolean specialMode=specialModeCheckBox.isSelected();

            parentframe.gameMenuGameButtonPressed(gameSize,specialMode);
        }
    };

}
