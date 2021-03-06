package utils;

import java.util.ArrayList;
import java.util.Arrays;

import static utils.NodeType.*;

abstract class Operator {
    static final ArrayList<NodeType> unaryOperators = new ArrayList<>(Arrays.asList(ADD, SUB, DIV, MOD, MUL, POW));
    static final ArrayList<NodeType> binaryOperators = new ArrayList<>(Arrays.asList(SIN, COS, TAN, CTG, LOG));

    static boolean isUnaryOperator(NodeType nt) {
        return Operator.unaryOperators.contains(nt);
    }

    static boolean isBinaryOperator(NodeType nt) {
        return Operator.binaryOperators.contains(nt);
    }

    /**
     * 
     * @param s
     * @return priority of operator
     */
    public static int getPriority(String s) {
        if (s.equals("-") || s.equals("+"))
            return 1;
        else if (s.equals("*") || s.equals("/"))
            return 2;
        else if (s.equals("^"))
            return 3;
        return 0;
    }
}