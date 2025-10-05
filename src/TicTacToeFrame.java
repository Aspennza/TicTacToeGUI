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
    JPanel controlPnl;

    JLabel titleLbl;

    TicTacToeButton[][] board;
    JButton quitBtn;
    JButton clearBtn;

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

        createControlPnl();
        mainPnl.add(controlPnl, BorderLayout.SOUTH);


        setSize(screenWidth * 3/4, screenHeight * 3/4);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setVisible(true);
    }

    public void clearBoard()
    {
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                board[row][col].setText(" ");
            }
        }
    }

    public void setButton(int row, int col, String player)
    {
        board[row][col].setText(player);
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
                    TicTacToeRunner.buttonAction(selectedButton.getRow(), selectedButton.getCol());
//                    String player = TicTacToeRunner.sendPlayer();
//
//                    boolean messageYN = TicTacToeRunner.updateButton(selectedButton.getRow(), selectedButton.getCol());
//
//                    if(!messageYN && TicTacToeRunner.sendPlaying())
//                    {
//                        JOptionPane.showMessageDialog(null,"This space is full. Please select a different space.");
//                    } else if (!TicTacToeRunner.sendPlaying())
//                    {
//                        JOptionPane.showMessageDialog(null, "The game is finished! Please select Clear Board to replay.");
//                    } else
//                    {
//                        board[selectedButton.getRow()][selectedButton.getCol()].setText(player);
//                    }
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

    private void createControlPnl()
    {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 2));

        clearBtn = new JButton("Clear Board");
        quitBtn = new JButton("Quit");

        controlPnl.add(clearBtn);
        clearBtn.addActionListener((ActionEvent ae) ->
        {
            TicTacToeRunner.clearBoard();
        });

        controlPnl.add(quitBtn);
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




