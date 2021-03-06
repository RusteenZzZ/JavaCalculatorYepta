package client;

import java.util.EventObject;

public class ButtonClickEvent extends EventObject {
    private String label;
    private KeyboardButtonType type;

    public ButtonClickEvent(Object source, String label, KeyboardButtonType type) {
        super(source);
        this.label = label;
        this.type = type;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return the type
     */
    public KeyboardButtonType getType() {
        return type;
    }
}