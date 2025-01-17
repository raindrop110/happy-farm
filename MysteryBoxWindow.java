import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MysteryBoxWindow extends JFrame{
    Player player;

    public MysteryBoxWindow(Player p) {
        player = p;
    }

    public void create() {
        JLabel text = new JLabel();
        JFrame frame = new JFrame("Mystery Box");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(0, 0, 300, 500);
        //Set up the content pane.
        Container pane = (frame.getContentPane());
        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.setBounds(100, 100, 81, 140);

        if (player.getCoins() < 25) {
            Font font = new Font("SansSerif", Font.BOLD, 25);
            text.setFont(font);
            text.setText("Not enough coins!" );
            text.setHorizontalAlignment(JLabel.CENTER);
            //text.setAlignmentX(CENTER_ALIGNMENT);
            text.setAlignmentY(CENTER_ALIGNMENT);

            JLabel text2 = new JLabel("You need 25 coins to unlock a mystery box!");
            Font font2 = new Font("SansSerif", Font.BOLD, 20);
            text2.setFont(font2);

            pane.add(text);
            pane.add(text2);
            frame.setSize(500,300);
            frame.setVisible(true);
            return;
        }

        player.buyChest(25);
        // initial text
        text.setText("You have unlocked...");
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setAlignmentX(CENTER_ALIGNMENT);
        text.setAlignmentY(CENTER_ALIGNMENT);

        Font font = new Font("SansSerif", Font.BOLD, 35);
        JLabel space = new JLabel(" ");
        space.setFont(font);
        pane.add(space);
        pane.add(text);
        
        // using Swing timer
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("www");
                pane.removeAll();

                // display unlocked animal
                String aStr = player.mysteryBox();
                text.setText(aStr.toUpperCase()+ "!");
                text.setFont(font);
                JLabel lvl = new JLabel();
                for (Animal a : player.animals) {
                    if (a.getType().equals(aStr)) {
                        player.updateLvl(a);
                        a.unlock();
                        lvl = new JLabel("Animal Level:" + a.getLvl());
                        lvl.setAlignmentX(CENTER_ALIGNMENT);
                        System.out.println("updated");
                        break;
                    }
                }

                JLabel img = new JLabel(new ImageIcon("images/" + aStr + ".png"));
                img.setAlignmentX(CENTER_ALIGNMENT);

                pane.add(space);
                pane.add(text);
                pane.add(lvl);
                pane.add(img);
                
                pane.revalidate();
                pane.repaint();
            };
        };

        Timer t = new Timer(1000, taskPerformer);
        t.setRepeats(false);
        t.start();

        


        frame.setSize(500,300);
        frame.setVisible(true);

    }


}