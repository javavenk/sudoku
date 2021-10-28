public class ProblemPrinter implements Printable {

    @Override
    public void print(Cell cell) {
        cell.printSolution();
    }
}
