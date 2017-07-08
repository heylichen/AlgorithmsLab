package algorithms.sedgewick.ch1_fundamentals.sub3_collection.exercises;

import edu.princeton.cs.algs4.ResizingArrayStack;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chen Li on 2017/4/23.
 */
public class ParenthesesCheck {

  private static final Map<String, String> RIGHT_AND_LEFT_PARENTHESES_MAP;

  static {
    RIGHT_AND_LEFT_PARENTHESES_MAP = new HashMap<>();
    RIGHT_AND_LEFT_PARENTHESES_MAP.put(")", "(");
    RIGHT_AND_LEFT_PARENTHESES_MAP.put("]", "[");
    RIGHT_AND_LEFT_PARENTHESES_MAP.put("}", "{");
  }

  /**
   * 1.3.4 Write a stack client Parentheses that reads in a text stream from standard input
   * and uses a stack to determine whether its parentheses are properly balanced. For example,
   * your program should print true for [()]{}{[()()]()} and false for [(]).
   * <p>
   * returns Pair, left: true,valid, false, invalid,
   * right: error msg.
   */
  public static Pair<Boolean, String> checkParentheses(String input) {
    ResizingArrayStack<String> parenthesesStack = new ResizingArrayStack<>();
    for (int i = 0; i < input.length(); i++) {
      String character = input.substring(i, i + 1);
      if (isLeft(character)) {
        parenthesesStack.push(character);
      }
      if (isRight(character)) {
        String gotLeft = parenthesesStack.isEmpty() ? null : parenthesesStack.pop();
        String expectedLeft = RIGHT_AND_LEFT_PARENTHESES_MAP.get(character);
        if (!hasEncounteredLeftParenthesis(expectedLeft, gotLeft)) {
          return Pair.of(false, "expected left parenthesis " + expectedLeft + " not found!");
        }
      }
    }
    if (parenthesesStack.isEmpty()) {
      return Pair.of(true, null);
    } else{
      return Pair.of(false, "more left parentheses than right parentheses!");
    }
  }

  private static boolean hasEncounteredLeftParenthesis(String expectedLeft, String existedCh){
    if (expectedLeft == null) {
      return false;
    }
    return expectedLeft.equals(existedCh);
  }

  private static boolean isLeft(String character) {
    return RIGHT_AND_LEFT_PARENTHESES_MAP.values().contains(character);
  }

  private static boolean isRight(String character) {
    return RIGHT_AND_LEFT_PARENTHESES_MAP.keySet().contains(character);
  }

  public static void main(String[] args) {
    String valid = "[()]{}{[()()]()}";
    Pair<Boolean, String> validAndError = checkParentheses(valid);
    if (!validAndError.getLeft()) {
      System.err.println(valid + " is valid, but checked result is invalid.");
    }

    String invalid = "[(])";
    Pair<Boolean, String> invalidAndError = checkParentheses(invalid);
    if (invalidAndError.getLeft()) {
      System.err.println(invalid + " is invalid, but checked result is valid.");
    }
  }
}
