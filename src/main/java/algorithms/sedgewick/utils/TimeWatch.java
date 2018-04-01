package algorithms.sedgewick.utils;

import com.google.common.base.Stopwatch;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * Created by Chen Li on 2017/7/9.
 * not thread safe
 */
public class TimeWatch {
  private Stopwatch stopwatch;
  private Duration total;
  private Map<String, Duration> tagCosts;

  public TimeWatch() {
    total = Duration.of(0, SECONDS);
    tagCosts = new ConcurrentHashMap<>();
    stopwatch = Stopwatch.createStarted();
  }

  public void tag(String tag) {
    stopwatch.stop();
    Duration duration = stopwatch.elapsed();
    stopwatch.reset().start();

    total = total.plus(duration);
    if (tag == null) {
      return;
    }
    Duration tagDuration = tagCosts.get(tag);
    if (tagDuration != null) {
      tagDuration = tagDuration.plus(duration);
    } else {
      tagDuration = duration;
    }
    tagCosts.put(tag, tagDuration);
  }

  public long getTagCostMs(String tag) {
    return getTagCost(tag, TimeUnit.MILLISECONDS);
  }

  public long getTagCost(String tag, TimeUnit unit) {
    Duration duration = tagCosts.get(tag);
    if (duration == null) {
      return 0;
    }
    return unit.convert(duration.toNanos(), TimeUnit.NANOSECONDS);
  }

  public long getTotalCostMs() {
    return getTotalCost(TimeUnit.MILLISECONDS);
  }

  public long getTotalCost(TimeUnit unit) {
    return unit.convert(total.toNanos(), TimeUnit.NANOSECONDS);
  }
}
