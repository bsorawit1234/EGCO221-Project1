package Project1_6513122;

import java.util.ArrayList;
import java.util.LinkedList;

public class RatInMazeDFS {
    public static void run(ArrayList<LinkedList<String>> maze, ArrayList<Integer> r_index, ArrayList<Integer> f_index, ArrayList<String> path, boolean[][] visit) throws MyException {
        int ratNode = r_index.get(0) * maze.get(0).size() + r_index.get(1);
        int foodNode = f_index.get(0) * maze.get(0).size() + f_index.get(1);
        ArrayList<LinkedList<String>> adjacencyList = createMazeAdjacencyList(maze);
//        boolean[][] visited = new boolean[maze.size()][maze.get(0).size()];
        boolean[][] visited = visit;

        if(!findPathDFS(adjacencyList, visited, String.valueOf(ratNode), foodNode, path)) throw new MyException("No solution");
    }

    // Function to create and initialize the adjacency list for the maze
    private static ArrayList<LinkedList<String>> createMazeAdjacencyList(ArrayList<LinkedList<String>> maze) {
        ArrayList<LinkedList<String>> adjacencyList = new ArrayList<>();
        int rows = maze.size();
        int cols = maze.get(0).size();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                LinkedList<String> neighbors = new LinkedList<>();

                if (maze.get(i).get(j).compareToIgnoreCase("1") == 0 | maze.get(i).get(j).compareToIgnoreCase("R") == 0 | maze.get(i).get(j).compareToIgnoreCase("F") == 0) {
                    if ((j < cols - 1 ) && ( maze.get(i).get(j + 1).compareToIgnoreCase("1") == 0 | maze.get(i).get(j + 1).compareToIgnoreCase("R") == 0 | maze.get(i).get(j + 1).compareToIgnoreCase("F") == 0)) {
                        neighbors.add(String.valueOf(i * cols + j + 1)); // Right
                    }
                    if ((j - 1 >= 0) && (maze.get(i).get(j - 1).compareToIgnoreCase("1") == 0 | maze.get(i).get(j - 1).compareToIgnoreCase("R") == 0 | maze.get(i).get(j - 1).compareToIgnoreCase("F") == 0)) {
                        neighbors.add(String.valueOf(i * cols + j - 1)); // Left
                    }
                    if ((i - 1 >= 0) && (maze.get(i - 1).get(j).compareToIgnoreCase("1") == 0 | maze.get(i - 1).get(j).compareToIgnoreCase("R") == 0 | maze.get(i - 1).get(j).compareToIgnoreCase("F") == 0)) {
                        neighbors.add(String.valueOf((i - 1) * cols + j)); // Up
                    }
                    if ((i < rows - 1) && (maze.get(i + 1).get(j).compareToIgnoreCase("1") == 0 || maze.get(i + 1).get(j).compareToIgnoreCase("R") == 0 || maze.get(i + 1).get(j).compareToIgnoreCase("F") == 0)) {
                        neighbors.add(String.valueOf((i + 1) * cols + j)); // Down
                    }

                    adjacencyList.add(neighbors);
                } else {
                    adjacencyList.add(new LinkedList<>()); // Add an empty list for walls (non-1 cells)
                }

            }
        }
        return adjacencyList;
    }

    // Function to perform DFS traversal to find a path for the rat in the maze
    private static boolean findPathDFS(ArrayList<LinkedList<String>> adjacencyList, boolean[][] visited,
                                       String currentNode, int destination, ArrayList<String> path) {
            int currNode = Integer.parseInt(currentNode);
            if (currNode == destination) {
                path.add(currentNode);
                return true; // Path found
            }

            int rows = visited.length;
            int cols = visited[0].length;

            int currentRow = currNode / cols;
            int currentCol = currNode % cols;

            visited[currentRow][currentCol] = true;

            for (String neighbor : adjacencyList.get(currNode)) {
                int neighborRow = Integer.parseInt(neighbor) / cols;
                int neighborCol = Integer.parseInt(neighbor) % cols;

                if (!visited[neighborRow][neighborCol]) {
                    if (findPathDFS(adjacencyList, visited, neighbor, destination, path)) {
                        path.add(0, currentNode); // Add the current node to the path
                        return true; // Path found
                    }
                }
            }
        return false; // Path not found
    }
}