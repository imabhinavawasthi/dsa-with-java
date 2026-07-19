package SessionLinkedList;

class CircularNode {
    CircularNode(int val) {
        this.val = val;
        this.next = null;
    }
    int val;
    CircularNode next;
}

public class CircularLinkedListBasics {
    public static void printLinkedList(CircularNode head) {
        CircularNode temp = head;
        do{
            System.out.print(temp.val + " ");
            temp = temp.next;
        } while(temp!=head);
        System.out.println();
    }
    public static void main(String[] args) {
        CircularNode a = new CircularNode(10);
        CircularNode b = new CircularNode(4);
        CircularNode c = new CircularNode(7);
        a.next = b;
        b.next = c;
        c.next = a;
        CircularNode head = a;

        printLinkedList(head);
    }
}
