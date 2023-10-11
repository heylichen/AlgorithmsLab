package algorithms.strings.tries.practice200201;

import algorithms.strings.tries.TSTNode;

import java.util.ArrayList;
import java.util.Collection;

public class TernarySearchTrie<V> {
    private TSTNode<V> root;

    public V get(String key) {
        TSTNode<V> node = getNode(root, key, 0);
        return node == null ? null : node.getValue();
    }

    private TSTNode<V> getNode(TSTNode<V> node, String key, int keyCharIndex) {
        if (node == null) {
            return null;
        }
        if (keyCharIndex >= key.length()) {
            return node;
        }
        char ch = key.charAt(keyCharIndex);
        int compare = ch - node.getCharacter();

        TSTNode<V> nextNode;
        int nextKeyCharIndex;
        if (compare == 0) {
            if (keyCharIndex == key.length() - 1) {
                return node;
            }
            nextNode = node.getEqual();
            nextKeyCharIndex = keyCharIndex + 1;
        } else if (compare > 0) {
            nextNode = node.getGreater();
            nextKeyCharIndex = keyCharIndex;
        } else {
            nextNode = node.getLess();
            nextKeyCharIndex = keyCharIndex;
        }
        return getNode(nextNode, key, nextKeyCharIndex);
    }

    public void put(String key, V value) {
        root = putNode(root, key, 0, value);
    }

    private TSTNode<V> putNode(TSTNode<V> node, String key, int keyCharIndex, V value) {
        char ch = key.charAt(keyCharIndex);
        if (node == null) {
            node = new TSTNode<V>(ch);
        }
        TSTNode<V> nextNode;
        int compare = ch - node.getCharacter();
        if (compare == 0) {
            if (keyCharIndex == key.length() - 1) {
                node.setValue(value);
                return node;
            }
            nextNode = putNode(node.getEqual(), key, keyCharIndex + 1, value);
            node.setEqual(nextNode);
        } else if (compare > 0) {
            nextNode = putNode(node.getGreater(), key, keyCharIndex, value);
            node.setGreater(nextNode);
        } else {
            nextNode = putNode(node.getLess(), key, keyCharIndex, value);
            node.setLess(nextNode);
        }
        return node;
    }

    public void delete(String key) {

    }

//    private TSTNode<V> deleteNode(TSTNode<V> node, String key, int keyCharIndex) {
//        if (node == null) {
//            return null;
//        }
//        if (keyCharIndex >= key.length()) {
//            return node;
//        }
//        char ch = key.charAt(keyCharIndex);
//        int compare = ch - node.getCharacter();
//        if (compare == 0) {
//            if (keyCharIndex == key.length() - 1) {
//                node.setValue(null);
//
//            }
//        } else if (compare < 0) {
//
//        } else {
//
//        }
//
//    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        TSTNode<V> node = getNode(root, prefix, 0);
        Collection<String> keys = new ArrayList<>();

        if (prefix.length() > 0) {
            prefix = prefix.substring(0, prefix.length() - 1);
        }

        collect(node, prefix, keys);
        return keys;
    }

    /**
     * prefix does not contain node.character
     *
     * @param node
     * @param prefix
     * @param keys
     */
    private void collect(TSTNode<V> node, String prefix, Collection<String> keys) {
        if (node == null) {
            return;
        }
        if (node.hasValue()) {
            keys.add(prefix + node.getCharacter());
        }
        collect(node.getEqual(), prefix + node.getCharacter(), keys);
        collect(node.getLess(), prefix, keys);
        collect(node.getGreater(), prefix, keys);
    }

    public String longestPrefixOf(String string) {
        int len = longestPrefixLen(root, string, 0, 0);
        if (len <= 0) {
            return null;
        }
        return string.substring(0, len);
    }

    private int longestPrefixLen(TSTNode<V> node, String string, int charIndex, int len) {
        if (node == null) {
            return len;
        }
        if (charIndex >= string.length()) {
            return len;
        }
        char ch = string.charAt(charIndex);
        int compare = ch - node.getCharacter();
        if (compare == 0) {
            if (node.hasValue()) {
                len = charIndex + 1;
            }
            if (charIndex == string.length() - 1) {
                return len;
            }
            return longestPrefixLen(node.getEqual(), string, charIndex + 1, len);
        } else if (compare < 0) {
            return longestPrefixLen(node.getLess(), string, charIndex, len);
        } else {
            return longestPrefixLen(node.getGreater(), string, charIndex, len);
        }
    }

    public Iterable<String> keysThatMatch(String keyPattern) {
        Collection<String> keys = new ArrayList<>();
        collect(root, "", keyPattern, keys);
        return keys;
    }

    private void collect(TSTNode<V> node, String prefix, String keyPattern, Collection<String> keys) {
        if (node == null) {
            return;
        }
        int patternCharIndex = prefix.length();
        if (patternCharIndex >= keyPattern.length()) {
            return;
        }
        char ch = keyPattern.charAt(patternCharIndex);
        boolean isWildcard = ch == '.';
        int compare = ch - node.getCharacter();

        String currentPrefix = prefix;
        if (compare == 0 || isWildcard) {
            currentPrefix = currentPrefix + node.getCharacter();
            if (node.hasValue() && patternCharIndex == keyPattern.length() - 1) {
                keys.add(currentPrefix);
                return;
            }
            collect(node.getEqual(), currentPrefix, keyPattern, keys);
        }
        if (compare < 0 || isWildcard) {
            collect(node.getLess(), prefix, keyPattern, keys);
        }

        if (compare > 0 || isWildcard) {
            collect(node.getGreater(), prefix, keyPattern, keys);
        }
    }
}