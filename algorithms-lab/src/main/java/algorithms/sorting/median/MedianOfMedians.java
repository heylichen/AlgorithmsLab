package algorithms.sorting.median;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author lichen
 * @date 2022-5-12
 */
public class MedianOfMedians {
    private static final int FIVE = 5;

    public static <T extends Comparable<T>> T findK(List<T> values, int k) {
        if (values.size() <= FIVE) {
            values.sort(Comparator.naturalOrder());
            return values.get(k);
        }

        List<T> medians = partitionAndFindMedians(values);
        int medianOfMedianIndex = medians.size() / 2;
        T pivot = findK(medians, medianOfMedianIndex);

        List<T> left = new ArrayList<>();
        List<T> right = new ArrayList<>();
        int compared;
        for (T value : values) {
            compared = value.compareTo(pivot);
            if (compared < 0) {
                left.add(value);
            } else if (compared > 0) {
                right.add(value);
            } else {
                //discard the pivot
            }
        }
        int leftSize = left.size();
        if (leftSize == k) {
            return pivot;
        } else if (leftSize > k) {
            return findK(left, k);
        } else {
            return findK(right, k - leftSize - 1);
        }
    }

    private static <T extends Comparable<T>> List<T> partitionAndFindMedians(List<T> values) {
        List<List<T>> partitions = partition(values, FIVE);
        List<T> medians = new ArrayList<>(partitions.size());

        for (List<T> partition : partitions) {
            partition.sort(Comparator.naturalOrder());
            int medianIndex = partition.size() / 2;
            medians.add(partition.get(medianIndex));
        }
        return medians;
    }

    private static <T extends Comparable<T>> List<List<T>> partition(List<T> values, int batch) {
        int size = (values.size() + batch - 1) / batch;
        List<List<T>> result = new ArrayList<>(size);
        List<T> currentList = new ArrayList<>(batch);
        for (T value : values) {
            currentList.add(value);
            if (currentList.size() >= batch) {
                result.add(currentList);
                currentList = new ArrayList<>(batch);
            }
        }
        return result;
    }
}