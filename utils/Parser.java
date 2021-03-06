package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Parser {

    public Parser() {
        super();
    }

    public ArrayList<String> infixToPrefix(ArrayList<String> infix) {
        ArrayList<String> postfix = infixToPostfix(infix);
        Collections.reverse(postfix);
        return postfix;
    }

    /**
     * 
     * @param infix
     * @return postfix expression parsed from infix
     */
    public ArrayList<String> infixToPostfix(ArrayList<String> infix) {
        ArrayList<String> postfix = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        infix.forEach(s -> {
            if (NodeType.isDouble(s) || NodeType.isSpecialValue(s)) {
                postfix.add(s);
            } else if (s.equals("(")) {
                stack.push(s);
            } else if (s.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    postfix.add(stack.pop());
                }

                // Remove '(' from the stack
                stack.pop();
            } else if (NodeType.isOperator(s)) {
                while (!stack.isEmpty() && Operator.getPriority(s) <= Operator.getPriority(stack.peek())) {
                    postfix.add(stack.pop());
                }
                stack.push(s);
            }
        });

        // pop all the operators from the stack
        while (!stack.isEmpty()) {
            if (stack.peek().equals("(")) {
                System.out.println("Asdasdasdsa");
            }
            postfix.add(stack.pop());

        }

        return postfix;
    }
}