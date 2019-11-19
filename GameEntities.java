package Game;

import Entities.*;
import javafx.animation.KeyFrame;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.MediaPlayer;

import java.security.Key;
import java.util.*;
import java.util.Arrays;

public interface GameEntities
{
    //Towers
    Coordinate[] TowerCoordinate = new Coordinate[10];
    List<Coordinate> TowersCoordinate = new ArrayList<Coordinate>();
    ImageView[] ViewMachineGunTowerSample = new ImageView[Config.MaxTowerNumber];
    ImageView[] ViewSniperTowerSample = new ImageView[Config.MaxTowerNumber];
    ImageView[] ViewNormalTowerSample = new ImageView[Config.MaxTowerNumber];

    List<Tower> Towers = new ArrayList<Tower>();

    //Enemies
    BossEnemy[] BOSS_ENEMIES = new BossEnemy[Config.MaxBossEnemyNumber];
    NormalEnemy[] NORMAL_ENEMIES = new NormalEnemy[Config.MaxNormalEnemyNumber];
    SmallerEnemy[] SMALLER_ENEMIES = new SmallerEnemy[Config.MaxSmallerEnemyNumber];

    List<BossEnemy> BossEnemies = new ArrayList<BossEnemy>();
    List<NormalEnemy> NormalEnemies = new ArrayList<NormalEnemy>();
    List<SmallerEnemy> SmallerEnemies = new ArrayList<SmallerEnemy>();
    List<Enemy> Enemies = new ArrayList<Enemy>();

    KeyFrame[] Keys = new KeyFrame[Config.MaxEnemies];
    //List<KeyFrame> Keys = new ArrayList<KeyFrame>();
}
