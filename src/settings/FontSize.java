package settings;

public enum FontSize {
    SMALL("Small",20), MEDIUM("Medium",30), BIG("Big",50);
    String text;
    int size;
    FontSize(String text, int size){
        this.text=text;
        this.size=size;
    }
    @Override
    public String toString() {
        return text;
    }
}
