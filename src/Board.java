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
                        this.rows[i*THREE+k].setCell(cell,j*3+l);
                        this.columns[j*THREE+l].setCell(cell,i*3+k);
                    }
                }
            }
        }
    }

    public void updatePossibilities(int blockRow, int blockCol, int cellRow, int cellCol) {
        blocks[blockRow][blockCol].updatePossibilities(cellRow,cellCol);
        rows[blockRow*THREE+cellRow].updatePossibilites();
        columns[blockCol*THREE+cellCol].updatePossibilites();
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

    public String print(Printer printer) {
        StringBuffer output = new StringBuffer();
        output.append("\n");
        for(int boardRow = 0;boardRow<THREE;boardRow++) {
            for(int blockRow=0;blockRow<THREE;blockRow++) {
                for(int boardCol=0;boardCol<THREE;boardCol++) {
                    output.append(blocks[boardRow][boardCol].print(blockRow, printer));
                    output.append(" ");
                }
                output.append("\n");
            }
            output.append("\n");
        }
        return output.toString();
    }

    public void updatePossibilities() {
        for(int blockRow = 0;blockRow<THREE;blockRow++) {
            for(int blockCol=0;blockCol<THREE;blockCol++) {
                blocks[blockRow][blockCol].updatePossibilites();
            }
        }
        for(int boardRow = 0;boardRow<NINE;boardRow++) {
            rows[boardRow].updatePossibilites();
        }
        for(int boardCol = 0;boardCol<NINE;boardCol++) {
            columns[boardCol].updatePossibilites();
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

    public boolean updateUniquePossibilities() {
        boolean boardStateChanged = false, blockStateChanged;
        for(int boardRow = 0;boardRow<THREE;boardRow++) {
            for(int boardCol=0;boardCol<THREE;boardCol++) {
                blockStateChanged = blocks[boardRow][boardCol].updateUniquePossibilities();
                if(blockStateChanged) {
                    boardStateChanged = true;
                }
            }
        }
        return boardStateChanged;
    }

    public void solve() {
        this.updatePossibilities();
        boolean stateChanged = true;
        while(!this.solved() && stateChanged) {
            stateChanged = this.updateUniquePossibilities();
            if(!stateChanged) {
                stateChanged = updateRowScans();
            }
            if(!stateChanged) {
                stateChanged = updateColumnScans();
            }
        }
    }

    private boolean updateRowScans() {
        Cell first, second, third;
        int cellRowFirst, cellColFirst, cellRowSecond, cellColSecond, cellRowThird;
        boolean stateChanged = false;
        for(int blockRow = 0;blockRow<THREE;blockRow++) {
            for (int blockColFirst = 0; blockColFirst < THREE; blockColFirst++) {
                cellRowFirst=0;
                cellColFirst=0;
                first = blocks[blockRow][blockColFirst].nextSolvedCell(cellRowFirst, cellColFirst);
                while (first != null) {
                    cellRowFirst=first.cellRow;
                    cellColFirst=first.cellCol+1;
                    if(cellColFirst>=THREE) {
                        cellRowFirst++;
                        cellColFirst=0;
                    }
                    for (int blockColSecond = 0; blockColSecond < THREE; blockColSecond++) {
                        if(blockColSecond!=blockColFirst) {
                            cellRowSecond = 0;
                            cellColSecond = 0;
                            second = blocks[blockRow][blockColSecond].nextSolvedCell(cellRowSecond, cellColSecond);
                            while (second != null) {
                                cellRowSecond = second.cellRow;
                                cellColSecond = second.cellCol + 1;
                                if(cellColSecond>=THREE) {
                                    cellRowSecond++;
                                    cellColSecond=0;
                                }
                                if(first.cellRow!=second.cellRow && first.solution == second.solution) {
                                    int blockColThird = 3 - blockColFirst - blockColSecond;
                                    cellRowThird = 3 - first.cellRow - second.cellRow;
                                    third = blocks[blockRow][blockColThird].ifRowNeeds(cellRowThird,first.solution);
                                    if (third != null) {
                                        third.updateUniquePossibility(first.solution);
                                        stateChanged = true;
                                    }
                                }
                                second = blocks[blockRow][blockColSecond].nextSolvedCell(cellRowSecond, cellColSecond);
                            }
                        }
                    }
                    first = blocks[blockRow][blockColFirst].nextSolvedCell(cellRowFirst, cellColFirst);
                }
            }
        }
        return stateChanged;
    }

    private boolean updateColumnScans() {
        Cell first, second, third;
        int cellRowFirst, cellColFirst, cellRowSecond, cellColSecond, cellColThird;
        boolean stateChanged = false;
        for(int blockCol = 0;blockCol<THREE;blockCol++) {
            for (int blockRowFirst = 0; blockRowFirst < THREE; blockRowFirst++) {
                cellRowFirst=0;
                cellColFirst=0;
                first = blocks[blockRowFirst][blockCol].nextSolvedCell(cellRowFirst, cellColFirst);
                while (first != null) {
                    cellRowFirst=first.cellRow;
                    cellColFirst=first.cellCol+1;
                    if(cellColFirst>=THREE) {
                        cellRowFirst++;
                        cellColFirst=0;
                    }
                    for (int blockRowSecond = 0; blockRowSecond < THREE; blockRowSecond++) {
                        if(blockRowSecond!=blockRowFirst) {
                            cellRowSecond = 0;
                            cellColSecond = 0;
                            second = blocks[blockRowSecond][blockCol].nextSolvedCell(cellRowSecond, cellColSecond);
                            while (second != null) {
                                cellRowSecond = second.cellRow;
                                cellColSecond = second.cellCol + 1;
                                if(cellColSecond>=THREE) {
                                    cellRowSecond++;
                                    cellColSecond=0;
                                }
                                if(first.cellCol!=second.cellCol && first.solution == second.solution) {
                                    int blockRowThird = 3 - blockRowFirst - blockRowSecond;
                                    cellColThird = 3 - first.cellCol - second.cellCol;
                                    third = blocks[blockRowThird][blockCol].ifColumnNeeds(cellColThird,first.solution);
                                    if (third != null) {
                                        third.updateUniquePossibility(first.solution);
                                        stateChanged = true;
                                    }
                                }
                                second = blocks[blockRowSecond][blockCol].nextSolvedCell(cellRowSecond, cellColSecond);
                            }
                        }
                    }
                    first = blocks[blockRowFirst][blockCol].nextSolvedCell(cellRowFirst, cellColFirst);
                }
            }
        }
        return stateChanged;
    }
}
