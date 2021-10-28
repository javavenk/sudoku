public class Block {
    private Cell [][] cells = new Cell[Board.THREE][Board.THREE];
    private int blockRow,blockCol;

    public void setCell(Cell cell, int cellRow, int cellCol) {
        cells[cellRow][cellCol] = cell;
    }

    public Block(int blockRow, int blockCol) {
        this.blockRow = blockRow;
        this.blockCol = blockCol;
    }

    public void updatePossibilities(int cellRow, int cellCol) {
        for (int row = 0; row < Board.THREE; row++) {
            for (int col = 0; col < Board.THREE; col++) {
                if(row!=cellRow || col!=cellCol) {
                    cells[row][col].removePossibility(cells[cellRow][cellCol].solution);
                }
            }
        }
    }

    public String print(int row, Printer printer) {
        StringBuffer output = new StringBuffer();
        for(int col = 0;col<Board.THREE;col++) {
            output.append(cells[row][col].print(printer));
            output.append(" ");
        }
        return output.toString();
    }

    public void updatePossibilites() {
        for (int row = 0; row < Board.THREE; row++) {
            for (int col = 0; col < Board.THREE; col++) {
                updatePossibilities(row,col);
            }
        }
    }

    public boolean solved() {
        boolean solved = true;
        for (int row = 0; row < Board.THREE; row++) {
            for (int col = 0; col < Board.THREE; col++) {
                solved = solved && cells[row][col].solved();
            }
        }
        return solved;
    }

    public boolean updateUniquePossibilities() {
        boolean blockStateChanged = false, cellStateChanged;
        for (int row = 0; row < Board.THREE; row++) {
            for (int col = 0; col < Board.THREE; col++) {
                cellStateChanged = cells[row][col].updateUniquePossibility();
                if(cellStateChanged) {
                    blockStateChanged = true;
                }
            }
        }
        return blockStateChanged;
    }
}
