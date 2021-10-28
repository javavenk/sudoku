public class Solver {

    public static Board board = new Board("000000000000000000000000000000000000123456789123456789123456789123456789999999999");

    public static void main(String[] args) {
        System.out.println(board.print(new SolutionPrinter()));
        //board.updatePossibilities();
    }

    private static void allTests() {
        readsInput();
        printsPossibilities();
        printRows();
        printColumns();
    }

    public static void readsInput() {
        System.out.println(" PROBLEM ");
        Board board = new Board("111111111222222222333333333444444444123456789123456789123456789123456789999999990");
        System.out.println(board.print(new ProblemPrinter()));
    }

    private static void printsPossibilities() {
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
