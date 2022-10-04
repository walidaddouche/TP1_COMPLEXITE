import java.io.FileWriter;
import java.io.IOException;

public class FibonacciIterative {
    static long fib(long n) {
        long fib = 0;
        long a = 1;
        for(long i=0; i<n; i++) {
            long temp = fib;
            fib = fib + a;
            a = temp;
        }
        return fib;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(fib(50));


    }

}
