package SessionLinkedList;

class Node {
    Node(int val) {
        this.val = val;
        this.next = null;
    }
    int val;
    CircularNode next;
}

public class LinkedListBasics {
    public static void printLinkedList(CircularNode head) {
        CircularNode temp = head;
        while(temp!=null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        CircularNode a = new CircularNode(10);
        CircularNode b = new CircularNode(4);
        CircularNode c = new CircularNode(7);
        a.next = b;
        b.next = c;
        CircularNode head = a;

        System.out.print("Initial LL: ");
        printLinkedList(head);

        // insert at the beginning
        CircularNode newCircularNode = new CircularNode(2);
        newCircularNode.next = head;
        head = newCircularNode;

        System.out.print("After inserting 2 at the beginning: ");
        printLinkedList(head);

        // insert at the end
        newCircularNode = new CircularNode(9);
        CircularNode last = head;
        while(last.next!=null) {
            last = last.next;
        }
        last.next = newCircularNode;

        System.out.print("After inserting 9 at the end: ");
        printLinkedList(head);

        // insert after kth node
        int k = 3;
        newCircularNode = new CircularNode(5);
        CircularNode temp = head;
        for(int i=0;i<k-1;i++) {
            temp = temp.next;
        }
        newCircularNode.next = temp.next;
        temp.next = newCircularNode;

        System.out.print("After inserting 5 after 3rd node: ");
        printLinkedList(head);

        // delete the first node
        head = head.next;

        System.out.print("After deleting first node: ");
        printLinkedList(head);

        // delete the last node
        temp = head;
        while(temp.next.next!=null) {
            temp = temp.next;
        }
        temp.next = null;

        System.out.print("After deleting last node: ");
        printLinkedList(head);

        // delete the kth node
        k = 3;
        temp = head;
        for(int i=0;i<k-2;i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;

        System.out.print("After deleting kth node: ");
        printLinkedList(head);
    }
}
