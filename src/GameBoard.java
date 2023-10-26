import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JPanel implements ActionListener
{
    private JButton[] gameBoard;
    private JLabel textBox;
    private int moveCount;
    private Player currentPlayer;
    private Player playerX;
    private Player playerO;

    public GameBoard(Player playerX, Player playerO)
    {
        this.playerX = playerX;
        this.playerO = playerO;

        currentPlayer = playerX;

        setLayout(new BorderLayout());
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(3, 3, 0, 0));

        gameBoard = new JButton[9];
        moveCount = 0;

        for (int i = 0; i < 9; i++)
        {
            gameBoard[i] = new JButton();
            gameBoard[i].setFont(new Font("Arial", Font.BOLD, 60));
            gameBoard[i].setFocusable(false);
            gameBoard[i].addActionListener(this);
            gridPanel.add(gameBoard[i]);
        }

        textBox = new JLabel();
        textBox.setBackground(new Color(0, 0, 0));
        textBox.setForeground(new Color(255, 255, 0));
        textBox.setFont(new Font("Arial", Font.BOLD, 30));
        textBox.setHorizontalAlignment(JLabel.CENTER);
        textBox.setText("Tic Tac Toe");
        textBox.setOpaque(true);

        add(textBox, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == gameBoard[i] && gameBoard[i].getText().equals(""))
            {
                gameBoard[i].setText(currentPlayer.getSymbol());
                moveCount++;
                if (checkWinner(currentPlayer.getSymbol()))
                {
                    textBox.setText(currentPlayer.getName() + " wins. Play Again?");
                    disableButtons();
                } else if (moveCount == 9)
                {
                    textBox.setText("Tie Game! Play Again?");
                    disableButtons();
                } else
                {
                    currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
                    textBox.setText("Player " + currentPlayer.getSymbol() + " turn");
                }
            }
        }
    }

    public void clearBoard()
    {
        for (int i = 0; i < 9; i++)
        {
            gameBoard[i].setText("");
            gameBoard[i].setEnabled(true);
        }
        moveCount = 0;
        currentPlayer = playerX;
        textBox.setText("Tic Tac Toe");
    }

    private boolean checkWinner(String symbol)
    {
        // Check horizontal winner
        for (int i = 0; i < 9; i += 3)
        {
            if (gameBoard[i].getText().equals(symbol) &&
                    gameBoard[i + 1].getText().equals(symbol) &&
                    gameBoard[i + 2].getText().equals(symbol))
            {
                return true;
            }
        }

        // Check for vertical winner
        for (int i = 0; i < 3; i++)
        {
            if (gameBoard[i].getText().equals(symbol) &&
                    gameBoard[i + 3].getText().equals(symbol) &&
                    gameBoard[i + 6].getText().equals(symbol))
            {
                return true;
            }
        }

        // Check for diagonal winner
        if (gameBoard[0].getText().equals(symbol) &&
                gameBoard[4].getText().equals(symbol) &&
                gameBoard[8].getText().equals(symbol))
        {
            return true;
        }

        if (gameBoard[2].getText().equals(symbol) &&
                gameBoard[4].getText().equals(symbol) &&
                gameBoard[6].getText().equals(symbol))
        {
            return true;
        }

        return false;
    }

    private void disableButtons()
    {
        for (int i = 0; i < 9; i++)
        {
            gameBoard[i].setEnabled(false);
        }
    }
}
