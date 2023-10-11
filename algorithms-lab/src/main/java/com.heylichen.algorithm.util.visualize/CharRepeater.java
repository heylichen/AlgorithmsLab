package com.heylichen.algorithm.util.visualize;

public class CharRepeater {
    private final char character;
    private int cacheChars = 200;
    private String cacheLine;

    public CharRepeater(char character) {
        this.character = character;
        init();
    }

    private void init() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cacheChars; i++) {
            sb.append(character);
        }
        cacheLine = sb.toString();
    }

    public String repeat(int n) {
        if (n < cacheChars) {
            return cacheLine.substring(0, n);
        }
        int times = n / cacheChars;
        int left = n - times * cacheChars;
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < times; i++) {
            sb.append(cacheLine);
        }
        if (left > 0) {
            sb.append(cacheLine.substring(0, left));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        CharRepeater cr = new CharRepeater(' ');
        System.out.println(cr.repeat(10));
    }
}