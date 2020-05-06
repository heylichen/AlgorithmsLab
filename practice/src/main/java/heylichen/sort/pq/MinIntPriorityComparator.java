package heylichen.sort.pq;

import org.springframework.stereotype.Component;

@Component
public class MinIntPriorityComparator implements PriorityComparator<Integer> {
    /**
     * smaller, higher priority
     * @param a
     * @param b
     * @return
     */
    @Override
    public boolean higherThan(Integer a, Integer b) {
        return a.compareTo(b) < 0;
    }
}
