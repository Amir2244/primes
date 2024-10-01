package JAVAEE;

import junit.framework.TestCase;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PrimeCalculatorTest extends TestCase {

    public void testPrimeCalculationLargeRange() throws ExecutionException, InterruptedException {
        int low = 2;
        int high = 1_000_000;
        int expectedPrimes = 78498;

        //  2 threads
        PrimeCalculator calculatorWithTwoThreads = new PrimeCalculator(low, high, 2);
        List<Integer> primesWithTwoThreads = calculatorWithTwoThreads.calculatePrimes();
        assertEquals(expectedPrimes, primesWithTwoThreads.size());

        // 4 threads
        PrimeCalculator calculatorWithFourThreads = new PrimeCalculator(low, high, 4);
        List<Integer> primesWithFourThreads = calculatorWithFourThreads.calculatePrimes();
        assertEquals(expectedPrimes, primesWithFourThreads.size());

        //  6 threads
        PrimeCalculator calculatorWithSixThreads = new PrimeCalculator(low, high, 6);
        List<Integer> primesWithSixThreads = calculatorWithSixThreads.calculatePrimes();
        assertEquals(expectedPrimes, primesWithSixThreads.size());
    }

    public void testPrimeCalculationVeryLargeRange() throws ExecutionException, InterruptedException {
        int low = 2;
        int high = 10_000_000;
        int expectedPrimes = 664579;

        //  2 threads
        PrimeCalculator calculatorWithTwoThreads = new PrimeCalculator(low, high, 2);
        List<Integer> primesWithTwoThreads = calculatorWithTwoThreads.calculatePrimes();
        assertEquals(expectedPrimes, primesWithTwoThreads.size());

        //  4 threads
        PrimeCalculator calculatorWithFourThreads = new PrimeCalculator(low, high, 4);
        List<Integer> primesWithFourThreads = calculatorWithFourThreads.calculatePrimes();
        assertEquals(expectedPrimes, primesWithFourThreads.size());

        //  6 threads
        PrimeCalculator calculatorWithEightThreads = new PrimeCalculator(low, high, 6);
        List<Integer> primesWithEightThreads = calculatorWithEightThreads.calculatePrimes();
        assertEquals(expectedPrimes, primesWithEightThreads.size());
    }

    public void testPrimeCalculationExtremelyLargeRange() throws ExecutionException, InterruptedException {
        int low = 2;
        int high = 1_000_000_000;
        int expectedPrimes = 50_847_534;

        //  4 threads
        PrimeCalculator calculatorWithFourThreads = new PrimeCalculator(low, high, 4);
        List<Integer> primesWithFourThreads = calculatorWithFourThreads.calculatePrimes();
        assertEquals(expectedPrimes, primesWithFourThreads.size());

        //  8 threads
        PrimeCalculator calculatorWithEightThreads = new PrimeCalculator(low, high, 8);
        List<Integer> primesWithEightThreads = calculatorWithEightThreads.calculatePrimes();
        assertEquals(expectedPrimes, primesWithEightThreads.size());

        // 12 threads
        PrimeCalculator calculatorWithTwelveThreads = new PrimeCalculator(low, high, 12);
        List<Integer> primesWithTwelveThreads = calculatorWithTwelveThreads.calculatePrimes();
        assertEquals(expectedPrimes, primesWithTwelveThreads.size());
    }
}
