import javax.swing.*;

//write javadoc
//make UML diagram

/**
 * @author wulft
 */
public class TicTacToeRunner
{
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String player = "X";
    private static TicTacToeFrame frame;
    private static int moveCnt = 0;
    private static final int MOVES_FOR_WIN = 5;
    private static final int MOVES_FOR_TIE = 7;
    private static boolean playing = true;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        frame = new TicTacToeFrame();
        clearBoard();
    }

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

    private static boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if(board[row][col].equals(" "))
            retVal = true;

        return retVal;

    }
    private static boolean isWin(String player)
    {
        if(isColWin(player) || isRowWin(player) || isDiagnalWin(player))
        {
            return true;
        }

        return false;
    }
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

    private static boolean isFullBoardTie()
    {
        boolean emptyVal = false;

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

    public static void buttonAction(int row, int col)
    {
        String prevPlayer = player;

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

            if(player.equals("X"))
            {
                player = "O";
            }
            else
            {
                player = "X";
            }
        }

        if(moveCnt >= MOVES_FOR_WIN)
        {
            if(isWin(prevPlayer))
            {
                JOptionPane.showMessageDialog(frame, "Player " + prevPlayer + " Wins!");
                playing = false;
                JOptionPane.showMessageDialog(null, "Click Play Again to clear the board and play the game again!");
            }
        }
        if(moveCnt >= MOVES_FOR_TIE && playing)
        {
            if(isFullBoardTie())
            {
                JOptionPane.showMessageDialog(frame ,"The game has tied! The board is full.");
                JOptionPane.showMessageDialog(null, "Click Play Again to clear the board and play the game again!");
                playing = false;
            }
        }

        if(moveCnt >= MOVES_FOR_TIE && playing)
        {
            if(isTie())
            {
                JOptionPane.showMessageDialog(frame, "The game has come to a tie before the board is full!");
                playing = false;
                JOptionPane.showMessageDialog(null, "Click Play Again to clear the board and play the game again!");
            }
        }
    }
}
