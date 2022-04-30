import java.util.Scanner;

public class Evaluator {

    private String[] equation;
    private Stack stack;
    private int currentEquationIndex;
    // public static void main(String[] args) {
    //     currentEquationIndex = 0;
    //     String str;
    //     Scanner scan = new Scanner(System.in);
    //     System.out.print("Enter equation here: ");
    //     str = scan.nextLine();
    //     scan.close();

    //     equation = new String[str.length()];
    //     stack = new Stack(str);

    //     createStack(str);
    //     moveStackToEquation();

    //     for (int i = 0; i < equation.length; i++) {
    //         System.out.println(equation[i]);
    //     }
    // }

    public Evaluator(String equation) {
        this.currentEquationIndex = 0;
        this.equation = new String[equation.length()];
        this.stack = new Stack(equation);

        createStack(equation);
        moveStackToEquation();
    }

    public String[] getEquation() {
        int i = 0;
        String[] result = new String[currentEquationIndex];
        for (String inputs : equation) {
            result[i] = inputs;
            i++;
        }
        return result;
    }

    private void createStack(String str) {
        Scanner scan = new Scanner(str);
        do {
            equationOrStack(scan.next());
        } while (scan.hasNext());
        scan.close();
    }

    private void moveStackToEquation() {
        while (stack.getCurrentStackIndex() > 0) {
            equation[currentEquationIndex] = stack.getPreviousStackItem();
            stack.pop();
            currentEquationIndex++;
        }
    }

    private void equationOrStack(String input) {
        
        switch (input) {
            case "(":
                stack.push(input);
                break;
            case ")":
                int i = 0;
                stack.Parenthesis();
                String[] temp = new String[stack.getTemp().length];
                temp = stack.getTemp();
                while (temp[i] != null) {
                    equation[currentEquationIndex] = temp[i];
                    i++;
                    currentEquationIndex++;
                }
                break;
            case "^":
                stack.push(input);
                break;
            case "*":
            case "/":
                if (stack.getCurrentStackIndex() == 0 || stack.getPreviousStackItem().equals("(") || stack.getPreviousStackItem().equals("+") || stack.getPreviousStackItem().equals("-")) {
                    stack.push(input);
                } else {
                    while (stack.getCurrentStackIndex() != 0 && (stack.getPreviousStackItem().equals("^") || stack.getPreviousStackItem().equals("/") || stack.getPreviousStackItem().equals("*"))) {
                        equation[currentEquationIndex] = stack.getPreviousStackItem();
                        stack.pop();
                        currentEquationIndex++;
                    }
                    stack.push(input);
                }
                break;
            case "+":
            case "-":
                // if index == 0, or equals "(" push
                // if "^" or "*" or "/", then give equation previous item and pop and push.
                if (stack.getCurrentStackIndex() == 0 || stack.getPreviousStackItem().equals("(")) {
                    stack.push(input);
                } else {
                    while ((stack.getCurrentStackIndex() != 0) && (stack.getPreviousStackItem().equals("^") || stack.getPreviousStackItem().equals("/") || stack.getPreviousStackItem().equals("*") || stack.getPreviousStackItem().equals("+") || stack.getPreviousStackItem().equals("-"))) {
                        equation[currentEquationIndex] = stack.getPreviousStackItem();
                        stack.pop();
                        currentEquationIndex++;
                    }
                    stack.push(input);
                }
                break;
            default:
                equation[currentEquationIndex] = input;
                currentEquationIndex++;
                break;
        }
    }

    public String toString() {
        String result = "";
        for (String input : equation) {
            if (input == null) {

            } else {
                result += "\n" + input;
            }
        }
        return result;
    }
}