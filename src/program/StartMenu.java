package program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu extends JPanel{
    MainFrame parentFrame;
    public StartMenu(MainFrame parentFrame){

        this.parentFrame=parentFrame;

        //sets Picture
        JLabel label = new JLabel(); //JLabel Creation
        label.setIcon(new ImageIcon("Download.png")); //Sets the image to be displayed as an icon
        Dimension size = label.getPreferredSize(); //Gets the size of the image
        label.setBounds(50, 30, size.width, size.height); //Sets the location of the image
        this.add(label);

        //init selection Panel
        JPanel buttonSelectPanel = new JPanel();
        buttonSelectPanel.setLayout(new GridLayout(1,2));


        //init gameButton
        JButton playButton =new JButton("Play");
        playButton.addActionListener(gameActionListener);
        buttonSelectPanel.add(playButton);

        //init looksButton
        JButton looksButton=new JButton("Looks");
        looksButton.addActionListener(looksActionListener);
        buttonSelectPanel.add(looksButton);

        this.add(buttonSelectPanel);

        //TODO Remove
        this.setBackground(Color.BLUE);
        this.setPreferredSize(new Dimension(300,300));
    }

    ActionListener gameActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            parentFrame.startMenuGameButtonPressed();
        }
    };

    ActionListener looksActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            parentFrame.startMenuLookButtonPressed();
        }
    };
}
