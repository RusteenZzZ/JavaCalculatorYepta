package client;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class Screen extends JPanel {
  private JLabel label;

  public Screen() {
    label = new JLabel("");
    label.setFont(new Font("Calibri", Font.PLAIN, 38));
    this.add(label);
  }

  public String getText() {
    return label.getText();
  }

  public void setText(String text) {
    label.setText(text);
  }
}
