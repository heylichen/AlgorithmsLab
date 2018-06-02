package algorithms.graph.practice.weighted.digraph;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import algorithms.graph.DirectedEdge;
import algorithms.graph.DirectedEdgeImpl;
import algorithms.graph.EdgeWeightedDigraph;
import algorithms.graph.EdgeWeightedDigraphImpl;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

/**
 * Created by Chen Li on 2018/6/2.
 */
@Getter
@Setter
public class ParallelJobs {

  private int source;
  private int sink;
  private EdgeWeightedDigraph graph;
  private int jobSize;
  //inner
  private Double ZERO = 0d;

  public ParallelJobs(List<ParallelJobs.Job> jobs) {
    jobSize = jobs.size();
    int vertices = 2 * jobSize + 2;
    source = vertices - 2;
    sink = vertices - 1;
    graph = new EdgeWeightedDigraphImpl();
    graph.init(vertices);

    for (Job job : jobs) {
      int jobId = job.id;
      DirectedEdge jobEdge = edgeFromJobId(jobId, job.duration);
      graph.addEdge(jobEdge);
      graph.addEdge(new DirectedEdgeImpl(ZERO, source, jobEdge.from()));
      graph.addEdge(new DirectedEdgeImpl(ZERO, jobEdge.to(), sink));
      if (CollectionUtils.isEmpty(job.completeBeforeJobs)) {
        continue;
      }
      for (Integer completeBeforeJobId : job.completeBeforeJobs) {
        int jobFrom = fromVertexOfJobId(completeBeforeJobId);
        graph.addEdge(new DirectedEdgeImpl(ZERO, jobEdge.to(), jobFrom));
      }
    }
  }

  public Collection<Integer> criticalPath() {
    DAGLongestPaths longestPaths = new DAGLongestPaths();
    longestPaths.init(graph, source);

    Set<Integer> set = new LinkedHashSet<>();
    for (DirectedEdge directedEdge : longestPaths.pathTo(sink)) {
      if (directedEdge.weight() > ZERO) {
        int from = directedEdge.from();
        Optional<Integer> jobIdOptional = toJobId(from);
        set.add(jobIdOptional.get());
      }
    }
    return set;
  }

  private DirectedEdge edgeFromJobId(int jobId, double weight) {
    int from = fromVertexOfJobId(jobId);
    return new DirectedEdgeImpl(weight, from, from + 1);
  }

  private int fromVertexOfJobId(int jobId) {
    return jobId * 2;
  }

  private Optional<Integer> toJobId(int vertex) {
    int jobId = vertex / 2;
    if (jobId < jobSize) {
      return Optional.of(jobId);
    } else {
      return Optional.empty();
    }
  }

  @Getter
  @Setter
  public static class Job {

    private int id;
    private double duration;
    private List<Integer> completeBeforeJobs;

    public Job(int id, double duration, List<Integer> completeBeforeJobs) {
      this.id = id;
      this.duration = duration;
      this.completeBeforeJobs = completeBeforeJobs;
    }

    public Job(int id, double duration) {
      this.id = id;
      this.duration = duration;
    }
  }
}
