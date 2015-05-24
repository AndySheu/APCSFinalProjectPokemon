
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Music implements LineListener {

    private AudioPlayer MGP = AudioPlayer.player;
    private AudioStream BGM;
    private AudioData MD;
    private String[] paths;

    private ContinuousAudioDataStream loop = null;

    private boolean isPlaying = false, stopped = true;

    public Music(String[] paths) {
	this.paths = paths;
	reset();
    }

    private boolean isPlaying() {
	return isPlaying;
    }

    public void update(LineEvent event) {
	LineEvent.Type type = event.getType();

	if (type == LineEvent.Type.START) {
	    isPlaying = true;
	} else if (type == LineEvent.Type.STOP) {
	    isPlaying = false;
	    System.out.println("HERE");
	    if (!stopped) {
		nextSong();
	    }
	}

    }

    private void reset() {
	try {
	    BGM = new AudioStream(new FileInputStream(paths[0]));
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
	String temp = null;
	for (int i = 0; i < paths.length - 1; i++) {
	    try {
		paths[i] = paths[i + 1];
		paths[i + 1] = null;
	    } catch (NullPointerException e) {

	    }
	}
	if (paths[0] == null) {
	    System.out.println("FINISHED SONGS");
	}
	start();
    }

    public void set(String[] paths) {
	this.paths = paths;
	reset();
    }
}
