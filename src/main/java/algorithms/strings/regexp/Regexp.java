package algorithms.strings.regexp;

import java.util.BitSet;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import algorithms.graph.Digraph;
import algorithms.graph.DigraphImpl;

public class Regexp {

  private String pattern;
  private Digraph digraph;
  private static final char LEFT_PARENTHESIS = '(';
  private static final char RIGHT_PARENTHESIS = ')';
  private static final char OR = '|';
  private static final char CLOSURE = '*';
  private static final char DOT = '.';
  private Integer ACCEPT_POSITION;

  public void compile(String pattern) {
    pattern = pattern.replace(" ", "");
    if (pattern.charAt(0) == CLOSURE) {
      throw new IllegalArgumentException("first char must not be *");
    }
    this.pattern = pattern;
    digraph = new DigraphImpl();
    digraph.init(pattern.length() + 1);
    ACCEPT_POSITION = pattern.length();

    Deque<Integer> stack = new LinkedList<>();
    int i = 0;
    int lastLeftParenthesis = -1;
    while (i < pattern.length()) {
      char ch = pattern.charAt(i);
      switch (ch) {
        case LEFT_PARENTHESIS:
          //red edge, empty transition
          digraph.addEdge(i, i + 1);
          stack.push(i);
          break;
        case RIGHT_PARENTHESIS:
          //red edge, empty transition
          digraph.addEdge(i, i + 1);
          int pop = stack.pop();
          lastLeftParenthesis = pop;
          if (pattern.charAt(pop) == OR) {
            //or transition
            digraph.addEdge(pop, i);
            lastLeftParenthesis = stack.pop();
          }
          break;
        case OR:
          //or transition
          int leftParenthesis = stack.peek();
          digraph.addEdge(leftParenthesis, i + 1);
          stack.push(i);
          break;
        case CLOSURE:
          //red edge, empty transition
          digraph.addEdge(i, i + 1);
          int previousPosition = i - 1;
          char previousChar = pattern.charAt(previousPosition);
          int from = previousChar == RIGHT_PARENTHESIS ? lastLeftParenthesis : previousPosition;
          digraph.addEdge(from, i);
          digraph.addEdge(i, from);
          break;
        default:
          break;
      }
      i++;
    }
  }

  public boolean recognizes(String text) {
    int i = 0;
    int textLength = text.length();
    Set<Integer> currentPositions = new HashSet<>();
    currentPositions = findEmptyTransitions(0, currentPositions);

    while (i < textLength && !currentPositions.isEmpty()) {
      char textChar = text.charAt(i++);
      Set<Integer> reachableStatesAfterMatched = findReachableStatesIfMatched(textChar, currentPositions);
      currentPositions = findEmptyTransitions(reachableStatesAfterMatched);
    }
    return i == textLength && currentPositions.contains(ACCEPT_POSITION);
  }

  private Set<Integer> findReachableStatesIfMatched(char textChar, Set<Integer> currentPositions) {
    Set<Integer> matchedStates = new HashSet<>();
    for (Integer patternPosition : currentPositions) {
      if (patternPosition >= ACCEPT_POSITION) {
        continue;
      }
      char patternChar = pattern.charAt(patternPosition);
      if (textChar == patternChar || patternChar == DOT) {
        matchedStates.add(patternPosition + 1);
      }
    }
    return matchedStates;
  }

  private Set<Integer> findEmptyTransitions(Set<Integer> fromPositions) {
    if (fromPositions.isEmpty()) {
      return Collections.emptySet();
    }
    Set<Integer> reachablePositions = new HashSet<>();
    for (Integer patternPosition : fromPositions) {
      reachablePositions = findEmptyTransitions(patternPosition, reachablePositions);
    }
    return reachablePositions;
  }

  private Set<Integer> findEmptyTransitions(Integer fromPosition, Set<Integer> reachablePositions) {
    reachablePositions.add(fromPosition);
    BitSet marked = new BitSet(this.digraph.verticesCount());
    marked.set(fromPosition);

    visit(this.digraph, fromPosition, marked, reachablePositions);
    return reachablePositions;
  }

  private void visit(Digraph digraph, Integer vertex, BitSet marked, Set<Integer> queue) {
    for (Integer v : this.digraph.adjacentVertices(vertex)) {
      if (marked.get(v)) {
        continue;
      }
      marked.set(v);
      queue.add(v);
      visit(digraph, v, marked, queue);
    }
  }
}
