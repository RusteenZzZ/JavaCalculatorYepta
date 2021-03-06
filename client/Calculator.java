package client;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.util.ArrayList;

public class Calculator extends JFrame {
  public static final int WIDTH = 600;
  public static final int HEIGHT = 500;

  public Calculator(){
    ArrayList<String> expression = new ArrayList<String>();

    this.setTitle("Calculator");
    this.setSize(WIDTH, HEIGHT);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    this.setLayout(new FlowLayout());

    Screen screen = new Screen();
    screen.setPreferredSize(new Dimension(600, 50));
    screen.setBackground(new Color(200, 200, 200));
    screen.setLayout(new FlowLayout(FlowLayout.LEFT));
    this.add(screen);

    Keyboard keyboard = new Keyboard();
    keyboard.setPreferredSize(new Dimension(600, 350));
    keyboard.setListener(new ButtonClickListener() {

      @Override
      public void ButtonClickEventOccurred(ButtonClickEvent e) {
        KeyboardButtonType type = e.getType();
        String label = e.getLabel();
        String currentText = screen.getText();
        expression.add(label);
        screen.setText(currentText + label);
      }
    });

    this.add(keyboard);

    this.setVisible(true);
  }
}
