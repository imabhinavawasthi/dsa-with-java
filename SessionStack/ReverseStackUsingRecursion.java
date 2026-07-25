package SessionStack;

import java.util.Stack;

public class ReverseStackUsingRecursion {
    public static void insert_at_bottom(Stack<Integer> st, int x) {
        if(st.isEmpty()) {
            st.push(x);
            return;
        }
        int top = st.pop();
        insert_at_bottom(st,x);
        st.push(top);
    }
    public static void reverse_stack(Stack<Integer> st) {
        if(st.size()<=1) {
            return;
        }
        int top = st.pop();
        reverse_stack(st);
        insert_at_bottom(st,top);
    }
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);
        System.out.println("Initial Stack: " + st);

        reverse_stack(st);

        System.out.println("Reversed Stack: " + st);
    }
}
