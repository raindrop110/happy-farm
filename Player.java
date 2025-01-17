import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.random.*;

/**
 * class player, keeps track of player animals, stats, and sets up mini game
 * @author Yvonne Wu
 */
public class Player {
    private int coins;
    private int avgPercentage;
    private int gamesPlayed;
    private int percent;
    private int highestLevel;
    ArrayList<Animal> animals = new ArrayList<Animal>();

    /**
     * constructor, initializes variables
     */
    public Player() {
        coins = 0;
        avgPercentage = 0;
        gamesPlayed = 0;
        animals.add(new Animal("rabbit", 0));
        animals.add(new Animal("chicken", 0));
        animals.add(new Animal("sheep", 0));
        animals.add(new Animal("goat", 0));
        animals.add(new Animal("pig", 0));
        animals.add(new Animal("horse", 0));
    }

    /**
     * updates number of player coins
     * 
     * @param percent
     *                the previous effciency of the player's game
     */
    public void updateCoins(int percent) {
        if (percent >= 40) {
            coins += (percent / 2);
        } else {
            coins += 5;
        }
        calculateHighestLevel();
        coins += highestLevel;
        System.out.println("coins: " + coins);
    }

    /**
     * updates average percentage
     * 
     * @param percent
     *                new recent percent
     */
    public void updateAvgPercentage(int percent) {
        avgPercentage = avgPercentage + percent / gamesPlayed;
    }

    /**
     * increments number of games played
     * 
     */
    public void updateGamesPlayed() {
        gamesPlayed++;
    }

    /**
     * gets num of coins
     * 
     * @return
     *         number of coins
     */
    public int getCoins() {
        return coins;
    }

    /**
     * getter method for average percentage
     * 
     * @return
     *         avg percentage
     */
    public int getAvgPercentage() {
        return avgPercentage;
    }

    /**
     * getter method for # of games played
     * 
     * @return
     *         how many games played
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * returns the arrayList of animals
     * 
     * @return
     *         arrayList animals
     */
    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    /**
     * increments coins to buy a chest
     * 
     * @param m
     *          cost of chest
     */
    public void buyChest(int m) {
        coins -= m;
    }

    // * actually dont even need this method
    // * because there will only be one animal
    // * of each type, you will change the level
    // * each time, you will not add new
    // * "animal" just because it upgraded.
    // *
    // * Actually I do need this method becuase
    // * even if there is only one animal of each
    // * type, I will not know which animal has
    // * the highest level
    /**
     * calulates the highest level of any one of the animals
     */
    public void calculateHighestLevel() {
        int max = 0;
        int track = 0;
        Iterator<Animal> itr = animals.iterator();
        while (itr.hasNext()) {
            track = itr.next().getLvl();
            if (max < track) {
                max = track;
            }
        }
        highestLevel = max;
    }

    /**
     * updates level of an animal
     * 
     * @param a
     *          the animal to update
     */
    public void updateLvl(Animal a) {
        String name = a.getType();
        for (Animal x : animals) {
            if (name.equals(x.getType())) {
                a.lvlUp();
            }
        }
    }

    /**
     * returns an animal based on rarities.
     * 
     * @param num
     *            random int
     * @return
     *         animal based on rarity of each
     */
    public String rarity(int num) {
        if (num < 40) {
            // Double r = Math.random();
            // rarity = r * 10 + 1;
            // if(r < 5)
            // {
            //
            // }
            return "rabbit";
        } else if (num < 60) {
            return "chicken";
        } else if (num < 75) {
            return "sheep";
        } else if (num < 85) {
            return "goat";
        } else if (num < 95) {
            return "pig";
        }
        return "horse";
    }

    // * Will connect to when a player clicks a button.
    // * When the button is clicked, this method will be called
    // * In this method, a "memory game" object will be created
    // * Then we will call it's methods on it, like "play" and "getStats" or
    // * "getPercentage"
    // * Then I will process it ORR the methods can just provide for me the results
    // * like win or lose or number of coins gained
    // * 5/12/23 idea...
    // * Game is played using .play() and this method from the MemoryGame class will
    // * return the percentage accuracy for this certain game, and I will calculate
    // * how many coins the player would gain from this percentage and update the
    // * avgPercentage
    // *
    // * After game is played, you will update all the values, then will call the
    // * getter methods from the GUI class/file

    /**
     * starts the game
     * 
     * @param pw
     *           Player's gui window
     */
    public void playGame(PlayerWindow pw) {
        MemoryPuzzle game = new MemoryPuzzle(gameLvl()); // will call the method that tells the level of game, and input
                                                         // it as a parameter
        MemoryPuzzleWindow w = new MemoryPuzzleWindow(game, this, pw);
        // while (true) {
        // if (w.isGameOver()) {
        // int percent = w.getEfficiency();
        // System.out.println("percent:" + percent);
        // updateCoins(percent);
        // updateGamesPlayed();
        // updateAvgPercentage(percent);
        // return;
        // }
        // }

    }

    // make a method that tells when the player will play easy, medium or hard
    // Find the "total" lvl of all animals
    /**
     * decides memorypuzzle level
     * 
     * @return
     *         String of easy, medium, or hard
     */
    public String gameLvl() {
        int total = 0;
        for (Animal a : animals) {
            total += a.getLvl();
        }
        if (total < 10) {
            return "easy";
        } else if (total < 20) {
            return "medium";
        }
        return "hard";
    }

    /**
     * simulates opening a mysterbox using rarity function
     * 
     * @return
     *         String of animal unlocked
     */
    public String mysteryBox() {
        int num = (int) (Math.random() * 100 + 1);
        return rarity(num);
    }

}