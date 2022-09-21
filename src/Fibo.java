import java.util.Arrays;

public class Fibo {
    //Suite de fibonnacci : n+2 = n+1 + n
    //n0 = 1, n1 = 1, n2 = 3
    int n;

    public Fibo(){

    }

    public static int SuiteIterative(int n){
        int res = 0;
        int now = 1;
        int last = 0;
        if(n==0) return 0;
        if(n ==1) return 1;

        for(int i = 2; i <= n; i++){
            res = now + last;
            last = now;
            now = res;
        }
        return res;
    }


    public static void SuiteRecursive(int n){

        int nbr1 = 0;
        int nbr2 = 1;
        int nbr3 = 0;

        if(n>1){
            nbr3 = nbr1+nbr2;
            nbr1=nbr2;
            nbr2=nbr3;
            System.out.print("  "+nbr3);
            SuiteRecursive(n-1);
        }
    }


    /*public static double[] SuiteExpoMatrice(int n){
        double[][] A = new double[2][2];
        A[0][0] = 1;
        A[0][1] = 1;
        A[1][0] = 1;
        A[1][1] = 0;

        double[] V = new double[2];
        V[0] = 1;
        V[1] = 0;

        if(n == 0) System.out.println(V[1]);
        else if(n == 1) System.out.println(V[0]);

        for(int i = 2; i <= n; i++){
            double[] next = multiply(A, V);
            V = next;
        }
        return V;
    }*/

    static boolean isPowerOfTwo(int n)
    {
        if (n == 0)
            return false;

        while (n != 1) {
            if (n % 2 != 0)
                return false;
            n = n / 2;
        }
        return true;
    }
    public static double[] SuiteExpoMatrice(int n){
        double[][] A = new double[2][2];
        A[0][0] = 1;
        A[0][1] = 1;
        A[1][0] = 1;
        A[1][1] = 0;

        double[] V = new double[2];
        V[0] = 1;
        V[1] = 0;

        if(isPowerOfTwo(n)) {

        }

        if(n == 0) System.out.println(V[1]);
        else if(n == 1) System.out.println(V[0]);



        for(int i = 2; i <= n; i++){
            double[] next = multiply(A, V);
            V = next;
        }
        return V;
    }


    // Methode multiply reprise d'Internet
    public static double[] multiply(double[][] matrix, double[] vector) {
        int rows = matrix.length;
        int columns = matrix[0].length;

        double[] result = new double[rows];

        for (int row = 0; row < rows; row++) {
            double sum = 0;
            for (int column = 0; column < columns; column++) {
                sum += matrix[row][column]
                        * vector[column];
            }
            result[row] = sum;
        }
        return result;
    }

    public static void main(String[] args) {
        //System.out.print("0  1");
        //SuiteRecursive(10);

        double[] fibo = SuiteExpoMatrice(8);
        System.out.println(fibo[0]);
    }
}
