package client;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;

public class Main {
  public static final int WIDTH = 600;
  public static final int HEIGHT = 450;

  public static void main(String args[]) {
    JFrame frame = new JFrame("Calculator");
    frame.setSize(WIDTH, HEIGHT);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    frame.setLayout(new FlowLayout());

    Screen screen = new Screen();
    screen.setPreferredSize(new Dimension(600, 50));
    screen.setBackground(new Color(200, 200, 200));
    screen.setLayout(new FlowLayout(FlowLayout.LEFT));
    frame.add(screen);

    Keyboard keyboard = new Keyboard();
    keyboard.setPreferredSize(new Dimension(600, 350));
    frame.add(keyboard);

    frame.setVisible(true);
  }
}
