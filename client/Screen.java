package client;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;

public class Screen extends JPanel {
  private JTextField textField;

  public Screen() {
    textField = new JTextField("");
    textField.setFont(new Font("Calibri", Font.PLAIN, 38));
    textField.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
    textField.setBackground(new Color(150, 150, 150));

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
}
