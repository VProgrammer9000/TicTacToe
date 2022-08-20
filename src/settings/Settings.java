package settings;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Settings {
    private static Settings settings;

    private Color background = new Color(0x00c0c7);
    private Color foreground = Color.BLACK;
    private FontSize textFontSize= FontSize.MEDIUM;
    private FontSize gameBoardFontSize= FontSize.MEDIUM;
    private char[] playersSymbol = {'X','O'};
    private Color[] playersColor = {Color.WHITE, Color.BLACK};
    private Color winMarker = Color.GREEN;

    private Font textFont;

    //TODO get values from FILE
    private Settings(){
        initFonts();
    }
    private void initFonts(){
        try{
            // load a custom font in your project folder
            textFont = Font.createFont(Font.TRUETYPE_FONT, new File("data/TextFont.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("data/TextFont.ttf")));
        }
        catch(IOException | FontFormatException e){}

    }

    public static Settings getInstance(){
        if (settings==null) {
            settings = new Settings();
        }
        return settings;
    }

    public Color[] getPlayersColor() {
        return playersColor;
    }

    protected void setPlayersColor(Color[] playersColor) {
        this.playersColor = playersColor;
    }

    public char[] getPlayersSymbol() {
        return playersSymbol;
    }

    protected void setPlayersSymbol(char[] playersSymbol) {
        this.playersSymbol = playersSymbol;
    }

    public Color getBackground() {
        return background;
    }

    protected void setBackground(Color background) {
        this.background = background;
    }

    public Color getForeground() {
        return foreground;
    }

    protected void setForeground(Color foreground) {
        this.foreground = foreground;
    }

    public Color getWinMarker() {
        return winMarker;
    }

    protected void setWinMarker(Color winMarker) {
        this.winMarker = winMarker;
    }

    public Font getTextFont() {
        return textFont;
    }

    public int getTextFontSize() {
        return textFontSize.size;
    }

    protected FontSize getTextFontSizeEnum() {
        return textFontSize;
    }


    protected void setTextFontSize(FontSize textFontSize) {
        this.textFontSize = textFontSize;
    }

    public int getGameBoardFontSize() {
        return gameBoardFontSize.size;
    }

    protected FontSize getGameBoardFontSizeEnum() {
        return gameBoardFontSize;
    }


    protected void setGameBoardFontSize(FontSize gameBoardFontSize) {
        this.gameBoardFontSize = gameBoardFontSize;
    }
}