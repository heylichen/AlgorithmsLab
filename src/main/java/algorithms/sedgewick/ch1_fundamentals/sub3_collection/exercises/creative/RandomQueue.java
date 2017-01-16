package algorithms.sedgewick.ch1_fundamentals.sub3_collection.exercises.creative;

import algorithms.sedgewick.ch1_fundamentals.sub3_collection.IQueue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by lc on 2016/4/10.
 * IQueue with randomized dequeue method and a random iterator
 */
public class RandomQueue<T> implements IQueue<T> {
    private T[] elements;
    private static final int DEFAULT_SIZE = 8;
    private int size = 0;
    private Random random = new Random();
    public RandomQueue() {
        elements = (T[]) new Object[DEFAULT_SIZE];
    }


    @Override
    public void enqueue(T item) {
        if (size == elements.length) {
            //resizing
            ensureCapacity();
        }
        elements[size++] = item;
    }
    /**
     *remove and return a random item
     (sample without replacement)
     */
    @Override
    public T dequeue() {
        if (size == 0) {
            throw new IllegalStateException("stack size ==0");
        }
        int index = random.nextInt(size);
        T e = elements[index];
        elements[index] = null;
        //swap
         if(size>1){
             int tailIndex = size-1;
             T eleAtTail = elements[tailIndex];
             elements[tailIndex] = elements[index];
             elements[index] = eleAtTail;
         }

        size--;
        return e;
    }

    public T sample(){
        int index = random.nextInt(size);
        T e = elements[index];
        return e;
    }

    private void ensureCapacity() {
        T[] newElements = (T[]) new Object[elements.length * 2 + 1];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
            elements[i] = null;
        }
        elements = newElements;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int index = 0;

        public ArrayIterator() {
        }

        @Override
        public boolean hasNext() {
            return index <size;
        }

        @Override
        public T next() {
            if (index >= size) {
                throw new ArrayIndexOutOfBoundsException("index:" + index);
            }
            return elements[index++];
        }
    }

    public Iterator<T> randomIterator() {
        T[] eles = Arrays.copyOf(elements,size);
        eles = randomize(eles);
        return new algorithms.sedgewick.ch1_fundamentals.sub3_collection.impl.ArrayIterator(eles,0,eles.length);
    }

    private T[] randomize(T[] elements){
        Random rand =new Random();
        for(int i= size-1; i>0; i--){
            int randIndex = rand.nextInt(i+1);
            if(randIndex!=i){
                T itemAtI = elements[i];
                elements[i] =  elements[randIndex];
                elements[randIndex] = itemAtI;
            }
        }

        return elements;
    }




    public static void main(String[] a){
        RandomQueue<String> randQue = new RandomQueue<>();
        for(int i=0; i<10; i++){
            randQue.enqueue("item"+i);
        }
        Iterator<String> it = randQue.randomIterator();
        System.out.println("----------random iterator");
        while(it.hasNext()){
            System.out.println(it.next());
        }
        System.out.println("----------dequeue");
        while(!randQue.isEmpty()){
            System.out.println(randQue.dequeue());
        }

    }
}
