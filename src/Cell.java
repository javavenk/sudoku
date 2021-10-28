import java.util.HashSet;
import java.util.Set;

public class Cell implements RepresentationComponent {
    public int problem, solution, blockRow,blockCol,cellRow, cellCol;
    private Set<Integer> possibilities = new HashSet<Integer>();
    private Board board;

    public Cell(int value, int blockRow, int blockCol, int cellRow, int cellCol, Board board) {
        this.problem = value;
        this.blockRow=blockRow;
        this.blockCol=blockCol;
        this.cellRow = cellRow;
        this.cellCol = cellCol;
        this.board = board;
        if(this.problem ==0) {
            for (int i = 1; i < 10; i++) {
                possibilities.add(i);
            }
        } else {
            this.solution = this.problem;
            this.possibilities.add(this.solution);
        }
    }

    public void removePossibility(int value) {
        possibilities.remove(value);
    }

    public boolean updateUniquePossibility() {
        if(this.solution == 0 && possibilities.size()==1) {
            this.solution = (int) possibilities.toArray()[0];
            this.board.updatePossibilities(blockRow,blockCol,cellRow,cellCol);
            return true;
        }
        return false;
    }

    public String printProblem() {
        return problem ==0?"[ ]":String.valueOf("["+ problem +"]");
    }

    public String printSolution() {
        return solution ==0?"[ ]":String.valueOf("["+ solution +"]");
    }

    public String printPossibilities() {
        StringBuffer output = new StringBuffer();
        String spaces = "";
        if(possibilities.size()<9) {
            spaces = String.format("%1$"+(9-possibilities.size())+"s", "");
        }
        output.append("[");
        for(Integer possibility : possibilities) {
            output.append(possibility);
        }
        output.append(spaces).append("]");
        return output.toString();
    }

    @Override
    public String print(Printer printer) {
        return printer.print(this);
    }

    public boolean solved() {
        return solution!=0;
    }

    public void updateUniquePossibility(int solution) {
        this.possibilities.clear();
        this.possibilities.add(solution);
        this.updateUniquePossibility();
    }

    boolean isAPossibility(int digit){
        return possibilities.contains(digit);
    }
}
