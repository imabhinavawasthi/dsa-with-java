package SessionStack;

import java.util.Stack;

public class ReverseStack {

    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(1);
        st.push(2);
        st.push(3);
        st.push(4);
        System.out.println("Initial Stack: " + st);

        Stack<Integer> st2 = new Stack<>();

        while(!st.isEmpty()) {
            st2.push(st.peek());
            st.pop();
        }

        System.out.println("Reversed Stack: " + st2);
    }
}
