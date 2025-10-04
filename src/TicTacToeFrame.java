import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends javax.swing.JFrame
{
    JPanel mainPnl;
    JPanel titlePnl;
    JPanel ticTacToePnl;
    JPanel quitPnl;

    JLabel titleLbl;
    JOptionPane messageOpt;

    TicTacToeButton[][] board;
    JButton quitBtn;

    public TicTacToeFrame(String messageOption)
    {
        super("Tic Tac Toe");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        add(mainPnl);

        createTitlePnl();
        mainPnl.add(titlePnl, BorderLayout.NORTH);

        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                board[row][col] = new TicTacToeButton(row, col);
                board[row][col].setText(" ");
            }
        }
    }

    private void createTitlePnl()
    {
        titlePnl = new JPanel();
        titleLbl = new JLabel("Tic Tac Toe");
        titlePnl.add(titleLbl);
    }
}

//messageOpt = new JOptionPane(messageOption); for the buttons!!

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


