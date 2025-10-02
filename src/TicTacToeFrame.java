import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeFrame extends JFrame {
    private static final int SIZE = 3;
    private TicTacToeTile[][] board = new TicTacToeTile[SIZE][SIZE];
    private String currentPlayer = "X";
    private int moveCount = 0;

    public TicTacToeFrame() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Grid of buttons
        JPanel boardPanel = new JPanel(new GridLayout(SIZE, SIZE));
        ButtonHandler handler = new ButtonHandler();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = new TicTacToeTile(row, col);
                board[row][col].addActionListener(handler);
                boardPanel.add(board[row][col]);
            }
        }

        // Quit button
        JButton quitBtn = new JButton("Quit");
        quitBtn.addActionListener(e -> System.exit(0));

        add(boardPanel, BorderLayout.CENTER);
        add(quitBtn, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            TicTacToeTile btn = (TicTacToeTile) e.getSource();
            if (!btn.getText().equals(" ")) {
                JOptionPane.showMessageDialog(TicTacToeFrame.this,
                        "Illegal move! Square already taken.");
                return;
            }

            btn.setText(currentPlayer);
            moveCount++;

            // Check win/tie
            if (moveCount >= 5 && isWin(currentPlayer)) {
                JOptionPane.showMessageDialog(TicTacToeFrame.this,
                        "Player " + currentPlayer + " wins!");
                gameOver("Player " + currentPlayer + " absolutely dominated the other player!");
                return;
            }
            if (moveCount >= 7 && isTie()) {
                JOptionPane.showMessageDialog(TicTacToeFrame.this,
                        "It's a tie!");
                gameOver("You both suck!");
                return;
            }

            // Switch player
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }
    }

    private void resetGame() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                board[r][c].setText(" ");
            }
        }
        currentPlayer = "X";
        moveCount = 0;
    }

    private void gameOver(String message) {
        int choice = JOptionPane.showConfirmDialog(
                this,
                message + "\nDo you want to play again?",
                "Game Over",
                JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }


    private boolean isWin(String player) {
        // Rows
        for (int r = 0; r < SIZE; r++) {
            if (board[r][0].getText().equals(player) &&
                    board[r][1].getText().equals(player) &&
                    board[r][2].getText().equals(player)) {
                return true;
            }
        }
        // Cols
        for (int c = 0; c < SIZE; c++) {
            if (board[0][c].getText().equals(player) &&
                    board[1][c].getText().equals(player) &&
                    board[2][c].getText().equals(player)) {
                return true;
            }
        }
        // Diagonals
        if (board[0][0].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][2].getText().equals(player)) {
            return true;
        }
        if (board[0][2].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][0].getText().equals(player)) {
            return true;
        }
        return false;
    }

    private boolean isTie() {
        // Simple check: all filled with no winner
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c].getText().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
}
