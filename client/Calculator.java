package client;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import services.Evaluate;

import static client.KeyboardButtonType.*;
import static utils.NodeType.isDouble;

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
        String text = e.getText();

        if (type == EQUAL) {
          int length = expression.size();
          if (length == 0) {
            screen.setText("0");
          } else if (length > 1) {
            try {
              String result = String.valueOf(evaluate.evaluate(expression));
              expression.clear();
              expression.add(result);
              screen.setText(result);
            } catch (Exception ex) {
              System.out.println(ex.getMessage());
            }
          }
        } else if (type == CLEAR) {
          screen.setText("");
          expression.clear();
        } else {
          handleAddition(text, type);
        }
      }
    });

    this.add(keyboard, BorderLayout.CENTER);

    this.pack();

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

  private void handleAddition(String text, KeyboardButtonType type) {
    int length = expression.size();
    boolean added = false;
    if (length > 0 && (type == OPERAND || text.equals("."))) {
      String lastElement = expression.get(length - 1);
      if (isDouble(lastElement)) {
        if ((!lastElement.contains(".") && text.equals(".")) || type == OPERAND) {
          added = true;
          expression.set(length - 1, lastElement + text);
        }
      }
    }
    if (!added) {
      expression.add(text);
    }
    String currentText = screen.getText();
    screen.setText(currentText + text);
  }
}
