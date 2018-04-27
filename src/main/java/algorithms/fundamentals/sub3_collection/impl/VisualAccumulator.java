package algorithms.fundamentals.sub3_collection.impl;

/**
 * Created by Chen Li on 2017/1/5.
 */
public interface VisualAccumulator {
  void addDataValue(double val);

  double avg();

  String toString();

  //testing purpose
  void enableDraw(boolean enable);

}
