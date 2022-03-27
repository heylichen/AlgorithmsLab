package heylichen.problem.network;

import org.junit.Test;

public class QueueDelayTest {
    @Test
    public void name() {
        QueueDelay qd = new QueueDelay(800000, 2000);
        System.out.println(qd.calculateQueueDelay(38));
        System.out.println(qd.calculateQueueDelay(62));
    }
}