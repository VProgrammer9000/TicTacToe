import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    private JFrame frame=new JFrame();
    private JPanel infoPanel =new JPanel();
    private JPanel gamePanel=new JPanel();
    private JButton[][] button=new JButton[3][3];
    private JButton playAgain=new JButton("Play again");
    private JLabel infoPlayer=new JLabel("X am Zug");
    private boolean player = true;


    //Main
    public static void main(String[] args) {
        GUI gui=new GUI();
    }

    public GUI() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500,646);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        initButtons();
        initPanels();
        frame.add(infoPanel);
        frame.add(gamePanel);
        frame.setVisible(true);
    }
    private void initButtons(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                button[i][j]=new JButton();
                button[i][j].addActionListener(buttonAL);
                button[i][j].setFont(new Font("Serif", Font.PLAIN, 50));
                button[i][j].setBackground(Color.green);
                gamePanel.add(button[i][j]);
            }
        }
    }
    public void initPanels(){
        infoPanel.setLayout(new BorderLayout());
        gamePanel.setLayout(new GridLayout(3, 3 , 5, 5));
        infoPanel.setBounds(0,0,500,100);
        gamePanel.setBounds(0,100,500,500);
        infoPanel.setBackground(Color.black);
        gamePanel.setBackground(Color.black);
        infoPlayer.setFont(new Font("Serif", Font.PLAIN, 50));
        infoPlayer.setForeground(Color.white);
        infoPanel.add(infoPlayer);
    }

    ActionListener buttonAL=new ActionListener() {
        private int number=0;
        @Override
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (e.getSource() == button[i][j]&&button[i][j].getText().equals("")) {
                        if (player) {
                            button[i][j].setText("X");
                            player = false;
                            infoPlayer.setText("O am Zug");
                        } else {
                            button[i][j].setText("O");
                            player = true;
                            infoPlayer.setText("X am Zug");
                        }
                    }
                }
            }
            check();
        }
    };
    private void check() {
        boolean winner=false;
        for (int i = 0; i < 3; i++) {
            if (button[i][0].getText().equals(button[i][1].getText())&&button[i][1].getText().equals(button[i][2].getText())&&!button[i][0].getText().equals("")){
                printWinscreen(button[i][0].getText());
                winner=true;
            }
            if (button[0][i].getText().equals(button[1][i].getText())&&button[1][i].getText().equals(button[2][i].getText())&&!button[2][i].getText().equals("")){
                printWinscreen(button[0][i].getText());
                winner=true;
            }
        }
        if(((button[0][0].getText().equals(button[1][1].getText())&&button[1][1].getText().equals(button[2][2].getText()))||(button[0][2].getText().equals(button[1][1].getText())&&button[1][1].getText().equals(button[2][0].getText())))&&!button[1][1].getText().equals("")){
            printWinscreen(button[1][1].getText());
            winner=true;
        }
        if(!winner){
            boolean draw=true;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (button[i][j].getText().equals("")){
                        draw=false;
                    }
                }
            }
            if (draw){
                printWinscreen("draw");
            }
        }
    }
    public void printWinscreen(String winner){
        frame.dispose();
        frame=new JFrame();
        JLabel label;
        if(!winner.equals("draw")){
            label=new JLabel("Player "+winner+" won");
        }else{
            label=new JLabel("Its Draw");
        }
        label.setFont(new Font("Serif", Font.PLAIN, 30));
        JPanel winScreenTxt=new JPanel();
        JPanel winScreenButton=new JPanel();
        playAgain.addActionListener(playAgainAL);
        playAgain.setBounds(100, 0, 300, 200);
        playAgain.setBackground(Color.green);
        playAgain.setFocusable(false);
        playAgain.setOpaque(true);
        playAgain.setText("Play again");
        label.setForeground(Color.white);

        winScreenTxt.add(label);
        winScreenTxt.setBackground(Color.black);
        winScreenButton.setLayout(null);
        winScreenButton.setBackground(Color.BLACK);
        winScreenButton.setOpaque(true);
        winScreenButton.add(playAgain);
        winScreenTxt.setBounds(0,0,500,100);
        winScreenButton.setBounds(0,100,500,350);

        frame.setLayout(null);
        frame.setSize(500,500);
        frame.add(winScreenTxt);
        frame.add(winScreenButton);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    ActionListener playAgainAL=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new GUI();
        }
    };
}