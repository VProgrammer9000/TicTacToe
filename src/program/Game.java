package program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Game extends JPanel{
    JFrame gameMenu;

    JPanel infoPanel=new JPanel();
    JLabel textField=new JLabel();

    JPanel gameBoardPanel =new JPanel();
    GameButton[] buttons;

    JPanel selectionPanel;
    JTable table;

    JPanel menuPanel;


    private final int size;
    private final boolean specialMode;
    private final char[] players;
    //priority = index
    private int[][] priorityAmount;
    private String[][] data;
    private int currentPlayer=0;

    public Game(
            int gameBoardSize,
            boolean specialMode,
            char[] playerSymbol,
            JFrame gameMenu
    ) {
        this.size = gameBoardSize;
        this.specialMode = specialMode;
        this.players = playerSymbol;
        this.gameMenu = gameMenu;


        //init InfoPanel
        infoPanel.add(textField);
        textField.setText("Player "+ players[currentPlayer] + " turn");
        this.add(infoPanel,BorderLayout.NORTH);

        //inits gamePanel
        buttons = new GameButton[size*size];
        gameBoardPanel.setLayout(new GridLayout(size,size));
        for (int i = 0; i < buttons.length; i++) {
            buttons[i]=new GameButton();
            buttons[i].addActionListener(gameButtonActionListener);
            buttons[i].setRolloverEnabled(false);
            gameBoardPanel.add(buttons[i]);
        }

        if (specialMode){

            //init playerMovePriority
            int amountOfPriority=size-1;

            priorityAmount =new int[3][amountOfPriority];

            for (int i = 0; i < amountOfPriority; i++) {
                priorityAmount[0][i]=i;
            }

            Arrays.fill(priorityAmount[1], amountOfPriority);
            this.priorityAmount[2] = priorityAmount[1].clone();



            selectionPanel=new JPanel();
            // table start
            String[] columnNames={"priority", "amount p1", "amount p2"};
            data=new String[amountOfPriority][3];
            repaintTable();


            //init Table
            table =new JTable(data,columnNames);
            table.setPreferredScrollableViewportSize(new Dimension(200,500));
            table.setFillsViewportHeight(true);
            table.setFont(new Font("SansSerif Bold",Font.PLAIN,30));
            //TODO Change COLOR IF SELECTED
            //table.setSelectionBackground(Color.red);

            //uneditable
            table.setDefaultEditor(Object.class, null);
            table.setFocusable(true);
            table.setColumnSelectionAllowed(false);
            table.setRowSelectionAllowed(false);

            //colum size
            table.setRowHeight(30);
            //table.getColumnModel().getColumn(0).setPreferredWidth(20);
            //table.getColumnModel().getColumn(1).setPreferredWidth(20);


            JScrollPane scrollPane = new JScrollPane(table);
            selectionPanel.add(scrollPane);

            //table end

            this.add(selectionPanel,BorderLayout.EAST);

        }



        this.add(gameBoardPanel,BorderLayout.CENTER);

        //init menu Panel
        menuPanel=new JPanel();
        JButton reloadButton=new JButton("⟳");
        JButton menuButton=new JButton("☰");

        menuPanel.add(reloadButton);
        reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (GameButton button:buttons) {
                    button.setValue("");
                    button.setPriority(-1);
                    button.setEnabled(true);
                    button.setBackground(null);
                }
            }
        });

        menuPanel.add(menuButton);
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

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
                //TODO Fehlermeldung
                textField.setText("Fehlermeldung");
                return;
            }


            //TODO lower than 0 not possible
            //TODO Table will be repainted corrected



            if (priority > button.getPriority()){
                button.setValue("" + players[currentPlayer]);
                button.setPriority(priority);
                priorityAmount[currentPlayer+1][priority]--;
                repaintTable();
            }else {
                textField.setText("Too low priority \n Player "+ players[currentPlayer] + " turn");
                return;
            }
        }else{

            if(button.getText().equals("")){
                button.setValue(""+players[currentPlayer]);
            }else {
                textField.setText("Unavailable Button \n Player "+ players[currentPlayer] + " turn");
                return;
            }
        }


        //Switch Player
        if (currentPlayer == 0)
            currentPlayer = 1;
        else
            currentPlayer = 0;

        //Update Textfield Text
        textField.setText("Player "+ players[currentPlayer] + " turn");
    }
    private void checkGameOver(){
        if (size==3) {
            // vertical
            for (int i = 0; i < size * size; i += size) {
                if ((buttons[i].getText().equals(buttons[i + 1].getText()))
                        && (buttons[i].getText().equals(buttons[i + 2].getText()))
                        && (!buttons[i].getText().equals(""))) {
                    int[] buttonIndex={i,i+1,i+2};
                    gameOver(buttonIndex);
                }
            }

            //horizontal
            for (int i = 0; i < size; i++) {
                if ((buttons[i].getText().equals(buttons[i + size].getText()))
                        && (buttons[i].getText().equals(buttons[i + 2 * size].getText()))
                        && (!buttons[i].getText().equals(""))) {
                    int[] buttonIndex={i,i+size,i+2*size};
                    gameOver(buttonIndex);
                }
            }
            //upper left to lower right
            if ((buttons[0].getText().equals(buttons[4].getText()))
                    && (buttons[0].getText().equals(buttons[8].getText()))
                    && (!buttons[0].getText().equals(""))) {
                int[] buttonIndex={0,4,8};
                gameOver(buttonIndex);
            }

            //upper right to lower left
            if ((buttons[2].getText().equals(buttons[4].getText()))
                    && (buttons[2].getText().equals(buttons[6].getText()))
                    && (!buttons[2].getText().equals(""))) {
                int[] buttonIndex={2,4,6};
                gameOver(buttonIndex);
            }

        }else{
            //vertical
            for (int i = 0; i < size * size; i += size) {
                for (int j = 0; j < size - 3; j++) {
                    if ((buttons[i+j].getText().equals(buttons[i + j + 1].getText()))
                            && (buttons[i+j].getText().equals(buttons[i + j + 2].getText()))
                            && (buttons[i+j].getText().equals(buttons[i + j + 3].getText()))
                            && (!buttons[i+j].getText().equals(""))) {
                        int[] buttonIndex={i+j,i + j + 1,i + j + 2,i + j + 3};
                        gameOver(buttonIndex);
                    }
                }
            }

            //horizontal
            for (int i = 0; i < size; i++){
                for (int j = 0; j < size - 3; j++) {
                    if ((buttons[i + j*size].getText().equals(buttons[i + (j + 1) * size].getText()))
                            && (buttons[i + j*size].getText().equals(buttons[i + (j + 2) * size].getText()))
                            && (buttons[i + j*size].getText().equals(buttons[i + (j + 3) * size].getText()))
                            && (!buttons[i + j*size].getText().equals(""))) {
                        int[] buttonIndex={i + j*size,i + (j + 1) * size,i + (j + 2) * size,i + (j + 3) * size};
                        gameOver(buttonIndex);
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
                if ((buttons[startPoint].getText().equals(buttons[startPoint + (size + 1)].getText()))
                        && (buttons[startPoint].getText().equals(buttons[startPoint + 2 * (size + 1)].getText()))
                        && (buttons[startPoint].getText().equals(buttons[startPoint + 3 * (size + 1)].getText()))
                        && (!buttons[startPoint].getText().equals(""))) {
                    int[] buttonIndex={startPoint,startPoint + (size + 1),startPoint + 2 * (size + 1),startPoint + 3 * (size + 1)};
                    gameOver(buttonIndex);
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
                if ((buttons[startPoint].getText().equals(buttons[startPoint + (size - 1)].getText()))
                        && (buttons[startPoint].getText().equals(buttons[startPoint + 2 * (size - 1)].getText()))
                        && (buttons[startPoint].getText().equals(buttons[startPoint + 3 * (size - 1)].getText()))
                        && (!buttons[startPoint].getText().equals(""))) {
                    int[] buttonIndex={startPoint,startPoint + (size - 1),startPoint + 2 * (size - 1),startPoint + 3 * (size - 1)};
                    gameOver(buttonIndex);
                }
            }

        }

    }

    private void gameOver(int[] buttonsIndex){
        if (currentPlayer==0){
            currentPlayer=1;
        }else{
            currentPlayer=0;
        }

        for (JButton button:buttons) {
            button.setEnabled(false);
        }

        for (int index:buttonsIndex) {
            buttons[index].setBackground(Color.RED);
        }

        textField.setText("Player " + players[currentPlayer] + " wins");

    }
    private void repaintTable(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < size-1; j++) {
                data[j][i] = priorityAmount[i][j]+"";
            }
        }
    }

    public class GameButton extends JButton {
        String value;
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
