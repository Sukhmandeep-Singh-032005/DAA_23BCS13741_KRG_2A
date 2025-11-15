class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class LinkedListQueue {
    private Node front;
    private Node rear;
    private int size;

    public void enqueue(int value) {
        Node newNode = new Node(value);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public int dequeue() {
        if (front == null) throw new RuntimeException("Queue is empty");
        int val = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return val;
    }

    public int peek() {
        if (front == null) throw new RuntimeException("Queue is empty");
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public void print() {
        Node temp = front;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedListQueue q = new LinkedListQueue();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.print();
        System.out.println(q.dequeue());
        q.print();
        System.out.println(q.peek());
        System.out.println(q.size());
    }
}
