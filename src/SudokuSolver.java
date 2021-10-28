import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SudokuSolver {
    static int THREE = 3;
    static int[][][][] board =
            {
                    {{{4,6,9}, {7,0,0}, {2,8,1}}, {{0,0,0}, {2,8, 0}, {3,9,6}}, {{0,0,2}, {0,9,6}, {0, 4,0}}},
                    {{{8,7,0}, {6,0, 0}, {5,1, 0}}, {{5,0,9}, {8, 0, 0}, {0, 7, 0}}, {{6, 0, 0}, {0, 0, 0}, {9,8,4}}},
                    {{{0,2, 6}, {0, 0, 0}, {1, 0, 0}}, {{0, 0, 0}, {0,2, 0}, {0, 0, 0}}, {{0, 7,8}, {5,0, 0}, {4,2,0}}}
            };
            /*{
                    {{{5, 3, 0}, {0, 0, 0}, {0, 4, 0}}, {{7, 0, 0}, {4, 0, 0}, {3, 0, 0}}, {{9, 0, 4}, {0, 0, 3}, {0, 0, 7}}},
                    {{{9, 0, 0}, {1, 8, 0}, {0, 0, 0}}, {{0, 4, 5}, {0, 0, 0}, {0, 9, 8}}, {{0, 0, 0}, {0, 0, 0}, {5, 6, 1}}},
                    {{{4, 0, 6}, {0, 0, 8}, {0, 0, 0}}, {{0, 0, 0}, {9, 0, 0}, {0, 2, 0}}, {{0, 0, 2}, {7, 4, 0}, {0, 9, 6}}}
            };*/

    static List[][][][] possibilities = new List[THREE][THREE][THREE][THREE];

    static boolean[][] solved = new boolean[THREE][THREE];

    static {
        for (int a = 0; a < THREE; a++) {
            for (int b = 0; b < THREE; b++) {
                for (int c = 0; c < THREE; c++) {
                    for (int d = 0; d < THREE; d++) {
                        possibilities[a][b][c][d]= new ArrayList<Integer>();
                        if(board[a][b][c][d]==0) {
                            for (int i = 1; i < 10; i++) {
                                possibilities[a][b][c][d].add(Integer.valueOf(i));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        print();

        for (int a = 0; a < THREE; a++) {
            for (int b = 0; b < THREE; b++) {
                for (int c = 0; c < THREE; c++) {
                    for (int d = 0; d < THREE; d++) {
                        if(board[a][b][c][d]!=0) {
                            updatePossibilities(a,b,c,d);
                        }
                    }
                }
            }
        }

        boolean stateChanged = true;

        while (check() && stateChanged) {

            while (check() && stateChanged) {
                stateChanged = false;
                for (int a = 0; a < THREE; a++) {
                    for (int b = 0; b < THREE; b++) {
                        for (int c = 0; c < THREE; c++) {
                            for (int d = 0; d < THREE; d++) {
                                if (board[a][b][c][d] == 0 && possibilities[a][b][c][d].size() == 1) {
                                    board[a][b][c][d] = (Integer) possibilities[a][b][c][d].get(0);
                                    possibilities[a][b][c][d].remove(0);
                                    updatePossibilities(a, b, c, d);
                                    stateChanged = true;
                                }
                            }
                        }
                    }
                }
            }

            while (check() && stateChanged) {
                stateChanged = false;
                for (int a = 0; a < THREE; a++) {
                    for (int b = 0; b < THREE; b++) {
                        for (int c = 0; c < THREE; c++) {
                            for (int d = 0; d < THREE; d++) {
                                if (board[a][b][c][d] != 0) {
                                    boolean found = false;
                                    int h = 0, i = 0;
                                    for (int e = (b + 1) % THREE; e != b && !found; e = (e + 1) % THREE) {
                                        for (int f = (c + 1) % THREE; f != c && !found; f = (f + 1) % THREE) {
                                            for (int g = 0; g < THREE && !found; g++) {
                                                if (board[a][b][c][d] == board[a][e][f][g]) {
                                                    found = true;
                                                    h = THREE - b - e;
                                                    i = THREE - f - c;
                                                }
                                            }
                                        }
                                    }
                                    if (found) {
                                        int z = 0, zc = 0;
                                        boolean replace = true;
                                        for (int j = 0; j < THREE; j++) {
                                            if (board[a][h][i][j] == 0) {
                                                zc++;
                                                z = j;
                                            }
                                            if (board[a][h][i][j] == board[a][b][c][d]) {
                                                replace = false;
                                            }
                                        }
                                        if (zc == 1 && replace) {
                                            board[a][h][i][z] = board[a][b][c][d];
                                            stateChanged = true;
                                            markSolved(a, h);
                                        }
                                    }

                                    found = false;
                                    h = 0;
                                    i = 0;

                                    for (int e = (a + 1) % THREE; e != a && !found; e = (e + 1) % THREE) {
                                        for (int f = (d + 1) % THREE; f != d && !found; f = (f + 1) % THREE) {
                                            for (int g = 0; g < THREE && !found; g++) {
                                                if (board[a][b][c][d] == board[e][b][g][f]) {
                                                    found = true;
                                                    h = THREE - a - e;
                                                    i = THREE - f - d;
                                                }
                                            }
                                        }
                                    }
                                    if (found) {
                                        int z = 0, zc = 0;
                                        boolean replace = true;
                                        for (int j = 0; j < THREE; j++) {
                                            if (board[h][b][j][i] == 0) {
                                                zc++;
                                                z = j;
                                            }
                                            if (board[h][b][j][i] == board[a][b][c][d]) {
                                                replace = false;
                                            }
                                        }
                                        if (zc == 1 && replace) {
                                            board[h][b][z][i] = board[a][b][c][d];
                                            stateChanged = true;
                                            markSolved(h, b);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("===== SOLUTION =====");
        print();
    }

    private static void updatePossibilities(int A, int B, int C, int D) {
        for (int c = 0; c < THREE; c++) {
            for (int d = 0; d < THREE; d++) {
                if(board[A][B][c][d]==0) {
                    int index = possibilities[A][B][c][d].indexOf(board[A][B][C][D]);
                    if(index!=-1) {
                        possibilities[A][B][c][d].remove(index);
                    }
                }
            }
        }
        for (int b = 0; b < THREE; b++) {
            for (int d = 0; d < THREE; d++) {
                if(board[A][b][C][d]==0) {
                    int index = possibilities[A][b][C][d].indexOf(board[A][B][C][D]);
                    if(index!=-1) {
                        possibilities[A][b][C][d].remove(index);
                    }
                }
            }
        }
        for (int a = 0; a < THREE; a++) {
            for (int c = 0; c < THREE; c++) {
                if(board[a][B][c][D]==0) {
                    int index = possibilities[a][B][c][D].indexOf(board[A][B][C][D]);
                    if(index!=-1) {
                        possibilities[a][B][c][D].remove(index);
                    }
                }
            }
        }
    }

    private static void markSolved(int a, int g) {
        int sum = 0;
        for (int c = 0; c < THREE; c++) {
            for (int d = 0; d < THREE; d++) {
                sum += board[a][g][c][d];
            }
        }
        if (sum == 45) {
            solved[a][g] = true;
        }
    }

    static boolean check() {
        for (int i = 0; i < THREE; i++) {
            for (int j = 0; j < THREE; j++) {
                if (!solved[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    static void print() {
        for (int a = 0; a < THREE; a++) {
            for (int b = 0; b < THREE; b++) {
                for (int c = 0; c < THREE; c++) {
                    for (int d = 0; d < THREE; d++) {
                        System.out.print(board[a][c][b][d] + " ");
                    }
                    System.out.print("  ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}