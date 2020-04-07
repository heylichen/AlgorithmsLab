package heylichen.string.regexp;

import heylichen.alg.graph.structure.directed.Digraph;
import heylichen.alg.graph.structure.directed.SimpleDigraph;
import heylichen.alg.graph.tasks.path.DFSMultipleSourceReachability;
import heylichen.alg.graph.tasks.path.MultipleSourceReachability;

import java.util.*;

public class NFA {
    private final char[] patternChars;
    private final int states;

    private Digraph digraph;
    private NFACharacters characters;
    private static final int START_STATE = 0;
    private final List<Integer> startStates;


    public NFA(String pattern) {
        characters = new NFACharacters();
        patternChars = characters.filterValidChars(pattern.toCharArray());
        states = patternChars.length;
        digraph = new SimpleDigraph(states + 1);
        initEpsilonTransitions();

        startStates = new ArrayList<>();
        initStartStates();
    }

    private void initStartStates() {
        List<Integer> initStates = new LinkedList<>();
        initStates.add(START_STATE);
        startStates.addAll(epsilonReachableStates(initStates));
    }

    private void initEpsilonTransitions() {
        Deque<Integer> opStack = new ArrayDeque<>(states + 1);
        boolean isMatchableChar;
        for (int i = 0; i < patternChars.length; i++) {
            char ch = patternChars[i];
            isMatchableChar = characters.isMatchableChar(ch);
            if ((!isMatchableChar) && !characters.isOr(ch)) {
                digraph.addEdge(i, i + 1);
            }

            if (characters.isLeftParenthesis(ch) || characters.isOr(ch)) {
                // ( or |
                opStack.push(i);
                continue;
            }
            if (isMatchableChar && isNextCharAsterisk(i)) {
                // A*
                digraph.addEdge(i, i + 1);
                digraph.addEdge(i + 1, i);
                continue;
            }
            if (characters.isRightParenthesis(ch)) {
                processRightParenthesis(i, opStack);
            }
        }
    }

    public boolean recognize(String text) {
        List<Integer> currentStates = simulateNFA(text, startStates);
        return containsAcceptState(currentStates);
    }

    private List<Integer> simulateNFA(String text, List<Integer> states) {
        if (text == null || text.length() == 0) {
            return states;
        }
        List<Integer> currentStates = new LinkedList<>(states);
        for (int i = 0; i < text.length(); i++) {
            if (currentStates.isEmpty()) {
                return currentStates;
            }
            char textChar = text.charAt(i);
            currentStates = findMatchedTransitions(textChar, currentStates);
            currentStates = epsilonReachableStates(currentStates);
        }
        return currentStates;
    }

    private boolean containsAcceptState(List<Integer> currentStates) {
        if (currentStates.isEmpty()) {
            return false;
        }
        for (Integer currentState : currentStates) {
            if (isAcceptState(currentState)) {
                return true;
            }
        }
        return false;
    }

    private List<Integer> findMatchedTransitions(char textChar, List<Integer> currentStates) {
        List<Integer> matchedStates = new LinkedList<>();
        for (Integer currentState : currentStates) {
            if (currentState >= patternChars.length) {
                continue;
            }
            char patternChar = patternChars[currentState];
            if (patternChar == textChar) {
                matchedStates.add(currentState + 1);
            }
        }
        return matchedStates;
    }

    private List<Integer> epsilonReachableStates(List<Integer> states) {
        MultipleSourceReachability multipleSourceReachability = new DFSMultipleSourceReachability(digraph, states);
        Collection<Integer> reachableVertices = multipleSourceReachability.getReachableVertices();
        if (reachableVertices instanceof List) {
            return (List<Integer>) reachableVertices;
        }
        List<Integer> reachableStates = new LinkedList<>();
        for (Integer reachableVertex : multipleSourceReachability.getReachableVertices()) {
            reachableStates.add(reachableVertex);
        }
        return reachableStates;
    }

    private boolean isAcceptState(int i) {
        return i == states;
    }

    private void processRightParenthesis(int i, Deque<Integer> opStack) {
        int pop1 = opStack.pop();
        char pop1Char = patternChars[pop1];
        int leftParenthesis;
        if (characters.isOr(pop1Char)) {
            //( | )
            int pop2 = opStack.pop();
            assertLeftParenthesis(patternChars[pop2]);

            leftParenthesis = pop2;
            digraph.addEdge(leftParenthesis, pop1 + 1);
            digraph.addEdge(pop1, i);
        } else {
            //( )
            assertLeftParenthesis(pop1Char);
            leftParenthesis = pop1;
        }
        // () *
        if (isNextCharAsterisk(i)) {
            digraph.addEdge(leftParenthesis, i + 1);
            digraph.addEdge(i + 1, leftParenthesis);
        }
    }

    private void assertLeftParenthesis(char ch) {
        if (!characters.isLeftParenthesis(ch)) {
            throw new IllegalStateException("expected left parenthesis (, but got " + ch);
        }
    }

    private boolean isNextCharAsterisk(int i) {
        int nextI = i + 1;
        return nextI < patternChars.length && characters.isAsterisk(patternChars[nextI]);
    }


    public static void main(String[] args) {
//        String a = " ( ( A * B | A C ) D )";
//        NFACharacters nfaCharacters = new NFACharacters();
//        char[] validChars = nfaCharacters.filterValidChars(a.toCharArray());
//        System.out.println(validChars);
        String pat = "( . * A B ( ( C | D * E ) F ) * G )";
        NFA nfa = new NFA(pat);
        System.out.println(nfa.recognize("ABCFGF"));
    }

}
