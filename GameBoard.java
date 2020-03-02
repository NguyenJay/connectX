package cpsc2150.connectX;
/** @Invariant: 0 < column < 8
 *  @Invariant: 0 < rows < 7
 *  @Invariant: winning token amount = 4
 *  */
public class GameBoard implements IGameBoard {
    private int rows;
    private int cols;
    private char[][] myBoard;
    private BoardPosition lastToken;
    private char currentPlayer;
    private int amtToWin = 0;
    static final int zero = 0;
    static final int winNum = 4;


    /**
     * @Post: Ensures the creation of an empty grid of characters of determinate size filled with ' '.
     */
    public GameBoard() {
        rows = 6;
        cols = 7;
        myBoard = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                myBoard[i][j] = ' ';
            }
        }
    }

    /**
     * @Pre: c > 0
     * @Post: c = column
     * @Return: true if column is able to accept another token, false otherwise
     * */
    public boolean checkIfFree(int c) {
        for (int i = 0; i < rows; i++) {
            if (myBoard[i][c] == ' '){
                return true;
            }
        }
        return false;
    }
    /**
     * @Pre: c > 0
     * @Post: c = column
     * @Return: Returns true if the last token placed resulted in the player winning the game,
     *          false otherwise
     *          */
    public boolean checkForWin(int c) {
        if (checkHorizWin(lastToken, currentPlayer) == true) {
            return true;
        }
        if (checkVertWin(lastToken, currentPlayer) == true) {
            return true;
        }
        if (checkDiagWin(lastToken, currentPlayer) == true) {
            return true;
        }
        return false;
    }
    /**
     * @Pre: p = 'x' or 'o' & c >= 0
     * @Post: p = player, c = column
     *        Places a token p in column c
     * */
    public void placeToken(char p, int c) {
        for (int i = rows-1; i >= zero; i--) {
            if (myBoard[i][c] == ' ') {
                myBoard[i][c] = p;
                lastToken = new BoardPosition(i, c);
                currentPlayer = p;
                break;
            }
        }
    }
    /**
     * @Pre: pos > 0 char = 'x' or 'o'
     * @Post: pos = position p = player
     * @Return: true if the last token placed resulted in the player winning
     *          by getting 4 in a row horizontally, false otherwise
     *          */
    public boolean checkHorizWin(BoardPosition pos, char p) {
        amtToWin = zero;
        for (int i = 0; i < cols; i++) {
            if (myBoard[pos.getRow()][i] == p) {
                amtToWin++;
            }
            else {
                amtToWin = zero;
            }
            if (amtToWin == winNum) {
                return true;
            }
        }
        return false;
    }
    /**
     * @Pre: pos > 0 char = 'x' or 'o'
     * @Post: pos = position p = player
     * @Return: true if the last token placed resulted in the player winning
     *          by getting 4 in a row vertically, false otherwise
     *          */
    public boolean checkVertWin(BoardPosition pos, char p) {
        amtToWin = zero;
        for (int i = 0; i < rows; i++) {
            if (myBoard[i][pos.getColumn()] == p) {
                amtToWin++;
            }
            else {
                amtToWin = zero;
            }
            if (amtToWin == winNum) {
                return true;
            }
        }
        return false;
    }
    /**
     * @Pre: pos > 0 char = 'x' or 'o'
     * @Post: pos = position p = player
     * @Return: true if the last token placed resulted in the player winning
     *          by getting 4 in a row diagonally, false otherwise
     *          */
    public boolean checkDiagWin(BoardPosition pos, char p) {
        boolean topLeft = true;
        boolean topRight = true;
        boolean botLeft = true;
        boolean botRight = true;
        int newRow;
        int newCol;
        amtToWin = zero;
        //topLeft check
        newRow = pos.getRow()-1;
        newCol = pos.getColumn()-1;
        if (newRow < zero || newCol < zero) {
            topLeft = false;
        }
        else if (myBoard[newRow][newCol] != p) {
            topLeft = false;
        }
        //topRight check
        newRow = pos.getRow()-1;
        newCol = pos.getColumn()+1;
        if (newRow < 0 || newCol >= cols) {
            topRight = false;
        }
        else if (myBoard[newRow][newCol] != p) {
            topRight = false;
        }
        //botLeft check
        newRow = pos.getRow()+1;
        newCol = pos.getColumn()-1;
        if (newRow >= rows || newCol < zero) {
            botLeft = false;
        }
        else if (myBoard[newRow][newCol] != p) {
            botLeft = false;
        }
        //botRight check
        newRow = pos.getRow()+1;
        newCol = pos.getColumn()+1;
        if (newRow >= rows || newCol >= cols) {
            botRight = false;
        }
        else if (myBoard[newRow][newCol] != p) {
            botRight = false;
        }

        // Checks from token diagonally to the top left for win condition.
        amtToWin++;
        int iterator = 1;
        while (topLeft == true) {
            newRow = pos.getRow() - iterator;
            newCol = pos.getColumn() - iterator;
            iterator++;
            if (newRow < 0 || newCol < zero) {
                topLeft = false;
                break;
            }
            else if (myBoard[newRow][newCol] != p) {
                topLeft = false;
            }
            if (myBoard[newRow][newCol] == p) {
                amtToWin++;
            }
            if (amtToWin == winNum){
                return true;
            }
        }
        // Checks from token diagonally to the top right for win condition.
        amtToWin = 1;
        iterator = 1;
        while (topRight == true) {
            newRow = pos.getRow()-iterator;
            newCol = pos.getColumn()+iterator;
            iterator++;
            if (newRow < zero || newCol >= cols) {
                topRight = false;
                break;
            }
            else if (myBoard[newRow][newCol] != p) {
                topRight = false;
            }
            if (myBoard[newRow][newCol] == p) {
                amtToWin++;
            }
            if (amtToWin == winNum) {
                return true;
            }
        }
        // Checks from token diagonally to the bottom right for win condition.
        amtToWin = 1;
        iterator = 1;
        while (botLeft == true) {
            newRow = pos.getRow() + iterator;
            newCol = pos.getColumn() - iterator;
            iterator++;
            if (newRow >= rows || newCol < zero) {
                botLeft = false;
                break;
            }
            else if (myBoard[newRow][newCol] != p) {
                botLeft = false;
            }
            if (myBoard[newRow][newCol] == p) {
                amtToWin++;
            }
            if (amtToWin == winNum) {
                return true;
            }
        }
        // Checks from token diagonally to the bottom for win condition.
        amtToWin = 1;
        iterator = 1;
        while (botRight == true) {
            newRow = pos.getRow() + iterator;
            newCol = pos.getColumn() + iterator;
            iterator++;
            if (newRow >= rows || newCol >= cols) {
                botRight = false;
                break;
            }
            else if (myBoard[newRow][newCol] != p) {
                botRight = false;
            }
            if (myBoard[newRow][newCol] == p) {
                amtToWin++;
            }
            if (amtToWin == winNum) {
                return true;
            }
        }
        return false;
    }
    /**
     * @Pre: pos row & column > 0
     * @Post: pos = position player = X or O depending who is at that position.
     * @Return: the char that is in position pos of the game board,
     *          if no token at the spot return " "
     *          */
    public char whatsAtPos(BoardPosition pos) {
        return myBoard[pos.getRow()][pos.getColumn()];
    }
    /**
     * @Pre: pos > 0 player = 'x' or 'o'
     * @Post: pos = position
     * @Return: true if the player is at that position, false otherwise.
     * */
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if (this.whatsAtPos(pos) == player) {
            return true;
        }
        return false;
    }

    /**
     *
     * @return: A String that has been formatted for printing out to the terminal the game board.
     */
    @Override
    public String toString() {
        String format;
        int front = zero;
        format = "|0|1|2|3|4|5|6|\n";
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (front == zero) {
                    format += "|";
                    front = 1;
                }
                format = format + myBoard[i][j] + "|";
            }
            front = zero;
            format += "\n";
        }
        return format;
    }
    /**
     * @Pre: No win conditions done
     * @Return: true is there is a tie, false otherwise
     * */
    public boolean checkTie() {
        int tieCounter = zero;
        for (int i = 0; i < cols; i++) {
            if (myBoard[zero][i] != ' ') {
                tieCounter++;
            }
        }
        if (tieCounter == cols) {
            return true;
        }
        return false;
    }
    /**
     * @Post: getNumRows = rows;
     * @return: int with the number of rows in the GameBoard
     */
    public int getNumRows() {
        return rows;
    }
    /**
     * @Post: getNumColumns = columns;
     * @return: int with the number of columns in the GameBoard
     */
    public int getNumColumns() {
        return cols;
    }
    /**
     * @return: int with the number of tokens in a row needed to win the game
     */
    public int getNumToWin() {
        return winNum;
    }
}
