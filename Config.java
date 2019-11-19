package Game;

import javafx.scene.Group;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class Config
{
    // WIDTH,HEIGHT
    public static final long WIDTH = 1024;

    public static final long HEIGHT = 768;

    public static final long TPS = 20;

    public static final long GAME_NSPT = Math.round(1000000000.0 / TPS);

    // TILES
    public static final long TILE = 64;

    public static final long TILE_HORIZONTAL = 16;

    public static final long TILE_VERTICAL = 12;

    public static final int NUMBEROFTILES = (int) (TILE_HORIZONTAL * TILE_VERTICAL);

    public static final long SCREEN_WIDTH = TILE * TILE_HORIZONTAL;

    public static final long SCREEN_HEIGHT = TILE * TILE_VERTICAL;

    //TIME
    public static final double Tick = 500;

    //MAX NUMBER OF TOWERS
    public static final int MaxTowerNumber = 20;

    //MAX NUMBER OF ENEMIES
    public static final int MaxBossEnemyNumber = 4;
    public static final int MaxNormalEnemyNumber = 25;
    public static final int MaxSmallerEnemyNumber = 64;
    public static final int MaxEnemies = MaxBossEnemyNumber + MaxNormalEnemyNumber + MaxSmallerEnemyNumber;

    //Path of the map
    public static final double SpawnX = 510.0;
    public static final double SpawnY = 0.0;
    public static final double VanishX = 527.0;
    public static final double VanishY = 768.0;
    public static final double MovingTime = 20000.0;
    public static final int CycleCount = 1;

    public static Path PathforMap(Group group)
    {
        Path MapPath = new Path();
        MoveTo Spawn = new MoveTo(SpawnX,SpawnY);
        MapPath.getElements().add(Spawn);
        LineTo Line1 = new LineTo(SpawnX,80.6);
        MapPath.getElements().add(Line1);
        LineTo Line2 = new LineTo(181,80.6);
        MapPath.getElements().add(Line2);
        LineTo Line3 = new LineTo(181,563.9);
        MapPath.getElements().add(Line3);
        LineTo Line4 = new LineTo(682.7,563.9);
        MapPath.getElements().add(Line4);
        LineTo Line5 = new LineTo(682.7,290);
        MapPath.getElements().add(Line5);
        LineTo Line6 = new LineTo(512,290);
        MapPath.getElements().add(Line6);
        LineTo Line7 = new LineTo(512,456);
        MapPath.getElements().add(Line7);
        LineTo Line8 = new LineTo(336.2,456);
        MapPath.getElements().add(Line8);
        LineTo Line9 = new LineTo(336.2,177.2);
        MapPath.getElements().add(Line9);
        LineTo Line10 = new LineTo(848.2,177.2);
        MapPath.getElements().add(Line10);
        LineTo Line11 = new LineTo(848.2,682);
        MapPath.getElements().add(Line11);
        LineTo Line12 = new LineTo(VanishX,682);
        MapPath.getElements().add(Line12);
        LineTo Line13 = new LineTo (VanishX,VanishY);
        MapPath.getElements().add(Line13);
        return MapPath;
    }
    //Timelines
    public static double PreparingTime = 0; //10000
    public static double BreakTime = 15000;
}
