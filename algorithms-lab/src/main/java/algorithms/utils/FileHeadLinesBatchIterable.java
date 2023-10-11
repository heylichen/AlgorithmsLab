package algorithms.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

/**
 * Created by Chen Li on 2018/4/30.
 */
public class FileHeadLinesBatchIterable implements Iterable<List<String>>, Closeable {

  private BufferedReader reader;
  private int batch;
  private List<String> headlines;

  public FileHeadLinesBatchIterable(String fileClassPath, int batch, int headlines, int bufferBytes) {
    ClassPathResource cpr = new ClassPathResource(fileClassPath);
    this.batch = batch;
    try {
      BufferedReader reader = new BufferedReader(new FileReader(cpr.getFile()), bufferBytes);
      this.reader = reader;
      this.headlines = new ArrayList<>();
      for (int i = 0; i < headlines; i++) {
        if (reader.ready()) {
          this.headlines.add(reader.readLine());
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Iterator<List<String>> iterator() {
    return new FileHeadLinesBatchIterable.FileLinesBatchIterator();
  }

  private class FileLinesBatchIterator implements Iterator<List<String>> {

    @Override
    public boolean hasNext() {
      try {
        return reader.ready();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    @Override
    public List<String> next() {
      List<String> lines = new ArrayList<>();
      try {
        while (reader.ready() && lines.size() < batch) {
          lines.add(reader.readLine());
        }
        return lines;
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Override
  public void close() throws IOException {
    reader.close();
  }

  public String getHeadline(int i) {
    if (this.headlines.size() <= i) {
      throw new IllegalArgumentException("index out of bounds");
    }
    return this.headlines.get(i);
  }
}
