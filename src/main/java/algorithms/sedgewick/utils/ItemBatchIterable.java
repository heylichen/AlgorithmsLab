package algorithms.sedgewick.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class ItemBatchIterable<T> implements Iterable<Collection<T>> {

  private Collection<T> items;
  private int batchSize;

  public ItemBatchIterable(Collection<T> items, int batchSize) {
    if (items == null) {
      items = Collections.emptyList();
    }
    if (batchSize < 1) {
      throw new IllegalArgumentException("batch size must be >= 1!");
    }
    this.items = items;
    this.batchSize = batchSize;
  }

  @Override
  public Iterator<Collection<T>> iterator() {
    return new ItemsBatchesIterator(items.iterator());
  }

  private class ItemsBatchesIterator implements Iterator<Collection<T>> {

    private Iterator<T> iterator;

    public ItemsBatchesIterator(Iterator<T> iterator) {
      this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public Collection<T> next() {
      List<T> subCollection = new ArrayList<>();
      int got = 0;
      while (got < batchSize && iterator.hasNext()) {
        subCollection.add(iterator.next());
        got++;
      }
      return subCollection;
    }
  }
}