package algorithms.sedgewick.sorting.compare;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import algorithms.sedgewick.utils.ItemBatchIterable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

/**
 * Created by Chen Li on 2018/4/1.
 */
@Slf4j
public class SortProfilesLogReport {

  public void reportProfiles(SortProfiles profiles) {
    if (CollectionUtils.isEmpty(profiles.getProfiles())) {
      return;
    }
    Map<String, List<SortProfileInfo>> profilesBySize = new LinkedHashMap<>();
    for (SortProfileInfo profileInfo : profiles.getProfiles()) {
      SortSizeConfig sizeConfig = profileInfo.getSizeConfig();
      String key = sizeConfig.getSize() + "_" + sizeConfig.getTimes();
      List<SortProfileInfo> list = profilesBySize.computeIfAbsent(key, k -> new ArrayList<>());
      list.add(profileInfo);
    }
    for (Map.Entry<String, List<SortProfileInfo>> stringListEntry : profilesBySize.entrySet()) {
      List<SortProfileInfo> list = stringListEntry.getValue();
      reportProfiles(list);
    }
  }

  private void reportProfiles(List<SortProfileInfo> list) {
    if (CollectionUtils.isEmpty(list)) {
      return;
    }
    if (list.size() == 1) {
      SortProfileInfo profile1 = list.get(0);
      long elapsed1 = profile1.getTotalElapsed();

      log.info("{} {}\t{} {}", profile1.getSorterName(),
               profile1.getTotalElapsed(),
               profile1.getSizeConfig().getSize(),
               profile1.getSizeConfig().getTimes()
      );
      return;
    }
    ItemBatchIterable<SortProfileInfo> itemBatchIterable = new ItemBatchIterable(list, 2);
    for (Collection<SortProfileInfo> sortProfileInfos : itemBatchIterable) {
      reportProfilePair((List<SortProfileInfo>) sortProfileInfos);
    }
  }

  private void reportProfilePair(List<SortProfileInfo> list) {
    if (CollectionUtils.isEmpty(list)) {
      return;
    }
    Collections.sort(list, Comparator.comparing(SortProfileInfo::getSorterName));
    SortProfileInfo profile1 = list.get(0);
    long elapsed1 = profile1.getTotalElapsed();
    SortProfileInfo profile2 = list.get(1);
    long elapsed2 = profile2.getTotalElapsed();
    double faster = (double) elapsed2 / (double) elapsed1;
    String fasterStr = String.format("%.4f", faster);

    log.info("{}: {}\t{}: {}\t{} faster  {} {}", profile1.getSorterName(), profile1.getTotalElapsed(),
             profile2.getSorterName(), profile2.getTotalElapsed(),
             fasterStr,
             profile1.getSizeConfig().getSize(),
             profile1.getSizeConfig().getTimes()
    );
  }
}
