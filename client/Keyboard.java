package client;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;

public class Keyboard extends JPanel {
  public Keyboard() {
    GridLayout layout = new GridLayout(5, 6);
    layout.setHgap(2);
    layout.setVgap(2);
    this.setLayout(layout);

    this.buttonsInit();
  }

  public void buttonsInit() {
    JButton pi = new JButton("Pi");
    JButton e = new JButton("e");
    JButton parenthesisLeft = new JButton("(");
    JButton parenthesisRight = new JButton(")");
    JButton percent = new JButton("%");
    JButton AC = new JButton("AC");
    JButton sin = new JButton("sin");
    JButton ln = new JButton("ln");
    JButton seven = new JButton("7");
    JButton eight = new JButton("8");
    JButton nine = new JButton("9");
    JButton divide = new JButton("/");
    JButton cos = new JButton("cos");
    JButton log = new JButton("log");
    JButton four = new JButton("4");
    JButton five = new JButton("5");
    JButton six = new JButton("6");
    JButton multiply = new JButton("x");
    JButton tan = new JButton("tan");
    JButton sqrt = new JButton("sqrt");
    JButton one = new JButton("1");
    JButton two = new JButton("2");
    JButton three = new JButton("3");
    JButton subtract = new JButton("-");
    JButton exp = new JButton("EXP");
    JButton power = new JButton("x^y");
    JButton zero = new JButton("0");
    JButton point = new JButton(".");
    JButton submit = new JButton("=");
    JButton sum = new JButton("+");

    JButton[] operations = { pi, e, parenthesisLeft, parenthesisRight, percent, AC, sin, ln, divide, cos, log, multiply,
        tan, sqrt, subtract, exp, power, sum };
    for (int i = 0; i < operations.length; i++) {
      operations[i].setBackground(new Color(218, 220, 224));
      operations[i].setFont(new Font("Calibri", Font.PLAIN, 20));
      operations[i].setBorder(BorderFactory.createEmptyBorder());
      operations[i].setFocusPainted(false);
      operations[i].setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
    }
    JButton[] nums = { point, zero, one, two, three, four, five, six, seven, eight, nine };
    for (int i = 0; i < nums.length; i++) {
      nums[i].setBackground(new Color(241, 243, 244));
      nums[i].setFont(new Font("Calibri", Font.PLAIN, 20));
      // nums[i].setBorder(BorderFactory.createEmptyBorder());
      nums[i].setFocusPainted(false);
      nums[i].setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
    }

    this.add(pi);
    this.add(e);
    this.add(parenthesisLeft);
    this.add(parenthesisRight);
    this.add(percent);
    this.add(AC);
    this.add(sin);
    this.add(ln);
    this.add(seven);
    this.add(eight);
    this.add(nine);
    this.add(divide);
    this.add(cos);
    this.add(log);
    this.add(four);
    this.add(five);
    this.add(six);
    this.add(multiply);
    this.add(tan);
    this.add(sqrt);
    this.add(one);
    this.add(two);
    this.add(three);
    this.add(subtract);
    this.add(exp);
    this.add(power);
    this.add(zero);
    this.add(point);

    submit.setFont(new Font("Calibri", Font.PLAIN, 20));
    submit.setBackground(new Color(66, 133, 244));
    submit.setForeground(Color.WHITE);
    submit.setFocusPainted(false);
    submit.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 2, true));
    this.add(submit);

    this.add(sum);
  }
}
