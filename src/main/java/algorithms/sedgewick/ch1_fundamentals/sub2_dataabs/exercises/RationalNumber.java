package algorithms.sedgewick.ch1_fundamentals.sub2_dataabs.exercises;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Chen Li on 2017/1/9.
 */
public class RationalNumber {
  private final long p;
  private final long q;


  public RationalNumber(long p, long q) {
    long factor = maxCommonFactor(p, q);
    this.p = p / factor;
    this.q = q / factor;
  }

  public long maxCommonFactor(long a, long b) {
    if (b == 0) {
      return a;
    }
    return maxCommonFactor(b, a % b);
  }

  public RationalNumber plus(RationalNumber that) {
    if (that == null) {
      return this;
    }
    long numerator = this.p * that.getQ() + this.q * that.getP();
    long denominator = this.q * that.getQ();
    return new RationalNumber(numerator, denominator);
  }

  public RationalNumber minus(RationalNumber that) {
    if (that == null) {
      return this;
    }
    long numerator = this.p * that.getQ() - this.q * that.getP();
    long denominator = this.q * that.getQ();
    return new RationalNumber(numerator, denominator);
  }

  public RationalNumber times(RationalNumber that) {
    if (that == null) {
      return this;
    }
    long numerator = this.p * that.getP();
    long denominator = this.q * that.getQ();
    return new RationalNumber(numerator, denominator);
  }

  public RationalNumber divides(RationalNumber that) {
    if (that == null) {
      return this;
    }
    long numerator = this.p * that.getQ();
    long denominator = this.q * that.getP();
    return new RationalNumber(numerator, denominator);
  }


  public String toString() {
    return String.format("%s/%s", p, q);
  }

  public long getP() {
    return p;
  }


  public long getQ() {
    return q;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RationalNumber that = (RationalNumber) o;

    if (p != that.p) return false;
    return q == that.q;

  }

  @Override
  public int hashCode() {
    int result = (int) (p ^ (p >>> 32));
    result = 31 * result + (int) (q ^ (q >>> 32));
    return result;
  }

  public static void main(String[] args) {
    RationalNumber a = new RationalNumber(1, 7);
    RationalNumber b = new RationalNumber(1, 8);
    testClient(a, b);
  }

  private static void testClient(RationalNumber a, RationalNumber b) {
    RationalNumber plus = a.plus(b);
    RationalNumber minus = a.minus(b);
    RationalNumber times = a.times(b);
    RationalNumber divides = a.divides(b);
    StdOut.printf("ration numbers: %s, %s\n +: %s, -: %s, *: %s, /: %s\n",
        a, b, plus, minus, times, divides);
  }

}
