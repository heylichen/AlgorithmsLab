package algorithms.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Chen Li on 2018/4/26.
 */
@Slf4j
public class FileLinesIterable implements Iterable<String>, Closeable {

  private BufferedReader reader;

  public FileLinesIterable(String fileClassPath, int bufferBytes) {
    ClassPathResource cpr = new ClassPathResource(fileClassPath);
    try {
      BufferedReader reader = new BufferedReader(new FileReader(cpr.getFile()), bufferBytes);
      this.reader = reader;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Iterator<String> iterator() {
    return new FileLinesBatchIterator();
  }

  private class FileLinesBatchIterator implements Iterator<String> {

    @Override
    public boolean hasNext() {
      try {
        return reader.ready();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    @Override
    public String next() {
      try {
        return reader.readLine();
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
