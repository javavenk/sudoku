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
            System.out.print(cells[i].problem + " ");
        }
    }

    public void updatePossibilites() {
        for(int cellCol = 0;cellCol<Board.NINE;cellCol++) {
            updatePossibilites(cellCol);
        }
    }

    private void updatePossibilites(int cellCol) {
        for(int cellColInner = 0;cellColInner<Board.NINE;cellColInner++) {
            if(cellCol!=cellColInner) {
                cells[cellColInner].removePossibility(cells[cellCol].solution);
            }
        }
    }

    public boolean basicSwordFish(int digit) {
        int count = 0, chosenColumn = -1;
        for(int cellCol=0;cellCol<Board.NINE;cellCol++) {
            if(!cells[cellCol].solved() && cells[cellCol].isAPossibility(digit)) {
                count++;
                chosenColumn = cellCol;
            }
        }
        if(count==1) {
            cells[chosenColumn].updateUniquePossibility(digit);
            return true;
        }
        return false;
    }
}
