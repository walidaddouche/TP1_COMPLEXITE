public class FibonacciIterative {
    static public long fibo(long n) {
        if(n <= 1) {
            return n;
        }
        long fib = 1;
        long prevFib = 1;

        for(long i=2; i<n; i++) {
            long temp = fib;
            fib += prevFib;
            prevFib = temp;
        }
        return fib;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 90 ; i++) {
            //System.out.println("i : " + i ) ;
            //System.out.println(FibonacciRecursive.fib(i) == FibonacciExpoMatrix.fib(i));

        }

    }

}
