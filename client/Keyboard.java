package client;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.util.LinkedHashMap;

import static client.KeyboardButtonType.*;

public class Keyboard extends JPanel {
  private ButtonClickListener listener;
  private LinkedHashMap<String, KeyboardButtonType> buttonsData = new LinkedHashMap<String, KeyboardButtonType>() {
    {
      put("π", OPERATION);
      put("e", OPERATION);
      put("(", OPERATION);
      put(")", OPERATION);
      put("%", OPERATION);
      put("AC", OPERATION);
      put("sin", OPERATION);
      put("ln", OPERATION);
      put("7", OPERAND);
      put("8", OPERAND);
      put("9", OPERAND);
      put("/", OPERATION);
      put("cos", OPERATION);
      put("log", OPERATION);
      put("4", OPERAND);
      put("5", OPERAND);
      put("6", OPERAND);
      put("x", OPERATION);
      put("tan", OPERATION);
      put("√", OPERATION);
      put("1", OPERAND);
      put("2", OPERAND);
      put("3", OPERAND);
      put("-", OPERATION);
      put("EXP", OPERATION);
      put("xⁿ", OPERATION);
      put("0", OPERAND);
      put(".", OPERAND);
      put("=", EQUAL);
      put("+", OPERATION);
    }
  };

  public Keyboard() {
    GridLayout layout = new GridLayout(5, 6);
    layout.setHgap(2);
    layout.setVgap(2);
    this.setLayout(layout);

    this.buttonsInit();
  }

  public void buttonsInit() {
    buttonsData.forEach((label, type) -> {
      KeyboardButton btn = new KeyboardButton(label, type);
      btn.setFont(new Font("Calibri", Font.PLAIN, 20));
      btn.setFocusPainted(false);
      btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
      if (type == OPERATION) {
        btn.setBackground(new Color(218, 220, 224));
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
      } else if (type == OPERAND) {
        btn.setBackground(new Color(241, 243, 244));
        btn.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
      } else if (type == EQUAL) {
        btn.setBackground(new Color(66, 133, 244));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 2, true));
      }

      btn.addActionListener(ev -> {
        ButtonClickEvent e = new ButtonClickEvent(btn, label, type);
        this.listener.ButtonClickEventOccurred(e);
      });

      this.add(btn);
    });
  }

  /**
   * @param listener the listener to set
   */
  public void setListener(ButtonClickListener listener) {
    this.listener = listener;
  }
}
