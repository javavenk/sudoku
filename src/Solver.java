public class Solver {

    public static Board board = new Board("469700281000280396002096040870600510509800070600000984026000100000020000078500420");
    // ("530000040700400300904003007900180000045000098000000561406008000000900020002740096");
            //("469700281000280396002096040870600510509800070600000984026000100000020000078500420");

    public static void main(String[] args) {
        System.out.println(board.print(new ProblemPrinter()));
        board.solve();
        System.out.println(board.print(new SolutionPrinter()));
        System.out.println(board.print(new PossibilitiesPrinter()));
    }

    private static void allTests() {
        readsInput();
        printsPossibilities();
        printsSolution();
        printRows();
        printColumns();
    }

    public static void readsInput() {
        System.out.println(" PROBLEM ");
        Board board = new Board("111111111222222222333333333444444444123456789123456789123456789123456789999999990");
        System.out.println(board.print(new ProblemPrinter()));
    }

    private static void printsPossibilities() {
        System.out.println(" POSSIBILITIES ");
        Board board = new Board("111111111222222222333333333444444444123456789123456789123456789123456789999999990");
        System.out.println(board.print(new PossibilitiesPrinter()));
    }

    public static void printsSolution() {
        System.out.println(" SOLUTION ");
        Board board = new Board("111111111222222222333333333444444444123456789123456789123456789123456789999999990");
        System.out.println(board.print(new SolutionPrinter()));
    }

    public static void printRows() {
        System.out.println(" PROBLEM BY ROWS ");
        Board board = new Board("111111111222222222333333333444444444123456789123456789123456789123456789999999990");
        board.printByRows();
    }

    public static void printColumns() {
        System.out.println(" PROBLEM BY COLUMNS ");
        Board board = new Board("111111111222222222333333333444444444123456789123456789123456789123456789999999990");
        board.printByColumns();
    }
}
