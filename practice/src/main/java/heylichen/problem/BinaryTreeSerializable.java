package heylichen.problem;

/**
 * @author lichen
 * @date 2020/10/22 9:37
 * @desc
 */
public interface BinaryTreeSerializable {
    String serialize(Node<Integer> node);

    Node<Integer> deserialize(String string);
}
