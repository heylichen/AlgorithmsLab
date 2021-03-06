package algorithms.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Chen Li on 2018/4/26.
 */
@Slf4j
public class FileLinesBatchIterable implements Iterable<List<String>>, Closeable {

  private BufferedReader reader;
  private int batch;

  public FileLinesBatchIterable(String fileClassPath, int batch, int bufferBytes) {
    ClassPathResource cpr = new ClassPathResource(fileClassPath);
    this.batch = batch;
    try {
      BufferedReader reader = new BufferedReader(new FileReader(cpr.getFile()), bufferBytes);
      this.reader = reader;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Iterator<List<String>> iterator() {
    return new FileLinesBatchIterator();
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
}
