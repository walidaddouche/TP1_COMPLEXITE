public class SuiteFibonacciMethod2{

    static int nbr1=0;
    static int nbr2=1;
    static int nbr3=0;

    static void displayFibonacci(int c){
        if(c > 0){
            nbr3 = nbr1 + nbr2;
            nbr1 = nbr2;
            nbr2 = nbr3;
            System.out.print(" " + nbr3);
            displayFibonacci(c-1);
        }
    }

    public static void main(String args[]){
        int c = 11;
        //print 0 and 1
        System.out.print(nbr1+" "+nbr2);
        //deduire 2 car 0 et 1 sont deja affiches
        displayFibonacci(c-1);
    }

}