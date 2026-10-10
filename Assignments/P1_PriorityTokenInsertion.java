import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList {
    Node head;

    void addFirst(int token) {
        Node newNode = new Node(token);
        newNode.next = head;
        head = newNode;
    }

    void addLast(int token) {
        Node newNode = new Node(token);

        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    void insertAt(int index, int token) {
        if (index == 0) {
            addFirst(token);
            return;
        }

        Node current = head;

        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        Node newNode = new Node(token);
        newNode.next = current.next;
        current.next = newNode;
    }

    void display() {
        Node current = head;

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }
}

public class P1_PriorityTokenInsertion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SinglyLinkedList list = new SinglyLinkedList();

        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            list.addLast(sc.nextInt());
        }

        int operations = sc.nextInt();

        for (int i = 0; i < operations; i++) {
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    list.addFirst(sc.nextInt());
                    break;

                case 2:
                    list.addLast(sc.nextInt());
                    break;

                case 3:
                    int index = sc.nextInt();
                    int token = sc.nextInt();
                    list.insertAt(index, token);
                    break;
            }
        }

        list.display();
        sc.close();
    }
}