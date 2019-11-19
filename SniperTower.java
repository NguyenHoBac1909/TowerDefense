package Entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;


import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SniperTower extends Tower
{
    public static final Image image = new Image("GameFiles/Image/Entities Image/SniperTower.png");
    public SniperTower(Group group, double x, double y, double r, double dx, double dy, double dr, double health, double damage)
    {
        super(group, image, x, y, r, dx, dy, dr, health, damage);
        this.setDamage(Settings.SNIPER_BULLET_STRENGTH);
        targetRange = Settings.SNIPER_TOWER_RANGE;
        speed = Settings.SNIPER_TOWER_SPEED;
    }
}
