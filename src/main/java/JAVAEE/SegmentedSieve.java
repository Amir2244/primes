package JAVAEE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SegmentedSieve implements PrimeAlgorithm {
    private final int low;
    private final int high;

    public SegmentedSieve(int low, int high) {
        this.low = low;
        this.high = high;
    }

    @Override
    public List<Integer> findPrimes() {
        List<Integer> primes = new ArrayList<>();
        int sqrt = (int) Math.sqrt(high);

        List<Integer> smallPrimes = sieveOfEratosthenes(sqrt);
        for (int prime : smallPrimes) {
            if (prime >= low) {
                primes.add(prime);
            }
        }

        int segmentSize = Math.min(sqrt, 1_000_000);
        boolean[] segment = new boolean[segmentSize];

        for (long start = Math.max(sqrt + 1, low); start <= high; start += segmentSize) {
            sieveSegment(segment, start, smallPrimes, primes);
        }

        return primes;
    }

    private List<Integer> sieveOfEratosthenes(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        for (int i = 2; i <= limit; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    private void sieveSegment(boolean[] segment, long start, List<Integer> smallPrimes, List<Integer> primes) {
        Arrays.fill(segment, true);

        long end = Math.min(start + segment.length - 1, high);

        for (int prime : smallPrimes) {
            long startIdx = Math.max((start + prime - 1) / prime, prime) * prime - start;
            for (long i = startIdx; i < segment.length && start + i <= end; i += prime) {
                segment[(int) i] = false;
            }
        }

        for (int i = 0; i < segment.length && start + i <= end; i++) {
            if (segment[i]) {
                primes.add((int) (start + i));
            }
        }
    }
}