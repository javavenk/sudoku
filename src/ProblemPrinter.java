public class ProblemPrinter implements Printer {

    @Override
    public String print(Cell cell) {
        return cell.printProblem();
    }
}
