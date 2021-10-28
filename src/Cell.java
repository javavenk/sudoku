import java.util.HashSet;
import java.util.Set;

public class Cell implements RepresentationComponent {
    private int value, blockRow,blockCol,cellRow, cellCol;
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

    public void printSolution() {
        System.out.print(value);
    }

    public void printPossibilities() {
        System.out.print("[");
        for(Integer possibility : possibilities) {
            System.out.print(possibility + " ");
        }
        System.out.print("]");
    }

    @Override
    public void print(Printable printable) {
        printable.print(this);
    }

    public boolean solved() {
        return possibilities.size()==1;
    }
}
