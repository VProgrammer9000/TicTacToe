package settings;

import program.MainFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

public class SettingsMenu extends JPanel {
    MainFrame mainFrame;

    public SettingsMenu(MainFrame mainFrame) {
        this.mainFrame=mainFrame;
        this.setLayout(new BorderLayout());

        // sets Title
        JPanel panelTitle =new JPanel();
        panelTitle.setBackground(Settings.getInstance().getBackground());

        JLabel title=new JLabel("settings");
        title.setForeground(Settings.getInstance().getForeground());
        title.setFont(new Font(Settings.getInstance().getTextFont().getFontName(), Font.BOLD, (int)(Settings.getInstance().getTextFontSize()*1.5)));
        title.setBounds(20,0,20,20);

        panelTitle.add(title);
        this.add(panelTitle,BorderLayout.NORTH);

        JPanel settingsPanel = new JPanel();
        settingsPanel.setBackground(Settings.getInstance().getBackground());
        settingsPanel.setLayout(new GridLayout(5,2,50,20));
        //settingsPanel.setBorder(new EmptyBorder(40,40,40,40));



        //init Background color Button
        JButton backgroundColorButton=new JButton("Background Color");
        backgroundColorButton.setBorder(LineBorder.createBlackLineBorder());
        backgroundColorButton.setBackground(Settings.getInstance().getBackground());
        backgroundColorButton.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.PLAIN,Settings.getInstance().getTextFontSize()));
        backgroundColorButton.addActionListener(colorChanger);

        settingsPanel.add(backgroundColorButton);



        //init Foreground color Button
        JButton foregroundColorButton=new JButton("Foreground Color");
        foregroundColorButton.setBorder(LineBorder.createBlackLineBorder());
        foregroundColorButton.setBackground(Settings.getInstance().getForeground());
        foregroundColorButton.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.PLAIN,Settings.getInstance().getTextFontSize()));
        foregroundColorButton.addActionListener(colorChanger);

        settingsPanel.add(foregroundColorButton);



        DefaultComboBoxModel<FontSize> data1 = new DefaultComboBoxModel<>(FontSize.values());
        DefaultComboBoxModel<FontSize> data2 = new DefaultComboBoxModel<>(FontSize.values());
        JPanel textFontSizePanel =new JPanel();
        textFontSizePanel.setBorder(LineBorder.createBlackLineBorder());
        textFontSizePanel.setBackground(Settings.getInstance().getBackground());

        JLabel textFontSizeLabel =new JLabel("Text Size ");
        textFontSizeLabel.setForeground(Settings.getInstance().getForeground());
        textFontSizeLabel.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.PLAIN,Settings.getInstance().getTextFontSize()));
        textFontSizePanel.add(textFontSizeLabel);

        //init text FontSize Combobox
        JComboBox<FontSize> textFontSizeComboBox = new JComboBox<>(data1);
        textFontSizeComboBox.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.PLAIN,Settings.getInstance().getTextFontSize()));
        textFontSizeComboBox.setForeground(Settings.getInstance().getForeground());
        textFontSizeComboBox.setSelectedItem(Settings.getInstance().getTextFontSizeEnum());
        textFontSizePanel.add(textFontSizeComboBox);

        settingsPanel.add(textFontSizePanel);


        JPanel gameBoardFontSizePanel =new JPanel();
        gameBoardFontSizePanel.setBorder(LineBorder.createBlackLineBorder());
        gameBoardFontSizePanel.setBackground(Settings.getInstance().getBackground());
        //init text FontSize Label
        JLabel gameBoardFontSizeLabel =new JLabel("Game Text Size ");
        gameBoardFontSizeLabel.setForeground(Settings.getInstance().getForeground());
        gameBoardFontSizeLabel.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.PLAIN,Settings.getInstance().getTextFontSize()));
        gameBoardFontSizePanel.add(gameBoardFontSizeLabel);

        //init text FontSize Combobox
        JComboBox<FontSize> gameBoardFontSizeComboBox = new JComboBox<>(data2);
        gameBoardFontSizeComboBox.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.PLAIN,Settings.getInstance().getTextFontSize()));
        gameBoardFontSizeComboBox.setForeground(Settings.getInstance().getForeground());
        gameBoardFontSizeComboBox.setSelectedItem(Settings.getInstance().getGameBoardFontSizeEnum());
        gameBoardFontSizePanel.add(gameBoardFontSizeComboBox);
        settingsPanel.add(gameBoardFontSizePanel);








        JPanel player1SymbolPanel =new JPanel();
        player1SymbolPanel.setBorder(LineBorder.createBlackLineBorder());
        player1SymbolPanel.setBackground(Settings.getInstance().getBackground());
        //init Player 1 Symbol Label
        JLabel player1SymbolLabel =new JLabel("Player 1 ");
        player1SymbolLabel.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.PLAIN,Settings.getInstance().getTextFontSize()));
        player1SymbolLabel.setForeground(Settings.getInstance().getForeground());
        player1SymbolPanel.add(player1SymbolLabel);
        //init Player 1 Symbol Textfield
        JTextField player1SymbolTextfield =new JTextField();
        player1SymbolTextfield.setText(""+Settings.getInstance().getPlayersSymbol()[0]);
        player1SymbolTextfield.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.PLAIN,Settings.getInstance().getTextFontSize()));
        player1SymbolTextfield.addKeyListener(textFieldValidation);
        player1SymbolPanel.add(player1SymbolTextfield);
        settingsPanel.add(player1SymbolPanel);

        JPanel player2SymbolPanel=new JPanel();
        player2SymbolPanel.setBorder(LineBorder.createBlackLineBorder());
        player2SymbolPanel.setBackground(Settings.getInstance().getBackground());
        //init Player 2 Symbol Label
        JLabel player2SymbolLabel =new JLabel("Player 2 ");
        player2SymbolLabel.setForeground(Settings.getInstance().getForeground());
        player2SymbolLabel.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.PLAIN,Settings.getInstance().getTextFontSize()));
        player2SymbolPanel.add(player2SymbolLabel);
        //init Player 2 Symbol Textfield
        JTextField player2SymbolTextfield =new JTextField();
        player2SymbolTextfield.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.PLAIN,Settings.getInstance().getTextFontSize()));
        player2SymbolTextfield.setText(""+Settings.getInstance().getPlayersSymbol()[1]);
        player2SymbolTextfield.addKeyListener(textFieldValidation);
        player2SymbolPanel.add(player2SymbolTextfield);

        settingsPanel.add(player2SymbolPanel);





        //init Player 1 Color Button
        JButton player1ColorButton=new JButton("Color Player 1");
        player1ColorButton.setBorder(LineBorder.createBlackLineBorder());
        player1ColorButton.setBackground(Settings.getInstance().getPlayersColor()[0]);
        player1ColorButton.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.PLAIN,Settings.getInstance().getTextFontSize()));
        player1ColorButton.addActionListener(colorChanger);

        settingsPanel.add(player1ColorButton);

        //init Player 2 Color Button
        JButton player2ColorButton=new JButton("Color Player 2");
        player2ColorButton.setBorder(LineBorder.createBlackLineBorder());
        player2ColorButton.setBackground(Settings.getInstance().getPlayersColor()[1]);
        player2ColorButton.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.PLAIN,Settings.getInstance().getTextFontSize()));
        player2ColorButton.addActionListener(colorChanger);

        settingsPanel.add(player2ColorButton);

        //init win Marker Button
        JButton winMarkerButton=new JButton("Color Wining Marker");
        winMarkerButton.setBorder(LineBorder.createBlackLineBorder());
        winMarkerButton.setBackground(Settings.getInstance().getWinMarker());
        winMarkerButton.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.PLAIN,Settings.getInstance().getTextFontSize()));
        winMarkerButton.addActionListener(colorChanger);

        settingsPanel.add(winMarkerButton);

        this.add(settingsPanel, BorderLayout.CENTER);

        JPanel footer= new JPanel();
        footer.setBackground(Settings.getInstance().getBackground());
        footer.setLayout(new FlowLayout());




        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.BOLD,Settings.getInstance().getTextFontSize()));
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.openStartMenu();
            }
        });

        footer.add(cancelButton);

        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font(Settings.getInstance().getTextFont().getFontName(),Font.BOLD,Settings.getInstance().getTextFontSize()));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color background = backgroundColorButton.getBackground();
                Color foreground = foregroundColorButton.getBackground();

                FontSize textFontSize= (FontSize) textFontSizeComboBox.getSelectedItem();
                FontSize gameBoardFontSize= (FontSize) gameBoardFontSizeComboBox.getSelectedItem();

                char[] playersSymbol = {
                        player1SymbolTextfield.getText().charAt(0),
                        player2SymbolTextfield.getText().charAt(0)
                };
                Color[] playersColor = {
                        player1ColorButton.getBackground(),
                        player2ColorButton.getBackground()
                };
                Color winMarker = winMarkerButton.getBackground();

                Settings settings = Settings.getInstance();

                settings.setBackground(background);
                settings.setForeground(foreground);
                settings.setTextFontSize(textFontSize);
                settings.setGameBoardFontSize(gameBoardFontSize);
                settings.setPlayersSymbol(playersSymbol);
                settings.setPlayersColor(playersColor);
                settings.setWinMarker(winMarker);

                mainFrame.restart();

            }
        });

        footer.add(saveButton);
        this.add(footer,BorderLayout.SOUTH);

    }
    ActionListener colorChanger=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color color =JColorChooser.showDialog(null, "Background Color", Color.BLACK);
            JButton button = (JButton) e.getSource();
            button.setBackground(color);
        }
    };

    KeyAdapter textFieldValidation = new KeyAdapter() {
        public void keyTyped(KeyEvent e) {
            JTextField textField= (JTextField) e.getSource();
            if (textField.getText().length() >= 1 ) // limit textfield to 1 characters
                e.consume();
        }
    };
}
