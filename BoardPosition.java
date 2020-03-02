package cpsc2150.connectX;
/** @invariant: row > 0 & column > 0
 * */
public class BoardPosition {
    private int myRow;
    private int myCol;

    /**
     * @Pre: row > 0 & column > 0
     * @Post: row = # of rows for board, column = # of columns for board
     * */
    BoardPosition(int row, int column) {
        myRow = row;
        myCol = column;
    }
    /**
    * @Post: getRow = row*/
    public int getRow() {
        return myRow;
    }
    /**
    * @Post: getColumn = column*/
    public int getColumn() {
        return myCol;
    }
    /**
     *
     * @param obj
     * @return
     */
    public boolean equals(BoardPosition obj) {
        if (this.myCol == obj.myCol && this.myRow == obj.myRow) {
            return true;
        }
        else { return false; }
    }

    /**
     *
     * @return board position.
     */
    @Override
    public String toString () {
        int newRow = myRow - myRow;
        if (newRow < 0) {
            newRow = newRow * -1;
        }

        String output = newRow + "," + myCol;
        return output;
    }
}
