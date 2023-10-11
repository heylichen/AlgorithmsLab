import com.google.common.base.Splitter;
import com.heylichen.commons.resource.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.List;
import java.util.regex.Pattern;

public class SimpleCal {
  //  public static final String
  public static final Splitter SPLITTER = Splitter.on("+-").trimResults();
  public static final Pattern pattern = Pattern.compile("[ ]");

  @Test
  public void testRun() {
    List<String> lines = FileUtils.readLines("input.txt");
    StringBuilder sb = new StringBuilder();
    int i = 1;
    for (String line : lines) {
      i = cal(line, sb, i);
    }
    System.out.println(sb);
  }

  private static int cal(String line, StringBuilder resultSb, int i) {
    try {
      if (StringUtils.isBlank(line)) {
        return i;
      }
      String[] ps = pattern.split(line);
      int result = Integer.parseInt(ps[0].trim());

      result = cal(result, Integer.parseInt(ps[2].trim()), ps[1].trim());
      if (ps.length > 3) {
        result = cal(result, Integer.parseInt(ps[4].trim()), ps[3].trim());
      }
      String dim = i > 0 && i % 3 == 0 ? "\n" : "\t\t";
      resultSb.append(line).append(" = ").append(result).append(dim);
      return i + 1;
    } catch (NumberFormatException e) {
      System.err.println(line);
    }
    return i;
  }

  private static int cal(int a, int b, String op) {
    if (op.equals("-")) {
      return a - b;
    } else {
      return a + b;
    }
  }
}
