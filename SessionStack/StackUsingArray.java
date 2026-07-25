package SessionStack;

public class StackUsingArray {
    static class StackArray {
        int[] arr;
        int i;
        int capacity;
        StackArray(int capacity) {
            arr = new int[capacity];
            i = 0;
            this.capacity = capacity;
        }
        public void push(int x) {
            if(isFull()) {
                System.out.println("Stack OverFlow");
                return;
            }
            arr[i] = x;
            i += 1;
        }
        public void pop() {
            if(isEmpty()) {
                System.out.println("Stack is Empty");
                return;
            }
            i-=1;
        }
        public int peek() {
            if(isEmpty()) {
                System.out.println("Stack is Empty");
                return -1;
            }
            return arr[i-1];
        }
        public boolean isEmpty() {
            return i==0;
        }
        public boolean isFull() {
            return i==capacity;
        }
        public int size() {
            return i;
        }
    }
    public static void main(String[] args) {
        StackArray st = new StackArray(5);
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
        System.out.println(st.isFull()); // O(1)
    }
}
