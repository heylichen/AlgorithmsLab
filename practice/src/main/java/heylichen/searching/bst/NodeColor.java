package heylichen.searching.bst;

public enum NodeColor {
    RED,BLACK;

    public boolean isRed() {
        return this == RED;
    }

    public boolean isBlack() {
        return this == BLACK;
    }
}
