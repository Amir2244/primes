package JAVAEE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SieveOfAtkin implements PrimeAlgorithm {
    private final int low;
    private final int high;
    private final ArrayList<Integer> primes;

    public SieveOfAtkin(int low, int high) {
        this.low = low;
        this.high = high;
        this.primes = new ArrayList<>();
    }

    private void fillPrime(ArrayList<Integer> primes) {
        boolean[] isPrime = new boolean[high + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= high; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= Math.sqrt(high); j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i * i <= high; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
    }

    @Override
    public List<Integer> findPrimes() {
        ArrayList<Integer> primes = new ArrayList<>();
        fillPrime(primes);

        boolean[] isPrimeInRange = new boolean[high - low + 1];
        Arrays.fill(isPrimeInRange, true);

        for (int prime : primes) {
            int lower = (low / prime);

            if (lower <= 1) {
                lower = prime * 2;
            } else if (low % prime != 0) {
                lower = (lower * prime) + prime;
            } else {
                lower = lower * prime;
            }

            for (int j = lower; j <= high; j += prime) {
                isPrimeInRange[j - low] = false;
            }
        }

        for (int i = low; i <= high; i++) {
            if (isPrimeInRange[i - low]) {
                this.primes.add(i);
            }
        }
        return this.primes;
    }
}