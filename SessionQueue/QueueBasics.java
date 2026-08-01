package SessionQueue;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueBasics {
    public static void main(String[] args) {
        Queue<Integer> q = new ArrayDeque<>();

        // add
        q.offer(100);
        q.offer(15);
        q.offer(50);

        q.poll();

        System.out.println(q);
        System.out.println(q.peek());
        System.out.println(q.size());
        System.out.println(q.isEmpty());
    }
}
