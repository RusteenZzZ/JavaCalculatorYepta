package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import utils.NodeType;
import utils.InvalidExpression;

import static utils.Operator.*;
import static utils.NodeType.*;

public class EvaluateImpl implements Evaluate {
    public double evaluate(ArrayList<String> expression) throws InvalidExpression {
        Stack<Double> values = new Stack<Double>();
        Stack<NodeType> operators = new Stack<NodeType>();

        expression.forEach(s -> {
            System.out.println(s);
            System.out.println(values);
            if (NodeType.isDouble(s) || NodeType.isSpecialValue(s)) {
                values.add(Double.parseDouble(s));
            } else if (s.equals("(")) {
                operators.push(LB);
            } else if (s.equals(")")) {
                while (!operators.isEmpty() && operators.peek() != LB) {
                    values.push(calc(operators.pop(), values));
                }

                // Remove '(' from the stack
                operators.pop();

            } else if (NodeType.isOperator(s)) {
                while (!operators.isEmpty() && getPriority(getType(s)) <= getPriority(operators.peek())) {
                    values.push(calc(operators.pop(), values));
                }
                operators.push(getType(s));
            }
        });
        while (!operators.isEmpty()) {
            values.push(calc(operators.pop(), values));
        }

        return values.pop();
    }

    private double calc(NodeType op, Stack<Double> values) {
        if (isBinaryOperator(op)) {
            return _calc(op, values.pop(), values.pop());
        } else {
            return _calc(op, values.pop());
        }
    }

    private double _calc(NodeType op, double x, double y) {
        switch (op) {
            case ADD:
                return y + x;
            case SUB:
                return y - x;
            case MUL:
                return y * x;
            case DIV:
                return y / x;
            case MOD:
                return (int) y % (int) x;
            case POW:
                return Math.pow(y, x);
            default:
                return 0;
        }

    }

    private double _calc(NodeType op, double x) {
        return 0;
    }

    public static void main(String args[]) {
        ArrayList<String> list = new ArrayList<String>(
                Arrays.asList("(", "20", "*", "12", ")", "+", "4", "/", "2", "-", "7", "^", "9"));
        EvaluateImpl ev = new EvaluateImpl();
        try {
            System.out.println(ev.evaluate(list));
        } catch (Exception e) {

        }
    }
}