package heylichen.problem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lichen
 * @date 2020/10/22 9:38
 * @desc
 */
@Getter
@Setter
@AllArgsConstructor
class Node<T> {
    private T data;
    private Node<T> left;
    private Node<T> right;

    public Node(T data) {
        this.data = data;
    }

    public void setChildren(Node<T> l, Node<T> r) {
        left = l;
        right = r;
    }
}
