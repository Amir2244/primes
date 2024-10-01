package JAVAEE;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PrimeCalculator {
    private final int low;
    private final int high;
    private final int numThreads;
    private final ExecutorService executor;

    public PrimeCalculator(int low, int high, int numThreads) {
        this.low = low;
        this.high = high;
        this.numThreads = numThreads;
        this.executor = Executors.newFixedThreadPool(numThreads);
    }

    public List<Integer> calculatePrimes() throws InterruptedException, ExecutionException {
        List<Future<List<Integer>>> futures = new ArrayList<>();
        int rangePerThread = (high - low + 1) / numThreads;

        // splitting the range and assign to Callable workers
        for (int i = 0; i < numThreads; i++) {
            int threadLow = low + i * rangePerThread;
            int threadHigh = (i == numThreads - 1) ? high : threadLow + rangePerThread - 1;
            // you can change the algorithm in this section
            PrimeWorkerCallable worker = new PrimeWorkerCallable( new SieveOfAtkin(threadLow, threadHigh));
            futures.add(executor.submit(worker));
        }

        // collect results from each worker
        List<Integer> allPrimes = new ArrayList<>();
        for (Future<List<Integer>> future : futures) {
            allPrimes.addAll(future.get());  // get() waits for the thread to finish
        }

        executor.shutdown();  //  shut down the executor after tasks are done
        return allPrimes;
    }
}