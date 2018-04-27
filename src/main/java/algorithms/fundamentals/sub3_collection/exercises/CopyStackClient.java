package algorithms.fundamentals.sub3_collection.exercises;

import edu.princeton.cs.algs4.ResizingArrayStack;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;


/**
 * Created by Chen Li on 2017/4/24.
 */
public class CopyStackClient {

  public static ResizingArrayStack<String> copyStack(ResizingArrayStack<String> original) {

    ResizingArrayStack<String> copy = new ResizingArrayStack<>();
    if (original == null || original.isEmpty()) {
      return copy;
    }

    String[] values = new String[original.size()];
    int j = 0;

    for (String item : original) {
      values[j++] = item;
    }

    for (int i = values.length - 1; i >= 0; i--) {
      copy.push(values[i]);
    }
    return copy;
  }

  public static void main(String[] args) {
    ResizingArrayStack<String> stack1 = new ResizingArrayStack<>();
    for (int i = 0; i < 10; i++) {
      stack1.push(String.valueOf(i));
    }

    ResizingArrayStack<String> stack2 = copyStack(stack1);
    assertEquals("copy stack size should equals original stack.", stack1.size(), stack2.size());


    Iterator<String> it1 = stack1.iterator();
    Iterator<String> it2 = stack2.iterator();
    while (it1.hasNext()) {
      String originalString = it1.next();
      String copyString = it2.next();
      assertEquals("copy stack value should equals original stack value.", originalString, copyString);
    }
  }
}