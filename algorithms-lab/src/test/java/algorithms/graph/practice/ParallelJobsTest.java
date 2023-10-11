package algorithms.graph.practice;

import java.util.List;

import algorithms.graph.PathLogUtil;
import algorithms.graph.practice.weighted.digraph.ParallelJobs;
import algorithms.graph.practice.weighted.digraph.ParallelJobsLoader;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/6/2.
 */
public class ParallelJobsTest {

  @Test
  public void name() {
    ParallelJobsLoader loader = new ParallelJobsLoader();
    List<ParallelJobs.Job> jobs = loader.load("algorithms/graph/directed/parallelJobs.txt");
    ParallelJobs parallelJobs = new ParallelJobs(jobs);
    System.out.println(PathLogUtil.pathToString( parallelJobs.criticalPath()));
  }
}
