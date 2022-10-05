package Exo2;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {

    public int[][] array; // Adjacency matrix == matrix d'adjacence

    public Graph(int[][] array) {
        this.array = array;
        //array[i][j] = 1;
    }

    public void addArc(int from , int to){
        array[from][to] = 1;
        array[to][from] = 1;


    }


    public boolean EmptyZone(int[] X) {
        // ZONE VIDE
        for (Integer i : X) {
            for (Integer j : X) {
                if (array[i][j] == 1)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[9][9];

        Graph graph = new Graph(matrix);

        graph.addArc(0,1);graph.addArc(0,5);

        graph.addArc(1,2);graph.addArc(1,3);graph.addArc(1,7);

        graph.addArc(2,4);

        graph.addArc(3,6);

        graph.addArc(4,6);

        graph.addArc(5,8);graph.addArc(5,7);

        graph.addArc(6,7);graph.addArc(6,8);



        int[] x = {3,8,4};

        System.out.println(graph.EmptyZone(x));
    }
}
