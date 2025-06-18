import java.util.*;
public class sortedarray {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean continueCalc = true;
        while (continueCalc) {
            try {
                List<String> tokens = new ArrayList<>();
                int openBrackets = 0;
                System.out.print("Enter first number or '(': ");
                tokens.add(getToken(input, true));
                if (tokens.get(0).equals("(")) openBrackets++;
                while (true) {
                    System.out.print("Enter operator (+, -, *, /), '(', ')' or '=' to finish, or 'back' to undo: ");
                    String op = input.next();
                    if (op.equals("=")) {
                        if (openBrackets > 0) {
                            System.out.println("You have unclosed brackets. Please close them with ')'.");
                            continue;
                        }
                        String expression = String.join(" ", tokens);
                        try {
                            double result = evaluateExpression(expression);
                            System.out.println("Expression: " + expression + " = " + result);
                            List<Double> numbers = extractNumbers(tokens);
                            Collections.sort(numbers);
                            System.out.println("Sorted numbers: " + numbers);
                            List<Double> even = new ArrayList<>();
                            List<Double> odd = new ArrayList<>();
                            for (double n : numbers) {
                                if (((int)n) % 2 == 0) even.add(n);
                                else odd.add(n);
                            }
                            System.out.println("Even values: " + even);
                            System.out.println("Odd values: " + odd);
                        } catch (Exception e) {
                            System.out.println("Invalid expression: " + e.getMessage());
                        }
                        break;
                    }
                    if (op.equalsIgnoreCase("back")) {
                        if (!tokens.isEmpty()) {
                            String removed = tokens.remove(tokens.size() - 1);
                            if (removed.equals("(")) openBrackets--;
                            else if (removed.equals(")")) openBrackets++;
                            System.out.println("Removed last entry: " + removed);
                        } else {
                            System.out.println("Nothing to undo.");
                        }
                        continue;
                    }
                    if (op.equals("(")) {
                        tokens.add(op);
                        openBrackets++;
                        continue;
                    }
                    if (op.equals(")")) {
                        if (openBrackets <= 0) {
                            System.out.println("No matching opening bracket for ')'. Try again.");
                            continue;
                        }
                        tokens.add(op);
                        openBrackets--;
                        continue;
                    }
                    if (op.length() == 1 && "+-*/".contains(op)) {
                        tokens.add(op);
                        System.out.print("Enter number or '(': ");
                        String next = getToken(input, true);
                        if (next.equals("(")) openBrackets++;
                        tokens.add(next);
                    } else {
                        System.out.println("Invalid operator.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
                input.nextLine();
            }
            System.out.print("Do you want to perform another calculation? (yes/no): ");
            String choice = input.next();
            if (choice.equalsIgnoreCase("no")) {
                continueCalc = false;
                System.out.println("Calculator exited.");
            }
        }

        input.close();
    }
    private static String getToken(Scanner input, boolean allowBracket) {
        while (true) {
            String token = input.next();
            if (allowBracket && token.equals("(")) return token;
            try {
                Double.parseDouble(token);
                return token;
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }
    private static List<Double> extractNumbers(List<String> tokens) {
        List<Double> numbers = new ArrayList<>();
        for (String token : tokens) {
            try {
                numbers.add(Double.parseDouble(token));
            } catch (NumberFormatException ignored) {
            }
        }
        return numbers;
    }
    private static double evaluateExpression(String expr) {
        return evaluateRPN(toRPN(expr));
    }
    private static List<String> toRPN(String expr) {
        String[] tokens = expr.split(" ");
        Stack<String> ops = new Stack<>();
        List<String> output = new ArrayList<>();
        Map<String, Integer> precedence = new HashMap<>();
        precedence.put("+", 1);
        precedence.put("-", 1);
        precedence.put("*", 2);
        precedence.put("/", 2);
        for (String token : tokens) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                output.add(token);
            } else if ("+-*/".contains(token)) {
                while (!ops.isEmpty() && precedence.containsKey(ops.peek()) &&
                        precedence.get(ops.peek()) >= precedence.get(token)) {
                    output.add(ops.pop());
                }
                ops.push(token);
            } else if (token.equals("(")) {
                ops.push(token);
            } else if (token.equals(")")) {
                while (!ops.peek().equals("(")) {
                    output.add(ops.pop());
                }
                ops.pop();
            }
        }
        while (!ops.isEmpty()) {
            output.add(ops.pop());
        }
        return output;
    }
    private static double evaluateRPN(List<String> rpn) {
        Stack<Double> stack = new Stack<>();
        for (String token : rpn) {
            if (token.matches("-?\\d+(\\.\\d+)?")) {
                stack.push(Double.parseDouble(token));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+": stack.push(a + b); break;
                    case "-": stack.push(a - b); break;
                    case "*": stack.push(a * b); break;
                    case "/":
                        if (b == 0) throw new ArithmeticException("Division by zero");
                        stack.push(a / b);
                        break;
                }
            }
        }
        return stack.pop();
    }
}
