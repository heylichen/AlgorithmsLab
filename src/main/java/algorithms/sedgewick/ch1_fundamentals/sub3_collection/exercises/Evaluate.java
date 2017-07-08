package algorithms.sedgewick.ch1_fundamentals.sub3_collection.exercises;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import org.apache.commons.lang3.tuple.Pair;

public class Evaluate {
  
  public static int eval(String expression) {
    if (expression == null || expression.isEmpty()) {
      throw new IllegalArgumentException("Illegal expression, empty!");
    }
    Stack<String> ops = new Stack<>();
    Stack<Integer> vals = new Stack<>();
    for (int i = 0; i < expression.length(); ) {
      char ch = expression.charAt(i);
      if (ch >= '0' && ch <= '9') {
        Pair<Integer, Integer> valueAndPosition = parseIntegerAndMovePosition(expression, i);
        i = valueAndPosition.getRight();
        vals.push(valueAndPosition.getLeft());
      } else if (ch == 's' && expression.substring(i).startsWith("sqrt")) {
        ops.push("sqrt");
        i += "sqrt".length();
      } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
        ops.push(String.valueOf(ch));
        i++;
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
        i++;
      } else {
        i++;
      }
    }
    return vals.pop();
  }

  private static Pair<Integer, Integer> parseIntegerAndMovePosition(String expression, int fromCharAt) {
    int position = fromCharAt;
    char ch = expression.charAt(position);
    StringBuilder sb = new StringBuilder();
    while (ch >= '0' && ch <= '9') {
      sb.append(ch);
      ch = expression.charAt(++position);
    }
    Integer value = Integer.valueOf(sb.toString());
    return Pair.of(value, position);
  }

  public static void main(String[] args) {
    String s = "1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )";
    StdOut.println(eval(s));
  }
}