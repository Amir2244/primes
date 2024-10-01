package JAVAEE;

import java.util.List;
import java.util.concurrent.Callable;

public class PrimeWorkerCallable implements Callable<List<Integer>> {
    private final PrimeAlgorithm algorithm;

    public PrimeWorkerCallable(PrimeAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public List<Integer> call() {
        return algorithm.findPrimes();
    }
}

