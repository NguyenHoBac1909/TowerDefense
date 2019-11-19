package Game;

import java.io.File;
import java.util.*;

import Entities.*;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.FileNotFoundException;

import java.io.FileInputStream;

public final class GameField implements GameEntities
{
    public int i;
    public int step;
    private long Height;
    private long Width;
    private Stage Game;

    Group FieldGroup = new Group();

    Text goldText = new Text();
    long gold = 200;

    public Image SmallerEnemiesImage = new Image("GameFiles/Image/Entities Image/SmallerEnemy.png");
    public Image NormalEnemiesImage = new Image("GameFiles/Image/Entities Image/NormalEnemy.png");
    public Image BossEnemiesImage = new Image("GameFiles/Image/Entities Image/BossEnemy.png");

    public ImageView ViewSmallerEnemies = new ImageView("GameFiles/Image/Entities Image/SmallerEnemy.png");
    public ImageView ViewNỏrmalEnemies = new ImageView("GameFiles/Image/Entities Image/NormalEnemy.png");
    public ImageView ViewBossEnemies = new ImageView("GameFiles/Image/Entities Image/BossEnemy.png");

    private void createGoldLayer()
    {
        goldText.setFont( Font.font( null, FontWeight.BOLD, 48));
        goldText.setStroke(Color.BLACK);
        goldText.setFill(Color.RED);

        FieldGroup.getChildren().add( goldText);

        goldText.setText( String.valueOf( gold));

        double x = (1024 - goldText.getBoundsInLocal().getWidth()) / 2;
        double y = 0;
        goldText.relocate(x, y);

        goldText.setBoundsType(TextBoundsType.VISUAL);
    }

    private void updateScore()
    {
        goldText.setText (String.valueOf( gold));
    }

    private void checkCollisions()
    {
        for (Tower Tower : Towers)
        {
            for (SmallerEnemy Target: SmallerEnemies)
            {
                if (Tower.hitsTarget(Target) == true)
                {
                    List<Enemy> Targets = new ArrayList<Enemy>();
                    Targets.add(Target);
                    Tower.checkRemovability();
                    if (Tower.Target != null)
                    {
                        Tower.FireBullet(Target);
                        Target.getDamagedBy(Tower);
                    }
                    if (!Tower.Target.isAlive())
                    {
                        Target.setRemovable(true);
                        gold = gold + 20;
                        updateScore();
                        Target.removeFromGroup();
                    }
                }
            }
        }
    }

    private void removeSprites(List<? extends SpriteBase> spriteList)
    {
        Iterator<? extends SpriteBase> iter = spriteList.iterator();
        while( iter.hasNext())
        {
            SpriteBase sprite = iter.next();

            if( sprite.isRemovable()) {

                // remove from layer
                sprite.removeFromGroup();

                // remove from list
                iter.remove();
            }
        }
    }

    public GameField(long height, long width, Stage Game,Group group) throws FileNotFoundException
    {
        this.Game = Game;
        this.Height = height;
        this.Width = width;

        //Group of things

        //Map 1
        Image Map1 = new Image(new FileInputStream("C:\\Users\\ADMIN\\IdeaProjects\\Tower of Defense FX\\src\\GameFiles\\Image\\Map 1.jpg"));
        ImageView ViewMap1 = new ImageView(Map1);
        ViewMap1.setX(0);
        ViewMap1.setY(0);
        ViewMap1.setFitHeight(this.Height);
        ViewMap1.setFitWidth(this.Width);
        FieldGroup.getChildren().add(ViewMap1);

        //Create Money Text
        createGoldLayer();

        //Sounds for Map1
        String Map1SoundPath = "C:\\Users\\ADMIN\\IdeaProjects\\Tower of Defense FX\\src\\GameFiles\\Sound\\Shamburger.mp3";
        Media Map1Sound = new Media(new File(Map1SoundPath).toURI().toString());
        MediaPlayer PlayMap1Sound = new MediaPlayer(Map1Sound);
        MediaView ViewMap1Sound = new MediaView(PlayMap1Sound);
        FieldGroup.getChildren().add(ViewMap1Sound);
        PlayMap1Sound.setCycleCount(1);
        PlayMap1Sound.play();

        /*Create Tower Buttons Selection*/
        //Create MachineGunTower Button
        Image MachineGunTowerImage = new Image("GameFiles/Image/Entities Image/MachineGunTower.png");
        ImageView ViewMachineGunTower = new ImageView(MachineGunTowerImage);
        Button MachineGunTowerButton = new Button("",ViewMachineGunTower);
        MachineGunTowerButton.setShape(new Circle(30));
        MachineGunTowerButton.setLayoutX(186.2);
        MachineGunTowerButton.setLayoutY(665.6);
        MachineGunTowerButton.setPrefSize(60,60);
        FieldGroup.getChildren().add(MachineGunTowerButton);

        //Create NormalTower Button
        Image NormalTowerImage = new Image("GameFiles/Image/Entities Image/NormalTower.png");
        ImageView ViewNormalTower = new ImageView(NormalTowerImage);
        Button NormalTowerButton = new Button("",ViewNormalTower);
        NormalTowerButton.setShape(new Circle(30));
        NormalTowerButton.setLayoutX(280);
        NormalTowerButton.setLayoutY(665.6);
        NormalTowerButton.setPrefSize(60,60);
        FieldGroup.getChildren().add(NormalTowerButton);

        //Create SniperTower Button
        Image SniperTowerImage = new Image("GameFiles/Image/Entities Image/SniperTower.png");
        ImageView ViewSniperTower = new ImageView(SniperTowerImage);
        Button SniperTowerButton = new Button("",ViewSniperTower);
        SniperTowerButton.setShape(new Circle(30));
        SniperTowerButton.setLayoutX(379);
        SniperTowerButton.setLayoutY(665.6);
        SniperTowerButton.setPrefSize(60,60);
        FieldGroup.getChildren().add(SniperTowerButton);

        //Setting places to place towers
        TowerCoordinate[0] = new Coordinate(51.7,429.6);
        TowerCoordinate[1] = new Coordinate(51.7,241.7);
        TowerCoordinate[2] = new Coordinate(217.2,188);
        TowerCoordinate[3] = new Coordinate(217.2,386.79);
        TowerCoordinate[4] = new Coordinate(382.7,311.5);
        TowerCoordinate[5] = new Coordinate(574,69.8);
        TowerCoordinate[6] = new Coordinate(574,392);
        TowerCoordinate[7] = new Coordinate(822.3,80.56);
        TowerCoordinate[8] = new Coordinate(734.4,424.3);
        TowerCoordinate[9] = new Coordinate(910.2,290);

        for (i = 0;i < 10;i++)
        {
            TowerCoordinate[i].PlaceTowerStatus = true;
            TowersCoordinate.add(TowerCoordinate[i]);
        }

        /*Processing the Game*/
        Queue<ImageView> ViewMachineGunTowerQueue = new LinkedList<>();
        Queue<ImageView> ViewNormalTowerQueue = new LinkedList<>();
        Queue<ImageView> ViewSniperTowerQueue = new LinkedList<>();
        Queue<ImageView> ViewTowerQueue = new LinkedList<>();

        //EventHandler for MachineGunTower
        Image MachineGunTowerImageSample = new Image("GameFiles/Image/Entities Image/MachineGunTower.png");
        for (i = 0;i < Config.MaxTowerNumber;i++)
        {
            ViewMachineGunTowerSample[i] = new ImageView(MachineGunTowerImageSample);
        }
        for (i = 0;i < Config.MaxTowerNumber;i++)
        {
            ViewMachineGunTowerQueue.add(ViewMachineGunTowerSample[i]);
        }
        EventHandler<MouseEvent> PlaceMachineGunTowerEvents = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                step = 1;
                FieldGroup.getChildren().add(ViewMachineGunTowerQueue.peek());
                EventHandler<MouseEvent> mouseMove = new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event1)
                    {
                        if (step == 1)
                        {
                            (ViewMachineGunTowerQueue.peek()).setLayoutX(event1.getX() - (ViewMachineGunTowerQueue.peek()).getLayoutBounds().getWidth()/2);
                            (ViewMachineGunTowerQueue.peek()).setLayoutY(event1.getY() - (ViewMachineGunTowerQueue.peek()).getLayoutBounds().getHeight()/2);
                        }
                    }
                };

                EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event2)
                    {
                        if (step == 1)
                        {
                            double x = event2.getX() - (ViewMachineGunTowerQueue.peek()).getLayoutBounds().getWidth() / 2;
                            double y = event2.getY() - (ViewMachineGunTowerQueue.peek()).getLayoutBounds().getHeight() / 2 ;
                            for (i = 0;i < TowersCoordinate.size();i++)
                            {
                                if (x >= TowersCoordinate.get(i).getX() && x <= TowersCoordinate.get(i).getX() + Config.TILE&& y >= TowersCoordinate.get(i).getY() && x <= TowersCoordinate.get(i).getY() + Config.TILE && TowersCoordinate.get(i).checkPlaceTower() == true && TowersCoordinate.size() > 0)
                                {
                                    TowersCoordinate.get(i).PlaceTowerStatus = false;
                                    (ViewMachineGunTowerQueue.peek()).setLayoutX(x);
                                    (ViewMachineGunTowerQueue.peek()).setLayoutY(y);
                                    Towers.add(new MachineGunTower(FieldGroup,0,0,0,0,0,0,100,100));
                                    TowersCoordinate.remove(i);
                                    String PlacingTowerSoundPath = "C:\\Users\\ADMIN\\IdeaProjects\\Tower of Defense FX\\src\\GameFiles\\Sound\\Placing Towers Sound.mp3";
                                    Media PlacingTowerSound = new Media(new File(PlacingTowerSoundPath).toURI().toString());
                                    MediaPlayer PlayPlacingTowerSound = new MediaPlayer(PlacingTowerSound);
                                    MediaView ViewPlacingTowerSound = new MediaView(PlayPlacingTowerSound);
                                    FieldGroup.getChildren().add(ViewPlacingTowerSound);
                                    PlayPlacingTowerSound.play();
                                    step = 0;
                                    ViewMachineGunTowerQueue.remove();
                                }
                            }
                        }
                    }
                };
                FieldGroup.setOnMouseMoved(mouseMove);
                FieldGroup.setOnMouseClicked(mouseClick);
            }
        };

        //EventHandler for NormalTower
        Image NormalTowerImageSample = new Image("GameFiles/Image/Entities Image/NormalTower.png");
        for (i = 0;i < Config.MaxTowerNumber;i++)
        {
            ViewNormalTowerSample[i] = new ImageView(NormalTowerImageSample);
        }
        for (i = 0;i < Config.MaxTowerNumber;i++)
        {
            ViewNormalTowerQueue.add(ViewNormalTowerSample[i]);
        }
        EventHandler<MouseEvent> PlaceNormalTowerEvents = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                step = 1;
                FieldGroup.getChildren().add(ViewNormalTowerQueue.peek());
                EventHandler<MouseEvent> mouseMove = new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event1)
                    {
                        if (step == 1)
                        {
                            (ViewNormalTowerQueue.peek()).setLayoutX(event1.getX() - (ViewNormalTowerQueue.peek()).getLayoutBounds().getWidth()/2);
                            (ViewNormalTowerQueue.peek()).setLayoutY(event1.getY() - (ViewNormalTowerQueue.peek()).getLayoutBounds().getHeight()/2);
                        }
                    }
                };

                EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event2)
                    {
                        if (step == 1)
                        {
                            double x = event2.getX() - (ViewNormalTowerQueue.peek()).getLayoutBounds().getWidth() / 2;
                            double y = event2.getY() - (ViewNormalTowerQueue.peek()).getLayoutBounds().getHeight() / 2;
                            for (i = 0;i < TowersCoordinate.size();i++)
                            {
                                if (x >= TowersCoordinate.get(i).getX() && x <= TowersCoordinate.get(i).getX() + Config.TILE&& y >= TowersCoordinate.get(i).getY() && x <= TowersCoordinate.get(i).getY() + Config.TILE && TowersCoordinate.get(i).checkPlaceTower() == true && TowersCoordinate.size() > 0)
                                {
                                    TowersCoordinate.get(i).PlaceTowerStatus = false;
                                    (ViewNormalTowerQueue.peek()).setLayoutX(x);
                                    (ViewNormalTowerQueue.peek()).setLayoutY(y);
                                    TowersCoordinate.remove(i);
                                    Towers.add(new NormalTower(FieldGroup, 0, 0, 0, 0, 0, 0, 100, 100));
                                    String PlacingTowerSoundPath = "C:\\Users\\ADMIN\\IdeaProjects\\Tower of Defense FX\\src\\GameFiles\\Sound\\Placing Towers Sound.mp3";
                                    Media PlacingTowerSound = new Media(new File(PlacingTowerSoundPath).toURI().toString());
                                    MediaPlayer PlayPlacingTowerSound = new MediaPlayer(PlacingTowerSound);
                                    MediaView ViewPlacingTowerSound = new MediaView(PlayPlacingTowerSound);
                                    FieldGroup.getChildren().add(ViewPlacingTowerSound);
                                    PlayPlacingTowerSound.play();
                                    step = 0;
                                    ViewNormalTowerQueue.remove();
                                }
                            }
                        }
                    }
                };
                FieldGroup.setOnMouseMoved(mouseMove);
                FieldGroup.setOnMouseClicked(mouseClick);
            }
        };

        //EventHandler for NormalTower
        Image SniperTowerImageSample = new Image("GameFiles/Image/Entities Image/SniperTower.png");
        for (i = 0;i < Config.MaxTowerNumber;i++)
        {
            ViewSniperTowerSample[i] = new ImageView(SniperTowerImageSample);
        }
        for (i = 0;i < Config.MaxTowerNumber;i++)
        {
            ViewSniperTowerQueue.add(ViewSniperTowerSample[i]);
        }
        EventHandler<MouseEvent> PlaceSniperTowerEvents = new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent event)
            {
                step = 1;
                FieldGroup.getChildren().add(ViewSniperTowerQueue.peek());
                EventHandler<MouseEvent> mouseMove = new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event1)
                    {
                        if (step == 1)
                        {
                            (ViewSniperTowerQueue.peek()).setLayoutX(event1.getX() - (ViewSniperTowerQueue.peek()).getLayoutBounds().getWidth()/2);
                            (ViewSniperTowerQueue.peek()).setLayoutY(event1.getY() - (ViewSniperTowerQueue.peek()).getLayoutBounds().getHeight()/2);
                        }
                    }
                };

                EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent event2)
                    {
                        if (step == 1)
                        {
                            double x = event2.getX() - (ViewSniperTowerQueue.peek()).getLayoutBounds().getWidth() / 2;
                            double y = event2.getY() - (ViewSniperTowerQueue.peek()).getLayoutBounds().getHeight() / 2;
                            for (i = 0;i < TowersCoordinate.size();i++)
                            {
                                if (x >= TowersCoordinate.get(i).getX() && x <= TowersCoordinate.get(i).getX() + Config.TILE&& y >= TowersCoordinate.get(i).getY() && x <= TowersCoordinate.get(i).getY() + Config.TILE && TowersCoordinate.get(i).checkPlaceTower() == true && TowersCoordinate.size() > 0)
                                {
                                    TowersCoordinate.get(i).PlaceTowerStatus = false;
                                    (ViewSniperTowerQueue.peek()).setLayoutX(x);
                                    (ViewSniperTowerQueue.peek()).setLayoutY(y);
                                    TowersCoordinate.remove(i);
                                    Towers.add(new SniperTower(FieldGroup, 0, 0, 0, 0, 0, 0, 100, 100));
                                    String PlacingTowerSoundPath = "C:\\Users\\ADMIN\\IdeaProjects\\Tower of Defense FX\\src\\GameFiles\\Sound\\Placing Towers Sound.mp3";
                                    Media PlacingTowerSound = new Media(new File(PlacingTowerSoundPath).toURI().toString());
                                    MediaPlayer PlayPlacingTowerSound = new MediaPlayer(PlacingTowerSound);
                                    MediaView ViewPlacingTowerSound = new MediaView(PlayPlacingTowerSound);
                                    FieldGroup.getChildren().add(ViewPlacingTowerSound);
                                    PlayPlacingTowerSound.play();
                                    step = 0;
                                    ViewSniperTowerQueue.remove();
                                }
                            }
                        }
                    }
                };
                FieldGroup.setOnMouseMoved(mouseMove);
                FieldGroup.setOnMouseClicked(mouseClick);
            }
        };
        MachineGunTowerButton.setOnMouseClicked(PlaceMachineGunTowerEvents);
        SniperTowerButton.setOnMouseClicked(PlaceSniperTowerEvents);
        NormalTowerButton.setOnMouseClicked(PlaceNormalTowerEvents);

        //Add all Enemies and Towers
        Enemies.addAll(BossEnemies);
        Enemies.addAll(SmallerEnemies);
        Enemies.addAll(NormalEnemies);

        //Create Enemy Units
        for (i = 0;i < Config.MaxBossEnemyNumber;i++)
        {
            BOSS_ENEMIES[i] = new BossEnemy(FieldGroup, BossEnemiesImage,0,0,0,0,0,0,Settings.BOSS_ENEMY_HEALTH,Settings.BOSS_ENEMY_ARMOR,0);
            BossEnemies.add(BOSS_ENEMIES[i]);
        }

        for (i = 0;i < Config.MaxNormalEnemyNumber;i++)
        {
            NORMAL_ENEMIES[i] = new NormalEnemy(FieldGroup, NormalEnemiesImage,0,0,0,0,0,0,Settings.NORMAL_ENEMY_HEALTH,Settings.NORMAL_ENEMY_ARMOR,0);
            NormalEnemies.add(NORMAL_ENEMIES[i]);
        }

        for (i = 0;i < Config.MaxSmallerEnemyNumber;i++)
        {
            SMALLER_ENEMIES[i] = new SmallerEnemy(FieldGroup, SmallerEnemiesImage,0,0,0,0,0,0,Settings.SMALLER_ENEMY_HEALTH,Settings.SMALLER_ENEMY_ARMOR,0);
            SmallerEnemies.add(SMALLER_ENEMIES[i]);
        }

        //Enemies go to war & Transition on the path & Timelines
        Timeline TimeLine = new Timeline();
        for (i = 0;i < SmallerEnemies.size();i++)
        {
            Enemy enemy = SmallerEnemies.get(i);
            Keys[i] = new KeyFrame(Duration.millis(i * Config.Tick + Config.PreparingTime),event ->
            {
                enemy.move(Config.PathforMap(FieldGroup));
                //enemy.ShowHP();
            });
            TimeLine.getKeyFrames().add(Keys[i]);
        }
        for (i = SmallerEnemies.size();i < SmallerEnemies.size() + NormalEnemies.size();i++)
        {
            Enemy enemy = NormalEnemies.get(i - SmallerEnemies.size());
            Keys[i] = new KeyFrame(Duration.millis(Config.PreparingTime + i * Config.Tick + Config.BreakTime),event ->
            {
                enemy.move(Config.PathforMap(FieldGroup));
                //enemy.ShowHP();
            });
            TimeLine.getKeyFrames().add(Keys[i]);
        }
        for (i = SmallerEnemies.size() + NormalEnemies.size();i < Config.MaxEnemies;i++)
        {
            Enemy enemy = BossEnemies.get(i - SmallerEnemies.size() - NormalEnemies.size());
            Keys[i] = new KeyFrame(Duration.millis(Config.PreparingTime + i * Config.Tick + Config.BreakTime * 2),event ->
            {
                enemy.move(Config.PathforMap(FieldGroup));
               // enemy.ShowHP();
            });
            TimeLine.getKeyFrames().add(Keys[i]);
        }
        TimeLine.setAutoReverse(false);
        TimeLine.play();

        //Shoot the Enemies
        AnimationTimer gameLoop = new AnimationTimer()
        {
            public void handle(long now)
            {
                // check if target is still valid
                Towers.forEach( tower -> tower.checkTarget());
                Enemies.forEach(enemy -> enemy.ShowHP());

                // tower movement: find target
                for( Tower tower: Towers)
                {
                    tower.findTarget(Enemies);
                }

                // movement
                Towers.forEach(sprite -> sprite.Rotate());

                // check collisions
                checkCollisions();

                // check if sprite can be removed
                Enemies.forEach(sprite -> sprite.checkRemovability());

                // remove removables from list, layer, etc
                removeSprites(Enemies);

                // update score, health, etc
                updateScore();
            }
        };
        gameLoop.start();

        //Group of things
        Game.setScene(new Scene(FieldGroup,this.Width,this.Height));
        Game.show();
    }
}