import javax.swing.JButton;

public class TicTacToeTile extends JButton {
    private final int row;
    private final int col;

    public TicTacToeTile(int row, int col) {
        super(" ");
        this.row = row;
        this.col = col;
        setFocusPainted(false);
        setFont(getFont().deriveFont(36f));
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
}
