package SessionLinkedList;

class Node {
    Node(int val) {
        this.val = val;
        this.next = null;
    }
    int val;
    Node next;
}

public class LinkedListBasics {
    public static void printLinkedList(Node head) {
        Node temp = head;
        while(temp!=null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Node a = new Node(10);
        Node b = new Node(4);
        Node c = new Node(7);
        a.next = b;
        b.next = c;
        Node head = a;

        System.out.print("Initial LL: ");
        printLinkedList(head);

        // insert at the beginning
        Node newNode = new Node(2);
        newNode.next = head;
        head = newNode;

        System.out.print("After inserting 2 at the beginning: ");
        printLinkedList(head);

        // insert at the end
        newNode = new Node(9);
        Node last = head;
        while(last.next!=null) {
            last = last.next;
        }
        last.next = newNode;

        System.out.print("After inserting 9 at the end: ");
        printLinkedList(head);

        // insert after kth node
        int k = 3;
        newNode = new Node(5);
        Node temp = head;
        for(int i=0;i<k-1;i++) {
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;

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
