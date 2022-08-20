package program;

import settings.Settings;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JPanel{
    MainFrame mainFrame;
    public StartMenu(MainFrame parentFrame){
        this.mainFrame =parentFrame;
        this.setBackground(Settings.getInstance().getBackground());
        this.setLayout(new BorderLayout());

        //sets Picture
        JLabel picture = new JLabel(); //JLabel Creation
        picture.setIcon(new ImageIcon("data/3x3.png")); //Sets the image to be displayed as an icon

        JPanel picturePanel=new JPanel();
        picturePanel.setBackground(Settings.getInstance().getBackground());
        picturePanel.add(picture);

        this.add(picturePanel);

        //init selection Panel
        JPanel buttonSelectPanel = new JPanel();
        buttonSelectPanel.setLayout(new GridLayout(1,2));


        //init gameButton
        JButton playButton =new JButton("Play");
        playButton.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.BOLD,Settings.getInstance().getTextFontSize()));
        playButton.setBackground(Settings.getInstance().getBackground());
        playButton.setForeground(Settings.getInstance().getForeground());
        playButton.setPreferredSize(new Dimension(250,100));
        playButton.setBorder(LineBorder.createBlackLineBorder());
        playButton.addActionListener(gameActionListener);
        buttonSelectPanel.add(playButton);

        //init settingButton
        JButton settingButton=new JButton("Setting");
        settingButton.setBackground(Settings.getInstance().getBackground());
        settingButton.setForeground(Settings.getInstance().getForeground());
        settingButton.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.BOLD,Settings.getInstance().getTextFontSize()));
        settingButton.setPreferredSize(new Dimension(250,100));
        settingButton.setBorder(LineBorder.createBlackLineBorder());
        settingButton.addActionListener(settingActionListener);
        buttonSelectPanel.add(settingButton);
        //TODO Correct picture
        this.add(buttonSelectPanel,BorderLayout.SOUTH);

    }

    ActionListener gameActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.openGameMenu();
        }
    };

    ActionListener settingActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mainFrame.openSettingMenu();
        }
    };
}
