import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;

public class PlayerWindow extends JFrame{

    private Player player;
    private JFrame frame;
    private JTextField coinsText;
    private GridBagConstraints c;
    private Container pane;

    private LinkedHashMap<Animal, JLabel> animals = new LinkedHashMap<Animal, JLabel>();
    private ArrayList<JTextField> levels = new ArrayList<JTextField>();

    public PlayerWindow(Player p) {

        MusicPlayer.playMusic("media/farmmusic.wav");

        //initialize linked hash map with animals and default lock images
        player = p;
        for (Animal a : player.animals) {
            animals.put(a, new JLabel(new ImageIcon("images/lock.png")));
        }

        //Create and set up the window.
        frame = new JFrame("Happy Farm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, 700, 700);

        frame.setContentPane(new JLabel(new ImageIcon("images/bg.png")));

        //Set up the game content pane.
        pane = (frame.getContentPane());
        pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        pane.setLayout(new GridBagLayout());
        // frame.setLayout(new GridBagLayout());

        c = new GridBagConstraints();
        //natural height, maximum width
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.weighty = 0.0;

        //Title
        Font font = new Font("SansSerif", Font.BOLD, 35);
        JTextField title = new JTextField("Happy Farm");
        title.setFont(font);
        title.setHorizontalAlignment(JTextField.CENTER);
        title.setEditable(false);
        title.setOpaque(false);
        title.setBorder(null);
        c.ipady = 35;      //make this component tall
        c.gridwidth =  GridBagConstraints.REMAINDER;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(title, c);
    
        // Rules button
        JButton button = new JButton("Rules");
        c.ipady = 5;
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0,0,30,0);
        pane.add(button, c); 
        button.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RuleWindow r = new RuleWindow();
                r.create();
         }
         });

        // Stats button
        JButton b = new JButton("Stats");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        pane.add(b, c); 
        b.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RuleWindow();
                seeStats();
            }
            });
        
        // Coins text field
        font = new Font("SansSerif", Font.BOLD, 20);
        coinsText = new JTextField("Coins: " + player.getCoins());
        coinsText.setFont(font);
        coinsText.setHorizontalAlignment(JTextField.CENTER);
        coinsText.setBackground(Color.YELLOW);
        coinsText.setEditable(false);
        c.ipady = 30;      //make this component tall
        c.gridwidth =  GridBagConstraints.REMAINDER;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(0,0,20,0);
        pane.add(coinsText, c);

        // add lock images to panel
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0; 
        c.gridy = 3; 
        c.gridwidth = 1;
        for (Map.Entry<Animal, JLabel> a : animals.entrySet()) {
            pane.add(a.getValue());
        }

        // Level labels for each animal
        JTextField level;
        c.gridy = 4;
        int i = -1;
        ArrayList<Animal> ani = player.animals;
        for (Animal a : ani) {
            i ++;
            level = new JTextField("Level: " + a.getLvl());
            level.setHorizontalAlignment(JTextField.CENTER);
            level.setEditable(false);
            level.setOpaque(false);
            level.setBorder(null);
            levels.add(level);
            c.gridx  = i;
            pane.add(level, c);
        }

        // Play Game Button
        button = new JButton("Play Game!");
        font = new Font("SansSerif", Font.BOLD, 20);
        button.setFont(font);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 25;
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        c.insets = new Insets(30,0,0,0);
        pane.add(button, c); 
        button.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playGame();
            }
            });

        // Open Box button
        button = new JButton("Open Mystery Box!");
        button.setFont(font);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 25;
        c.gridx = 3;
        c.gridy = 5;
        c.gridwidth = 2;
        c.insets = new Insets(30,0,0,0);
        pane.add(button, c); 
        button.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openBox();
            }
            });


        //Display the window.
        frame.setSize(900,600);
        frame.setVisible(true);

    }

    public void seeStats()
    {
        System.out.println("See Stats");
        StatsWindow stat = new StatsWindow(player);
        stat.create();
        update();
    }

    public void openBox() {
         System.out.println("open box, ");
         MysteryBoxWindow mbw = new MysteryBoxWindow(player);
         mbw.create();

         ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
             update();
            }
        };
        Timer t = new Timer(2000, taskPerformer);
        t.setRepeats(false);
        t.start();
        
    }

    public void playGame() {
        System.out.println("playing game");
        player.playGame(this);
    }

    public void gameOver() {
        GameOverWindow g = new GameOverWindow();
    }

    // Call whenever a button is pressed/animal is unlocked/game is played
    public void update() {
        System.out.println("update");


        // update coins
        coinsText.setText("Coins: " + player.getCoins());
        
        c.weighty = 0.0;
        c.weightx = 0.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0; 
        c.gridy = 3; 
        c.gridwidth = 1;
        int i = 0;
        int numUnlocked = 0;
        for (Map.Entry<Animal, JLabel> ani : animals.entrySet()){
            //update images
            Animal a = ani.getKey();
            if (a.isUnlocked()) {
                numUnlocked++;
                System.out.println(a.getType()+ " unlocked");
                ImageIcon newImage = new ImageIcon ("images/" + a.getType() + ".png");
                ani.getValue().setIcon(newImage);
            }

            // update level
            JTextField lvl = levels.get(i);
            lvl.setText("Level: " + a.getLvl() );
            i ++;

        }

        // game over
        if (numUnlocked == 6) {
            gameOver();
        }

        this.validate();
        this.repaint();
    }



    
}