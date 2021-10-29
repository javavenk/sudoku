import java.util.Scanner;

public class Solver {

    //public static Board board = new Board("469700281000280396002096040870600510509800070600000984026000100000020000078500420");
    public static String exampleBoardString = "111111111222222222333333333444000000000555000000000666123000000000456000000000789";
    public static Board exampleBoard = new Board(exampleBoardString);

    public static void main(String[] args) {

        System.out.print("\nEXAMPLE LAYOUT\n" + exampleBoard.print(new ProblemPrinter()));
        System.out.print("NUMERICAL SEQUENCE for the above board : "+exampleBoardString);
        System.out.print("\n\nType the NUMERICAL SEQUENCE for your SUDOKU in as shown in above example, use 0 for unknown values, and press <ENTER>. \n\n\nYOUR INPUT : ");

        Scanner reader = new Scanner(System.in);
        String puzzle = reader.nextLine();
        reader.close();

        if(puzzle ==null || puzzle.length()!=81 || !puzzle.matches("\\d+")) {
            System.out.println("INVALID BOARD LAYOUT, EXITING...");
            System.exit(0);
        }

        Board board = new Board(puzzle);

        System.out.println("\nPROBLEM\n" + board.print(new ProblemPrinter()));
        board.solve();
        if(board.solved()) {
            System.out.println("\nSOLUTION\n" + board.print(new SolutionPrinter()));
        }
        else {
            System.out.println("\nSTUCK AT\n" + board.print(new PossibilitiesPrinter()));
        }
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
