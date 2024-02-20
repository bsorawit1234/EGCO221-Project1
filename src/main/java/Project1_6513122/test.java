package Project1_6513122;

import java.util.*;

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
//        ArrayList<LinkedList<Integer>> adjacencyList = createUndirectedAdjacencyList(edgeData);
        ArrayList<LinkedList<String>> adjacencyListC = createUndirectedAdjacencyList(edgeData);

        // Print the adjacency list
        System.out.println("Undirected Graph Adjacency List:");
        printAdjacencyList(adjacencyListC);
    }

    // Function to create and initialize the adjacency list for an undirected graph
    private static ArrayList<LinkedList<String>> createUndirectedAdjacencyList(int[][] edgeData) {
        ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<>();
        ArrayList<LinkedList<String>> adjacencyListC = new ArrayList<>();
        for (int i = 0; i < edgeData.length; i++) { //row

            for (int j = 0; j < edgeData[i].length; j++) { //col
                LinkedList<Integer> neighbors = new LinkedList<>();
                LinkedList<String> neighborsDIR = new LinkedList<>();
                if (edgeData[i][j] == 1) {
//                    if(edgeData[i + 1][j] == 1 && i != edgeData[0].length - 1) {
                    if(j < edgeData[i].length - 1) {
//                        if(edgeData[i][j + 1] == 1) neighbors.add(edgeData[i + 1][j]);
                        if(edgeData[i][j + 1] == 1) neighborsDIR.add("R");
                    }
                    if(j >= 1) {
//                        if(edgeData[i][j - 1] == 1) neighbors.add(edgeData[i - 1][j]);
                        if(edgeData[i][j - 1] == 1) neighborsDIR.add("L");
                    }
                    if(i >= 1) {
//                        if(edgeData[i - 1][j] == 1) neighbors.add(edgeData[i][j-1]);
                        if(edgeData[i - 1][j] == 1) neighborsDIR.add("U");
                    }
                    if(i < edgeData.length - 1) {
//                        if(edgeData[i + 1][j] == 1) neighbors.add(edgeData[i][j+1]);
                        if(edgeData[i + 1][j] == 1) neighborsDIR.add("D");
                    }

//                    adjacencyList.add(neighbors);
                    adjacencyListC.add(neighborsDIR);
                }

            }

        }

        return adjacencyListC;
    }

    // Function to print the adjacency list
    private static void printAdjacencyList(ArrayList<LinkedList<String>> adjacencyList) {
//        System.out.println(adjacencyList);
        for (int i = 0; i < adjacencyList.size(); i++) {
            System.out.println("Node " + i + ": " + adjacencyList.get(i));
        }
    }
}
