
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

    private boolean isPlaying = false;
    
    public Music() {
	reset();
    }
    
    private boolean isPlaying() {
	return isPlaying;
    }

    private void reset() {
	try {
	    BGM = new AudioStream(new FileInputStream("./src/Music/music.wav"));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException error) {
	    error.printStackTrace();
	}
    }

    public void start() {
	stop();
	reset();
	isPlaying = true;
	AudioPlayer.player.start(BGM);
	MGP.start(loop);

    }

    public void stop() {
	isPlaying = false;
	AudioPlayer.player.stop(BGM);
    }
}
