import java.util.Scanner;

public class EvaluatorDriver {
    public static void main(String[] args) {
        String str;
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter equation here: ");
        str = scan.nextLine();
        scan.close();

        Evaluator eval = new Evaluator(str);
        System.out.println(eval);
    }
}