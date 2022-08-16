package program;

import java.awt.*;

public class Settings {
    private static Settings settings;
    Color[] playersColor = {Color.RED, Color.BLACK};
    char[] playersSymbol = {'X','O'};
    Color background = Color.CYAN;
    Color winingMarker = Color.GREEN;
    //TODO get values from FILE
    private Settings(){}

    public static Settings getInstance(){
        if (settings==null) {
            settings = new Settings();
        }
        return settings;
    }

    public Color[] getPlayersColor() {
        return playersColor;
    }

    public void setPlayersColor(Color[] playersColor) {
        this.playersColor = playersColor;
    }

    public char[] getPlayersSymbol() {
        return playersSymbol;
    }

    public void setPlayersSymbol(char[] playersSymbol) {
        this.playersSymbol = playersSymbol;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Color getWiningMarker() {
        return winingMarker;
    }

    public void setWiningMarker(Color winingMarker) {
        this.winingMarker = winingMarker;
    }
}
