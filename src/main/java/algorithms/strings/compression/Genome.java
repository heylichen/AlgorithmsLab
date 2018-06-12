package algorithms.strings.compression;

import java.io.InputStream;

import algorithms.strings.alphabet.Alphabet;
import algorithms.strings.alphabet.GenomeAlphabet;
import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;

/**
 * Created by Chen Li on 2018/6/11.
 */
public class Genome {

  private Alphabet alphabet = new GenomeAlphabet();

  public void compress(String genome, BinaryOut out) {
    for (int i = 0; i < genome.length(); i++) {
      out.write(alphabet.toIndex(genome.charAt(i)), 2);
    }
  }

  public String expand(InputStream inputStream) {
    StringBuilder sb = new StringBuilder();
    BinaryIn binaryIn = new BinaryIn(inputStream);
    while (!binaryIn.isEmpty()) {
      int index = binaryIn.readInt(2);
      char ch = alphabet.toChar(index);
      sb.append(ch);
    }
    return sb.toString();
  }
}
