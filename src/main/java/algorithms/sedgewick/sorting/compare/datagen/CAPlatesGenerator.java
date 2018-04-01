package algorithms.sedgewick.sorting.compare.datagen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Chen Li on 2018/3/6.
 * non thread safe
 * 加州车牌号模拟生成
 * 7位随机字符，固定长度
 */
public class CAPlatesGenerator implements DataGenerator<String> {

  public static String[] characters;
  public static String[] numbers;
  public static final int CHAR_SIZE = 26;
  public static final int NUMBER_SIZE = 10;
  public static final char CHAR_START = 'A';
  public static final char NUMBER_START = '0';
  private Random rand = new Random();
  private StringBuilder sb = new StringBuilder();

  static {
    characters = new String[CHAR_SIZE];
    numbers = new String[NUMBER_SIZE];

    char currentChar = CHAR_START;
    for (int i = 0; i < CHAR_SIZE; i++, currentChar++) {
      characters[i] = String.valueOf(currentChar);
    }

    currentChar = NUMBER_START;
    for (int i = 0; i < NUMBER_SIZE; i++, currentChar++) {
      numbers[i] = String.valueOf(currentChar);
    }
  }

  private String randomPlate() {
    sb.delete(0, sb.length());
    for (int i = 0; i < 4; i++) {
      sb.append(characters[rand.nextInt(CHAR_SIZE)]);
    }
    for (int i = 0; i < 3; i++) {
      sb.append(numbers[rand.nextInt(NUMBER_SIZE)]);
    }
    return sb.toString();
  }

  public List<String> randomPlate(int size) {
    List<String> result = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      result.add(randomPlate());
    }
    return result;
  }

  @Override
  public String[] generate(int size) {
    List<String> list = randomPlate(size);
    return list.toArray(new String[size]);
  }

  public static void main(String[] args) {
    CAPlatesGenerator generator = new CAPlatesGenerator();

    for (int i = 0; i < 10; i++) {
      System.out.println(generator.randomPlate());
    }
  }

}