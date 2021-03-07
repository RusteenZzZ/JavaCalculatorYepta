package client;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import services.Evaluate;

import static client.KeyboardButtonType.*;
import static utils.NodeType.isDouble;
import static utils.NodeType.isOperator;;;

public class Calculator extends JFrame {
  public static final int WIDTH = 600;
  public static final int HEIGHT = 500;

  private ArrayList<String> expression;
  private Screen screen;

  public boolean isResult = false;

  public Calculator(Evaluate evaluate) {
    this.expression = new ArrayList<String>();

    this.setTitle("Calculator");
    this.setSize(WIDTH, HEIGHT);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    this.setLayout(new BorderLayout());

    screen = new Screen();
    screen.setPreferredSize(new Dimension(600, 50));
    screen.setBackground(new Color(200, 200, 200));

    screen.setListener(new InputChangeListener() {

      @Override
      public void inputChangeEventOccurred(InputChangeEvent ev) {
        KeyEvent e = ev.getEvent();
        char c = e.getKeyChar();
        String s = String.valueOf(c);

        if (Character.isDigit(c) || c == '.') {
          handleAddition(s, OPERAND, false);
        } else if (isOperator(s)) {
          handleAddition(s, OPERATION, false);
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
          removeLast();
          e.consume();
        } else {
          e.consume();
        }
      }
    });

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
          handleAddition(text, type, true);
        }
      }
    });

    this.add(keyboard, BorderLayout.CENTER);

    this.pack();

    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void handleAddition(String text, KeyboardButtonType type, boolean isButton) {
    if (isResult)
      handleClear();
    isResult = false;
    int length = expression.size();
    boolean added = false;
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

    if (isButton) {
      screen.setText(currentText + text);
    }
  }

  private void handleSubmit(Evaluate evaluate) {
    if (isResult == false)
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
