package client;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import services.Evaluate;

import static client.KeyboardButtonType.*;
import static utils.NodeType.isDouble;

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

    this.setFocusable(true);
    this.toFront();
    this.setState(java.awt.Frame.NORMAL);
    this.requestFocus();
    this.setLayout(new BorderLayout());

    setFocusTraversalKeysEnabled(false);

    screen = new Screen();
    screen.setPreferredSize(new Dimension(600, 50));
    screen.setBackground(new Color(200, 200, 200));

    screen.setListener(new InputChangeListener() {

      @Override
      public void inputChangeEventOccurred(InputChangeEvent ev) {

        String currentText = screen.getText();
        KeyEvent e = ev.getEvent();
        char c = e.getKeyChar();

        if (Character.isDigit(c) || c == '.') {
          handleAddition(String.valueOf(c), OPERAND);
        } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) { // Backslash
          removeLast();
          // System.out.println("GTRTGREFTGRRTBBGHTMUHYTRTG");
          JTextField f = (JTextField) e.getSource();
          // f.setText("");
          e.consume();

        } else {
          screen.setText(currentText);
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
    if(isResult == false) isResult = true;
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
    // System.out.println(expression); // +++
    int length = expression.size();
    if (length == 0)
      return;
    String removed = expression.remove(length - 1);
    System.out.println(expression);
    String currentText = screen.getText();
    System.out.println(currentText); // ---
    // System.out.println(removed + " " + removed.length()); // +++
    // Making sure to delete whole element not just one char of it in case of
    // multichared element
    System.out.println("-->" + currentText.substring(0, currentText.length() - removed.length())); // ---
    // System.out.println(currentText.length() + " " + (currentText.length() - removed.length()));
    // System.out.println(currentText.substring(0, currentText.length() - removed.length()));
    screen.update(screen.getGraphics());
    screen.setText(currentText.substring(0, currentText.length() - removed.length()));
  }
}
