package Exo2;

import java.util.*;

public class Graph {

    Random random = new Random();
    int size;

    static int MAX_LIMIT = 30;

    public int[][] matrix; // Adjacency matrix == matrix d'adjacence

    boolean[] testedElements;

    List<Integer> voidMax = new ArrayList<>();

    ArrayList<Integer> voidTemp = new ArrayList<>();


    public Graph(int[][] matrix) {
        this.matrix = matrix;
        this.size = this.matrix.length;
        //array[i][j] = 1;
    }

    public void addArc(int from, int to) {
        matrix[from][to] = 1;
        matrix[to][from] = 1;
    }

    public void printGraph() {
        System.out.println("Graph: (Adjacency Matrix)");
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(ints[j] + " ");
            }
            System.out.println();
        }
    }


    //Question 1
    public boolean EmptyZone(int[] X) {
        // ZONE VIDE
        for (Integer i : X) {
            for (Integer j : X) {
                if (matrix[i][j] == 1)
                    return false;
            }
        }
        return X.length != 0;
    }

    public boolean IsConnected(List<Integer> list, int s) {
        // on verifie si entre une list de sommet et un sommet y'a pas de lien 
        // si il y'a un lien on retourne false sinon true
        for (int x : list) {
            if (matrix[s][x] == 1) return false;
        }
        return true;
    }

    // Question 2
    public List<Integer> EmptyZoneMaximal() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            if (IsConnected(result, i)) {
                result.add(i);
            }
        }
        return result;
    }

    //Methodes realisé avec un camarade
    public void getAllMaximalVoids(int level) {
        if (level >= size) {
            if (voidTemp.size() > voidMax.size()) {
                voidMax = (List<Integer>) voidTemp.clone();
            }
            return;
        }
        for (int i = 0; i < size; i++) {
            if (!testedElements[i]) {
                testedElements[i] = true;
                if (IsConnected(voidTemp, i)) voidTemp.add(i);
                getAllMaximalVoids(level + 1);
                testedElements[i] = false;
                voidTemp.remove(Integer.valueOf(i));
            }
        }
    }

    // QUESTION 3
    public List<Integer> MaximumEmptyZone() {
        testedElements = new boolean[size];
        getAllMaximalVoids(0);
        return voidMax;
    }


    public void printArc(){
        ArrayList<Integer> list;
        for (int i = 0; i < matrix.length; i++) {
            list = new ArrayList<>();
            for (int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 1){
                    list.add(j);

                }
            }
            System.out.println(i + " -> " + list);

        }
    }





    public static void main(String[] args) {
        int[][] matrix = new int[9][9];

        Graph graph = new Graph(matrix);
        GFGRandomGraph randomGraph = new GFGRandomGraph();

        Graph g;
        long debut, fin;
        for (int i = 5; i < 100; i += 5) {
            g = randomGraph.RandomGraph(i);
            debut = System.nanoTime();
            g.EmptyZoneMaximal();
            fin = System.nanoTime() - debut;
            System.out.println("i : " + i + " durée " + fin);
        }
        graph.addArc(0, 1);
        graph.addArc(0, 5);
        graph.addArc(1, 2);
        graph.addArc(1, 3);
        graph.addArc(1, 7);
        graph.addArc(2, 4);
        graph.addArc(3, 6);
        graph.addArc(4, 6);
        graph.addArc(5, 8);
        graph.addArc(5, 7);
        graph.addArc(6, 7);
        graph.addArc(6, 8);

        int[] X = new int[]{0, 2, 7, 8};



       // System.out.println(Arrays.deepToString(g1.matrix));
        //System.out.println(Arrays.toString(X) + " est une zone vide : " + graph.EmptyZone(X));
        //System.out.println("Zone vide maximale : " + graph.EmptyZoneMaximal());
       // System.out.println("Zone vide maximum complète = " + graph.MaximumEmptyZone());

    }


}

