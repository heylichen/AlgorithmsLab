package algorithms.search.symboltable;

import algorithms.fundamentals.sub3_collection.impl.VisualAccumulator;
import algorithms.search.ST;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2017/6/26.
 */
@Getter
@Setter
public abstract class AbstractST<K, V> implements ST<K, V> {

  protected VisualAccumulator visualAccumulator;

  @Override
  public boolean isEmpty() {
    return size() == 0;
  }

  @Override
  public boolean contains(K key) {
    return key != null && get(key) != null;
  }

  //testing purpose

  protected void addDataValue(double value) {
    if (visualAccumulator != null) {
      visualAccumulator.addDataValue(value);
    }
  }

  @Override
  public void enableDraw(boolean enable) {
    if (visualAccumulator != null) {
      visualAccumulator.enableDraw(enable);
    }
  }
}
