import java.util.Arrays;

public class FibonacciExpoMatrix {
    //Suite de fibonnacci : n+2 = n+1 + n
    //n0 = 1, n1 = 1, n2 = 3
    static long[][] A = {{1, 1}, {1, 0}};
    static long[] V = {1, 0};


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


        if (n == 0) return V[1];
        else if (n == 1) return V[0];

        var a = power(A, n);
        var result = multiply(a, V);

        return result[1];
    }

}
