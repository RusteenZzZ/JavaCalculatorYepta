package client;

import javax.swing.JFrame;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import services.Evaluate;
import static client.KeyboardButtonType.*;

public class Calculator extends JFrame {
  public static final int WIDTH = 600;
  public static final int HEIGHT = 500;

  private ArrayList<String> expression;
  private Screen screen;

  public Calculator(Evaluate evaluate) {
    this.expression = new ArrayList<String>();

    this.setTitle("Calculator");
    this.setSize(WIDTH, HEIGHT);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    this.setFocusable(true);
    this.toFront();
    this.setState(java.awt.Frame.NORMAL);
    this.requestFocus();
    this.setLayout(new BorderLayout());

    setFocusTraversalKeysEnabled(false);

    screen = new Screen();
    screen.setPreferredSize(new Dimension(600, 50));
    screen.setBackground(new Color(200, 200, 200));
    
    this.add(screen, BorderLayout.PAGE_START);

    Keyboard keyboard = new Keyboard();
    keyboard.setPreferredSize(new Dimension(600, 350));
    keyboard.setListener(new ButtonClickListener() {

      @Override
      public void ButtonClickEventOccurred(ButtonClickEvent e) {
        KeyboardButtonType type = e.getType();
        String label = e.getLabel();

        if (type == EQUAL) {
          int length = expression.size();
          if (length == 0) {
            screen.setText("0");
          } else if (length > 1) {
            try {
              screen.setText(String.valueOf(evaluate.evaluate(expression)));
            } catch (Exception ex) {
              System.out.println(ex.getMessage());
            }
          }
        } else if (type == CLEAR) {
          screen.setText("");
          expression.clear();
        } else {
          String currentText = screen.getText();
          expression.add(label);
          screen.setText(currentText + label);
        }
      }
    });

    this.add(keyboard, BorderLayout.CENTER);  

    this.pack();

    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
          case 8: // Backspace
            removeLast();
            break;
          default:
            break;
        }
      }
    });

    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        toFront();
        requestFocus();
      }
    });

    setLocationRelativeTo(null);
    setVisible(true);
    pack();
  }

  private void removeLast() {
    int length = expression.size();
    if (length == 0)
      return;

    String removed = expression.remove(length - 1);
    String currentText = screen.getText();
    // Making sure to delete whole element not just one char of it in case of
    // multichared element
    screen.setText(currentText.substring(0, currentText.length() - removed.length()));
  }
}
