//Apply the concept of Linked list and write code to Insert and Delete an element at the beginning and atend in Doubly and Circular Linked List.

class Node {
    int data;
    Node next;
}

class CircularList {
    private Node last;

    CircularList() {
        last = null;
    }

    void insertBegin(int val) {
        Node temp = new Node();
        temp.data = val;
        if (last == null) {
            temp.next = temp;
            last = temp;
            return;
        }
        temp.next = last.next;
        last.next = temp;
    }

    void insertEnd(int val) {
        Node temp = new Node();
        temp.data = val;
        if (last == null) {
            temp.next = temp;
            last = temp;
            return;
        }
        temp.next = last.next;
        last.next = temp;
        last = temp;
    }

    void deleteBegin() {
        if (last == null) return;
        Node temp = last.next;
        if (temp == last) {
            last = null;
            return;
        }
        last.next = temp.next;
    }

    void deleteEnd() {
        if (last == null) return;
        Node cur = last.next;
        if (cur == last) {
            last = null;
            return;
        }
        while (cur.next != last) {
            cur = cur.next;
        }
        cur.next = last.next;
        last = cur;
    }

    void display() {
        if (last == null) {
            System.out.println();
            return;
        }
        Node cur = last.next;
        do {
            System.out.print(cur.data + " ");
            cur = cur.next;
        } while (cur != last.next);
        System.out.println();
    }
}

public class Experiment4Circular {
    public static void main(String[] args) {
        System.out.println("Circular Linked List Output:");
        CircularList c = new CircularList();
        c.insertBegin(10);
        c.insertEnd(20);
        c.insertBegin(5);
        System.out.print("After insertions: ");
        c.display();
        c.deleteBegin();
        c.deleteEnd();
        System.out.print("After deletions: ");
        c.display();
    }
}
