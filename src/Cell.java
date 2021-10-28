import java.util.HashSet;
import java.util.Set;

public class Cell implements RepresentationComponent {
    public int value, blockRow,blockCol,cellRow, cellCol;
    private Set<Integer> possibilities = new HashSet<Integer>();
    private Board board;

    public Cell(int value, int blockRow, int blockCol, int cellRow, int cellCol, Board board) {
        this.value = value;
        this.blockRow=blockRow;
        this.blockCol=blockCol;
        this.cellRow = cellRow;
        this.cellCol = cellCol;
        this.board = board;
        if(this.value==0) {
            for (int i = 1; i < 10; i++) {
                possibilities.add(i);
            }
        } else {
            possibilities.add(this.value);
        }
    }

    public void removePossibility(int value) {
        possibilities.remove(value);
    }

    public String printProblem() {
        return String.valueOf(value);
    }

    public String printPossibilities() {
        StringBuffer output = new StringBuffer();
        String spaces = "";
        if(possibilities.size()<9) {
            spaces = String.format("%1$"+(9-possibilities.size())+"s", "");
        }
        output.append("[ ").append(spaces);
        for(Integer possibility : possibilities) {
            output.append(possibility).append(" ");
        }
        output.append(spaces).append("]");
        return output.toString();
    }

    @Override
    public String print(Printer printer) {
        return printer.print(this);
    }

    public boolean solved() {
        return possibilities.size()==1;
    }
}
