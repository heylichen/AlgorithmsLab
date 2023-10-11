package algorithms.strings.tries.practice200201;

import algorithms.strings.alphabet.Alphabet;
import algorithms.strings.tries.TrieNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TrieST<V> {
    private TrieNode<V> root;
    private Alphabet alphabet;

    public TrieST(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    public V get(String key) {
        TrieNode<V> node = getNode(root, key, 0);
        return node == null ? null : node.getValue();
    }

    public void put(String key, V value) {
        root = putNode(root, key, 0, value);
    }

    public String longestPrefixOf(String string) {
        int len = longestPrefixLen(root, string, 0, 0);
        return string.substring(0, len);
    }

    private int longestPrefixLen(TrieNode<V> node, String string, int charIndex, int currentPrefixLength) {
        if (node == null) {
            return currentPrefixLength;
        }
        if (node.hasValue()) {
            currentPrefixLength = charIndex;
        }
        if (charIndex == string.length()) {
            return currentPrefixLength;
        }
        int childIndex = convertCharToIndex(string, charIndex);
        TrieNode<V> child = node.next(childIndex);
        charIndex++;
        return longestPrefixLen(child, string, charIndex, currentPrefixLength);
    }

    public Iterable<String> keysThatMatch(String keyPattern) {
        List<String> keys = new ArrayList<>();
        keysThatMatch(root, "", keyPattern, keys);
        return keys;
    }

    private void keysThatMatch(TrieNode<V> node, String prefix, String pattern, Collection<String> keys) {
        if (node == null) {
            return;
        }
        int patternIndex = prefix.length();
        if (patternIndex == pattern.length()) {
            if (node.hasValue()) {
                keys.add(prefix);
            }
            return;
        }
        char patternChar = pattern.charAt(patternIndex);
        String currentPrefix;
        for (int i = 0; i < node.getNext().length; i++) {
            char ch = alphabet.toChar(i);
            if (patternChar != '.' && ch != patternChar) {
                continue;
            }

            TrieNode<V> childNode = node.next(i);
            currentPrefix = prefix + ch;
            keysThatMatch(childNode, currentPrefix, pattern, keys);
        }
    }

    public void delete(String key) {
        root = deleteNode(root, key, 0);
    }

    private TrieNode<V> deleteNode(TrieNode<V> node, String key, int keyIndex) {
        if (node == null) {
            return node;
        }

        if (keyIndex == key.length()) {
            node.setValue(null);
            node = nullIfRemovable(node);
            return node;
        }

        int childIndex = convertCharToIndex(key, keyIndex);
        TrieNode<V> child = node.next(childIndex);
        child = deleteNode(child, key, keyIndex + 1);
        node.setNext(childIndex, child);
        node = nullIfRemovable(node);
        return node;
    }

    private TrieNode<V> nullIfRemovable(TrieNode<V> node) {
        return !node.hasValue() && node.isChildrenEmpty() ? null : node;
    }

    private TrieNode<V> putNode(TrieNode<V> node, String key, int keyIndex, V value) {
        if (node == null) {
            node = new TrieNode<>(alphabet.radix());
        }
        if (keyIndex == key.length()) {
            node.setValue(value);
            return node;
        }

        int childIndex = convertCharToIndex(key, keyIndex);
        TrieNode<V> childNode = node.next(childIndex);
        keyIndex++;

        childNode = putNode(childNode, key, keyIndex, value);
        node.setNext(childIndex, childNode);
        return node;
    }

    private int convertCharToIndex(String key, int keyIndex) {
        char ch = key.charAt(keyIndex);
        return alphabet.toIndex(ch);
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        List<String> keys = new ArrayList<>();
        TrieNode<V> prefixNode = getNode(root, prefix, 0);
        collectKeys(prefixNode, prefix, keys);
        return keys;
    }

    private void collectKeys(TrieNode<V> node, String prefix, Collection keys) {
        if (node == null) {
            return;
        }
        if (node.hasValue()) {
            keys.add(prefix);
        }
        TrieNode<V>[] children = node.getNext();
        for (int i = 0; i < children.length; i++) {
            TrieNode<V> child = children[i];
            char ch = alphabet.toChar(i);
            String currentPrefix = prefix + ch;
            collectKeys(child, currentPrefix, keys);
        }
    }

    private TrieNode<V> getNode(TrieNode<V> node, String key, int keyIndex) {
        if (node == null) {
            return null;
        }
        if (keyIndex == key.length()) {
            return node;
        }

        int charIndex = convertCharToIndex(key, keyIndex);
        TrieNode<V> nextNode = node.next(charIndex);
        keyIndex++;
        return getNode(nextNode, key, keyIndex);
    }
}
