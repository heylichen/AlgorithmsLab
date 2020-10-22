package heylichen.problem;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lichen
 * @date 2020/10/22 9:41
 * @desc
 */
public final class SerializeUtils {
    private static final String NULL = "N";
    private SerializeUtils() {
    }

    public static String toString(Iterable<Integer> list) {
        List<String> stringList = new LinkedList<>();
        for (Integer a : list) {
            stringList.add(  a == null ? "N" : a.toString());
        }
        return String.join(",", stringList);
    }

    public static List<Integer> parse(String string) {
        String[] pieces = string.split(",");
        List<Integer> list = new LinkedList<>();
        for (String piece : pieces) {
            if (piece.equals(NULL)) {
                list.add(null);
            } else{
                list.add(Integer.valueOf(piece));
            }
        }
        return list;
    }

    public static <T> boolean binaryTreeEquals(Node<T> a, Node<T> b) {
        if (a == null ^ b == null) {
            return false;
        }
        if (a == null) {
            return true;
        }
        T aV = a.getData();
        T bV = b.getData();
        if (aV == null ^ bV == null) {
            return false;
        }
        if (aV != null && !aV.equals(bV)) {
            return false;
        }
        return binaryTreeEquals(a.getLeft(), b.getLeft()) && binaryTreeEquals(a.getRight(), b.getRight());
    }

}
