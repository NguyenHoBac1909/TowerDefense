package Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public final class GameStage
{
    private long Height;
    private long Width;
    private Stage Game;

    public GameStage(long height,long width,Stage Game,Group group) throws FileNotFoundException
    {
        this.Game = Game;
        this.Height = height;
        this.Width = width;

        //Background
        Image Background = new Image(new FileInputStream("C:\\Users\\ADMIN\\IdeaProjects\\Tower of Defense FX\\src\\GameFiles\\Image\\Map selection background.png"));
        ImageView ViewBackground = new ImageView(Background);
        ViewBackground.setX(0);
        ViewBackground.setY(0);
        ViewBackground.setFitHeight(this.Height);
        ViewBackground.setFitWidth(this.Width);

        //Map for selection and Text
        Image Map = new Image( new FileInputStream("C:\\Users\\ADMIN\\IdeaProjects\\Tower of Defense FX\\src\\GameFiles\\Image\\Map 1 - Smaller version.jpg"));
        ImageView ViewMap = new ImageView(Map);
        Button MapButton = new Button("",ViewMap);
        MapButton.setShape(new Rectangle(300,210));
        MapButton.setPrefSize(320,220);
        MapButton.setLayoutX(300);
        MapButton.setLayoutY(250);
        Text MapSelection = new Text();
        MapSelection.setText("Choose Map:");
        MapSelection.setX(100);
        MapSelection.setY(180);
        MapSelection.setFont(Font.font("helvetica", FontWeight.BOLD, FontPosture.REGULAR, 50));
        MapSelection.setFill(Color.FORESTGREEN);

        //Music for Map Selection
        String MapSelectionSoundPath = "C:\\Users\\ADMIN\\IdeaProjects\\Tower of Defense FX\\src\\GameFiles\\Sound\\Map selection sound.mp3";
        Media MapSelectionSound = new Media(new File(MapSelectionSoundPath).toURI().toString());
        MediaPlayer MapSelectionSoundPlayer = new MediaPlayer(MapSelectionSound);
        MapSelectionSoundPlayer.setAutoPlay(true);
        MediaView ViewMapSelectionSound = new MediaView(MapSelectionSoundPlayer);
        MapSelectionSoundPlayer.setCycleCount(5);
        MapSelectionSoundPlayer.play();

        //If Map button is clicked
        MapButton.setOnMouseClicked(e ->
                {
                    MapSelectionSoundPlayer.stop();
                    try
                    {
                        GameField Field = new GameField(Config.HEIGHT,Config.WIDTH,this.Game,group);
                    }
                    catch (FileNotFoundException ex)
                    {
                        ex.printStackTrace();
                    }
                }
        );

        //Group of things
        Group StageGroup = new Group(ViewBackground,MapButton,MapSelection,ViewMapSelectionSound);
        Game.setScene(new Scene(StageGroup,this.Width,this.Height));
        Game.show();
    }
}
