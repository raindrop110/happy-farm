import java.awt.*;
import javax.swing.*;

public class GameOverWindow extends JFrame{
    Player player;

    public GameOverWindow() {
        JLabel text = new JLabel();
        JFrame frame = new JFrame("You Win");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 300, 500);

        //Set up the content pane.
        Container pane = (frame.getContentPane());
        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.setBounds(100, 100, 81, 140);

        Font font = new Font("SansSerif", Font.BOLD, 30);
        text.setFont(font);
        text.setText("YOU WIN !!!" );
        text.setHorizontalAlignment(JLabel.CENTER);
        //text.setAlignmentX(CENTER_ALIGNMENT);
        text.setAlignmentY(CENTER_ALIGNMENT);

        pane.add(text);
        frame.setSize(500,300);
        frame.setVisible(true);
    }


}

 