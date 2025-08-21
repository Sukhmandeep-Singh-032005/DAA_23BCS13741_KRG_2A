//Apply the concept of Linked list and write code to Insert and Delete an element at the beginning and atend in Doubly and Circular Linked List.

class Node {
    int data;
    Node prev;
    Node next;
}

class DoublyList {
    private Node head;

    DoublyList() {
        head = null;
    }

    void insertBegin(int val) {
        Node temp = new Node();
        temp.data = val;
        temp.prev = null;
        temp.next = head;
        if (head != null)
            head.prev = temp;
        head = temp;
    }

    void insertEnd(int val) {
        Node temp = new Node();
        temp.data = val;
        temp.next = null;
        if (head == null) {
            temp.prev = null;
            head = temp;
            return;
        }
        Node cur = head;
        while (cur.next != null)
            cur = cur.next;
        cur.next = temp;
        temp.prev = cur;
    }

    void deleteBegin() {
        if (head == null) return;
        Node temp = head;
        head = head.next;
        if (head != null)
            head.prev = null;
        temp = null;
    }

    void deleteEnd() {
        if (head == null) return;
        if (head.next == null) {
            head = null;
            return;
        }
        Node cur = head;
        while (cur.next != null)
            cur = cur.next;
        cur.prev.next = null;
        cur = null;
    }

    void display() {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }
}

public class Experiment4Doubly {
    public static void main(String[] args) {
        System.out.println("Doubly Linked List Output:");
        DoublyList d = new DoublyList();
        d.insertBegin(10);
        d.insertEnd(20);
        d.insertBegin(5);
        System.out.print("After insertions: ");
        d.display();
        d.deleteBegin();
        d.deleteEnd();
        System.out.print("After deletions: ");
        d.display();
    }
}
