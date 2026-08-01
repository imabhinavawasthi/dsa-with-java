package SessionQueue;

public class QueueUsingLinkedList {
    static class Node {
        Node(int val) {
            this.val = val;
            this.next = null;
        }
        int val;
        Node next;
    }
    static class QueueLinkedList {
        Node head, tail;
        int size;
        QueueLinkedList() {
            head = null;
            tail = null;
            size = 0;
        }
        public void offer(int x) {
            Node newNode = new Node(x);
            if(head==null) {
                head = newNode;
                tail = newNode;
            }
            else {
                tail.next = newNode;
                tail = newNode;
            }
            size += 1;
        }
        public void poll() {
            if(isEmpty()) return;

            head = head.next;
            if(head==null) {
                tail = null;
            }
            size -= 1;
        }
        public int peek() {
            if(isEmpty()) return -1;
            return head.val;
        }
        public boolean isEmpty() {
            return size==0;
        }
        public int size() {
            return size;
        }
    }
    public static void main(String[] args) {
        QueueLinkedList q = new QueueLinkedList();

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
