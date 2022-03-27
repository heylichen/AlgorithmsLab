package topdown;

import network.FileHelper;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class LatexLineFormatter {
    private boolean isComment = true;
    public static final String COMMENT_PREFIX = " * ";

    @Test
    public void name() {
        List<String> lines = FileHelper.readLines("lines.txt");
        List<String> list = new ArrayList<>();
        for (String line : lines) {
            line = StringUtils.trimLeadingWhitespace(line);
            line = StringUtils.trimLeadingCharacter(line, '*');
            list.add(line);
        }
        //95
        transform(list, 85);
    }

    private void transform(List<String> list, int lineLength) {
        StringBuilder line = new StringBuilder();
        for (String s : list) {
            line.append(s).append(" ");
        }
        line.deleteCharAt(line.length() - 1);

        String[] words = line.toString().split(" ");
        StringBuilder sb = new StringBuilder();
        sb.append(COMMENT_PREFIX);

        int lineChars = 0;
        for (String word : words) {
            if (word == null || word.length() == 0) {
                continue;
            }
            if (lineChars >= lineLength) {
                sb.append("\n").append(COMMENT_PREFIX);
                lineChars = 0;
            }
            sb.append(word).append(" ");
            lineChars += word.length() + 1;
        }
        System.out.println(sb);
    }



}
