package algorithms.ch1fundamentals;

/**
 * Created by lc on 2016/4/3.
 */
public interface VisualAccumulator {
    void addDataValue(double val);// add a new data value
    double avg();// average of all data values
    String toString();// string representation
}
