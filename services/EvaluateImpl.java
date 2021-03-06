package services;

import java.util.ArrayList;
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
            if (NodeType.isSpecialValue(s)) {
                values.add(getValueOfSpeialValue(s));
            }
            if (NodeType.isDouble(s)) {
                values.add(Double.parseDouble(s));
            } else if (s.equals("(")) {
                System.out.println("open");
                operators.push(LB);
            } else if (s.equals(")")) {
                while (!operators.isEmpty() && operators.peek() != LB) {
                    System.out.println("loop");
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
            if (operators.peek() == LB) {
                throw new InvalidExpression("Rustam qehbe");
            }
            values.push(calc(operators.pop(), values));
        }

        return values.pop();
    }

    private double calc(NodeType op, Stack<Double> values) {
        System.out.println(op);
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
        switch (op) {
            case SQRT:
                return Math.sqrt(x);
            case LOG:
                return Math.log10(x);
            case SIN:
                return Math.sin(x);
            case COS:
                return Math.cos(x);
            case TAN:
                return Math.tan(x);
            case LN:
                return Math.log(x);
            default:
                return 0;
        }
    }
}