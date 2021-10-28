
import java.util.*;

//900050700427100000000030000630009040000684000050200019000020000000003856008060002
//006000008132500900000004203007300200000000000008002100801900000004008693900000400
//000000000000010000000000000000005000000000000000000000000060000000070000000000000
public class SudokuSolver {
    static int THREE = 3;
    static int[][][][] board =
            {
                    {{{4,6,9}, {7,0,0}, {2,8,1}}, {{0,0,0}, {2,8,0}, {3,9,6}}, {{0,0,2}, {0,9,6}, {0, 4, 0}}},
                    {{{8,7,0}, {6,0,0}, {5,1,0}}, {{5,0,9}, {8,0,0}, {0, 7, 0}}, {{6, 0, 0}, {0,0,0}, {9,8,4}}},
                    {{{0,2,6}, {0, 0, 0}, {1, 0, 0}}, {{0, 0, 0}, {0, 2, 0}, {0, 0, 0}}, {{0, 7, 8}, {5,0, 0}, {4,2,0}}}
            };

    static List[][][][] possibilities = new List[THREE][THREE][THREE][THREE];

    static boolean[][] solved = new boolean[THREE][THREE];

    static {
        int i = 0;
        Scanner s = new Scanner(System.in);
        String input = s.next();
        char[] digits = input.toCharArray();
        for (int a = 0; a < THREE; a++) {
            for (int b = 0; b < THREE; b++) {
                for (int c = 0; c < THREE; c++) {
                    for (int d = 0; d < THREE; d++) {
                        board[a][b][c][d] = digits[i] - '0';
                        i++;
                    }
                }
            }
        }

        for (int a = 0; a < THREE; a++) {
            for (int b = 0; b < THREE; b++) {
                for (int c = 0; c < THREE; c++) {
                    for (int d = 0; d < THREE; d++) {
                        possibilities[a][b][c][d] = new ArrayList<Integer>();
                        if (board[a][b][c][d] == 0) {
                            for (i = 1; i < 10; i++) {
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
                        if (board[a][b][c][d] != 0) {
                            updatePossibilities(a, b, c, d);
                        }
                    }
                }
            }
        }

        boolean mainStepStateChanged = true, subStepStateChanged;

        while (check() && mainStepStateChanged) {

            mainStepStateChanged = false;

            subStepStateChanged = true;
            while (check() && subStepStateChanged) {
                subStepStateChanged = false;
                for (int a = 0; a < THREE; a++) {
                    for (int b = 0; b < THREE; b++) {
                        for (int c = 0; c < THREE; c++) {
                            for (int d = 0; d < THREE; d++) {
                                if (board[a][b][c][d] == 0 && possibilities[a][b][c][d].size() == 1) {
                                    board[a][b][c][d] = (Integer) possibilities[a][b][c][d].get(0);
                                    possibilities[a][b][c][d].remove(0);
                                    updatePossibilities(a, b, c, d);
                                    mainStepStateChanged = true;
                                    subStepStateChanged = true;
                                }
                            }
                        }
                    }
                }
            }

            subStepStateChanged = true;
            while (check() && subStepStateChanged) {
                subStepStateChanged = false;
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
                                        for (int j = 0; j < THREE; j++) {
                                            if (possibilities[a][h][i][j].contains(board[a][b][c][d])) {
                                                zc++;
                                                z = j;
                                            }
                                        }
                                        if (zc == 1) {
                                            board[a][h][i][z] = board[a][b][c][d];
                                            possibilities[a][h][i][z].clear();
                                            updatePossibilities(a, h, i, z);
                                            mainStepStateChanged = true;
                                            subStepStateChanged = true;
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
                                        for (int j = 0; j < THREE; j++) {
                                            if (possibilities[h][b][j][i].contains(board[a][b][c][d])) {
                                                zc++;
                                                z = j;
                                            }
                                        }
                                        if (zc == 1) {
                                            board[h][b][z][i] = board[a][b][c][d];
                                            possibilities[h][b][z][i].clear();
                                            updatePossibilities(h, b, z, i);
                                            mainStepStateChanged = true;
                                            subStepStateChanged = true;
                                            markSolved(h, b);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            /*if(check() && !mainStepStateChanged) {
                for (int a = 0; a < THREE; a++) {
                    for (int b = 0; b < THREE; b++) {
                        {
                            Map<String, SwordFishSet> sfSets = new HashMap<>();
                            for (int c = 0; c < THREE; c++) {
                                for (int d = 0; d < THREE; d++) {
                                    String possibility = makeKey(possibilities[a][b][c][d]);
                                    if(possibility!=null && possibility.length()>1) {
                                        if (sfSets.get(possibility) == null) {
                                            sfSets.put(possibility, new SwordFishSet(possibility));
                                        }
                                        SwordFishSet sfSet = sfSets.get(possibility);
                                        sfSet.pairs.add(new Pair(c, d));
                                        sfSets.put(possibility, sfSet);
                                    }
                                }
                            }

                            List<SwordFishSet> sfSetList = new ArrayList<>(sfSets.values());
                            for (int i = 0; i < sfSetList.size(); i++) {
                                for (int j = i + 1; j < sfSetList.size(); j++) {
                                    if (sfSetList.get(i).pairs.size() > sfSetList.get(j).pairs.size()) {
                                        SwordFishSet temp = sfSetList.get(i);
                                        sfSetList.set(i, sfSetList.get(j));
                                        sfSetList.set(j, temp);
                                    }
                                }
                            }

                            int chosenSfSetIndex = -1;
                            for (int i = 0; i < sfSetList.size(); i++) {
                                if (sfSetList.get(i).pairs.size() == sfSetList.get(i).possibility.length() && chosenSfSetIndex == -1) {
                                    chosenSfSetIndex = i;
                                    break;
                                }
                            }

                            if (chosenSfSetIndex != -1) {
                                SwordFishSet sfSet = sfSetList.get(chosenSfSetIndex);
                                Pair pair = sfSet.pairs.get(0);
                                List<Integer> impossibilities = possibilities[a][b][pair.r][pair.c];
                                for (int i = 0; i < THREE; i++) {
                                    for (int j = 0; j < THREE; j++) {
                                        if (!sfSet.find(i, j)) {
                                            for (int k = 0; k < impossibilities.size(); k++) {
                                                int index = possibilities[a][b][i][j].indexOf(impossibilities.get(k));
                                                if (index != -1) {
                                                    mainStepStateChanged = true;
                                                    possibilities[a][b][i][j].remove(index);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }*/
        }

        System.out.println("===== SOLUTION =====");
        print();
        printPossibilities();
    }

    private static String makeKey(List list) {
        StringBuffer sb = new StringBuffer();
        for(Object o:list) {
            sb.append((Integer)o);
        }
        return sb.toString();
    }

    static class SwordFishSet {
        public String possibility;
        public List<Pair> pairs = new ArrayList<>();

        public SwordFishSet(String possibility) {
            this.possibility = possibility;
        }

        public boolean find(int i, int j) {
            for (int k = 0; k < pairs.size(); k++) {
                if (pairs.get(k).r == i && pairs.get(k).c == j) {
                    return true;
                }
            }
            return false;
        }
    }

    static class Pair {
        public int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static void printPossibilities() {
        for (int a = 0; a < THREE; a++) {
            for (int b = 0; b < THREE; b++) {
                for (int c = 0; c < THREE; c++) {
                    for (int d = 0; d < THREE; d++) {
                        System.out.print("[");
                        for (int i = 0; i < 9; i++) {
                            if (i < possibilities[a][c][b][d].size()) {
                                System.out.print(possibilities[a][c][b][d].get(i));
                            } else {
                                System.out.print(" ");
                            }
                        }
                        System.out.print("]");
                    }
                    System.out.print("  ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private static void updatePossibilities(int A, int B, int C, int D) {
        for (int c = 0; c < THREE; c++) {
            for (int d = 0; d < THREE; d++) {
                if (board[A][B][c][d] == 0) {
                    int index = possibilities[A][B][c][d].indexOf(board[A][B][C][D]);
                    if (index != -1) {
                        possibilities[A][B][c][d].remove(index);
                    }
                }
            }
        }
        for (int b = 0; b < THREE; b++) {
            for (int d = 0; d < THREE; d++) {
                if (board[A][b][C][d] == 0) {
                    int index = possibilities[A][b][C][d].indexOf(board[A][B][C][D]);
                    if (index != -1) {
                        possibilities[A][b][C][d].remove(index);
                    }
                }
            }
        }
        for (int a = 0; a < THREE; a++) {
            for (int c = 0; c < THREE; c++) {
                if (board[a][B][c][D] == 0) {
                    int index = possibilities[a][B][c][D].indexOf(board[A][B][C][D]);
                    if (index != -1) {
                        possibilities[a][B][c][D].remove(index);
                    }
                }
            }
        }
    }

    private static void markSolved(int a, int b) {
        int sum = 0;
        for (int c = 0; c < THREE; c++) {
            for (int d = 0; d < THREE; d++) {
                sum += board[a][b][c][d];
            }
        }
        if (sum == 45) {
            solved[a][b] = true;
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
