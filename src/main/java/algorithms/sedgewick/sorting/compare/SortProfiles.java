package algorithms.sedgewick.sorting.compare;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Chen Li on 2018/3/30.
 */
@Getter
@Setter
public class SortProfiles {

  private List<SortProfileInfo> profiles = new ArrayList<>();

  public void addProfile(SortProfileInfo profileInfo) {
    profiles.add(profileInfo);
  }

}
