import javax.swing.JOptionPane;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

/**
 * class MusicPlayer, contains static methods to call music from other places.
 */
public class MusicPlayer {


    /**
     * plays any music file, but is used mostly to play background music
     * 
     * @param loc
     *            location of file
     */
    public static void playMusic(String loc) {

        try {

            File music_path = new File(loc);

            if (music_path.exists()) {

                AudioInputStream audioInp = AudioSystem.getAudioInputStream(music_path);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInp);
                clip.start();

            } else {

                System.out.println("can't find file");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * plays a random animal sound effect
     */
    public static void playRandAnimal() {

        String loc = "media/moo.wav";
        int rand = (int) (Math.random() * 3);
        System.out.println(rand);

        if (rand == 1) {
            loc = "media/chicken.wav";
        } else if (rand == 2) {
            loc = "media/sheep.wav";
        }

        try {

            File music_path = new File(loc);

            if (music_path.exists()) {

                AudioInputStream audioInp = AudioSystem.getAudioInputStream(music_path);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInp);
                clip.start();

            } else {

                System.out.println("can't find file");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}