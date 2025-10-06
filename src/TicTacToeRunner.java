import javax.swing.*;

//write javadoc
//make UML diagram

/**
 * Contains all the program logic for running the TicTacToeFrame object, including logging move locations,
 * testing for wins, and testing for ties.
 * @author wulft
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class TicTacToeRunner
{
    //An int for setting the permanent row count of the board array
    private static final int ROW = 3;

    //An int for setting the permanent column count of the board array
    private static final int COL = 3;

    //A 2D array for storing move locations
    private static String[][] board = new String[ROW][COL];

    //A String for storing which player (X or O) is active
    private static String player = "X";

    //An instance of TicTacToeFrame
    private static TicTacToeFrame frame;

    //An int for storing the number of moves that have occurred
    private static int moveCnt = 0;

    //An int storing the number of moves necessary before checking for wins
    private static final int MOVES_FOR_WIN = 5;

    //An int storing the number of moves necessary before checking for ties
    private static final int MOVES_FOR_TIE = 7;

    //A boolean tracking whether the game is still active or not
    private static boolean playing = true;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        frame = new TicTacToeFrame();
        clearBoard();
    }

    /**
     * A method for emptying the Tic Tac Toe array and GUI of any moves.
     */
    public static void clearBoard()
    {
        // sets all the board elements to a space
        for(int row=0; row < ROW; row++)
        {
            for(int col=0; col < COL; col++)
            {
                board[row][col] = " ";
            }
        }

        frame.clearBoard();
        playing = true;
        player = "X";
    }

    /**
     * A method for checking whether a move is allowed based on whether the move location is empty or not.
     * @param row the row selected by the player
     * @param col the column selected by the player
     * @return a boolean value representing whether the move is valid
     */
    private static boolean isValidMove(int row, int col)
    {
        //A boolean tracking whether a given move is valid
        boolean retVal = false;

        //This algorithm checks if the selected space is empty
        if(board[row][col].equals(" "))
            retVal = true;

        return retVal;
    }

    /**
     * A method for checking if the current player has achieved a win.
     * @param player the current player (X or O)
     * @return a boolean representing whether a win was achieved
     */
    private static boolean isWin(String player)
    {
        //An algorithm for running through possible win conditions
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }

        return false;
    }

    /**
     * A method for checking whether the current player has achieved a column win.
     * @param player the current player (X or O)
     * @return a boolean representing whether a column win was achieved
     */
    private static boolean isColWin(String player)
    {
        // checks for a col win for specified player
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player))
            {
                return true;
            }
        }
        return false; // no col win
    }

    /**
     * A method for checking if the current player has achieved a row win.
     * @param player the current player (X or O)
     * @return a boolean representing whether a row win was achieved
     */
    private static boolean isRowWin(String player)
    {
        // checks for a row win for the specified player
        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player))
            {
                return true;
            }
        }
        return false; // no row win
    }

    /**
     * A method for checking if a diagonal win was achieved by the current player.
     * @param player the current player (X or O)
     * @return a boolean representing whether the current player has achieved a diagonal win
     */
    private static boolean isDiagnalWin(String player)
    {
        // checks for a diagonal win for the specified player

        if(board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player) )
        {
            return true;
        }
        if(board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player) )
        {
            return true;
        }
        return false;
    }

    /**
     * A method for testing if a tie has been achieved because the board is full (in the absence of a win or earlier tie).
     * @return a boolean representing whether a full board tie was achieved.
     */
    private static boolean isFullBoardTie()
    {
        //A boolean for checking if there is an empty space on the board
        boolean emptyVal = false;

        //An algorithm for checking if any empty spaces remain on the board
        for(int row=0; row < ROW; row++)
        {
            for(int col=0; col < COL; col++)
            {
                if(board[row][col].equals(" "))
                {
                    emptyVal = true;
                }
            }
        }

        //An algorithm determining that if there are no empty values, a full board tie is achieved
        if(!emptyVal)
        {
            return true;
        } else
        {
            return false;
        }
    }

    // checks for a tie before board is filled.
    // check for the win first to be efficient
    /**
     * A method for checking whether a tie has occurred before the board is full.
     * @return a boolean representing whether the players have tied.
     */
    private static boolean isTie()
    {
        boolean xFlag = false;
        boolean oFlag = false;

        // Check all 8 win vectors for an X and O so
        // no win is possible
        // Check for row ties

        for(int row=0; row < ROW; row++)
        {
            if(board[row][0].equals("X") ||
                    board[row][1].equals("X") ||
                    board[row][2].equals("X"))
            {
                xFlag = true; // there is an X in this row
            }
            if(board[row][0].equals("O") ||
                    board[row][1].equals("O") ||
                    board[row][2].equals("O"))
            {
                oFlag = true; // there is an O in this row
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a row win
            }

            xFlag = oFlag = false;

        }
        // Now scan the columns
        for(int col=0; col < COL; col++)
        {
            if(board[0][col].equals("X") ||
                    board[1][col].equals("X") ||
                    board[2][col].equals("X"))
            {
                xFlag = true; // there is an X in this col
            }
            if(board[0][col].equals("O") ||
                    board[1][col].equals("O") ||
                    board[2][col].equals("O"))
            {
                oFlag = true; // there is an O in this col
            }

            if(! (xFlag && oFlag) )
            {
                return false; // No tie can still have a col win
            }
        }
        // Now check for the diagonals
        xFlag = oFlag = false;

        if(board[0][0].equals("X") ||
                board[1][1].equals("X") ||
                board[2][2].equals("X") )
        {
            xFlag = true;
        }
        if(board[0][0].equals("O") ||
                board[1][1].equals("O") ||
                board[2][2].equals("O") )
        {
            oFlag = true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }
        xFlag = oFlag = false;

        if(board[0][2].equals("X") ||
                board[1][1].equals("X") ||
                board[2][0].equals("X") )
        {
            xFlag =  true;
        }
        if(board[0][2].equals("O") ||
                board[1][1].equals("O") ||
                board[2][0].equals("O") )
        {
            oFlag =  true;
        }
        if(! (xFlag && oFlag) )
        {
            return false; // No tie can still have a diag win
        }

        // Checked every vector so I know I have a tie
        return true;
    }

    /**
     * A method for taking input from the GUI and calling all other in-game methods to log moves and check for wins and ties.
     * @param row the row selected by the player in the GUI
     * @param col the column selected by the player in the GUI
     */
    public static void buttonAction(int row, int col)
    {
        //A String for tracking which player played last (i.e., before the current players were switched)
        String prevPlayer = player;

        //This algorithm takes input from the player and checks whether the move is valid
        if(!playing)
        {
            JOptionPane.showMessageDialog(frame, "The game is over. Select Play Again to replay!");
        } else if (!isValidMove(row, col))
        {
            JOptionPane.showMessageDialog(frame, "This space is already occupied. Please select a different space.");
        } else
        {
            board[row][col] = player;
            frame.setButton(row, col, player);
            moveCnt++;

            //This algorithm switches between the players
            if(player.equals("X"))
            {
                player = "O";
            }
            else
            {
                player = "X";
            }
        }

        //This algorithm checks if enough moves have passed for a potential win, then checks for wins
        if(moveCnt >= MOVES_FOR_WIN)
        {
            //This algorithm checks for wins, and, if one is achieved, announces it and shuts down the game
            if(isWin(prevPlayer))
            {
                JOptionPane.showMessageDialog(frame, "Player " + prevPlayer + " Wins!");
                playing = false;
                JOptionPane.showMessageDialog(null, "Click Play Again to clear the board and play the game again!");
            }
        }

        //This algorithm checks if enough moves have passed for a potential tie, then checks for full board ties
        if(moveCnt >= MOVES_FOR_TIE && playing)
        {
            //This algorithm checks for full board ties, and, if one is achieved, announces it and shuts down the game
            if(isFullBoardTie())
            {
                JOptionPane.showMessageDialog(frame ,"The game has tied! The board is full.");
                JOptionPane.showMessageDialog(null, "Click Play Again to clear the board and play the game again!");
                playing = false;
            }
        }

        //This algorithm checks if enough moves have passed for a potential tie, then checks for non-full-board ties
        if(moveCnt >= MOVES_FOR_TIE && playing)
        {
            //This algorithm checks for non-full-board ties, and, if one is achieved, announces it and shuts down the game
            if(isTie())
            {
                JOptionPane.showMessageDialog(frame, "The game has come to a tie before the board is full!");
                playing = false;
                JOptionPane.showMessageDialog(null, "Click Play Again to clear the board and play the game again!");
            }
        }
    }
}
