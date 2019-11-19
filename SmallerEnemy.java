package Entities;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SmallerEnemy extends Enemy
{
    public static final Image SmallerEnemyImage = new Image("GameFiles/Image/Entities Image/SmallerEnemy.png");
    public SmallerEnemy(Group group, Image image, double x, double y, double r, double dx, double dy, double dr, double health, double shield, double damage) throws FileNotFoundException
    {
        super(group, image, x, y, r, dx, dy, dr, health, shield, damage);
        this.image = SmallerEnemyImage;
        HealthMax = Settings.SMALLER_ENEMY_HEALTH;
        speed = Settings.SMALLER_ENEMY_SPEED;
        this.imageView = new ImageView(new Image("GameFiles/Image/Entities Image/SmallerEnemy.png"));
    }
}
