package Game;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application implements GameEntities
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("Game.fxml"));
        //Name of the Game
        Text GameName = new Text();
        GameName.setText("Tower Defense");
        GameName.setX(330);
        GameName.setY(150);
        GameName.setFont(Font.font("mohave", FontWeight.BOLD, FontPosture.REGULAR, 60));
        GameName.setFill(Color.DEEPSKYBLUE);

        //Main menu background
        Image MainMenuBackground = new Image(new FileInputStream("C:\\Users\\ADMIN\\IdeaProjects\\Tower of Defense FX\\src\\GameFiles\\Image\\MainMenu.jpg"));
        ImageView ViewMainMenuBackground = new ImageView(MainMenuBackground);
        ViewMainMenuBackground.setX(0);
        ViewMainMenuBackground.setY(0);
        ViewMainMenuBackground.setFitHeight(Config.HEIGHT);
        ViewMainMenuBackground.setFitWidth(Config.WIDTH);

        //Start button
        Image InputStartButtonImage = new Image(new FileInputStream("C:\\Users\\ADMIN\\IdeaProjects\\Tower of Defense FX\\src\\GameFiles\\Image\\Start Button.png"));
        ImageView ViewStartButtonImage = new ImageView(InputStartButtonImage);
        Button StartButton = new Button("");
        StartButton.setGraphic(ViewStartButtonImage);
        StartButton.setPrefSize(200,100);
        StartButton.setLayoutX(400);
        StartButton.setLayoutY(300);

        //Instruction button
        Image InputCreditButtonImage = new Image(new FileInputStream("C:\\Users\\ADMIN\\IdeaProjects\\Tower of Defense FX\\src\\GameFiles\\Image\\Credit Button.png"));
        ImageView ViewCreditButtonImage = new ImageView(InputCreditButtonImage);
        Button CreditButton = new Button("",ViewCreditButtonImage);
        CreditButton.setPrefSize(180,60);
        CreditButton.setLayoutX(440);
        CreditButton.setLayoutY(420);

        //Sound button
        Image InputSoundImage = new Image(new FileInputStream("C:\\Users\\ADMIN\\IdeaProjects\\Tower of Defense FX\\src\\GameFiles\\Image\\SoundButton 2.png"));
        ImageView ViewSoundImage = new ImageView(InputSoundImage);
        Button SoundButton = new Button("",ViewSoundImage);
        SoundButton.setShape(new Circle(30));
        SoundButton.setPrefSize(60,60);
        SoundButton.setLayoutX(900);
        SoundButton.setLayoutY(650);

        //Main sound
        String MainSoundPath = "C:\\Users\\ADMIN\\IdeaProjects\\Tower of Defense FX\\src\\GameFiles\\Sound\\Main Sound.mp3";
        Media MainSound = new Media(new File(MainSoundPath).toURI().toString());
        MediaPlayer MainSoundPlayer = new MediaPlayer(MainSound);
        MainSoundPlayer.setAutoPlay(true);
        MediaView MainSoundView = new MediaView(MainSoundPlayer);

        Group MainGroup = new Group(root,ViewMainMenuBackground,StartButton,CreditButton,SoundButton,MainSoundView,GameName);

        //Put Main sound in SoundButton
        AtomicInteger SoundClickCount = new AtomicInteger(-1);
        SoundButton.setOnMouseClicked(e ->
        {
            SoundClickCount.getAndIncrement();
            if (SoundClickCount.get() % 2 == 0)
            {
                MainSoundPlayer.setCycleCount(5);
                MainSoundPlayer.play();
            }
            else
            {
                MainSoundPlayer.pause();
            }
        });

        //If Start button is clicked
        StartButton.setOnMouseClicked(e ->
                {
                    MainSoundPlayer.stop();
                    try
                    {
                        GameStage GameLoad = new GameStage(Config.HEIGHT,Config.WIDTH,primaryStage,MainGroup);
                    }
                    catch (FileNotFoundException ex)
                    {
                        ex.printStackTrace();
                    }
                }
        );

        //If Credit button is clicked
        CreditButton.setOnMouseClicked(e ->
                {
                    MainSoundPlayer.stop();
                    Image CreditsImage = new Image("GameFiles/Image/Game Credits.png");
                    ImageView ViewCredits = new ImageView(CreditsImage);
                    ViewCredits.setX(0);
                    ViewCredits.setY(0);
                    ViewCredits.setFitHeight(Config.HEIGHT);
                    ViewCredits.setFitWidth(Config.WIDTH);
                    MainGroup.getChildren().add(ViewCredits);
                }
        );

        //Group of things

        primaryStage.setTitle("Tower Defense");
        primaryStage.setScene(new Scene(MainGroup, 1024, 768));
        primaryStage.show();
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
