import javax.swing.*;

public class HappyFarm {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Player p = new Player();
                PlayerWindow window = new PlayerWindow(p);
            }});




    }

}
