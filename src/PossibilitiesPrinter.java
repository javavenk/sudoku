public class PossibilitiesPrinter implements Printer {

    @Override
    public String print(Cell cell) {
        return cell.printPossibilities();
    }
}
