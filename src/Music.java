
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
    private String[] paths = new String[V.musicList.length];

    private ContinuousAudioDataStream loop = null;

    private boolean isPlaying = false, stopped = true;
    
    private int index = 0;

    public Music(String[] paths) {
	for (int i = 0; i < V.musicList.length; i++) {
	    System.out.println(paths[i]);
	    this.paths[i] = paths[i];
	}
	reset();
    }

    private boolean isPlaying() {
	return isPlaying;
    }

    private void reset() {
	try {
	    BGM = new AudioStream(new FileInputStream(paths[index]));
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException error) {
	    error.printStackTrace();
	} catch (NullPointerException nullError) {
	    nullError.printStackTrace();
	}
    }

    public void start() {
	stop();
	reset();
	isPlaying = true;
	stopped = false;
	AudioPlayer.player.start(BGM);
	MGP.start(loop);

    }

    public void stop() {
	isPlaying = false;
	stopped = true;
	AudioPlayer.player.stop(BGM);
    }

    public void nextSong() {
	if (index++ >= paths.length) {
	    index = 0;
	}
	start();
    }

    public void set(String[] paths) {
	this.paths = paths;
	reset();
    }
}
