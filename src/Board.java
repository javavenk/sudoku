public class Board {
    public static int THREE = 3, NINE = 9;
    private static Block blocks[][] = new Block[THREE][THREE];
    private static Row rows[] = new Row[NINE];
    private static Column columns[] = new Column[NINE];

    public Board(String puzzle) {
        Cell cell;
        for(int i = 0;i<NINE;i++) {
            this.rows[i] = new Row(i);
            this.columns[i] = new Column(i);
        }
        for(int i = 0;i<THREE;i++) {
            for(int j = 0;j<THREE;j++) {
                this.blocks[i][j] = new Block(i, j);
                for(int k=0;k<THREE;k++) {
                    for(int l=0;l<THREE;l++) {
                        cell = new Cell(puzzle.charAt(i*27+j*9+k*3+l)-'0', i, j, k, l, this);
                        this.blocks[i][j].setCell(cell,k,l);
                        this.rows[i*3+k].setCell(cell,j*3+l);
                        this.columns[j*3+l].setCell(cell,i*3+k);
                    }
                }
            }
        }
    }

    public void updatePossibilities(int blockRow, int blockCol, int cellRow, int cellCol) {
        blocks[blockRow][blockCol].updatePossibilities(cellRow,cellCol);
    }

    public boolean solved() {
        boolean solved = true;
        for(int boardRow = 0;boardRow<THREE;boardRow++) {
            for(int boardCol=0;boardCol<THREE;boardCol++) {
                solved = solved && blocks[boardRow][boardCol].solved();
            }
        }
        return solved;
    }

    public void print(Printable printable) {
        System.out.println();
        for(int boardRow = 0;boardRow<THREE;boardRow++) {
            for(int blockRow=0;blockRow<THREE;blockRow++) {
                for(int boardCol=0;boardCol<THREE;boardCol++) {
                    blocks[boardRow][boardCol].print(blockRow,printable);
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public void updatePossibilities() {
        for(int boardRow = 0;boardRow<THREE;boardRow++) {
            for(int boardCol=0;boardCol<THREE;boardCol++) {
                blocks[boardRow][boardCol].updatePossibilites();
            }
        }
    }

    public void printByRows() {
        for(int i = 0; i<NINE; i++) {
            rows[i].print();
        }
        System.out.println();
    }

    public void printByColumns() {
        for(int i = 0; i<NINE; i++) {
            System.out.println();
            for(int j = 0; j<NINE; j++) {
                columns[j].print(i);
            }
        }
    }
}
