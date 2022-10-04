
public class fibo {


    /**
     * fonction qui calcule le n-iéme terme de la suite fibonacci d'une manière itérative
     *
     * @param n le terme a calculer
     * @return le n-ième terme de la suite fibonacci
     */
    public static int fibonacci_ite(int n) {
        int fn_1 = 0;
        int fn = 1;
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else {
            for (int i = 1; i < n; i++) {
                int temp = fn;
                fn = fn + fn_1;
                fn_1 = temp;
            }
            return fn;
        }
    }

    /**
     * fonction qui calcule le n-iéme terme de la suite fibonacci d'une manière récursive
     *
     * @param n le terme a calculer
     * @return le n-ième terme de la suite fibonacci
     */
    public static int fibonacci_recu(int n) {
        if (n == 0) return 0;
        else if (n == 1) {
            return 1;
        } else {
            return fibonacci_recu(n - 1) + fibonacci_recu(n - 2);
        }
    }

    /**
     * fonction qui calcule le n-iéme terme de la suite fibonacci en utilisant l'exponentiation de matrice
     *
     * @param n le terme a calculer
     * @return le n-ième terme de la suite fibonacci
     */
    public static int fibonacci_matrix(int n) {
        if (n == 0) return 0;
        else {
            int f[][] = {{1, 1}, {1, 0}};
            power_matrix(f, n - 1);
            return f[0][0];
        }
    }


    /**
     * calculer la puissance n de la matrice m
     *
     * @param m la matrice
     * @param n la puissance
     */
    public static void power_matrix(int[][] m, int n) {
        if (n != 0 && n != 1) {
            power_matrix(m, n / 2);
            mult_matrix(m, m);
            if (n % 2 != 0) {
                int f[][] = {{1, 1}, {1, 0}};
                mult_matrix(m, f);
            }
        }
    }

    /**
     * multiplier deux matrices m1 et m2
     *
     * @param m1 première matrice
     * @param m2 deuxième matrice
     */
    public static void mult_matrix(int[][] m1, int[][] m2) {

        int a = m1[0][0] * m2[0][0] + m1[1][0] * m2[0][1];
        int b = m1[0][0] * m2[1][0] + m1[1][0] * m2[1][1];
        int c = m1[0][1] * m2[0][0] + m1[1][1] * m2[0][1];
        int d = m1[0][1] * m2[1][0] + m1[1][1] * m2[1][1];

        m1[0][0] = a;
        m1[1][0] = b;
        m1[0][1] = c;
        m1[1][1] = d;

    }

    public static void main(String[] args) {
        long start, end, duree;
    }
}
        /*for(int i=0; i<=40; i++) {
            start = System.nanoTime();
            System.out.println("f" + i + " " +fibonacci_ite(i));
            end = System.nanoTime();
            duree = (end - start) / 1_000_000;
            System.out.println("Durée ite " + i + " = " + (long) duree + " ms");
        }

        for(int i=0; i<=45; i++) {
            start = System.nanoTime();
            System.out.println("f" + i + " " +fibonacci_recu(i));
            end = System.nanoTime();
            duree = (end - start) / 1_000_000;
            System.out.println("Durée recu " + i + " = " + (long) duree + " ms");
        }
        start = System.nanoTime();
        System.out.println("f" + 0 + " " +fibonacci_recu(0));
        end = System.nanoTime();
        duree = (end - start) / 1_000_000;
        System.out.println("Durée recu " + 0 + " = " + (long) duree + " ms");

        start = System.nanoTime();
        System.out.println("f" + 0 + " " +fibonacci_recu(0));
        end = System.nanoTime();
        duree = (end - start) / 1_000_000;
        System.out.println("Durée recu " + 0 + " = " + (long) duree + " ms");

        long start2 = System.nanoTime();
        for(int i=0; i<=100; i++) {
            System.out.println("f" + i + " " +fibonacci_matrix(i));
        }
        long end2 = System.nanoTime();
        long duree2 = (end2 - start2) / 1_000_000;
        System.out.println("Durée matrix = " + (long) duree2 + " ms");
    }

}

         */
