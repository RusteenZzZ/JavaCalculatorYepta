package utils;

public enum NodeType {
    OPERAND, ADD, SUB, DIV, MOD, MUL, POW, SIN, COS, TAN, CTG, LOG, PI, E, LB, RB;

    /**
     * 
     * @param s
     * @return NodeType element from string
     */
    public static NodeType getType(String s) {
        switch (s) {
            case "+":
                return ADD;
            case "-":
                return SUB;
            case "/":
                return DIV;
            case "x":
                return MUL;
            case "^":
                return POW;
            case "%":
                return MOD;
            case "sin":
                return SIN;
            case "cos":
                return COS;
            case "tan":
                return TAN;
            case "e":
                return E;
            case "π":
                return PI;
            case "(":
                return LB;
            case ")":
                return RB;
            default:
                return OPERAND;
        }
    }

    /**
     * 
     * @param nt
     * @return boolean value representing if the nt is an operator
     */
    public static boolean isOperator(NodeType nt) {
        return nt != OPERAND && nt != E && nt != PI;
    }

    /**
     * 
     * @param s
     * @return boolean value representing if the s is an operator
     */
    public static boolean isOperator(String s) {
        return isOperator(getType(s));
    }

    /**
     * 
     * @param nt
     * @return boolean value representing if the nt is an operand
     */
    public static boolean isOperand(NodeType nt) {
        return nt == OPERAND;
    }

    /**
     * 
     * @param nt
     * @return boolean value representing if the nt is a special value
     */
    public static boolean isSpecialValue(NodeType nt) {
        return nt == E || nt == PI;
    }

    /**
     * 
     * @param s
     * @return boolean value representing if the s is a special value
     */
    public static boolean isSpecialValue(String s) {
        return isSpecialValue(getType(s));
    }

    public static boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
};
