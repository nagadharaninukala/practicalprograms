// Generic class
class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

// Generic method
class Utils {
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}

public class GenericExample {
    public static void main(String[] args) {
        // Using generic class
        Box<Integer> intBox = new Box<>();
        intBox.set(123);
        System.out.println("Integer value: " + intBox.get());

        Box<String> strBox = new Box<>();
        strBox.set("Hello Generics");
        System.out.println("String value: " + strBox.get());

        // Using generic method
        Integer[] intArray = {1, 2, 3, 4};
        String[] strArray = {"A", "B", "C"};

        System.out.print("Integer Array: ");
        Utils.printArray(intArray);

        System.out.print("String Array: ");
        Utils.printArray(strArray);
    }
}
