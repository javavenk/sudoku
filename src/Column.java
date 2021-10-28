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

    public void updatePossibilites() {
        for(int cellRow = 0;cellRow<Board.NINE;cellRow++) {
            updatePossibilites(cellRow);
        }
    }

    private void updatePossibilites(int cellRow) {
        for(int cellRowInner = 0;cellRowInner<Board.NINE;cellRowInner++) {
            if(cellRow!=cellRowInner) {
                cells[cellRowInner].removePossibility(cells[cellRow].value);
            }
        }
    }
}
