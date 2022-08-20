package program;


import settings.Settings;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Game extends JPanel{
    MainFrame mainFrame;

    JPanel infoPanel=new JPanel();
    JLabel textField=new JLabel();

    JPanel gameBoardPanel =new JPanel();
    GameButton[] buttons;

    JPanel selectionPanel;
    JTable table;

    JPanel menuPanel;


    private final int size;
    private final boolean specialMode;
    private final char[] playersSymbols = Settings.getInstance().getPlayersSymbol();
    private final Color[] playersColors = Settings.getInstance().getPlayersColor();

    private int[][] priorityAmount;
    private String[][] data;

    private int currentPlayer=0;

    public Game(
            int gameBoardSize,
            boolean specialMode,
            MainFrame mainFrame
    ) {
        this.size = gameBoardSize;
        this.specialMode = specialMode;
        this.mainFrame = mainFrame;

        this.setLayout(new BorderLayout());
        this.setBackground(Settings.getInstance().getBackground());

        //init InfoPanel
        infoPanel.setBackground(Settings.getInstance().getBackground());

        textField.setForeground(Settings.getInstance().getForeground());
        textField.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.PLAIN,Settings.getInstance().getTextFontSize()));
        textField.setText("Player "+ playersSymbols[currentPlayer] + " turn");

        infoPanel.add(textField);
        this.add(infoPanel,BorderLayout.NORTH);


        //init gameBoard Panel and Buttons
        gameBoardPanel.setBorder( BorderFactory. createLineBorder(Settings.getInstance().getBackground(), 5));
        gameBoardPanel.setLayout(new GridLayout(size,size,20,20));
        gameBoardPanel.setBackground(Color.BLACK);

        buttons = new GameButton[size*size];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i]=new GameButton();
            buttons[i].addActionListener(gameButtonActionListener);
            buttons[i].setBackground(Settings.getInstance().getBackground());
            buttons[i].setRolloverEnabled(false);
            buttons[i].setFocusable(false);
            buttons[i].setBorder(null);
            buttons[i].setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.BOLD,Settings.getInstance().getGameBoardFontSize()));


            gameBoardPanel.add(buttons[i]);
        }

        if (specialMode){

            //init playerMovePriority


            priorityAmount =new int[3][size];

            for (int i = 0; i < size; i++) {
                priorityAmount[0][i]=i+1;
            }

            Arrays.fill(priorityAmount[1], size);
            priorityAmount[2] = priorityAmount[1].clone();



            selectionPanel=new JPanel();
            selectionPanel.setBackground(Settings.getInstance().getBackground());
            selectionPanel.setPreferredSize(new Dimension(400,selectionPanel.getHeight()));

            // table start
            String[] columnNames={"Power", "p1", "p2"};
            data=new String[size][3];
            repaintTable();


            //init Table
            table =new JTable(data,columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(200,500));
            table.setFillsViewportHeight(true);
            table.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.PLAIN,Settings.getInstance().getTextFontSize()));
            table.setBackground(Settings.getInstance().getBackground());
            table.setForeground(Settings.getInstance().getForeground());
            table.setGridColor(Color.BLACK);
            table.setRowHeight(50);



            //Table header
            table.getTableHeader().setBackground(Settings.getInstance().getBackground());
            table.getTableHeader().setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.PLAIN,Settings.getInstance().getTextFontSize()));
            table.getTableHeader().setReorderingAllowed(false);
            table.getTableHeader().setResizingAllowed(false);


            //TODO Change COLOR IF SELECTED
            //table.setSelectionBackground(Color.red);

            //uneditable
            table.setDefaultEditor(Object.class, null);
            table.setFocusable(true);
            table.setColumnSelectionAllowed(false);
            table.setRowSelectionAllowed(false);


            //colum size



            JScrollPane scrollPane = new JScrollPane(table);
            scrollPane.setBackground(Settings.getInstance().getBackground());
            scrollPane.setBorder(LineBorder.createBlackLineBorder());
            selectionPanel.add(scrollPane);

            //table end

            this.add(selectionPanel,BorderLayout.EAST);

        }

        this.add(gameBoardPanel,BorderLayout.CENTER);

        //init menu Panel
        menuPanel=new JPanel();
        menuPanel.setBackground(Settings.getInstance().getBackground());

        JButton reloadButton=new JButton("⟳");
        reloadButton.setBackground(Settings.getInstance().getBackground());
        reloadButton.setForeground(Settings.getInstance().getForeground());
        reloadButton.setFont(new Font("Serif",Font.BOLD,Settings.getInstance().getTextFontSize()));
        reloadButton.setPreferredSize(new Dimension(150,100));
        reloadButton.setBorder(LineBorder.createBlackLineBorder());
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (GameButton button:buttons) {
                    button.setValue("");
                    button.setPriority(-1);
                    button.setEnabled(true);
                    button.setBackground(Settings.getInstance().getBackground());

                    if (specialMode){
                        Arrays.fill(priorityAmount[1], size);
                        priorityAmount[2] = priorityAmount[1].clone();

                        repaintTable();

                    }
                }
            }
        });
        menuPanel.add(reloadButton);

        JButton menuButton=new JButton("☰");
        menuButton.setBackground(Settings.getInstance().getBackground());
        menuButton.setForeground(Settings.getInstance().getForeground());
        menuButton.setFont(new Font("Serif",Font.BOLD,Settings.getInstance().getTextFontSize()));
        menuButton.setPreferredSize(new Dimension(150,100));
        menuButton.setBorder(LineBorder.createBlackLineBorder());
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.openGameMenu();
            }
        });
        menuPanel.add(menuButton);

        this.add(menuPanel,BorderLayout.SOUTH);
    }

    ActionListener gameButtonActionListener =new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (GameButton button:buttons) {
                if (e.getSource() == button) {
                    buttonPressed(button);
                    checkGameOver();
                    return;
                }
            }

        }
    };
    private void buttonPressed(GameButton button){
        //Set Button Text
        if (specialMode){
            int priority = table.getSelectedRow();

            //TODO Deselect table
            table.setCellSelectionEnabled(false);
            table.setCellSelectionEnabled(true);

            if(priority<0){
                textField.setText("Select a Power");
                return;
            }


            if (priorityAmount[currentPlayer+1][priority]>=1){
                if (priority > button.getPriority()){
                button.setValue("" + playersSymbols[currentPlayer]);
                button.setForeground(playersColors[currentPlayer]);
                button.setPriority(priority);
                priorityAmount[currentPlayer+1][priority]--;
                repaintTable();
                }else {
                    textField.setText("Too low Power // Player "+ playersSymbols[currentPlayer] + " turn");
                    return;
                }
            }else {
                textField.setText("Power is completly used // Player "+ playersSymbols[currentPlayer]+ " turn");
                return;
            }
        }else{

            if(button.getValue().equals("")){
                button.setValue(""+ playersSymbols[currentPlayer]);
                button.setForeground(playersColors[currentPlayer]);
            }else {
                textField.setText("Unavailable Button // Player "+ playersSymbols[currentPlayer] + " turn");
                return;
            }
        }


        //Switch Player
        if (currentPlayer == 0)
            currentPlayer = 1;
        else
            currentPlayer = 0;

        //Update Textfield Text
        textField.setText("Player "+ playersSymbols[currentPlayer] + " turn");

    }
    private void checkGameOver(){
        if (size==3) {
            // vertical
            for (int i = 0; i < size * size; i += size) {
                if ((buttons[i].getValue().equals(buttons[i + 1].getValue()))
                        && (buttons[i].getValue().equals(buttons[i + 2].getValue()))
                        && (!buttons[i].getValue().equals(""))) {
                    int[] buttonIndex={i,i+1,i+2};
                    gameOver(buttonIndex);
                    return;
                }
            }

            //horizontal
            for (int i = 0; i < size; i++) {
                if ((buttons[i].getValue().equals(buttons[i + size].getValue()))
                        && (buttons[i].getValue().equals(buttons[i + 2 * size].getValue()))
                        && (!buttons[i].getValue().equals(""))) {
                    int[] buttonIndex={i,i+size,i+2*size};
                    gameOver(buttonIndex);
                    return;
                }
            }
            //upper left to lower right
            if ((buttons[0].getValue().equals(buttons[4].getValue()))
                    && (buttons[0].getValue().equals(buttons[8].getValue()))
                    && (!buttons[0].getValue().equals(""))) {
                int[] buttonIndex={0,4,8};
                gameOver(buttonIndex);
                return;
            }

            //upper right to lower left
            if ((buttons[2].getValue().equals(buttons[4].getValue()))
                    && (buttons[2].getValue().equals(buttons[6].getValue()))
                    && (!buttons[2].getValue().equals(""))) {
                int[] buttonIndex={2,4,6};
                gameOver(buttonIndex);
                return;
            }

        }else{
            //vertical
            for (int i = 0; i < size * size; i += size) {
                for (int j = 0; j < size - 3; j++) {
                    if ((buttons[i+j].getValue().equals(buttons[i + j + 1].getValue()))
                            && (buttons[i+j].getValue().equals(buttons[i + j + 2].getValue()))
                            && (buttons[i+j].getValue().equals(buttons[i + j + 3].getValue()))
                            && (!buttons[i+j].getValue().equals(""))) {
                        int[] buttonIndex={i+j,i + j + 1,i + j + 2,i + j + 3};
                        gameOver(buttonIndex);
                        return;
                    }
                }
            }

            //horizontal
            for (int i = 0; i < size; i++){
                for (int j = 0; j < size - 3; j++) {
                    if ((buttons[i + j*size].getValue().equals(buttons[i + (j + 1) * size].getValue()))
                            && (buttons[i + j*size].getValue().equals(buttons[i + (j + 2) * size].getValue()))
                            && (buttons[i + j*size].getValue().equals(buttons[i + (j + 3) * size].getValue()))
                            && (!buttons[i + j*size].getValue().equals(""))) {
                        int[] buttonIndex={i + j*size,i + (j + 1) * size,i + (j + 2) * size,i + (j + 3) * size};
                        gameOver(buttonIndex);
                        return;
                    }
                }
            }

            //upper left to lower right
                //init StartPoints
                int[] startPos = new int[(size-3)*(size-3 )];
                int counter = 0;
                for (int i = 0; i < size*(size-3); i++) {
                    if (i%size<size-3) {
                        startPos[counter]=i;
                        counter++;
                    }
                }
            for (int startPoint:startPos) {
                if ((buttons[startPoint].getValue().equals(buttons[startPoint + (size + 1)].getValue()))
                        && (buttons[startPoint].getValue().equals(buttons[startPoint + 2 * (size + 1)].getValue()))
                        && (buttons[startPoint].getValue().equals(buttons[startPoint + 3 * (size + 1)].getValue()))
                        && (!buttons[startPoint].getValue().equals(""))) {
                    int[] buttonIndex={startPoint,startPoint + (size + 1),startPoint + 2 * (size + 1),startPoint + 3 * (size + 1)};
                    gameOver(buttonIndex);
                    return;
                }
            }

            //upper right to lower left
                //init StartPoints
                startPos = new int[100];
                counter = 0;
                for (int i = 3; i < size*(size-3); i++) {
                    if (i%size>=3) {
                        startPos[counter]=i;
                        counter++;
                    }
                }
            for (int startPoint:startPos) {
                if ((buttons[startPoint].getValue().equals(buttons[startPoint + (size - 1)].getValue()))
                        && (buttons[startPoint].getValue().equals(buttons[startPoint + 2 * (size - 1)].getValue()))
                        && (buttons[startPoint].getValue().equals(buttons[startPoint + 3 * (size - 1)].getValue()))
                        && (!buttons[startPoint].getValue().equals(""))) {
                    int[] buttonIndex={startPoint,startPoint + (size - 1),startPoint + 2 * (size - 1),startPoint + 3 * (size - 1)};
                    gameOver(buttonIndex);
                    return;
                }
            }
        }
        //Draw
        boolean draw = true;
        if (!specialMode) {

            for (GameButton button : buttons) {
                if (button.getValue().equals("")) {
                    draw = false;
                }
            }

        }else {

            for (int i: priorityAmount[1]) {
                if (i!=0){
                    draw=false;
                }
            }
            for (int i: priorityAmount[2]) {
                if (i!=0){
                    draw=false;
                }
            }
        }
        if (draw){
            gameOver(null);
        }

    }

    private void gameOver(int[] buttonsIndex){

        for (JButton button:buttons) {
            button.setEnabled(false);
        }

        if (buttonsIndex==null) {

            textField.setText("Draw");

        }else{

            if (currentPlayer == 0) {
                currentPlayer = 1;
            } else {
                currentPlayer = 0;
            }

            for (int index : buttonsIndex) {
                buttons[index].setBackground(Settings.getInstance().getWinMarker());
            }

            textField.setText("Player " + playersSymbols[currentPlayer] + " wins");
        }
    }

    private void repaintTable(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < size; j++) {
                data[j][i] = priorityAmount[i][j]+"";
            }
        }
    }

    public class GameButton extends JButton {
        String value="";
        int priority=-1;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
            setText(value);
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
            //TODO Set Size
        }
    }
}
