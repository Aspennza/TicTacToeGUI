import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Come back to working on the action listener
//Come back to prof. wulf's 2nd lecture at 30 mins in

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


    }

    private void createTitlePnl()
    {
        titlePnl = new JPanel();
        titleLbl = new JLabel("Tic Tac Toe");
        titlePnl.add(titleLbl);
    }

    private void createTicTacToePnl()
    {
        ticTacToePnl = new JPanel();

        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                board[row][col] = new TicTacToeButton(row, col);
                board[row][col].setText(" ");
            }
        }

        class ticTacToeBtnListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if (ae.getSource() instanceof JButton selectedButton)
                {
                    String buttonText = selectedButton.getText();
                }
            }
        }
        }
    }

    private boolean isValidMove(int row, int col)
    {
        boolean retVal = false;
        if(board[row][col].getText().equals(" "))
            retVal = true;
        else
        {
            messageOption = "You selected a location that is already occupied. Please select a different location.";
        }
        return retVal;
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


