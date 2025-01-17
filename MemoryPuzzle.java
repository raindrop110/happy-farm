import java.awt.Color;
import java.util.ArrayList;

/**
 * This class creates the mini game - memory puzzle
 * 
 * 4x4 grid - easy
 * 4x6 grid - medium
 * 6x6 grid - hard
 * how many moves -> one move = 2 clicks
 * calculate efficiency - 15 moves in easy (16 squares - 8 pairs) 8/15
 * 
 * @author Raina Shrivastava
 * @version 5/5/2023
 * 
 */
public class MemoryPuzzle {

    private int rows;
    private int cols;
    private String[][] board;
    private String[] colors = { "red", "orange", "yellow", "blue", "green", "purple", "lavender", "pink", "mint",
            "cyan",
            "magenta", "olive", "lred", "lyellow", "lblue", "lpink", "brown", "maroon" };
    // colors has 18 colors, for max level hard which has 18 pairs, 36 cells.

    /**
     * initizales board size, rows and cols
     * 
     * @param level
     *              String that dictates how large the board is
     */
    public MemoryPuzzle(String level) {

        if (level.equals("easy")) {
            rows = 4;
            cols = 4;
        } else if (level.equals("medium")) {
            rows = 4;
            cols = 6;
        } else {
            rows = 6;
            cols = 6;
        }
        board = new String[rows][cols];

    }

    /**
     * fills sized board with color pairs in random locations
     */
    public void createBoard() {

        ArrayList<String> colors2 = new ArrayList<String>();

        for (int i = 0; i < (rows * cols) / 2; i++) {
            colors2.add(colors[i]);
        }
        for (int i = 0; i < (rows * cols) / 2; i++) {
            colors2.add(colors[i]);
        }

        int randIdx;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                randIdx = (int) (Math.random() * colors2.size());
                board[r][c] = colors2.get(randIdx);
                colors2.remove(randIdx);
            }
        }

    }

    /**
     * returns the color of the ith element in the 2d array
     * 
     * @param i index of element
     * @return Color of the elemnt
     */
    public Color getColor(int i) {

        int row = i / cols;
        int col = i % cols;

        System.out.println(row + ", " + col);
        String color = board[row][col];
        System.out.println(color);

        if (color == "red") {
            return new Color(255, 45, 0);
        } else if (color == "orange") {
            return new Color(255, 117, 0);
        } else if (color == "yellow") {
            return new Color(255, 247, 0);
        } else if (color == "blue") {
            return new Color(0, 113, 255);
        } else if (color == "green") {
            return new Color(21, 255, 0);
        } else if (color == "purple") {
            return new Color(150, 0, 255);
        } else if (color == "lavender") {
            return new Color(235, 207, 255);
        } else if (color == "pink") {
            return new Color(255, 89, 241);
        } else if (color == "mint") {
            return new Color(180, 245, 213);
        } else if (color == "cyan") {
            return new Color(0, 255, 238);
        } else if (color == "magenta") {
            return new Color(255, 0, 117);
        } else if (color == "olive") {
            return new Color(74, 103, 46);
        } else if (color == "lred") {
            return new Color(250, 105, 105);
        } else if (color == "lyellow") {
            return new Color(250, 231, 105);
        } else if (color == "lblue") {
            return new Color(143, 239, 255);
        } else if (color == "lpink") {
            return new Color(255, 161, 238);
        } else if (color == "brown") {
            return new Color(183, 135, 0);
        } else {
            return new Color(156, 0, 0);
        }

    }

    /**
     * gets # of columns
     * 
     * @return
     *         number
     */
    public int getCols() {
        return cols;
    }

    /**
     * gets number of rows
     * 
     * @return
     *         number of row
     */
    public int getRows() {
        return rows;
    }

    /**
     * testing method, prints out board
     */
    public void print() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                System.out.print(board[r][c] + " ");
            }
        }
    }

    /**
     * tells whether board has elements in all places - private testing method
     * 
     * @return
     *         true or false
     */
    private boolean isFull() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == null) {
                    return false;
                }
            }
        }
        return true;

    }

}