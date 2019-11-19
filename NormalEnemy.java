package Entities;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class NormalEnemy extends Enemy
{
    public final Image NormalEnemyImage = new Image("GameFiles/Image/Entities Image/NormalEnemy.png");
    public NormalEnemy(Group group, Image image, double x, double y, double r, double dx, double dy, double dr, double health, double shield, double damage)
    {
        super(group, image, x, y, r, dx, dy, dr, health, shield, damage);
        this.image = NormalEnemyImage;
        HealthMax = Settings.NORMAL_ENEMY_HEALTH;
        speed = Settings.NORMAL_ENEMY_SPEED;
    }
}
