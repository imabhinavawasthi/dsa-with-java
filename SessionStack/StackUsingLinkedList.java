package SessionStack;

public class StackUsingLinkedList {
    static class Node {
        Node(int val) {
            this.val = val;
            this.next = null;
        }
        int val;
        Node next;
    }
    static class StackLinkedList {
        Node head;
        int size;
        StackLinkedList() {
            head = null;
            size = 0;
        }
        public void push(int x) {
            Node newNode = new Node(x);
            newNode.next = head;
            head = newNode;
            size += 1;
        }
        public void pop() {
            if(isEmpty()) {
                System.out.println("Stack is Empty");
                return;
            }
            head = head.next;
            size -= 1;
        }
        public int peek() {
            if(isEmpty()) {
                System.out.println("Stack is Empty");
                return -1;
            }
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
        StackLinkedList st = new StackLinkedList();
        st.push(10);
        st.push(5);
        st.push(7);
        st.push(8);
        st.push(1);
        st.push(2);

        st.pop();

        System.out.println(st.peek()); // O(1)
        System.out.println(st.isEmpty()); // O(1)
        System.out.println(st.size()); // O(1)
    }
}
