import java.util.Scanner;

public class ScannerHelper {
    private Scanner keyboard = new Scanner(System.in);

    public String readString(String prompt) {
        System.out.print("> " + prompt);
        return keyboard.nextLine();
    }

    public int readInt(String prompt) {
        System.out.print(prompt);
        int number = keyboard.nextInt();
        keyboard.nextLine();
        return number;
    }

    public double readDouble(String prompt) {
        System.out.print(prompt);
        double number = keyboard.nextDouble();
        keyboard.nextLine();
        return number;
    }
}
