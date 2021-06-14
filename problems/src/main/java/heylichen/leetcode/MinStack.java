package heylichen.leetcode;

public class MinStack {
    private int[] values;
    private int size;
    private Integer minValue;
    public static final int DEFAULT_CAPACITY = 16;

    public MinStack() {
        values = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    public void push(int val) {
        if (size == values.length) {
            resize();
        }
        values[size++] = val;
        if (minValue == null || val < minValue.intValue()) {
            minValue = val;
        }
    }

    public void pop() {
        if (size == 0) {
            throw new IllegalArgumentException("can not pop from empty stack");
        }
        size--;
        if (size == 0) {
            minValue = null;
        } else{
            int newMin = Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                if (values[i] < newMin) {
                    newMin = values[i];
                }
            }
            this.minValue = newMin;
        }
    }

    public int top() {
        return values[size - 1];
    }

    public int getMin() {
        return minValue;
    }

    private void resize() {
        if (size < values.length) {
            return;
        }
        int[] valuesNew = new int[values.length * 2];
        System.arraycopy(values, 0, valuesNew, 0, values.length);
        this.values = valuesNew;
    }
}
