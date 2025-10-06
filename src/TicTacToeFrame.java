import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates an extension of the JFrame class called TicTacToeFrame. Acts as a
 * GUI for the logic in TicTacToeRunner.
 * @author Zoe Aspenns aspennza@mail.uc.edu
 */
public class TicTacToeFrame extends javax.swing.JFrame
{
    //A JPanel containing every other element in the JFrame
    JPanel mainPnl;

    //A JPanel containing a JLabel with the title of the game
    JPanel titlePnl;

    //A JPanel containing the Tic Tac Toe 'board' of JButtons
    JPanel ticTacToePnl;

    //A JPanel containing buttons for clearing the board and quitting the game
    JPanel controlPnl;

    //A JLabel containing the title of the game
    JLabel titleLbl;

    //A 2D array of TicTacToeButton objects for storing move locations on the board
    TicTacToeButton[][] board;

    //A JButton for quitting the game
    JButton quitBtn;

    //A JButton for clearing the board
    JButton clearBtn;

    /**
     * This constructor determines the basic settings for the TicTacToeFrame and also calls
     * all the methods that establish the individual panels in the frame.
     */
    public TicTacToeFrame()
    {
        super("Tic Tac Toe");

        //This Toolkit is used to find the screen size of the computer running the GUI
        Toolkit kit = Toolkit.getDefaultToolkit();

        //This Dimension stores the screen size
        Dimension screenSize = kit.getScreenSize();

        //This int stores the height of the screen
        int screenHeight = screenSize.height;

        //This int stores the width of the screen
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

    /**
     * This method clears the JButton 2D array of any Xs or Os
     */
    public void clearBoard()
    {
        //This algorithm iterates through every row and column in the board array and sets the text to a blank space
        for(int row = 0; row < 3; row++)
        {
            for(int col = 0; col < 3; col++)
            {
                board[row][col].setText(" ");
            }
        }
    }

    /**
     * This method is used so the TicTacToeRunner can set the GUI to reflect which spaces are taken.
     * @param row the row location of the taken space
     * @param col the column location of the taken space
     * @param player the player (X or O)
     */
    public void setButton(int row, int col, String player)
    {
        board[row][col].setText(player);
    }

    /**
     * This method establishes the titlePnl and gives it a JLabel.
     */
    private void createTitlePnl()
    {
        titlePnl = new JPanel();
        titleLbl = new JLabel("Tic Tac Toe");
        titlePnl.add(titleLbl);
    }

    /**
     * This method establishes the ticTacToePnl, its button array, and the ActionListener that determines how input is handled.
     */
    private void createTicTacToePnl()
    {
        ticTacToePnl = new JPanel();
        ticTacToePnl.setLayout(new GridLayout(3, 3));

        board = new TicTacToeButton[3][3];

        /**
         * This TicTacToeBtnListener uses the actionPerformed method to track which button on the board
         * was pressed and sends the input to the runner.
         */
        class ticTacToeBtnListener implements ActionListener
        {
            //This method tracks which button on the board was pressed and sends the input to the runner
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                if (ae.getSource() instanceof TicTacToeButton selectedButton)
                {
                    TicTacToeRunner.buttonAction(selectedButton.getRow(), selectedButton.getCol());
                }
            }
        }

        //This creates an instance of the TicTacToeBtnListener
        ticTacToeBtnListener listener = new ticTacToeBtnListener();

        //This algorithm creates TicTacToeButtons inside each index of the board array, sets their text to blank spaces, and adds ActionListeners
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

    /**
     * This method establishes the controlPnl and the clear and quit buttons that go inside it.
     */
    private void createControlPnl()
    {
        controlPnl = new JPanel();
        controlPnl.setLayout(new GridLayout(1, 2));

        clearBtn = new JButton("Play Again");
        quitBtn = new JButton("Quit");

        controlPnl.add(clearBtn);

        //This ActionListener calls the clearBoard() method in the runner
        clearBtn.addActionListener((ActionEvent ae) -> TicTacToeRunner.clearBoard());

        controlPnl.add(quitBtn);

        //This ActionListener closes the game and notifies the player
        quitBtn.addActionListener((ActionEvent ae) -> {
            JOptionPane.showMessageDialog(null, "You have quit the game.");
            System.exit(0);
        });
    }
}

/**
 * This class creates a type of JButton called a TicTacToeButton, which tracks its own row and column index.
 */
class TicTacToeButton extends JButton
{
    //This int tracks the row location of the JButton
    private int row;

    //This int tracks the column location of the JButton
    private int col;

    /**
     * This constructor establishes the TicTacToeButton with all the normal traits of a JButton,
     * but also row and column characteristics.
     * @param row the button's row index
     * @param col the button's column index
     */
    public TicTacToeButton(int row, int col)
    {
        super();
        this.row = row;
        this.col = col;
    }

    /**
     * This method returns the row index of the button.
     * @return an int representing the row index of the button.
     */
    public int getRow() {
        return row;
    }

    /**
     * This method returns the row index of the button.
     * @return an int representing the row index of the button.
     */
    public int getCol() {
        return col;
    }
}




