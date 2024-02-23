package Project1_6513122;

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
//        boolean checkFile = false, win = false, play_more = false;
        boolean play_more = false;
        int turn_play = 0; // should lower or equal 4
        String path = "src/main/java/Project1_6513122/";
        String[] fileNameList = {"maize_1.txt", "maize_2.txt", "maize_3.txt", "maize_4.txt"};

        do {
            String fileName = fileNameList[turn_play];
            System.out.printf(">>> maize_%d <<<\n", turn_play+1);
            boolean checkFile = false, win = false;
            while (!checkFile) {
                try {
                    Scanner scanFile = new Scanner(new File(path + fileName));
                    checkFile = true;
                    ArrayList<LinkedList<String>> AL = new ArrayList<>();
                    int col_size = 0, row_size = 0;
                    ArrayList<Integer> r_index = new ArrayList<>(); // [row, col] rat position
                    ArrayList<ArrayList<Integer>> f_index = new ArrayList<>(); // [ [row, col] ] or [ [row, col], [row, col] ] food position

                    for (int i = 0; scanFile.hasNext(); i++) {
                        String line = scanFile.nextLine();
                        String[] col = line.split(",");
                        LinkedList<String> column = new LinkedList<>();

                        for (int j = 0; j < col.length; j++) {
                            String c = col[j].trim();
                            if (c.equals("R")) {
                                r_index.add(i);
                                r_index.add(j);
                            } else if (c.equals("F")) {
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
                    while (!win) {

                        //================================================================================


                        System.out.printf("\nUser input %2d >> Enter move (U = up, D = down, L = left, R = right, A = auto)\n", ++round);
                        String input = scan.nextLine().trim();
                        int y = r_index.get(0), x = r_index.get(1);
                        if (input.equals("U") || input.equals("u")) {
                            if (y - 1 >= 0 && (AL.get(y - 1).get(x).equals("1") || AL.get(y - 1).get(x).equals("F"))) {
                                if (AL.get(y - 1).get(x).equals("F")) System.out.println("++++ Find Food ++++");
                                AL.get(y).set(x, "1");
                                r_index.set(0, y - 1);
                                AL.get(r_index.get(0)).set(r_index.get(1), "R");
                                printAL(AL, col_size, row_size);
                            } else {
                                System.out.println("Cannot move");
                            }
                        } else if (input.equals("D") || input.equals("d")) {
                            if (y + 1 >= 0 && (AL.get(y + 1).get(x).equals("1") || AL.get(y + 1).get(x).equals("F"))) {
                                if (AL.get(y + 1).get(x).equals("F")) System.out.println("++++ Find Food ++++");
                                AL.get(y).set(x, "1");
                                r_index.set(0, y + 1);
                                AL.get(r_index.get(0)).set(r_index.get(1), "R");
                                printAL(AL, col_size, row_size);
                            } else {
                                System.out.println("Cannot move");
                            }
                        } else if (input.equals("L") || input.equals("l")) {
                            if (x - 1 >= 0 && (AL.get(y).get(x - 1).equals("1") || AL.get(y).get(x - 1).equals("F"))) {
                                if (AL.get(y).get(x - 1).equals("F")) System.out.println("++++ Find Food ++++");
                                AL.get(y).set(x, "1");
                                r_index.set(1, x - 1);
                                AL.get(r_index.get(0)).set(r_index.get(1), "R");
                                printAL(AL, col_size, row_size);
                            } else {
                                System.out.println("Cannot move");
                            }
                        } else if (input.equals("R") || input.equals("r")) {
                            if (x + 1 >= 0 && (AL.get(y).get(x + 1).equals("1") || AL.get(y).get(x + 1).equals("F"))) {
                                if (AL.get(y).get(x + 1).equals("F")) System.out.println("++++ Find Food ++++");
                                AL.get(y).set(x, "1");
                                r_index.set(1, x + 1);
                                AL.get(r_index.get(0)).set(r_index.get(1), "R");
                                printAL(AL, col_size, row_size);
                            } else {
                                System.out.println("Cannot move");
                            }
                        } else if (input.equals("A") || input.equals("a")) {
                            int i_idx = r_index.get(0);
                            int j_idx = r_index.get(1);
                            for (int i = 0; i < f_index.size(); i++) {
                                boolean isStart = true;
                                ArrayList<String> Path = new ArrayList<>();

                                try {
                                    System.out.printf("\n=================== Finding Food %d ==================\n", i + 1);
                                    printAL(AL, col_size, row_size);
                                    System.out.println("\nRat path");
                                    RatInMazeDFS.run(AL, r_index, f_index.get(i), Path);
                                } catch (MyException e) {
                                    System.out.printf("No solution for Food %d\n", i + 1);
                                    continue;
                                }

                                for (int j = 0; j < Path.size() - 1; j++) {
                                    if (Integer.parseInt(Path.get(j + 1)) - Integer.parseInt(Path.get(j)) == -1) {
                                        if (isStart) {
                                            System.out.printf("Start -> (row %d, col %d, R)\n", i_idx, j_idx);
                                            isStart = false;
                                        }
                                        AL.get(i_idx).set(j_idx, "1");
                                        j_idx -= 1;
                                        r_index.set(1, j_idx);
                                        AL.get(i_idx).set(j_idx, "R");
                                        if (!(j + 1 == Path.size() - 1))
                                            System.out.printf("Left  -> (row %d, col %d, 1) \n", i_idx, j_idx);
                                        else System.out.printf("Left  -> (row %d, col %d, F)\n\n", i_idx, j_idx);
                                    } else if (Integer.parseInt(Path.get(j + 1)) - Integer.parseInt(Path.get(j)) == 1) {
                                        if (isStart) {
                                            System.out.printf("Start -> (row %d, col %d, R)\n", i_idx, j_idx);
                                            isStart = false;
                                        }
                                        AL.get(i_idx).set(j_idx, "1");
                                        j_idx += 1;
                                        r_index.set(1, j_idx);
                                        AL.get(i_idx).set(j_idx, "R");
                                        if (!(j + 1 == Path.size() - 1))
                                            System.out.printf("Right -> (row %d, col %d, 1)\n", i_idx, j_idx);
                                        else System.out.printf("Right -> (row %d, col %d, F)\n\n", i_idx, j_idx);
                                    } else if (Integer.parseInt(Path.get(j + 1)) - Integer.parseInt(Path.get(j)) == AL.get(0).size()) {
                                        if (isStart) {
                                            System.out.printf("Start -> (row %d, col %d, R)\n", i_idx, j_idx);
                                            isStart = false;
                                        }
                                        AL.get(i_idx).set(j_idx, "1");
                                        i_idx += 1;
                                        r_index.set(0, i_idx);
                                        AL.get(i_idx).set(j_idx, "R");
                                        if (!(j + 1 == Path.size() - 1))
                                            System.out.printf("Down  -> (row %d, col %d, 1)\n", i_idx, j_idx);
                                        else System.out.printf("Down  -> (row %d, col %d, F)\n\n", i_idx, j_idx);
                                    } else if (Integer.parseInt(Path.get(j + 1)) - Integer.parseInt(Path.get(j)) == -AL.get(0).size()) {
                                        if (isStart) {
                                            System.out.printf("Start -> (row %d, col %d, R)\n", i_idx, j_idx);
                                            isStart = false;
                                        }
                                        AL.get(i_idx).set(j_idx, "1");
                                        i_idx -= 1;
                                        r_index.set(0, i_idx);
                                        AL.get(i_idx).set(j_idx, "R");
                                        if (!(j + 1 == Path.size() - 1))
                                            System.out.printf("Up    -> (row %d, col %d, 1)\n", i_idx, j_idx);
                                        else System.out.printf("Up    -> (row %d, col %d, F)\n\n", i_idx, j_idx);
                                    }
                                }
//                            printAL(AL, col_size, row_size);
                            }
                            win = true;
                        }

                        for (int f = 0; f < f_index.size(); f++) {
                            if (f_index.get(f).get(0) == r_index.get(0) && f_index.get(f).get(1) == r_index.get(1)) {
                                f_index.remove(f);
                            }
                        }

                        if (f_index.isEmpty()) win = true;

                    }

                    if(turn_play < (fileNameList.length - 1)) { // Last round does not show
                        System.out.println("Do you want to play more (Y/N) = ");
                        String choice = scan.nextLine().trim();
                        if (choice.equals("y") || choice.equals("Y")) {
                            play_more = true;
                            turn_play++;
                        } else {
                            play_more = false;
                        }
                    }


                } catch (FileNotFoundException e) {
                    System.out.println(e);
                    System.out.println("New file name =");
                    fileName = scan.nextLine().trim();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } while(play_more && turn_play < fileNameList.length);
    }

    private static void printAL(ArrayList<LinkedList<String>> AL, int col_size, int row_size) {
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
