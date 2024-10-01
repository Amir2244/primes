package JAVAEE;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        int low = 2;
        int high = 1000_000_00;
        int numThreads = 8;  // change the number of threads depends on cores number
        PrimeCalculator calculator = new PrimeCalculator(low, high, numThreads);
        try {
            //  start time
            long startTime = System.nanoTime();

            List<Integer> primes = calculator.calculatePrimes();

            // end time
            long endTime = System.nanoTime();
            long durationInNano = endTime - startTime;

            double durationInMillis = durationInNano / 1_000_000.0;

            System.out.println("Primes in range " + low + " to " + high + ": " + primes.size());
            System.out.println("Calculation took " + durationInMillis + " milliseconds");
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }

    }
}