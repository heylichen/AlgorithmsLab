package algorithms.ch1fundamentals;

/**
 * Created by Chen Li on 2017/1/5.
 */
public interface VisualAccumulator {
  void addDataValue(double val);

  double avg();

  String toString();
}
