package program;

import settings.Settings;
import settings.SettingsMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    JFrame frame=new JFrame();
    JPanel mainPanel=new JPanel();
    CardLayout cardLayout = new CardLayout();
    JButton backButton;



    public MainFrame() {
        initFrame();

        mainPanel.setLayout(cardLayout);
        mainPanel.setBorder(new EmptyBorder(20,175,40,175));
        mainPanel.setBackground(Settings.getInstance().getBackground());

        JPanel startMenu=new StartMenu(this);
        JPanel gameMenu=new GameMenu(this);
        JPanel settingsMenu=new SettingsMenu(this);

        mainPanel.add(startMenu,"startMenu");
        mainPanel.add(gameMenu,"gameMenu");
        mainPanel.add(settingsMenu,"settingMenu");


        cardLayout.show(mainPanel,"startMenu");

        frame.setVisible(true);
    }
    private void initFrame(){
        frame.setTitle("TicTacToe");
        frame.setIconImage( new ImageIcon("data/XO.png").getImage());
        for ( Window w : Window.getWindows() ) {
           // GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow( w );
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Settings.getInstance().getBackground());

        //UIManager
        /*try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); TODO Chose UIManager
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }*/

        JPanel header=new JPanel();
        header.setLayout(new BorderLayout());
        header.setBackground(Settings.getInstance().getBackground());

        backButton=new JButton("\uD83D\uDD19 ");
        backButton.setFont(new Font("Serif",Font.BOLD, (int)(Settings.getInstance().getTextFontSize()*2)));
        backButton.setBackground(Settings.getInstance().getBackground());
        backButton.setForeground(Settings.getInstance().getForeground());
        backButton.setBorder(null);
        backButton.setVisible(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openStartMenu();
            }
        });

        header.add(backButton, BorderLayout.WEST);

        // sets Title
        JPanel panelTitle =new JPanel();
        panelTitle.setBackground(Settings.getInstance().getBackground());

        JLabel title=new JLabel("Tic Tac Toe");
        title.setForeground(Settings.getInstance().getForeground());
        title.setFont(new Font(Settings.getInstance().getTextFont().getFontName(), Font.BOLD, (int)(Settings.getInstance().getTextFontSize()*2)));
        title.setBounds(20,0,20,20);

        panelTitle.add(title);
        header.add(panelTitle);

        frame.add(header, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);
    }



    public void openStartMenu(){
        cardLayout.show(mainPanel,"startMenu");
        backButton.setVisible(false);

    }

    public void openGameMenu(){
        cardLayout.show(mainPanel,"gameMenu");
        backButton.setVisible(true);
    }

    public void openSettingMenu(){
        cardLayout.show(mainPanel,"settingMenu");
        backButton.setVisible(true);
    }

    public void gameMenuGameButtonPressed(int gameSize, boolean specialMode){
        JPanel panel = new Game(gameSize,specialMode, this);
        mainPanel.add(panel,"gameBoard");
        cardLayout.show(mainPanel,"gameBoard");
        backButton.setVisible(false);
    }

    public void restart(){
        frame.dispose();
        new MainFrame();
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
