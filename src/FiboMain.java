public class FiboMain {
    public static void main(String[] args) throws InterruptedException {
        long debut, fin, duration;

        for (long i = 0; i < 95; i += 1) {
            debut = System.nanoTime();
            FibonacciRecursive.fib(i);
            fin = System.nanoTime();
            duration = (fin - debut);
            System.out.format("%d : %s   \n", i, duration);
        }
       /* for (long i = 0; i < 90; i+=2) {
            debut = System.nanoTime();
            result = FibonacciIterative.fib(i);
            fin = System.nanoTime() ;
            System.out.println("fib(" +i +") = " + result + " avec une durée de " + (fin - debut) );
        }

        */
        /*
        for (long i = 0; i < 90; i+=2) {
            debut = System.nanoTime();
            result = FibonacciRecursive.fib(i);
            fin = System.nanoTime() ;
            System.out.println("fib(" +i +") = " + result + " avec une durée de " + (fin - debut) );
        }

         */
    }
}
