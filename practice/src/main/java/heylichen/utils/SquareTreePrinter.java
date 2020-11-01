package heylichen.utils;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class SquareTreePrinter<T> {

    private Function<T, String> labelGetter;
    private Function<T, T> leftChildGetter;
    private Function<T, T> rightChildGetter;
    private static CharRepeater DASH_REPEATER = new CharRepeater('─');
    private static CharRepeater SPACE_REPEATER = new CharRepeater(' ');
    private int hspace = 1;

    public SquareTreePrinter(Function<T, String> labelGetter, Function<T, T> leftChildGetter, Function<T, T> rightChildGetter) {
        this.labelGetter = labelGetter;
        this.leftChildGetter = leftChildGetter;
        this.rightChildGetter = rightChildGetter;
    }

    public SquareTreePrinter(Function<T, String> labelGetter, Function<T, T> leftChildGetter, Function<T, T> rightChildGetter, int hspace) {
        this.labelGetter = labelGetter;
        this.leftChildGetter = leftChildGetter;
        this.rightChildGetter = rightChildGetter;
        this.hspace = hspace;
    }

    public String print(T treeNode) {
        List<TreeLine> treeLines = buildLines(treeNode);
        return printTreeLines(treeLines);
    }

    private String printTreeLines(List<TreeLine> treeLines) {
        if (treeLines.isEmpty()) {
            return null;
        }

        int minLeftOffset = Integer.MAX_VALUE;
        int maxRightOffset = -1;
        for (TreeLine treeLine : treeLines) {
            if (treeLine.getLeftOffset() < minLeftOffset) {
                minLeftOffset = treeLine.getLeftOffset();
            }
            if (treeLine.getRightOffset() > maxRightOffset) {
                maxRightOffset = treeLine.getRightOffset();
            }
        }
        //trim left and right margins
        StringBuilder sb = new StringBuilder();
        StringBuilder lineSb = new StringBuilder();
        for (TreeLine treeLine : treeLines) {
            lineSb.append(SPACE_REPEATER.repeat(treeLine.getLeftOffset() - minLeftOffset))
                    .append(treeLine.getText())
                    .append(SPACE_REPEATER.repeat(maxRightOffset - treeLine.getRightOffset()));
            sb.append(lineSb.toString());
            sb.append('\n');
            lineSb.delete(0, lineSb.length());
        }
        return sb.toString();
    }

    private List<TreeLine> buildLines(T treeNode) {
        if (treeNode == null) {
            return Collections.emptyList();
        }
        String rootText = labelGetter.apply(treeNode);
        TreeLine rootLine = new TreeLine(rootText, -(rootText.length() - 1) / 2, rootText.length() / 2);
        List<TreeLine> lines = new LinkedList<>();
        lines.add(rootLine);

        //link between levels
        List<TreeLine> leftTreeLines = buildLines(leftChildGetter.apply(treeNode));
        List<TreeLine> rightTreeLines = buildLines(rightChildGetter.apply(treeNode));
        boolean leftEmpty = leftTreeLines.isEmpty();
        boolean rightEmpty = rightTreeLines.isEmpty();
        if (leftEmpty && rightEmpty) {
            return lines;
        }

        int minLineCount = Math.min(leftTreeLines.size(), rightTreeLines.size());
        int maxLineCount = Math.max(leftTreeLines.size(), rightTreeLines.size());
        int maxRootSpace = 0;
        for (int i = 0, tmp; i < minLineCount; i++) {
            tmp = leftTreeLines.get(i).getRightOffset() - rightTreeLines.get(i).getLeftOffset();
            if (tmp > maxRootSpace) {
                maxRootSpace = tmp;
            }
        }
        maxRootSpace += hspace;
        if (maxRootSpace % 2 == 0) {
            //make it odd
            maxRootSpace++;
        }

        int leftAdjusted = 0;
        int rightAdjusted = 0;
        if (leftEmpty) {
            rightAdjusted = 1;
            lines.add(new TreeLine("└┐", 0, rightAdjusted));
        } else if (rightEmpty) {
            leftAdjusted = -1;
            lines.add(new TreeLine("┌┘", leftAdjusted, 0));
        } else {
            int dashCount = (maxRootSpace) / 2;
            String shoulder = DASH_REPEATER.repeat(dashCount);
            String linkText = "┌" + shoulder + "┴" + shoulder + "┐";
            rightAdjusted = dashCount + 1;
            leftAdjusted = -rightAdjusted;
            lines.add(new TreeLine(linkText, leftAdjusted, rightAdjusted));
        }

        //left and right subtree lines
        for (int i = 0; i < maxLineCount; i++) {
            if (i >= leftTreeLines.size()) {
                TreeLine r = rightTreeLines.get(i);
                r.addOffsets(rightAdjusted);
                lines.add(r);
            } else if (i >= rightTreeLines.size()) {
                TreeLine l = leftTreeLines.get(i);
                l.addOffsets(leftAdjusted);
                lines.add(l);
            } else {
                TreeLine l = leftTreeLines.get(i);
                TreeLine r = rightTreeLines.get(i);
                String text = l.getText() + SPACE_REPEATER.repeat(maxRootSpace - l.getRightOffset() + r.getLeftOffset()) + r.getText();
                int lo = l.getLeftOffset() + leftAdjusted;
                int ro = r.getRightOffset() + rightAdjusted;
                lines.add(new TreeLine(text, lo, ro));
            }
        }
        return lines;
    }

}
