import java.util.Arrays;

public class FibonacciExpoMatrix {
    //Suite de fibonnacci : n+2 = n+1 + n
    //n0 = 1, n1 = 1, n2 = 3
    int n;

    public FibonacciExpoMatrix() {

    }


    // Methode multiply reprise d'Internet
    public static long[] multiply(long[][] matrix, long[] vector) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        long[] result = new long[rows];

        for (int row = 0; row < rows; row++) {
            long sum = 0;
            for (int column = 0; column < columns; column++) {
                sum += matrix[row][column]
                        * vector[column];
            }
            result[row] = sum;
        }
        return result;
    }

    static long[][] power(long[][] M, long n) {
        if (n == 1) return M;
        if (n % 2 == 0) {
            return power(multiplyMatrix(M, M), n / 2);
        } else {
            return multiplyMatrix(M, power(multiplyMatrix(M, M), n / 2));
        }
    }


    public static long[][] multiplyMatrix(long[][] firstMatrix, long[][] secondMatrix) {
        int r1 = 2;
        int c2 = 2;
        int c1 = 2;
        long[][] product = new long[r1][c2];
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    product[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }
        return product;
    }


    static public long fib(long n) {
        long[][] A = new long[2][2];
        A[0][0] = 1;
        A[0][1] = 1;
        A[1][0] = 1;
        A[1][1] = 0;

        long[] V = new long[2];
        V[0] = 1;


        if (n == 0) System.out.println(V[1]);
        else if (n == 1) System.out.println(V[0]);

        var a = power(A, n);
        var result = multiply(a, V);
        System.out.println(Arrays.toString(result));

        return result[1];
    }


    public static void main(String[] args) throws InterruptedException {
        //System.out.print("0  1");
        //SuiteRecursive(10);
        long[][] A = new long[2][2];
        A[0][0] = 1;
        A[0][1] = 1;
        A[1][0] = 1;
        A[1][1] = 0;
        long[] V = new long[2];
        V[0] = 1;
        long debut, fin;
        //var Apow2 = multiplyMatrix(A, A);
        for (int i = 0; i < 50; i++) {
           // System.out.printf("int", FibonacciRecursive.fib(i));
           // System.out.println(FibonacciRecursive.fib(i));
            System.out.println(FibonacciIterative.fibo(i));
            System.out.println(FibonacciRecursive.fib(i));
            System.out.println(FibonacciExpoMatrix.fib(i));


        }
    }
}
