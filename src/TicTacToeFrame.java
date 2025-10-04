import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class TicTacToeFrame extends javax.swing.JFrame
{
    JPanel mainPnl;
    JPanel titlePnl;
    JPanel ticTacToePnl;
    JPanel quitPnl;

    JLabel titleLbl;

    TicTacToeButton[][] board;
    JButton quitBtn;

    public TicTacToeFrame()
    {
        super("Tic Tac Toe");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        add(mainPnl);

        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                board[row][col] = new TicTacToeButton(row, col);
                board[row][col].setText(" ");
            }
        }
    }
}

class TicTacToeButton extends JButton
{
    private int row;
    private int col;

    public TicTacToeButton(int row, int col)
    {
        super();
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}


