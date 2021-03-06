package client;

import javax.swing.JButton;

public class KeyboardButton extends JButton {
    private KeyboardButtonType type;

    public KeyboardButton(String label, KeyboardButtonType type) {
        super(label);
        this.type = type;
    }

    /**
     * @return the type
     */
    public KeyboardButtonType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(KeyboardButtonType type) {
        this.type = type;
    }
}