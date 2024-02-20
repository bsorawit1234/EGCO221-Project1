package Project1_6513122;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean checkFile = false, win = false;
        String path = "src/main/java/Project1_6513122/";
        String fileName = "maize_2.txt";

        while(!checkFile) {
            try {
                Scanner scanFile = new Scanner(new File(path + fileName));
                checkFile = true;
                ArrayList<ArrayList<String>> AL = new ArrayList<>();
                int col_size = 0, row_size = 0;
                ArrayList<Integer> r_index = new ArrayList<>(); // [row, col]
                ArrayList<ArrayList<Integer>> f_index = new ArrayList<>(); // [ [row, col] ] or [ [row, col], [row, col] ]

                for(int i = 0; scanFile.hasNext(); i++) {
                    String line = scanFile.nextLine();
                    String []col = line.split(",");
                    ArrayList<String> column = new ArrayList<>();

                    for(int j = 0; j < col.length; j++) {
                        String c = col[j].trim();
                        if(c.equals("R")) {
                            r_index.add(i);
                            r_index.add(j);
                        } else if(c.equals("F")) {
                            ArrayList<Integer> af = new ArrayList<>();
                            af.add(i);
                            af.add(j);
                            f_index.add(af);
                        }
                        column.add(c);
                    }
                    AL.add(column);
                    col_size = column.size();
                    row_size++;
                }

                printAL(AL, col_size, row_size);

                int round = 0;
                while(!win) {
                    System.out.printf("\nUser input %2d >> Enter move (U = up, D = down, L = left, R = right, A = auto)\n", ++round);
                    String input = scan.nextLine().trim();
                    int y = r_index.get(0), x = r_index.get(1);
                    if(input.equals("U") || input.equals("u")) {
                        if(y-1 >= 0 && (AL.get(y-1).get(x).equals("1") || AL.get(y-1).get(x).equals("F"))) {
                            if(AL.get(y-1).get(x).equals("F")) System.out.println("++++ Find Food ++++");
                            AL.get(y).set(x, "1");
                            r_index.set(0, y - 1);
                            AL.get(r_index.get(0)).set(r_index.get(1), "R");
                            printAL(AL, col_size, row_size);
                        } else {
                            System.out.println("Cannot move");
                        }
                    }
                    else if(input.equals("D") || input.equals("d")) {
                        if(y + 1 >= 0 && (AL.get(y+1).get(x).equals("1") || AL.get(y+1).get(x).equals("F"))) {
                            if(AL.get(y+1).get(x).equals("F")) System.out.println("++++ Find Food ++++");
                            AL.get(y).set(x, "1");
                            r_index.set(0, y + 1);
                            AL.get(r_index.get(0)).set(r_index.get(1), "R");
                            printAL(AL, col_size, row_size);
                        } else {
                            System.out.println("Cannot move");
                        }
                    } else if(input.equals("L") || input.equals("l")) {
                        if(x - 1 >= 0 && (AL.get(y).get(x-1).equals("1") || AL.get(y).get(x-1).equals("F"))) {
                            if(AL.get(y).get(x-1).equals("F")) System.out.println("++++ Find Food ++++");
                            AL.get(y).set(x, "1");
                            r_index.set(1, x - 1);
                            AL.get(r_index.get(0)).set(r_index.get(1), "R");
                            printAL(AL, col_size, row_size);
                        } else {
                            System.out.println("Cannot move");
                        }
                    } else if(input.equals("R") || input.equals("r")) {
                        if(x + 1 >= 0 && (AL.get(y).get(x+1).equals("1") || AL.get(y).get(x+1).equals("F"))) {
                            if(AL.get(y).get(x+1).equals("F")) System.out.println("++++ Find Food ++++");
                            AL.get(y).set(x, "1");
                            r_index.set(1, x + 1);
                            AL.get(r_index.get(0)).set(r_index.get(1), "R");
                            printAL(AL, col_size, row_size);
                        } else {
                            System.out.println("Cannot move");
                        }
                    }

                    for(int f = 0; f < f_index.size(); f++) {
                        if(f_index.get(f).get(0) == r_index.get(0) && f_index.get(f).get(1) == r_index.get(1)) {
                            f_index.remove(f);
                        }
                    }

                    if(f_index.isEmpty()) win = true;

                }
            } catch (FileNotFoundException e) {
                System.out.println(e);
                System.out.println("New file name =");
                fileName = scan.nextLine().trim();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private static void printAL(ArrayList<ArrayList<String>> AL, int col_size, int row_size) {
        for(int row = 0; row < row_size; row++) {
            if(row == 0) {
                System.out.printf("%-7s", " ");
                for (int col = 0; col < col_size; col++) {
                    System.out.printf("%-8s", "col_" + col);
                }
                System.out.println();
            }
            System.out.printf("%-9s", "row_"+row);
            for(int col = 0; col < col_size; col++) {
                System.out.printf("%-8s", AL.get(row).get(col));
            }
            System.out.println();
        }
    }
}
