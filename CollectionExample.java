import java.util.ArrayList;
import java.util.LinkedList;
public class CollectionExample {
    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Cherry");
        System.out.println("ArrayList Elements:");
        for (String fruit : arrayList) {
            System.out.println(fruit);
        }
        System.out.println("Element at index 1 in ArrayList: " + arrayList.get(1));
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(10);
        linkedList.add(20);
        linkedList.addFirst(5); 
        linkedList.addLast(25);   
        System.out.println("\nLinkedList Elements:");
        for (int num : linkedList) {
            System.out.println(num);
        }
        linkedList.removeFirst();
        linkedList.removeLast();
        System.out.println("\nLinkedList after removing first and last:");
        for (int num : linkedList) {
            System.out.println(num);
        }
    }
}