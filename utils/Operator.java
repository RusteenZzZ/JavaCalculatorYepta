package utils;

import java.util.ArrayList;
import java.util.Arrays;

import static utils.NodeType.*;

public class Operator {
    static final ArrayList<NodeType> binaryOperators = new ArrayList<>(Arrays.asList(ADD, SUB, DIV, MOD, MUL, POW));
    static final ArrayList<NodeType> unaryOperators = new ArrayList<>(Arrays.asList(SIN, COS, TAN, CTG, LOG, SQRT, LN));

    public static boolean isUnaryOperator(NodeType nt) {
        return Operator.unaryOperators.contains(nt);
    }

    public static boolean isBinaryOperator(NodeType nt) {
        return Operator.binaryOperators.contains(nt);
    }

    public static boolean isBinaryOperator(String s){
        return isBinaryOperator(getType(s));
    }

    /**
     * 
     * @param nt
     * @return priority of operator
     */
    public static int getPriority(NodeType nt) {
        if (nt == LB || nt == RB)
            return 0;
        if (nt == SUB || nt == ADD)
            return 1;
        else if (nt == MUL || nt == DIV)
            return 2;
        else if (nt == POW)
            return 3;
        return 4;
    }
}