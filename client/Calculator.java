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

  public static boolean isResult = false;

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
          handleSubmit(evaluate);
        } else if (type == CLEAR) {
          handleClear();
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
    if(isResult) handleClear();
    isResult = false;
    int length = expression.size();
    boolean added = false;
    if(length == 0 && text.equals("-")){
      added = true;
      expression.add("0");
      expression.add("-");
    }
    if (length > 0) {
      String lastElement = expression.get(length - 1);
      if (isDouble(lastElement) && type == OPERAND || text.equals(".")) {
        // If the last inserted element is number and if it doesn't have decimal point
        // and the current text is decimal point or the current text is a number
        // then concatenate current text with the last one
        if ((!lastElement.contains(".") && text.equals(".")) || type == OPERAND) {
          added = true;
          expression.set(length - 1, lastElement + text);
        }
      }
    }
    if (!added) {
      int stringLength = text.length();

      // Separate braces from the operators
      if (stringLength > 1 && text.charAt(stringLength - 1) == '(') {
        if (stringLength > 2 && text.charAt(stringLength - 2) == '^') {

          // case of e^(
          expression.add(text.substring(0, stringLength - 2));
          expression.add("^");
        } else {
          // case of sin(, cos(, etc.
          expression.add(text.substring(0, stringLength - 1));
        }
        expression.add("(");

        added = true;
      } else {
        expression.add(text);
      }
    }
    String currentText = screen.getText();
    screen.setText(currentText + text);
  }

  private void handleSubmit(Evaluate evaluate) {
    isResult = true;
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
  }

  private void handleClear() {
    screen.setText("");
    expression.clear();
  }
}
