package heylichen.searching.bst.rb.standard;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * not thread safe
 *
 * @param <K>
 * @param <V>
 */
public class RBTreeChecker<K extends Comparable<K>, V> {
    private RBTree<K, V> tree;

    public CheckResult check(RBTree<K, V> tree) {
        if (tree.getRoot().isRed()) {
            return new CheckResult(false, "root node must be black node");
        }
        if (tree.getNull().isRed()) {
            return new CheckResult(false, "null node must be black node");
        }
        this.tree = tree;

        CheckResult result = checkContinuousRed();
        if (!result.isPass()) {
            return result;
        }
        return checkBlackBalance();
    }

    private CheckResult checkBlackBalance() {
        RBNode<K, V> root = tree.getRoot();
        Map<K, Integer> blackHeightMap = new HashMap<>();
        checkBlackBalance(root, 0, blackHeightMap);
        K oldKey = null;
        Integer height = null;
        for (Map.Entry<K, Integer> entry : blackHeightMap.entrySet()) {
            K key = entry.getKey();
            Integer bh = entry.getValue();
            if (height == null) {
                height = bh;
                oldKey = key;
            } else if (!height.equals(bh)) {
                return new CheckResult(false, "black height balance violated. " + oldKey + ":" + height + " != "
                        + key + ":" + bh
                );
            }
        }
        return new CheckResult(true, null);
    }

    private void checkBlackBalance(RBNode<K, V> node, int baseBlackHeight, Map<K, Integer> blackHeightMap) {
        int bh = node.isBlack() ? baseBlackHeight + 1 : baseBlackHeight;
        if (tree.isNull(node.getLeft())) {
            blackHeightMap.put(node.getKey(), bh);
        } else {
            checkBlackBalance(node.getLeft(), bh, blackHeightMap);
        }


        if (tree.isNull(node.getRight())) {
            blackHeightMap.put(node.getKey(), bh);
        } else {
            checkBlackBalance(node.getRight(), bh, blackHeightMap);
        }
    }

    private CheckResult checkContinuousRed() {
        RBNode node = tree.getRoot();

        CheckResult result = new CheckResult(true, null);
        checkContinuousRed(node, result);
        return result;
    }

    private void checkContinuousRed(RBNode node, CheckResult result) {
        if (tree.isNull(node) || !result.isPass()) {
            return;
        }
        if (isContinuousRed(node)) {
            result.setPass(false);
            result.setMsg("parent and child both red. p.key=" + node.getKey());
            return;
        }
        checkContinuousRed(node.getLeft(), result);
        checkContinuousRed(node.getRight(), result);
    }

    private boolean isContinuousRed(RBNode node) {
        return node.isRed() && (node.getLeft().isRed() || node.getRight().isRed());
    }

    @Getter
    @Setter
    public static final class CheckResult {
        private boolean pass;
        private String msg;

        public CheckResult(boolean pass, String msg) {
            this.pass = pass;
            this.msg = msg;
        }
    }
}
