import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameControl extends JFrame
{
    private GameBoard gameBoard;
    private Player playerX;
    private Player playerO;

    private JButton quitButton;
    private JButton onePlayerButton;

    public GameControl()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        getContentPane().setBackground(new Color(0, 50, 50));
        setLayout(new BorderLayout());

        playerX = new Player("Player X", "X");
        playerO = new Player("Player O", "O");
        gameBoard = new GameBoard(playerX, playerO);

        add(gameBoard, BorderLayout.CENTER);

        quitButton = new JButton("Quit");
        onePlayerButton = new JButton("1 Player");

        quitButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        onePlayerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        controlPanel.add(quitButton);
        controlPanel.add(onePlayerButton);

        add(controlPanel, BorderLayout.SOUTH);

        gameBoard.clearBoard();
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new GameControl();
    }
}
