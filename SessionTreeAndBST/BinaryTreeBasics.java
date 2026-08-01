package SessionTreeAndBST;

class Node {
    Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
    int val;
    Node left, right;
}

public class BinaryTreeBasics {
    public static void main(String[] args) {
        Node root = new Node(9);
        Node a = new Node(6);
        Node b = new Node(1);
        root.left = a;
        root.right = b;

        System.out.println(root.left.val);
    }
}
