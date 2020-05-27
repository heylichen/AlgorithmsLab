package heylichen.searching.bst;

/**
 * @author lichen
 * @date 2020/5/25 16:05
 * @desc
 */

public class NonRecursiveBSTTest extends SymbolTableTests {

    @Override
    protected <K extends Comparable<K>, V> SymbolTable<K, V> createSymbolTable() {
        return new  NonRecursiveBST<>();
    }
}