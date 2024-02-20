package Project1_6513122;

import java.util.ArrayList;
import java.util.LinkedList;

public class RatInMazeDFS {
    public static void main(String[] args) {
        // Your array representing the maze
        int[][] maze = {
                {0, 1, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 1, 0, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 1, 1, 1}
        };

        // Create and initialize the adjacency list for the maze
        ArrayList<LinkedList<Integer>> adjacencyList = createMazeAdjacencyList(maze);

        // Print the adjacency list
        System.out.println("Maze Adjacency List:");
        printAdjacencyList(adjacencyList);

        // Find a path for the rat in the maze using DFS
        System.out.println("\nDFS Path for Rat in Maze:");
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        ArrayList<Integer> path = new ArrayList<>();
        findPathDFS(adjacencyList, visited, 15, 24, path);
        System.out.println("Path: " + path);
    }

    // Function to create and initialize the adjacency list for the maze
    private static ArrayList<LinkedList<Integer>> createMazeAdjacencyList(int[][] maze) {
        ArrayList<LinkedList<Integer>> adjacencyList = new ArrayList<>();

        int rows = maze.length;
        int cols = maze[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                LinkedList<Integer> neighbors = new LinkedList<>();

                if (maze[i][j] == 1) {
                    if (j < cols - 1 && maze[i][j + 1] == 1)
                        neighbors.add(i * cols + j + 1); // Right
                    if (j >= 1 && maze[i][j - 1] == 1)
                        neighbors.add(i * cols + j - 1); // Left
                    if (i >= 1 && maze[i - 1][j] == 1)
                        neighbors.add((i - 1) * cols + j); // Up
                    if (i < rows - 1 && maze[i + 1][j] == 1)
                        neighbors.add((i + 1) * cols + j); // Down

                    adjacencyList.add(neighbors);
                } else {
                    adjacencyList.add(new LinkedList<>()); // Add an empty list for walls (non-1 cells)
                }
            }
        }
        return adjacencyList;
    }

    // Function to perform DFS traversal to find a path for the rat in the maze
    private static boolean findPathDFS(ArrayList<LinkedList<Integer>> adjacencyList, boolean[][] visited,
                                       int currentNode, int destination, ArrayList<Integer> path) {
        if (currentNode == destination) {
            path.add(currentNode);
            return true; // Path found
        }

        int rows = visited.length;
        int cols = visited[0].length;

        int currentRow = currentNode / cols;
        int currentCol = currentNode % cols;

        visited[currentRow][currentCol] = true;

        for (int neighbor : adjacencyList.get(currentNode)) {
            int neighborRow = neighbor / cols;
            int neighborCol = neighbor % cols;

            if (!visited[neighborRow][neighborCol]) {
                if (findPathDFS(adjacencyList, visited, neighbor, destination, path)) {
                    path.add(0, currentNode); // Add the current node to the path
                    return true; // Path found
                }
            }
        }

        return false; // Path not found
    }

    // Function to print the adjacency list
    private static void printAdjacencyList(ArrayList<LinkedList<Integer>> adjacencyList) {
        for (int i = 0; i < adjacencyList.size(); i++) {
            System.out.println("Node " + i + ": " + adjacencyList.get(i));
        }
    }
}

