public class DeleteLastNodeDoubly {

    static class DNode {
        int data;
        DNode prev, next;

        DNode(int data) {
            this.data = data;
        }
    }

    static DNode deleteLastNodeDoubly(DNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        DNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.prev.next = null;
        current.prev = null;

        return head;
    }

    static void display(DNode head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DNode head = new DNode(1);
        head.next = new DNode(2);
        head.next.prev = head;
        head.next.next = new DNode(3);
        head.next.next.prev = head.next;

        head = deleteLastNodeDoubly(head);
        display(head);

        DNode single = new DNode(5);
        single = deleteLastNodeDoubly(single);
        display(single);
    }
}