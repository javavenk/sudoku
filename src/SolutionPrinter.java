public class SolutionPrinter implements Printable {

    @Override
    public void print(Cell cell) {
        cell.printPossibilities();
    }
}
