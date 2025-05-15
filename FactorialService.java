public class FactorialService {

    public long calculateIterative(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public long calculateRecursive(int n) {
        if (n <= 1) return 1;
        return n * calculateRecursive(n - 1);
    }
}
