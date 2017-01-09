import algorithms.ch1fundamentals.Stack;
import algorithms.ch1fundamentals.cp.ArrayRandomBag;
import algorithms.ch1fundamentals.cp.Deque;
import algorithms.ch1fundamentals.cp.Steque;
import algorithms.ch1fundamentals.impl.ResizingArrayStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lc on 2016/4/6.
 */
public class ResizingArrayStackTest {
    private static Logger logger = LoggerFactory.getLogger(ResizingArrayStackTest.class);
    public static void main(String[] args){
        testRandBag();
    }

    public static void testStackPerformance(){
        Stack<String> resizingStack = new ResizingArrayStack<>();
        Stack<String> linkedListStack = new Steque<>();
        stackTest(resizingStack,1000000);
        stackTest(linkedListStack,1000000);
    }
    public static void testSteque(){
        Steque<String> linkedListStack = new Steque<>();
        for(int i=0; i<2; i++){
            for(int j=0; j< 10; j++){
                linkedListStack.push(i+", item-"+j);
            }
            while(!linkedListStack.isEmpty()){
                String item = linkedListStack.pop();
                System.out.println(item);
            }
            System.out.println("-------------turn to queue ");
            for(int j=0; j< 10; j++){
                linkedListStack.enqueue(i+", item-"+j);
            }
            while(!linkedListStack.isEmpty()){
                String item = linkedListStack.pop();
                System.out.println(item);
            }
        }
    }

    public static void testDeque(int count){
        Deque<String> dequeue = new Deque<>();
        System.out.println("------------->push left, pop left, as a stack" );
        for(int i=0; i<count; i++){
            dequeue.pushLeft( "item"+i);
        }
        for(int i=0; i<count; i++){
            System.out.println(dequeue.popLeft());
        }
        System.out.println("-> empty:"+dequeue.isEmpty());
        System.out.println("------------->push right, pop right, as a stack" );
        for(int i=0; i<count; i++){
            dequeue.pushRight( "item"+i);
        }
        for(int i=0; i<count; i++){
            System.out.println(dequeue.popRight());
        }
        System.out.println("-> empty:"+dequeue.isEmpty());
        System.out.println("------------->push left, pop right, as a queue" );

        for(int i=0; i<count; i++){
            dequeue.pushLeft( "item"+i);
        }
        for(int i=0; i<count; i++){
            System.out.println(dequeue.popRight());
        }
        System.out.println("-> empty:"+dequeue.isEmpty());

    }

    public static void testRandBag(){
        ArrayRandomBag<String> bag = new ArrayRandomBag<>();
        for(int i=0; i<100; i++){
            bag.add("item："+i);
        }

        for(String item: bag){
            System.out.println(item);
        }

    }
    public static void stackTest(Stack<String> stack, int testCount){
        long start = System.currentTimeMillis();
        for(int i=0; i<testCount; i++){
            stack.push("item "+i);
        }
        long stringLen = 0l;
        for(String item:stack){
            stringLen += item.length();
        }
        long end = System.currentTimeMillis();

        logger.info("using {} ms, by {}, test {} times, total length:{}", new Object[]{(end - start), stack.getClass(), testCount,stringLen});
    }



}
