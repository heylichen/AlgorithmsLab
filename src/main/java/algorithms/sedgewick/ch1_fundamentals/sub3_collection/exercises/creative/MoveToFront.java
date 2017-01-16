package algorithms.sedgewick.ch1_fundamentals.sub3_collection.exercises.creative;

/**
 * Created by lc on 2016/4/13.
 */
public class MoveToFront<T> {
    private T[] elements;
    private int size = 0;
    private static final int DEFAULT_SIZE = 8;


    public MoveToFront() {
        elements =  (T[]) new Object[DEFAULT_SIZE];
    }

    private void add(T ele) {
        int index = size;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(ele)) {
                index = i;
            }
        }

        if (index == size) {
            if( size + 1 > elements.length){
                T[] newElements = (T[]) new Object[elements.length * 2 + 1];
                System.arraycopy(elements, 0, newElements, 1, size);
                elements = newElements;
                elements[0] = ele;
                size++;
                return;
            } else{
                size++;
            }
        }
        shrink(index);
        elements[0] = ele;
    }

    public T head() {
        T ele = elements[0];
        for (int i = 0; i <size-1; i++) {
            elements[i] = elements[i+1];
        }
        size--;
        return ele;
    }

    public boolean isEmpty(){
        return size==0;
    }


    private void shrink(int tailIndex) {
        for (int i = tailIndex; i > 0; i--) {
            elements[i] = elements[i - 1];
        }
    }

    public static void main(String[] args){
        MoveToFront<Integer> mf = new MoveToFront<>();
        for(int i=1; i<=10; i++){
            mf.add(i);
        }
        for(int i=1; i<=3; i++){
            mf.add(i);
        }
        System.out.println(mf.isEmpty());
        while (!mf.isEmpty()){
            System.out.println(mf.head());
        }

    }

}
