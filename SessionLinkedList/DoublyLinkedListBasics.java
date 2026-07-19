package SessionLinkedList;

class DoublyNode {
    DoublyNode(int val) {
        this.val = val;
        this.next = null;
        this.prev = null;
    }
    int val;
    DoublyNode next;
    DoublyNode prev;
}

public class DoublyLinkedListBasics {
    public static void printLinkedList(DoublyNode head) {
        DoublyNode temp = head;
        while(temp!=null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        DoublyNode a = new DoublyNode(10);
        DoublyNode b = new DoublyNode(4);
        DoublyNode c = new DoublyNode(7);
        a.next = b;
        b.prev = a;
        b.next = c;
        c.prev = b;
        DoublyNode head = a;

        System.out.print("Initial LL: ");
        printLinkedList(head);
    }
}
