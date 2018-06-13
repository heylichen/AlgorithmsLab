package algorithms.strings.compression;

import java.io.InputStream;
import java.io.OutputStream;

import algorithms.strings.alphabet.Alphabet;
import algorithms.strings.alphabet.EnumerateAlphabet;
import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;

/**
 * Created by Chen Li on 2018/6/11.
 */
public class Genome {

  private Alphabet alphabet = new EnumerateAlphabet("ACGT");

  public void compress(InputStream in, OutputStream os) {
    BinaryIn binaryIn = new BinaryIn(in);
    String genome = binaryIn.readString();
    BinaryOut out = new BinaryOut(os);
    int size = genome.length();
    out.write(size);
    for (int i = 0; i < genome.length(); i++) {
      out.write(alphabet.toIndex(genome.charAt(i)), 2);
    }
    out.close();
  }

  public void expand(InputStream inputStream, OutputStream os) {
    StringBuilder sb = new StringBuilder();
    BinaryIn binaryIn = new BinaryIn(inputStream);
    BinaryOut binaryOut = new BinaryOut(os);

    int size = 0;
    if (!binaryIn.isEmpty()) {
      size = binaryIn.readInt();
    }
    int i = 0;
    while (!binaryIn.isEmpty() && i < size) {
      int index = binaryIn.readInt(2);
      char ch = alphabet.toChar(index);
      sb.append(ch);
      i++;
    }
    binaryOut.write(sb.toString());
    binaryOut.close();
  }
}
