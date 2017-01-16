package algorithms.sedgewick.ch1_fundamentals.sub3_collection.impl;

import edu.princeton.cs.algs4.ResizingArrayStack;
import edu.princeton.cs.algs4.StdOut;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Chen Li on 2017/1/10.
 */
public class ExpressionEval {


  public BigDecimal eval(String expression) {

    if (expression == null || expression.length() == 0) {
      throw new IllegalArgumentException("expression is Empty!");
    }
    ResizingArrayStack<String> operators = new ResizingArrayStack<>();
    ResizingArrayStack<BigDecimal> operands = new ResizingArrayStack<>();

    for (int i = 0; i < expression.length(); ) {
      Character ch = expression.charAt(i);

      if (ch == ')') {
        i++;
        if (operators.isEmpty()) {
          throw new IllegalStateException("invalid expression " + expression);
        }
        String operator = operators.pop();

        BigDecimal a;
        BigDecimal b;

        switch (operator) {
          case "+":
            a = operands.pop();
            b = operands.pop();
            operands.push(a.add(b));
            break;
          case "-":
            b = operands.pop();
            a = operands.pop();
            operands.push(a.subtract(b));
            break;
          case "*":
            a = operands.pop();
            b = operands.pop();
            operands.push(a.multiply(b));
            break;
          case "/":
            b = operands.pop();
            a = operands.pop();
            operands.push(a.divide(b, 4, RoundingMode.HALF_UP));
            break;
          case "sqrt":
            a = operands.pop();
            operands.push(BigDecimal.valueOf(Math.sqrt(a.doubleValue())));

            break;
          default:
            throw new IllegalStateException(" operator not supported " + operator);
        }
        continue;
      } else if (ch >= '0' && ch <= '9') {
        StringBuilder sb = new StringBuilder();
        do {
          sb.append(ch);
          i++;
          ch = expression.charAt(i);
        } while (ch >= '0' && ch <= '9');

        BigDecimal value = new BigDecimal(sb.toString());
        operands.push(value);
      } else if (expression.substring(i).startsWith("sqrt")) {
        operators.push("sqrt");
        i += "sqrt".length();
      } else if ("+-*/".indexOf(ch) != -1) {
        operators.push(String.valueOf(ch));
        i++;
      } else {
        i++;
      }
    }
    return operands.pop();
  }

  public static void main(String[] args) {

    List<String> expressionList = Arrays.asList(" ( 1+(2-(3*(4/sqrt(16)))) )  ", "sqrt((1*(1923/sqrt(2312 Invalid_Chars ))))");
    for (String s : expressionList) {
      testEval(s);
    }
  }

  private static void testEval(String ex) {
    ExpressionEval ee = new ExpressionEval();
    BigDecimal val = ee.eval(ex);
    StdOut.printf("%s = %.4f\n", ex.trim(), val.doubleValue());
  }

}
