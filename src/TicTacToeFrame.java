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
    String[][] transferBoard;
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

        createTitlePnl();
        mainPnl.add(titlePnl, BorderLayout.NORTH);

        createTicTacToePnl();
        mainPnl.add(ticTacToePnl, BorderLayout.CENTER);


        setSize(screenWidth * 3/4, screenHeight * 3/4);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setVisible(true);
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
        ticTacToePnl.setLayout(new GridLayout(3, 3));

        board = new TicTacToeButton[3][3];

        class ticTacToeBtnListener implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if (ae.getSource() instanceof TicTacToeButton selectedButton)
                {
                    String player = TicTacToeRunner.sendPlayer();

                    boolean messageYN = TicTacToeRunner.updateButton(selectedButton.getRow(), selectedButton.getCol());

                    if(!messageYN)
                    {
                        JOptionPane.showMessageDialog(null,"This space is full. Please select a different space.");
                    } else {
                        board[selectedButton.getRow()][selectedButton.getCol()].setText(TicTacToeRunner.sendPlayer());
                    }


                }
            }
        }

        ticTacToeBtnListener listener = new ticTacToeBtnListener();

        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                board[row][col] = new TicTacToeButton(row, col);
                board[row][col].setText(" ");
                board[row][col].addActionListener(listener);
                ticTacToePnl.add(board[row][col]);
            }
        }
    }

    private void createQuitPnl()
    {
        quitPnl = new JPanel();
        quitBtn = new JButton("Quit");

        quitPnl.add(quitBtn);

        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));
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




