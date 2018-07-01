package algorithms.fundamentals.divideconquer;

import java.util.Iterator;
import java.util.List;

import algorithms.utils.FileLinesIterable;
import com.google.common.base.Splitter;

public class MatrixLoader {

  private Splitter splitter = Splitter.on(",").trimResults();

  public Matrix load(String path) {
    FileLinesIterable iterable = new FileLinesIterable(path, 1024 * 1024);
    Iterator<String> lineIterator = iterable.iterator();
    if (!lineIterator.hasNext()) {
      return null;
    }
    String line = lineIterator.next();
    Integer[] firstLine = parseInts(line);
    int rows = firstLine.length;

    Integer[][] data = new Integer[rows][rows];
    data[0] = firstLine;

    int i = 1;
    while (lineIterator.hasNext()) {
      data[i] = parseInts(lineIterator.next());
      i++;
    }
    return new Matrix(data, rows);
  }

  private Integer[] parseInts(String line) {
    List<String> elements = splitter.splitToList(line);
    Integer[] data = new Integer[elements.size()];
    for (int i = 0; i < elements.size(); i++) {
      data[i] = Integer.valueOf(elements.get(i));
    }
    return data;
  }

  public static void main(String[] args) {
    MatrixLoader loader = new MatrixLoader();
    Matrix matrix = loader.load("algorithms/fundamental/divideconquer/a.text");
    System.out.println("ok");
  }
}
