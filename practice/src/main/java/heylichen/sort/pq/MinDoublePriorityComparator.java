package heylichen.sort.pq;

import org.springframework.stereotype.Component;

@Component
public class MinDoublePriorityComparator implements PriorityComparator<Double> {
    /**
     * smaller, higher priority
     * @param a
     * @param b
     * @return
     */
    @Override
    public boolean higherThan(Double a, Double b) {
        return a.compareTo(b) < 0;
    }
}
