package program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JPanel{
    MainFrame parentFrame;
    public StartMenu(MainFrame parentFrame){
        this.parentFrame=parentFrame;
        this.setBackground(Settings.getInstance().getBackground());
        this.setLayout(new BorderLayout());

        //sets Picture
        JLabel picture = new JLabel(); //JLabel Creation
        picture.setIcon(new ImageIcon("TicTacToe.png")); //Sets the image to be displayed as an icon
        picture.setSize(
                new Dimension((int)(picture.getPreferredSize().getHeight()*2),
                        (int)(picture.getPreferredSize().getWidth()*2))
        );
        this.add(picture);

        //init selection Panel
        JPanel buttonSelectPanel = new JPanel();
        buttonSelectPanel.setLayout(new GridLayout(1,2));


        //init gameButton
        JButton playButton =new JButton("Play");
        playButton.addActionListener(gameActionListener);
        buttonSelectPanel.add(playButton);

        //init settingButton
        JButton settingButton=new JButton("Setting");
        settingButton.addActionListener(settingActionListener);
        buttonSelectPanel.add(settingButton);

        this.add(buttonSelectPanel,BorderLayout.SOUTH);

    }

    ActionListener gameActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            parentFrame.openGameMenu();
        }
    };

    ActionListener settingActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            parentFrame.openSettingMenu();
        }
    };
}
