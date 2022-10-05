package Exo1;

public class FibonacciRecursive {

        public static long fib(long n){
            if(n == 0){
                return 0;
            }
            if(n == 1 || n == 2){
                return 1;
            }
            return fib(n-2) + fib(n-1);
        }
    public static void main(String[] args) {
        System.out.println(fib(50));

    }

}
