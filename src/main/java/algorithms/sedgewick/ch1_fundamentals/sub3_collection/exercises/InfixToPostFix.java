package algorithms.sedgewick.ch1_fundamentals.sub3_collection.exercises;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class InfixToPostFix {


  public static String eval(String expression) {
    if (expression == null || expression.length() == 0) {
      throw new IllegalArgumentException("Illegal expression, empty!");
    }
    Stack<String> ops = new Stack<>();
    Stack<Integer> vals = new Stack<>();
    Stack<String> expressionStack = new Stack<>();
    for (int i = 0; i < expression.length(); i++) {
      char ch = expression.charAt(i);
      if (ch >= '0' && ch <= '9') {
        StringBuilder sb = new StringBuilder();
        while (ch >= '0' && ch <= '9') {
          sb.append(ch);
          ch = expression.charAt(++i);
        }
        i--;
        expressionStack.push(sb.toString());
      } else if (ch == 's') {
        if (expression.substring(i).startsWith("sqrt")) {
          ops.push("sqrt");
          i += "sqrt".length() - 1;
        }
      } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
        ops.push(String.valueOf(ch));
      } else if (ch == ')') {
        String op = ops.pop();
        String v = expressionStack.pop();
        if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) {
          v = expressionStack.pop() + " " + v + " " + op;
        } else if (op.equals("sqrt")) {
          v = v + " " + op;
        }
        expressionStack.push(v);
      }
    }
    return expressionStack.pop();
  }

  public static void main(String[] args) {
    String s = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
    StdOut.println("before: " + s + "\n after: " + eval(s));

  }
}