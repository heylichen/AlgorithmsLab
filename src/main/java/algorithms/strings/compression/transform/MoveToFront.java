package algorithms.strings.compression.transform;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoveToFront {

  public List<Integer> encode(String string, String alphabet) {
    StringBuilder stringBuilder = new StringBuilder(alphabet);
    List<Integer> result = new ArrayList<>();

    for (int i = 0; i < string.length(); i++) {
      char ch = string.charAt(i);
      int index = stringBuilder.indexOf(String.valueOf(ch));
      result.add(index);
      stringBuilder.deleteCharAt(index).insert(0, ch);
    }
    return result;
  }

  public String decode(List<Integer> encodedList, String alphabet) {
    StringBuilder result = new StringBuilder();
    StringBuilder sb = new StringBuilder(alphabet);

    for (Integer index : encodedList) {
      String ch = String.valueOf(sb.charAt(index));
      result.append(ch);
      if (index != 0) {
        sb.deleteCharAt(index).insert(0, ch);
      }
    }
    return result.toString();
  }
}
