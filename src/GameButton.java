import javax.swing.*;

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
