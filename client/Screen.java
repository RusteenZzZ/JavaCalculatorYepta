package client;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;

public class Screen extends JPanel {
  private JTextField label;

  public Screen() {
    label = new JTextField("",1);
    label.setFont(new Font("Calibri", Font.PLAIN, 38));
    label.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
    label.setBackground(new Color(150, 150, 150));
    this.add(label);
  }

  public String getText() {
    return label.getText();
  }

  public void setText(String text) {
    label.setText(text);
  }

  public void setColumns(int cols){
    label.setColumns(cols);
  }
}
