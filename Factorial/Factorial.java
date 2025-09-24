import java.math.BigInteger;
import java.util.Scanner;

class Factorial {
    private static final String ANSI_RESET = "\u001B[0m"; // Reset color
    private static final String ANSI_RED = "\u001B[31m";   // Red color
    private static final String ANSI_GREEN = "\u001B[32m"; // Green Color

    private static Scanner reader = new Scanner(System.in); // Scanner (user input)

    /**
     * Entry point of the program.
     * <p>
     * Prompts the user to enter a non-negative integer and then choose how the factorial
     * should be calculated:
     * </p>
     * <ul>
     *   <li>Option 0: Recursive (limited to {@code <= 11})</li>
     *   <li>Option 1: Prime factorization (efficient for large numbers)</li>
     *   <li>Option 2: Iterative (limited to {@code <= 11})</li>
     * </ul>
     *
     * @param args command-line arguments (not used)
    */
    public static void main(String[] args) {
        int input;
        int choose;

        // get number for factorial calculation
        do {
            System.out.println("Please enter a number greater or equal zero.");
            System.out.print("Input: ");

            // validate input as long as input is no int
            while (!reader.hasNextInt()) {
                System.out.println("That's not a number!");
                System.out.print("Input: ");
                reader.next();
            }

            input = reader.nextInt();

        } while (input < 0); // just repeat if number was negative

        
        // input is a number greater or equal zero
        // check if input is 0 or 1 => result is 1
        if (input <= 1) {
            System.out.println(ANSI_GREEN + "Result: " + ANSI_RESET + input + "! = 1");
            return; 
        }


        // let the user choose how the factorial will be calculated 
        do {
            System.out.println("Please choose a option how you want to calculate the factorial:\n  0) Recursive (only till 12!) \n  1) Prime Factorization (faster with huge numbers) \n  2) Iterative");
            System.out.print("Input: ");

            // validate input 
            while (!reader.hasNextInt()) {
                System.out.println("Thats not a valid option!");
                System.out.print("Input: ");
                reader.next();
            }

            choose = reader.nextInt();
        } while (choose != 0 && choose != 1 && choose != 2); 

        // Action depending on choice
        switch (choose) {
            case 0:
                recursiveFactorial(input, 1);
                break;

            case 1:
                factorialByPrimeFactorization(input);
                break;

            case 2:
                iterativeFactorial(input);
                break;

            default:
                break;
        }
    }

    /**
     * Computes the factorial recursively.
     * <p>
     * Warning: Only valid for numbers {@code <= 11}, otherwise integer overflow occurs.
     * </p>
     *
     * @param input_number the current number to multiply with the intermediate result
     * @param res          the intermediate result of the previous calculations
     */
    private static void recursiveFactorial(int input_number, int res) {
        if (input_number > 11 ) {
            System.err.println(ANSI_RED + "Error: " + ANSI_RESET + "Due to overflow this methode can only calculate factorials under 12");
            return;
        }

        // print and break if input number is 0
        if (input_number == 0) {
            System.out.println(ANSI_GREEN + "Result: " + ANSI_RESET + res);
            return;
        } else {
            recursiveFactorial(input_number - 1, res * input_number);
        }
    }


    /**
     * Computes the factorial iteratively.
     * <p>
     * Warning: Only valid for numbers {@code <= 11}, otherwise integer overflow occurs.
     * </p>
     *
     * @param input_number the number whose factorial should be computed
     */
    private static void iterativeFactorial(int input_number) {
        int result = 1;

        // check if number is small enough to avoid integer overflow
        if (input_number > 11) {
            System.err.println(ANSI_RED + "Error: " + ANSI_RESET + "Due to overflow this methode can only calculate factorials under 12");
            return;
        }

        for (int i = input_number; i > 0; i--) {
            result = result * i;
        }

        System.out.println(ANSI_GREEN + "Result: " + ANSI_RESET + result);
    }


    /**
     * Generates all prime numbers up to {@code max} using the Sieve of Eratosthenes.
     *
     * @param max the maximum possible prime number
     * @return an array containing all prime numbers {@code <= max}
     */
    private static int[] sieveOfEratosthenes(int max) {
        boolean[] isComposite  = new boolean[max + 1];
        int count = 0;

        // Mark none primes 
        for (int i = 2; i * i <= max; i++) {
            if (!isComposite[i]) {
                for (int j = i * i; j <= max; j += i) {
                    isComposite[j] = true;
                }
            }
        }

        // Count primes
        for (int i = 2; i <= max; i++) {
            if (!isComposite[i]) count++;
        }

        // Fill primes into array
        int[] primes = new int[count]; 
        int index = 0;
        for (int i = 2; i <= max; i++) {
            if (!isComposite[i]) {
                primes[index++] = i;
            }
        }

        return primes;
    }


    /**
     * Computes the exponent of a prime number {@code p} in the prime factorization of {@code n!}.
     * <p>
     * Uses Legendre's formula:
     * <pre>
     * e_p = floor(n/p) + floor(n/p^2) + floor(n/p^3) + ...
     * </pre>
     * </p>
     *
     * @param input_number the number {@code n} whose factorial is considered
     * @param p            the prime number whose exponent in {@code n!} is to be computed
     * @return the exponent of {@code p} in the prime factorization of {@code n!}
     */
    private static int primeExponentInFactorial(int input_number, int p) {
        // compute exponent of prime p in n!:
        // e_p = floor(n/p) + floor(n/p^2) + floor(n/p^3) + ...
        int exponent = 0;
        long power = p;
        while (power <= input_number) {
            exponent += input_number / power;
            power *= p;
        }

        return exponent;
    }


    /**
     * Computes the factorial of a number {@code n} using prime factorization.
     * <p>
     * For each prime number, its exponent in {@code n!} is computed using Legendre's formula,
     * and the result is built by multiplying the primes raised to their respective exponents.
     * </p>
     * <p>
     * This method can handle very large values of {@code n} because it uses
     * {@link java.math.BigInteger BigInteger}.
     * </p>
     *
     * @param n the non-negative integer whose factorial is to be computed
     */
    private static void factorialByPrimeFactorization(int n) {
        int[] primes = sieveOfEratosthenes(n);
        BigInteger result = BigInteger.ONE;
    
        for (int p : primes) {
            int exp = primeExponentInFactorial(n, p);
            result = result.multiply(BigInteger.valueOf(p).pow(exp));
        }
        
        System.out.println(ANSI_GREEN + "Result: " + ANSI_RESET + n + "! = " + result);
    }
}
