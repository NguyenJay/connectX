package cpsc2150.connectX;

public interface IGameBoard {
    /**
     * Define: number_of_rows: Z
     *         number_of_cols: Z
     * Constraints: 0 < number_of_rows < MaxSize
     *              0 < number_of_cols < MaxSize
     * Initialization Ensures: An empty grid of ' ' chars in a 2D array.
     * */

    public int getNumRows();
    public int getNumColumns();
    public int getNumToWin();
    public boolean checkIfFree(int c);
    public boolean checkForWin(int c);
    public void placeToken(char p, int c);
    public boolean checkHorizWin(BoardPosition pos, char p);
    public boolean checkVertWin(BoardPosition pos, char p);
    public boolean checkDiagWin(BoardPosition pos, char p);
    public char whatsAtPos(BoardPosition pos);
    public boolean isPlayerAtPos(BoardPosition pos, char player);
    public boolean checkTie();
}
