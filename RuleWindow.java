import java.awt.*;
import javax.swing.*;
import java.nio.file.*;
import java.io.IOException;

public class RuleWindow extends JFrame{
    
    public void create() {
        JLabel text = new JLabel();

        JFrame frame = new JFrame("Happy Farm Rules");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(0, 0, 300, 500);
        //Set up the content pane.
        Container pane = (frame.getContentPane());
        pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);


        String content = "empty";
        Path filePath = Paths.get("rules.txt");
        try {
            // reading file from given path
            content = Files.readString(filePath);
        }
        catch (IOException e) {
            System.out.println("error");
        }

        text.setText(content);

        pane.add(text);

        frame.setSize(500,300);
        frame.setVisible(true);

    }
}
