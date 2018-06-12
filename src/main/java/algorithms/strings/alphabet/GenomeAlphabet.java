package algorithms.strings.alphabet;

/**
 * Created by Chen Li on 2018/6/12.
 */
public class GenomeAlphabet extends AbstractAlphabet {

  //ACTG
  private static final char A = 'A';
  private static final char C = 'C';
  private static final char T = 'T';
  private static final char G = 'G';

  private static final int A_INDEX = 0;
  private static final int C_INDEX = 1;
  private static final int T_INDEX = 2;
  private static final int G_INDEX = 3;

  @Override
  public char toChar(int index) {
    char ch;
    switch (index) {
      case A_INDEX:
        ch = A;
        break;
      case C_INDEX:
        ch = C;
        break;
      case G_INDEX:
        ch = G;
        break;
      case T_INDEX:
        ch = T;
        break;
      default:
        throw new IllegalArgumentException("illegl index");
    }
    return ch;
  }

  @Override
  public int toIndex(char c) {
    int index = -1;
    switch (c) {
      case A:
        index = A_INDEX;
        break;
      case C:
        index = C_INDEX;
        break;
      case T:
        index = T_INDEX;
        break;
      case G:
        index = G_INDEX;
        break;
      default:
        index = -1;
    }
    return index;
  }

  @Override
  public boolean contains(char ch) {
    return ch == A || ch == C || ch == T || ch == G;
  }

  @Override
  public int radix() {
    return 4;
  }

  @Override
  public int lgRadix() {
    return 2;
  }
}

