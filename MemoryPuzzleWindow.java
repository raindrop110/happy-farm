import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * main gui class for memory game, contains memory game functionality
 */
public class MemoryPuzzleWindow extends JPanel implements ActionListener {

    private int r;
    private int c;
    private final ArrayList<ButtonPanel> panels = new ArrayList<ButtonPanel>();
    private MemoryPuzzle m;
    private Stack<Integer> open = new Stack<Integer>();
    private int stkLen = 0;
    private int clicks;
    private JFrame frame;
    private Player p;
    private PlayerWindow pw;

    /**
     * Constructor, sets up window and calls it's memoryPuzzle class obj to
     * initialize a random board
     * 
     * @param mem
     *            memory puzzle obj
     * @param p
     *            player
     * @param pw
     *            player window
     */
    public MemoryPuzzleWindow(MemoryPuzzle mem, Player p, PlayerWindow pw) {

        this.p = p;
        this.pw = pw;
        m = mem;
        r = m.getRows();
        c = m.getCols();
        clicks = 0;

        frame = new JFrame("Memory Puzzle");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mem.createBoard();
        frame.add(this);
        frame.pack();

        frame.setVisible(true);
        frame.setSize(c * 100, r * 100);

        this.setLayout(new GridLayout(r, c, 3, 3));

        for (int i = 0; i < r * c; i++) {
            ButtonPanel bp = new ButtonPanel(m.getColor(i), i);
            panels.add(bp);
            this.add(bp);
        }

    }

    /**
     * tests if game is over
     * 
     * @return
     *         true or false
     */
    public boolean isGameOver() {

        for (ButtonPanel bp : panels) {
            if (bp.getStatus() == false) {
                return false;
            }
        }

        return true;

    }

    /**
     * gets efficiency of the game
     * 
     * @return
     *         int percent
     */
    public int getEfficiency() {

        double moves = clicks / 2;

        double eff = ((((r * c) / 2) / moves) * 100);

        return (int) Math.round(eff);
    }

    /**
     * called to display at the end of game, shows efficiency
     */
    public void endDisplay() {

        JFrame endFrame = new JFrame("You Won!");
        endFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label = new JLabel("You won! Your Efficiency: " + getEfficiency() + "%! ");
        label.setFont(new Font("SansSerif", Font.BOLD, 30));
        label.setForeground(new Color(255, 255, 255));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        endFrame.setContentPane(new JLabel(new ImageIcon("media/cutefarm.jpg")));
        Container panel = endFrame.getContentPane();

        // panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new GridLayout(0, 1));
        panel.setBackground(new Color(154, 227, 150));
        panel.add(label);

        // endFrame.setBackground(new Color(154, 227, 150));

        endFrame.setLocationRelativeTo(null);
        endFrame.setVisible(true);
        endFrame.setSize(626, 427);

    }

    /**
     * private class to set up the grid of buttons
     */
    private class ButtonPanel extends JPanel implements ActionListener {

        private Color gray = new Color(128, 128, 128);

        private Color bColor;
        private Color currColor;
        private JButton b;
        private int pos;
        private boolean status;

        /**
         * creates a single button panel that has a button on it
         * 
         * @param c
         *          Color of it
         * @param i
         *          it's position
         */
        public ButtonPanel(Color c, int i) {
            bColor = c;
            pos = i;
            b = new JButton();
            b.addActionListener(this);
            this.setBackground(gray);
            currColor = gray;
            this.add(b);
            status = false;
        }

        /**
         * gets position of panel
         * 
         * @return
         *         int pos
         */
        public int getPos() {
            return pos;
        }

        /**
         * gets current color of panel
         * 
         * @return
         *         Color current
         */
        public Color getColor() {
            return currColor;
        }

        /**
         * finalizes button so it can't be clicked anymore
         */
        public void makeFinal() {
            status = true;

        }

        /**
         * sees if button is final or not
         * 
         * @return
         *         boolean
         */
        public boolean getStatus() {

            return status;
        }

        /**
         * changes panel color
         * 
         * @param c
         *          color to change to
         */
        public void setColor(Color c) {
            setBackground(c);
            currColor = c;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if (this.getColor().equals(gray) && this.getStatus() == false) {

                clicks += 1;

                if (stkLen == 2) {
                    panels.get(open.pop()).setColor(gray);
                    panels.get(open.pop()).setColor(gray);
                    stkLen = 0;
                    this.setColor(bColor);
                    open.add(this.getPos());
                    stkLen += 1;
                }

                else if (stkLen == 1) {

                    this.setColor(bColor);

                    if (panels.get(open.peek()).getColor().equals(this.getColor())) {

                        MusicPlayer.playRandAnimal();

                        panels.get(open.pop()).makeFinal();
                        this.makeFinal();
                        stkLen = 0;

                    } else {

                        open.add(this.getPos());
                        stkLen += 1;

                    }
                } else {

                    this.setColor(bColor);
                    open.add(this.getPos());
                    stkLen += 1;

                }

                if (isGameOver()) {

                    System.out.println(getEfficiency());
                    endDisplay();
                    int percent = getEfficiency();
                    System.out.println("percent:" + percent);
                    p.updateCoins(percent);
                    p.updateGamesPlayed();
                    p.updateAvgPercentage(percent);
                    pw.update();

                }

            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}