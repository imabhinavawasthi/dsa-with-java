package SessionStack;

import java.util.*;

public class StackBasics {
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(10); // O(1)
        st.push(2);
        st.push(5);
        st.push(19);

        st.pop(); // O(1)

        System.out.println(st);
        System.out.println(st.peek()); // O(1)
        System.out.println(st.isEmpty()); // O(1)
        System.out.println(st.size()); // O(1)

        while(!st.isEmpty()) {
            System.out.print(st.peek() + " ");
            st.pop();
        }
    }
}
