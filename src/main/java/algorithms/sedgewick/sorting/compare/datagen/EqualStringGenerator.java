package algorithms.sedgewick.sorting.compare.datagen;

/**
 * Created by Chen Li on 2018/4/6.
 */
public class EqualStringGenerator implements DataGenerator<String> {

  private int length = 10;
  private String character = "A";
  @Override
  public String[] generate(int size) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      sb.append(character);
    }
    String str = sb.toString();
    String[] data = new String[size];
    for (int i = 0; i < size; i++) {
      data[i] = str;
    }
    return data;
  }
}
