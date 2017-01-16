package algorithms.sedgewick.ch1_fundamentals.sub3_collection.exercises.linkedlist;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Evaluate {


  public static int eval(String expression) {
    if (expression == null || expression.length() == 0) {
      throw new IllegalArgumentException("Illegal expression, empty!");
    }
    Stack<String> ops = new Stack<>();
    Stack<Integer> vals = new Stack<>();
    for (int i = 0; i < expression.length(); i++) {
      char ch = expression.charAt(i);
      if (ch >= '0' && ch <= '9') {
        StringBuilder sb = new StringBuilder();
        while (ch >= '0' && ch <= '9') {
          sb.append(ch);
          ch = expression.charAt(++i);
        }
        i--;
        Integer value = Integer.valueOf(sb.toString());
        vals.push(value);
      } else if (ch == 's') {
        if (expression.substring(i).startsWith("sqrt")) {
          ops.push("sqrt");
          i += "sqrt".length() - 1;
        }
      } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
        ops.push(String.valueOf(ch));
      } else if (ch == ')') {
        String op = ops.pop();
        Integer v = vals.pop();
        if (op.equals("+")) {
          v = vals.pop() + v;
        } else if (op.equals("-")) {
          v = vals.pop() - v;
        } else if (op.equals("*")) {
          v = vals.pop() * v;
        } else if (op.equals("/")) {
          v = vals.pop() / v;
        } else if (op.equals("sqrt")) {
          v = (int) Math.sqrt(v);
        }
        vals.push(v);
      }
    }
    return vals.pop();
  }

  public static void main(String[] args) {
    String s = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
    ;
    StdOut.println(eval(s));

  }
}