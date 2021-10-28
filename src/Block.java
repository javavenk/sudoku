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
                    cells[row][col].removePossibility(cells[cellRow][cellCol].value);
                }
            }
        }
    }

    public void print(int row,Printable printable) {
        for(int col = 0;col<Board.THREE;col++) {
            cells[row][col].print(printable);
            System.out.print(" ");
        }
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
}
