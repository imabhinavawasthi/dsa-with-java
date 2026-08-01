package SessionQueue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DequeueBasics {
    public static void main(String[] args) {
        Deque<Integer> dq = new ArrayDeque<>();

        dq.offerFirst(10);
        dq.offerFirst(3);
        dq.offerLast(7);

        dq.pollLast();

        System.out.println(dq.peekFirst());
        System.out.println(dq.peekLast());
        System.out.println(dq.isEmpty());
        System.out.println(dq.size());
    }
}
