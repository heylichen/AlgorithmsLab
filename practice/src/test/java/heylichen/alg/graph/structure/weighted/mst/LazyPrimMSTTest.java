package heylichen.alg.graph.structure.weighted.mst;

import heylichen.alg.graph.path.PathUtils;
import heylichen.alg.graph.structure.weighted.EdgeWeightedGraph;
import heylichen.test.AppTestContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lichen
 * @date 2020/4/8 16:12
 * @desc
 */
@Slf4j
public class LazyPrimMSTTest extends AppTestContext {
    @Autowired
    private EdgeWeightedGraph tinyEWG;

    @Autowired
    private EdgeWeightedGraph mediumEWG;

    @Test
    public void testTiny() {
        MST mst = new LazyPrimMST(tinyEWG);
        String logStr = PathUtils.viewEdges(mst.edges());
        log.info("{} {}", logStr, mst.weight());
    }

    @Test
    public void testMedium() {
        MST mst = new LazyPrimMST(mediumEWG);
        String logStr = PathUtils.viewEdges(mst.edges());
        log.info("{} {}", logStr, mst.weight());
    }
}
