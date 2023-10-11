package algorithms.graph.practice.weighted.digraph;

import java.util.ArrayList;
import java.util.List;

import algorithms.utils.FileHeadLinesBatchIterable;
import com.google.common.base.Splitter;

/**
 * Created by Chen Li on 2018/6/2.
 */
public class ParallelJobsLoader {

  private Splitter splitter = Splitter.on(" ").trimResults();

  public List<ParallelJobs.Job> load(String path) {
    FileHeadLinesBatchIterable lines = new FileHeadLinesBatchIterable(path, 100, 1, 8 * 1024);
    Integer jobNumber = Integer.valueOf(lines.getHeadline(0));
    List<ParallelJobs.Job> jobs = new ArrayList<>();
    for (List<String> lineList : lines) {
      for (String line : lineList) {
        ParallelJobs.Job job = parse(line);
        jobs.add(job);
      }
    }
    return jobs;
  }

  private ParallelJobs.Job parse(String line) {
    List<String> pieces = splitter.splitToList(line);
    Integer jobId = Integer.valueOf(pieces.get(0));
    Double duration = Double.valueOf(pieces.get(1));
    if (pieces.size() == 2) {
      return new ParallelJobs.Job(jobId, duration);
    }
    List<Integer> completeBeforeJobs = new ArrayList<>();
    for (int i = 2; i < pieces.size(); i++) {
      completeBeforeJobs.add(Integer.valueOf(pieces.get(i)));
    }
    return new ParallelJobs.Job(jobId, duration, completeBeforeJobs);
  }
}
