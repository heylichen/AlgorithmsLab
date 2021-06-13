package heylichen.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SlashTreePrinter<T> {
    private Function<T, String> labelGetter;
    private Function<T, T> leftChildGetter;
    private Function<T, T> rightChildGetter;
    public static final char SPACE = ' ';
    public static final String SPACE_STRING = " ";

    public SlashTreePrinter(Function<T, String> labelGetter, Function<T, T> leftChildGetter, Function<T, T> rightChildGetter) {
        this.labelGetter = labelGetter;
        this.leftChildGetter = leftChildGetter;
        this.rightChildGetter = rightChildGetter;
    }

    public void print(T root) {
        if (root == null) {
            return;
        }
        int height = calculateHeight(root);
        int maxWidth = calculateMaxWidth(height);
        List<StringBuilder> levelLines = createFullLines(height, maxWidth);
        generate(root, levelLines, 0, 0, maxWidth);
        for (StringBuilder levelLine : levelLines) {
            System.out.println(levelLine.toString());
        }
    }

    private List<StringBuilder> createFullLines(int height, int width) {
        List<StringBuilder> lines = new ArrayList<>();
        int linesCount = height * 2 - 1;
        StringBuilder sb = new StringBuilder();
        addNSpaces(sb, width);
        String line = sb.toString();

        for (int i = 0; i < linesCount; i++) {
            StringBuilder sb1 = new StringBuilder(line);
            lines.add(sb1);
        }
        return lines;
    }


    private void generate(T node, List<StringBuilder> levelLines, int level, int leftPadding, int width) {
        int textLevel = level * 2;
        StringBuilder textLine = levelLines.get(textLevel);
        setLine(textLine, leftPadding + width / 2, labelGetter.apply(node));

        T left = leftChildGetter.apply(node);
        T right = rightChildGetter.apply(node);

        int separatorLevel = textLevel + 1;

        int quarterWidth = width / 4;
        if (left != null) {
            StringBuilder separatorLine = levelLines.get(separatorLevel);
            separatorLine.setCharAt(leftPadding + quarterWidth, '/');
            generate(left, levelLines, level + 1, leftPadding, width / 2);
        }
        if (right != null) {
            StringBuilder separatorLine = levelLines.get(separatorLevel);
            separatorLine.setCharAt(leftPadding + width - 1 - quarterWidth, '\\');
            generate(right, levelLines, level + 1, leftPadding + width / 2 + 1, width / 2);
        }
    }

    private void setLine(StringBuilder line, int padding, String label) {
        for (int i = 0; i < label.length(); i++) {
            line.setCharAt(padding + i, label.charAt(i));
        }
    }

    private void addNSpaces(StringBuilder line, int n) {
        for (int i = 0; i < n; i++) {
            line.append(SPACE);
        }
    }

    private int calculateMaxWidth(int levels) {
        int width = 0;
        for (int i = 0; i < levels; i++) {
            width = width * 2 + 1;
        }
        return width;
    }

    private int calculateHeight(T root) {
        if (root == null) {
            return 0;
        }
        T left = leftChildGetter.apply(root);
        T right = rightChildGetter.apply(root);
        if (left == null && right == null) {
            return 1;
        }
        int leftHeight = calculateHeight(left);
        int rightHeight = calculateHeight(right);
        if (leftHeight >= rightHeight) {
            return leftHeight + 1;
        } else {
            return rightHeight + 1;
        }
    }
}
