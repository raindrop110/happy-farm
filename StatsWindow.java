import java.awt.*;
import java.awt.event.*;
import java.net.ContentHandler;

import javax.swing.*;
import javax.swing.text.html.parser.ContentModel;

import java.util.*;

public class StatsWindow extends JFrame{
    Player player;
    ArrayList<String> content = new ArrayList<String>();

    public StatsWindow(Player p) {
        player = p;
    }

    public void create() {
        JFrame frame = new JFrame("Stats Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(0, 0, 300, 500);
        //Set up the content pane.
        Container pane = (frame.getContentPane());
        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.setBounds(100, 100, 81, 140);

        JLabel text = new JLabel("Games Played: " + player.getGamesPlayed());
        Font font = new Font("SansSerif", Font.BOLD, 20);
        text.setFont(font);

        JLabel text2 = new JLabel("Coins: " + player.getCoins());
        Font font2 = new Font("SansSerif", Font.BOLD, 20);
        text2.setFont(font2);

        JLabel text3 = new JLabel("Average Acurracy Percentage: " + player.getAvgPercentage());
        Font font3 = new Font("SansSerif", Font.BOLD, 20);
        text3.setFont(font3);

        pane.add(text);
        pane.add(text2);
        pane.add(text3);

        frame.setSize(500,300);
        frame.setVisible(true);
    }
}
