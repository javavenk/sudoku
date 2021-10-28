public class Column {
    private Cell [] cells = new Cell[Board.NINE];
    private int colNum;
    public Column(int colNum) {
        this.colNum = colNum;
    }

    public void setCell(Cell cell, int cellRow) {
        cells[cellRow] = cell;
    }

    public void print(int rowNum) {
        System.out.print(cells[rowNum].value+ " ");
    }
}
