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

        for (String s : expression) {
            if (NodeType.isSpecialValue(s)) {
                values.add(getValueOfSpeialValue(s));
            }
            if (NodeType.isDouble(s)) {
                values.add(Double.parseDouble(s));
            } else if (s.equals("(")) {
                operators.push(LB);
            } else if (s.equals(")")) {
                if (operators.isEmpty()) {
                    throw new InvalidExpression("Braces don't match");
                }
                while (!operators.isEmpty() && operators.peek() != LB) {
                    try {
                        values.push(calc(operators.pop(), values));
                    } catch (Exception e) {
                    }
                }
                if (operators.isEmpty()) {
                    throw new InvalidExpression("Braces don't match");
                }

                // Remove '(' from the stack
                operators.pop();

            } else if (NodeType.isOperator(s)) {
                while (!operators.isEmpty() && getPriority(getType(s)) <= getPriority(operators.peek())) {
                    try {
                        values.push(calc(operators.pop(), values));
                    } catch (Exception e) {
                    }
                }
                operators.push(getType(s));
            }
        }

        while (!operators.isEmpty()) {
            NodeType operator = operators.pop();
            if (operator == LB) {
                throw new InvalidExpression("Bad Expression. Braces don't match");
            }
            values.push(calc(operator, values));
        }

        return values.pop();
    }

    private double calc(NodeType op, Stack<Double> values) throws InvalidExpression {
        int operandsCount = values.size();
        if (isBinaryOperator(op)) {
            if (operandsCount < 2) {
                throw new InvalidExpression("Invalid input");
            }
            return _calc(op, values.pop(), values.pop());
        } else if (isUnaryOperator(op)) {
            if (operandsCount < 1) {
                throw new InvalidExpression("Invalid input");
            }
            return _calc(op, values.pop());
        } else {
            throw new InvalidExpression("Invalid input");
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
                if (x == 0) {
                    throw new ArithmeticException("Zero division");
                }
                return y / x;
            case MOD:
                return y % x;
            case POW:
                return Math.pow(y, x);
            default:
                return 0;
        }

    }

    private double _calc(NodeType op, double x) {
        switch (op) {
            case SQRT:
                if (x < 0) {
                    throw new ArithmeticException("Cannot find square root of negative value");
                }
                return Math.sqrt(x);
            case LOG:
                if (x < 0) {
                    throw new ArithmeticException("Invalid input");
                }
                return Math.log10(x);
            case SIN:
                return Math.sin(x);
            case COS:
                return Math.cos(x);
            case TAN:
                return Math.tan(x);
            case LN:
                if (x < 0) {
                    throw new ArithmeticException("Invalid input");
                }
                return Math.log(x);
            default:
                return 0;
        }
    }
}