package Exo2;// Create a Random Graph Using
// Random Edge Generation in Java
import java.util.*;
import java.io.*;

public class GFGRandomGraph {

    public int vertices;
    public int edges;

    // Set a maximum limit to the vertices
    final int MAX_LIMIT = 20;

    // A Random instance to generate random values
    Random random = new Random();
    // An adjacency list to represent a graph
    public List<List<Integer> > adjacencyList;

    public int[][] adjacencMatrix;

    // Creating the constructor
    public Graph RandomGraph(int taille)
    {
        // Set a maximum limit for
        // the number of vertices say 20
        this.vertices = taille+1;

        // compute the maximum possible number of edges
        // and randomly choose the number of edges less than
        // or equal to the maximum number of possible edges
        this.edges = random.nextInt(computeMaxEdges(vertices)) + 1;
        this.adjacencMatrix = new int[vertices][vertices];
        // Creating an adjacency list
        // representation for the random graph


        // A for loop to randomly generate edges
        for (int i = 0; i < edges; i++) {
            // randomly select two vertices to
            // create an edge between them
            int v = random.nextInt(vertices);
            int w = random.nextInt(vertices);

            // add an edge between them
            addEdge(v, w);
        }
        return new Graph(adjacencMatrix);
    }

    // Method to compute the maximum number of possible
    // edges for a given number of vertices
    int computeMaxEdges(int numOfVertices)
    {
        // As it is an undirected graph
        // So, for a given number of vertices
        // there can be at-most v*(v-1)/2 number of edges
        return numOfVertices * ((numOfVertices - 1) / 2);
    }

    // Method to add edges between given vertices
    void addEdge(int v, int w)
    {
        // Note: it is an Undirected graph

        // Add w to v's adjacency list
        adjacencMatrix[v][w] = 1;
        adjacencMatrix[w][v] = 1;

    }

    public static void main(String[] args)
    {
        // Create a GFGRandomGraph object
        GFGRandomGraph randomGraph = new GFGRandomGraph();
        Graph graph  = new Graph(randomGraph.adjacencMatrix);
        graph.printArc();
    }
}
