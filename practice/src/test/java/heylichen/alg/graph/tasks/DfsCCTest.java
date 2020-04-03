package heylichen.alg.graph.tasks;

import heylichen.alg.graph.structure.Graph;
import heylichen.test.AppTestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class DfsCCTest extends AppTestContext {
    @Autowired
    private Graph tinyUndirectedGraph;

    @Autowired
    private Graph mediumUndirectedGraph;

    @Test
    public void testCC() {
        testCC(tinyUndirectedGraph);
    }

    @Test
    public void testDisplay() {
        CC cc = new DfsCC(mediumUndirectedGraph);
        display(cc, mediumUndirectedGraph);
    }

    private void testCC(Graph graph) {
        CC cc = new DfsCC(graph);
        Assert.assertEquals("there are 3 connected components", 3, cc.getComponentsCount());
        Assert.assertTrue(cc.connected(1, 4));
        Assert.assertTrue(cc.connected(7, 8));
        Assert.assertTrue(cc.connected(9, 12));
        Assert.assertFalse(cc.connected(9, 1));
    }

    private void display(CC cc, Graph graph) {
        List<List<Integer>> ll = new ArrayList<>(cc.getComponentsCount());
        for (int i = 0; i < cc.getComponentsCount(); i++) {
            ll.add(new LinkedList<>());
        }

        for (int i = 0; i < graph.getVertexCount(); i++) {
            int componentId = cc.getComponentId(i);
            ll.get(componentId).add(i);
        }

        log.info("{} connected components", ll.size());
        for (int i = 0; i < ll.size(); i++) {
            log.info("componentId={} : {}", i, StringUtils.join(ll.get(i)));
        }
    }
}