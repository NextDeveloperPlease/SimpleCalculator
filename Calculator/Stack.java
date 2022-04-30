public class Stack {
    
    private String[] temp, stack;
    private int currentStackIndex;

    public Stack(String equation) {
        stack = new String[equation.length()];
        currentStackIndex = 0;
    }

    public Stack(String[] equation) {
        stack = new String[equation.length];
        currentStackIndex = 0;
    }

    public void Parenthesis() {
        temp = new String[stack.length];
        int i = 0;
        while (!stack[currentStackIndex - 1].equals("(")) {
            temp[i] = getPreviousStackItem();
            pop();
            i++;
        }
        pop();
    }

    public String[] getTemp() {
        return temp;
    }

    public int getCurrentStackIndex() {
        return currentStackIndex;
    }

    public void setCurrentStackIndex(int currentStackIndex) {
        this.currentStackIndex = currentStackIndex;
    }

    public String getCurrentStackItem() {
        return stack[currentStackIndex];
    }

    public String getPreviousStackItem() {
        return stack[currentStackIndex - 1];
    }

    public void push(String stackItem) {
        stack[currentStackIndex] = stackItem;
        currentStackIndex++; 
    }

    public void pop() {
        currentStackIndex--;
        stack[currentStackIndex] = null;
    }

    public void popCurrent() {
        stack[currentStackIndex] = null;
    }

    public String toString() {
        String results = "";
        for (String stackItem : stack) {
            results += stackItem + "\n";
        }
        return results;
    }
}
