package Entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;


import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class NormalTower extends Tower
{
    public static final Image image = new Image("GameFiles/Image/Entities Image/NormalTower.png");
    public NormalTower(Group group, double x, double y, double r, double dx, double dy, double dr, double health, double damage)
    {
        super(group, image, x, y, r, dx, dy, dr, health, damage);
        this.setDamage(Settings.NORMAL_BULLET_STRENGTH);
        targetRange = Settings.NORMAL_TOWER_RANGE;
        speed = Settings.NORMAL_TOWER_SPEED;
    }
}
