package client;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class Screen extends JPanel {
  public Screen() {
    JLabel screen = new JLabel("2+4=6");
    screen.setFont(new Font("Calibri", Font.PLAIN, 38));
    this.add(screen);
  }
}
