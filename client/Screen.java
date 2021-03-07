package client;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;

public class Screen extends JPanel {
  private JTextField textField;
  private InputChangeListener listener;

  public Screen() {
    textField = new JTextField("");
    textField.setFont(new Font("Calibri", Font.PLAIN, 38));
    textField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
    textField.setBackground(new Color(150, 150, 150));
    textField.setTransferHandler(null);

    textField.setHighlighter(null);

    textField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() != '\b') {
          listener.inputChangeEventOccurred(new InputChangeEvent(textField, e));
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
          listener.inputChangeEventOccurred(new InputChangeEvent(textField, e));
        }
      }
    });

    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.weightx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    add(textField, gbc);
  }

  public String getText() {
    return textField.getText();
  }

  public void setText(String text) {
    textField.setText(text);
  }

  /**
   * @param listener the listener to set
   */
  public void setListener(InputChangeListener listener) {
    this.listener = listener;
  }
}
