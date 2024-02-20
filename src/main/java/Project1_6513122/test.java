package Project1_6513122;

import java.util.*;
import java.io.*;

public class test {
    public static void main(String[] args) {
        // Your array of arrays representing edges for an undirected graph
        int[][] edgeData = {
                {0, 1, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 1, 0, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 1, 1, 1}
        };



        // Create and initialize the adjacency list for an undirected graph
        ArrayList<LinkedList<Integer>> adjacencyList = createUndirectedAdjacencyList(edgeData);

        // Print the adjacency list
        System.out.println("Undirected Graph Adjacency List:");
        printAdjacencyList(adjacencyList);
    }

    // Function to create and initialize the adjacency list for an undirected graph
    private static ArrayList<LinkedList<Integer>> createUndirectedAdjacencyList(int[][] edgeData) {
        ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < edgeData.length; i++) {

            for (int j = 0; j < edgeData[i].length; j++) {
                LinkedList<Integer> neighbors = new LinkedList<>();

                if (edgeData[i][j] == 1) {

//                    if(edgeData[i + 1][j] == 1 && i != edgeData[0].length - 1) {
                    if(i + 1 < edgeData[i].length) {
                        if(edgeData[i + 1][j] == 1) neighbors.add(edgeData[i + 1][j]);
                    }
                    if(i - 1 > 0) {
                        if(edgeData[i - 1][j] == 1) neighbors.add(edgeData[i - 1][j]);
                    }
                    if(j - 1 > 0) {
                        if(edgeData[i][j - 1] == 1) neighbors.add(edgeData[i][j-1]);
                    }
                    if(j + 1 < edgeData.length) {
                        if(edgeData[i][j + 1] == 1) neighbors.add(edgeData[i][j+1]);
                    }

                    adjacencyList.add(neighbors);
                }

            }

        }

        return adjacencyList;
    }

    // Function to print the adjacency list
    private static void printAdjacencyList(ArrayList<LinkedList<Integer>> adjacencyList) {
        System.out.println(adjacencyList);
        for (int i = 0; i < adjacencyList.size(); i++) {
            System.out.println("Node " + i + ": " + adjacencyList.get(i));
        }
    }
}
