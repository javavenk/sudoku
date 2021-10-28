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
        System.out.print(cells[rowNum].problem + " ");
    }

    public void updatePossibilites() {
        for(int cellRow = 0;cellRow<Board.NINE;cellRow++) {
            updatePossibilites(cellRow);
        }
    }

    private void updatePossibilites(int cellRow) {
        for(int cellRowInner = 0;cellRowInner<Board.NINE;cellRowInner++) {
            if(cellRow!=cellRowInner) {
                cells[cellRowInner].removePossibility(cells[cellRow].solution);
            }
        }
    }

    public boolean basicSwordFish(int digit) {
        int count = 0, chosenRow = -1;
        for(int cellRow=0;cellRow<Board.NINE;cellRow++) {
            if(!cells[cellRow].solved() && cells[cellRow].isAPossibility(digit)) {
                count++;
                chosenRow = cellRow;
            }
        }
        if(count==1) {
            cells[chosenRow].updateUniquePossibility(digit);
            return true;
        }
        return false;
    }
}
