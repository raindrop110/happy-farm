
/**
 * class Animal, stores an animal type
 */
public class Animal implements Comparable<Animal> {
    private String type;
    private int lvl;
    private boolean unlocked;

    /**
     *  An animal object
     * @param t Constructs an animal of type t
     * @param l Initial level of animal
     */
    public Animal(String t, int l){
        type = t;
        lvl = l;
        unlocked = false;
    }

    /**
     * Get type of animal
     * @return String type
     */
    public String getType() {
        return type;
    };

    /**
     * Increases animal level by 1
     */
    public void lvlUp() {
        lvl ++;
    }

    /**
     * Get current level of animal
     * @return int level
     */
    public int getLvl() {
        return lvl;
    }

    /**
     * Shows whether or not the animal is unlocked
     * @return boolean is unlocked or not
     */
    public boolean isUnlocked() {
        return unlocked;
    }

    /**
     * Unlocks this animal
     */
    public void unlock() {
        unlocked = true;
    }

    /**
     * Compare To method for TreeSet
     * @param other Animal object
     * @return int that compares the two animal types
     */
    public int compareTo(Animal other) {
        return type.compareTo(other.getType());
    }
}
