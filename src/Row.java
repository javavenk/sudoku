public class Row {
    private Cell [] cells = new Cell[Board.NINE];
    private int rowNum;
    public Row(int rowNum) {
        this.rowNum = rowNum;
    }

    public void setCell(Cell cell, int cellCol) {
        cells[cellCol] = cell;
    }

    public void print() {
        System.out.println();
        for(int i = 0;i<Board.NINE;i++) {
            System.out.print(cells[i].value + " ");
        }
    }
}
