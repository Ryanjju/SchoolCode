import java.util.Scanner;

class Potenz {
    private static final String ANSI_RESET = "\u001B[0m"; // Reset color
    private static final String ANSI_RED = "\u001B[31m";   // Red color
    private static final String ANSI_GREEN = "\u001B[32m"; // Green Color

    private static Scanner reader = new Scanner(System.in);
    public static void main(String[] args) {
        int number;
        int exponent; 

        // get values 
        do {
            System.out.println("Please enter a base number gretare or equal zero");
            System.out.print("Input: ");

            while (!reader.hasNextInt()) {
                System.out.println("That' not a number!");
                System.out.print("Input: ");

                reader.next();
            }

            number = reader.nextInt();

        } while (number < 0);

        // if base number is 0 => Result will be 0 same for 1^x = 1
        if (number == 0 || number == 1) {
            System.out.println(ANSI_GREEN + "Result: " + ANSI_RESET + number);
            return;
        }

        // else get the exponent
        do {
            System.out.println("Please enter an exponent number greater or equal zero");
            System.out.print("Input: ");

            while (!reader.hasNext()) {
                System.out.println("That's not a number!");
                System.out.print("Input: ");

                reader.next();
            }

            exponent = reader.nextInt();
        } while (exponent < 0);

        // if expoenent is 0 => result is 1
        if (exponent == 0) {
            System.out.println(ANSI_GREEN + "Result: " + ANSI_RESET + 1);
            return;
        }   

        // 
        calc(number, exponent, number);
    }

    private static void calc(int base, int expoenent, int result) {
        if (expoenent == 1) {
            System.out.println(ANSI_GREEN + "Result: " + ANSI_RESET + result);
            return;
        } 

        calc(base, expoenent - 1, result * base);
    }
}
