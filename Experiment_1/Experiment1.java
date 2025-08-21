// Analyze if the stack is empty or full, and if elements are present, return the top element in the stack using templates. Also, perform push and pop operations on the stack.


class Stack {
    int size = 10;
    int arr[] = new int[size];
    int top = -1;

    boolean isEmpty() {
        return top == -1;
    }

    boolean isFull() {
        return top == size - 1;
    }

    void push(int val) {
        if (!isFull()) {
            arr[++top] = val;
        } else {
            System.out.println("Stack is full!");
        }
    }

    void pop() {
        if (!isEmpty()) {
            top--;
        } else {
            System.out.println("Stack is empty!");
        }
    }

    int peek() {
        if (!isEmpty()) {
            return arr[top];
        }
        return -1; 
    }
}

public class Experiment1 {
    public static void main(String[] args) {
        Stack s = new Stack();

        s.push(5);
        s.push(10);
        s.push(15);

        if (!s.isEmpty()) {
            System.out.println("Top: " + s.peek());
        }

        s.pop();

        if (!s.isEmpty()) {
            System.out.println("Top after pop: " + s.peek());
        }
    }
}
