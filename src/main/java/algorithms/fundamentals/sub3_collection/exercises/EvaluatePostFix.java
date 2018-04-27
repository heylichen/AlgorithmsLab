package algorithms.fundamentals.sub3_collection.exercises;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class EvaluatePostFix {


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
        Integer v = vals.pop();
        vals.push((int) Math.sqrt(v));
      } else if (ch == '+') {
        Integer v = vals.pop();
        v = vals.pop() + v;
        vals.push(v);
      } else if (ch == '-') {
        Integer v = vals.pop();
        v = vals.pop() - v;
        vals.push(v);
      } else if (ch == '*') {
        Integer v = vals.pop();
        v = vals.pop() * v;
        vals.push(v);
      } else if (ch == '/') {
        Integer v = vals.pop();
        v = vals.pop() / v;
        vals.push(v);
      }
    }
    return vals.pop();
  }

  public static void main(String[] args) {
    String ex = "1 2 + 3 4 - 5 6 - * *";
    StdOut.println(eval(ex));
  }
}