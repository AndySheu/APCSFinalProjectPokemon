
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {

    static void play() {
	try {
	    String loc = "./Music/music.mp3";
	    Media song = new Media(loc);
	    MediaPlayer mediaPlayer = new MediaPlayer(song);
	    mediaPlayer.play();
	} catch (Exception e) {
	    System.out.println("Error with playing sound.");
	    e.printStackTrace();
	    while(true);
	}
    }
}
