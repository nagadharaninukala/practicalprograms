import java.util.Scanner;
public class ArrayQueue {
    private int[] queue;
    private int front, rear, capacity;
    public ArrayQueue(int size) {
        queue = new int[size];
        front = 0;
        rear = 0;
        capacity = size;
    }
    public boolean isEmpty() {
        return front == rear;
    }
    public boolean isFull() {
        return rear == capacity;
    }
    public void enqueue(int item) {
        if (isFull()) {
            System.out.println("Queue is full!");
            return;
        }
        queue[rear++] = item;
    }
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        int removed = queue[front];
        for (int i = 0; i < rear - 1; i++) {
            queue[i] = queue[i + 1];
        }
        rear--;
        return removed;
    }
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.print("Queue: ");
        for (int i = front; i < rear; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter queue size: ");
        int size = sc.nextInt();
        ArrayQueue q = new ArrayQueue(size);
        while (true) {
            System.out.println("1.Enqueue  2.Dequeue  3.Display  4.Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter element to enqueue: ");
                    int elem = sc.nextInt();
                    q.enqueue(elem);
                    break;
                case 2:
                    q.dequeue();
                    break;
                case 3:
                    q.display();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}