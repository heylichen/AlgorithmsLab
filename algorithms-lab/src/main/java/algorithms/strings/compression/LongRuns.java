package algorithms.strings.compression;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;

public class LongRuns {

  private int bits;
  private int maxCount;
  private boolean startBit;

  public LongRuns(int maxCount) {
    this.maxCount = maxCount;
    this.bits = calculateBits(maxCount);
  }

  private int calculateBits(int count) {
    int bits = 0;
    while (count > 0) {
      bits++;
      count = count >>> 1;
    }
    return bits;
  }

  public void compress(InputStream in, OutputStream os) {
    BinaryIn binaryIn = new BinaryIn(in);
    BinaryOut binaryOut = new BinaryOut(os);

    int count = 0;
    Boolean lastBit = null;
    if (!binaryIn.isEmpty()) {
      startBit = binaryIn.readBoolean();
      lastBit = startBit;
      count++;
    }

    while (!binaryIn.isEmpty()) {
      boolean bit = binaryIn.readBoolean();
      if (lastBit != null && lastBit != bit) {
        binaryOut.write(count, bits);
        count = 1;
      } else if (count == maxCount) {
        binaryOut.write(count, bits);
        binaryOut.write(0, bits);
        count = 1;
      } else {
        count++;
      }
      lastBit = bit;
    }

    binaryOut.write(count, bits);
    binaryOut.close();
  }

  public void expand(InputStream inputStream, OutputStream os) {
    BinaryIn binaryIn = new BinaryIn(inputStream);
    BinaryOut binaryOut = new BinaryOut(os);

    boolean current = startBit;
    try {
      while (!binaryIn.isEmpty()) {
        //need a way to detect appended bits
        int count = binaryIn.readInt(bits);
        int currentBit = current ? 1 : 0;
        for (int i = 0; i < count; i++) {
          binaryOut.write(currentBit, 1);
        }
        current = !current;
      }
    } catch (NoSuchElementException e) {
       //encounter last bits of the stream, ignore
    }
    binaryOut.close();
  }
}
