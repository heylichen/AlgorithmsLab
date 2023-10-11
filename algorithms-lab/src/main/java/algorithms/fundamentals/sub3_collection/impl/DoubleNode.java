package algorithms.fundamentals.sub3_collection.impl;

public class DoubleNode<T> {
    private T item;
    private DoubleNode previous;
    private DoubleNode next;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public void insertBefore(DoubleNode<T> e){
        next = e;
        if(e!=null){
            e.previous = this;
        }
    }

    public void appendTo(DoubleNode<T> e){
        this.previous = e;
        if(e!=null){
            e.setNext(this);
        }
    }

    public DoubleNode getPrevious() {
        return previous;
    }

    public void setPrevious(DoubleNode previous) {
        this.previous = previous;
    }

    public DoubleNode getNext() {
        return next;
    }

    public void setNext(DoubleNode next) {
        this.next = next;
    }

    public DoubleNode() {
    }

    public DoubleNode(T item, DoubleNode previous, DoubleNode next) {

        this.item = item;
        this.previous = previous;
        this.next = next;
    }
}