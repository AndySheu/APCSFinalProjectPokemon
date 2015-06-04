
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Music {

    private AudioPlayer MGP = AudioPlayer.player;
    private AudioStream BGM;
    private AudioData MD;

    private ContinuousAudioDataStream loop = null;

    private boolean isPlaying = false, stopped = true;
    
    private int index = 0;
    
    static final String PALLET_TOWN = "./src/Music/FireRed/Pallet Town.wav";
    static final String ROUTE_1 = "./src/Music/FireRed/Route 1.wav";
    static final String TRAINER_BATTLE = "./src/Music/FireRed/Trainer Battle.wav";
    static final String TRAINER_VICTORY = "./src/Music/FireRed/Trainer Victory.wav";
    static final String WILD_BATTLE = "./src/Music/FireRed/Wild Battle.wav";
    static final String WILD_VICTORY = "./src/Music/FireRed/Wild Victory.wav";
    static final String DEFAULT = PALLET_TOWN;
    
    // Pre: None
    // Post: Create a Music object
    public Music() {
    }

    // Pre: None
    // Post: Returns a boolean true if music is playing, false if otherwise
    private boolean isPlaying() {
	return isPlaying;
    }

    // Pre: None
    // Post: Resets AudioStream BGM
    private void reset() {
	try {
	    BGM = new AudioStream(new FileInputStream(V.musicState));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException error) {
	    error.printStackTrace();
	} catch (NullPointerException nullError) {
	    nullError.printStackTrace();
	}
    }

    // Pre: None
    // Post: Calls stop(), reset(), and then starts the audio
    public void start() {
	stop();
	reset();
	isPlaying = true;
	stopped = false;
	AudioPlayer.player.start(BGM);
	MGP.start(loop);

    }

    // Pre: None
    // Post: Stops the audio
    public void stop() {
	isPlaying = false;
	stopped = true;
	AudioPlayer.player.stop(BGM);
    }
}
