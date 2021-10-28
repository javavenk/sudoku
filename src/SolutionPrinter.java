public class SolutionPrinter implements Printer {

    @Override
    public String print(Cell cell) {
        return cell.printPossibilities();
    }
}
