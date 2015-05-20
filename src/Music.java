
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Music extends javafx.application.Application {

    private static final String MEDIA_URL = "http://216.227.134.162/ost/pokemon-black-and-white/bpgkldxvrm/103-title.mp3";

    @Override
    public void start(Stage primaryStage) {
	primaryStage.setTitle("Music");
	Group root = new Group();
	Scene scene = new Scene(root, 0, 0);

	primaryStage.setScene(scene);
	primaryStage.sizeToScene();
	primaryStage.show();
	
	// create media player
	Media media = new Media(MEDIA_URL);
	MediaPlayer mediaPlayer = new MediaPlayer(media);
	mediaPlayer.setAutoPlay(true);

	// create mediaView and add media player to the viewer
	MediaView mediaView = new MediaView(mediaPlayer);
	((Group) scene.getRoot()).getChildren().add(mediaView);
    }

    public void main(String[] args) {
	launch(args);
    }

}
