import java.util.*;
public class MultiDSCalculator {
    static final Scanner scanner = new Scanner(System.in);
    static final char[] VALID_OPERATORS = {'+', '-', '*', '/','(',')','='};
    public static void main(String[] args) {
        boolean continueCalc = true;
        while (continueCalc) {
            System.out.println("\nChoose data structure for calculation:");
            System.out.println("1. Array");
            System.out.println("2. LinkedList");
            System.out.println("3. Queue");
            System.out.print("> ");
            int choice = scanner.nextInt();
            InputData inputData = getInputSequential();
            switch (choice) {
                case 1 -> calculateUsingArray(inputData);
                case 2 -> calculateUsingLinkedList(inputData);
                case 3 -> calculateUsingQueue(inputData);
                default -> System.out.println("Invalid choice!");
            }
            System.out.print("\nDo you want to perform another calculation? (yes/no): ");
            continueCalc = scanner.next().equalsIgnoreCase("yes");
        }
        System.out.println("Thanks for using MultiDS Calculator!");
    }
    // Input structure
    static class InputData {
        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
    }
    // Get input numbers/operators sequentially
    static InputData getInputSequential() {
        InputData inputData = new InputData();
        System.out.print("Enter number 1: ");
        inputData.numbers.add(scanner.nextDouble());
        int count = 2;
        while (true) {
            char op = promptOperator();
            inputData.operators.add(op);
            System.out.print("Enter number " + count + ": ");
            inputData.numbers.add(scanner.nextDouble());
            count++;
            System.out.print("Do you want to enter another operation? (yes/no): ");
            String cont = scanner.next();
            if (!cont.equalsIgnoreCase("yes")) {
                break;
            }
        }
        return inputData;
    }
    // Operator prompt and validation
    static char promptOperator() {
        while (true) {
            System.out.print("Enter operator (+, -, *, /,(,),=: ");
            char op = scanner.next().charAt(0);
            for (char valid : VALID_OPERATORS) {
                if (op == valid) return op;
            }
            System.out.println("Invalid operator, try again.");
        }
    }
    // Calculation using Array
    static void calculateUsingArray(InputData data) {
        System.out.println("You selected Array-based calculator.");
        double[] arr = uniqueSortedArray(data.numbers);
        double result = computeResult(arr, data.operators);
        displayNumbersInfo(arr);
        System.out.println("Result using Array: " + result);
    }
    // Calculation using LinkedList
    static void calculateUsingLinkedList(InputData data) {
        System.out.println("You selected LinkedList-based calculator.");
        LinkedList<Double> numList = new LinkedList<>(uniqueSortedList(data.numbers));
        LinkedList<Character> opsList = new LinkedList<>(data.operators);
        double result = numList.removeFirst();
        while (!opsList.isEmpty()) {
            result = applyOperation(result, numList.removeFirst(), opsList.removeFirst());
        }
        displayNumbersInfo(numList.stream().mapToDouble(Double::doubleValue).toArray());
        System.out.println("Result using LinkedList: " + result);
    }
    // Calculation using Queue
    static void calculateUsingQueue(InputData data) {
        System.out.println("You selected Queue-based calculator.");
        Queue<Double> numQueue = new LinkedList<>(uniqueSortedList(data.numbers));
        Queue<Character> opsQueue = new LinkedList<>(data.operators);
        double result = numQueue.poll();
        while (!opsQueue.isEmpty()) {
            result = applyOperation(result, numQueue.poll(), opsQueue.poll());
        }
        displayNumbersInfo(numQueue.stream().mapToDouble(Double::doubleValue).toArray());
        System.out.println("Result using Queue: " + result);
    }
    // Returns a sorted array with unique numbers
    static double[] uniqueSortedArray(List<Double> numbers) {
        Set<Double> uniqueSet = new HashSet<>(numbers);
        List<Double> uniqueList = new ArrayList<>(uniqueSet);
        Collections.sort(uniqueList);
        double[] arr = uniqueList.stream().mapToDouble(Double::doubleValue).toArray();
        return arr;
    }
    // Returns sorted unique list
    static List<Double> uniqueSortedList(List<Double> numbers) {
        Set<Double> uniqueSet = new HashSet<>(numbers);
        List<Double> uniqueList = new ArrayList<>(uniqueSet);
        Collections.sort(uniqueList);
        return uniqueList;
    }
    // Compute result applying operators sequentially
    static double computeResult(double[] numbers, List<Character> operators) {
        double result = numbers[0];
        for (int i = 0; i < operators.size(); i++) {
            result = applyOperation(result, numbers[i + 1], operators.get(i));
        }
        return result;
    }
    // Apply one operation safely
    static double applyOperation(double a, double b, char op) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) {
                    System.out.println("Warning: Division by zero, result set to 0.");
                    yield 0;
                }
                yield a / b;
            }
            default -> throw new IllegalArgumentException("Invalid operator: " + op);
        };
    }
    // Display sorted unique numbers, evens, and odds
    static void displayNumbersInfo(double[] numbers) {
        System.out.println("Sorted Unique Numbers: " + Arrays.toString(numbers));
        List<Double> evens = new ArrayList<>();
        List<Double> odds = new ArrayList<>();
        for (double num : numbers) {
            if (((int) num) % 2 == 0) evens.add(num);
            else odds.add(num);
        }
        System.out.println("Even numbers: " + evens);
        System.out.println("Odd numbers: " + odds);
    }
}