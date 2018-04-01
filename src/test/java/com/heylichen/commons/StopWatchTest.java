package com.heylichen.commons;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;
import org.junit.Test;

/**
 * Created by Chen Li on 2018/4/1.
 */
public class StopWatchTest {

  @Test
  public void collectTest() throws Exception{
    Stopwatch watch = Stopwatch.createUnstarted();
    for (int i = 0; i < 3; i++) {

      Thread.sleep(500);
      watch.start();
      Thread.sleep(500);
      watch.stop();
    }

    System.out.println(watch.elapsed(TimeUnit.MILLISECONDS));
  }
}
