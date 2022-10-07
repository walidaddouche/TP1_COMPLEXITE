package Exo2;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph1 {

    public int[][] array; // Adjacency matrix == matrix d'adjacence
    public int[] summits;

    public Graph1(int[][] array, int[] summits) {
        this.array = array;
        //array[i][j] = 1;
        this.summits = summits;
    }

    public void addArc(int from , int to){
        array[from][to] = 1;
        array[to][from] = 1;
    }

    public int[][] getArray() {
        return array;
    }

    public int[] getSummits() {
        return summits;
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

    public int[] maxEmptyZone(){
        Graph1 graph = this;
        ArrayList<Integer> temp1 = new ArrayList<>();
        int[][] array = graph.getArray();
        for(int i:graph.getSummits()){
            ArrayList<Integer> temp2 = new ArrayList<>();
            for(int j:graph.getSummits()){
              // System.out.println(array[i][j] + "     ARRAY");
                if(array[i][j] == 0){
                    if(!temp2.contains(i)) temp2.add(i);
                    if(!temp2.contains(j)) temp2.add(j);
                }
            }
            //System.out.println(temp2 + " TEMP2");
            if(temp1.size() < temp2.size()) temp1 = temp2;
            //System.out.println(temp1 + "          TEMP1");
        }
        int[] res = new int[temp1.size()];
        for (int i = 0; i < temp1.size(); i++) {
            res[i] = temp1.get(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[9][9];
        int[] summits = new int[9];

        Graph1 graph = new Graph1(matrix, summits);

        graph.addArc(0,1);graph.addArc(0,5);

        graph.addArc(1,2);graph.addArc(1,3);graph.addArc(1,7);

        graph.addArc(2,4);

        graph.addArc(3,6);

        graph.addArc(4,6);

        graph.addArc(5,8);graph.addArc(5,7);

        graph.addArc(6,7);graph.addArc(6,8);



        int[] x = {3,8,4};

        //System.out.println(Arrays.deepToString(graph.getArray()));
        //System.out.println(graph.EmptyZone(x));
        System.out.println(Arrays.toString(graph.maxEmptyZone()));
    }
}