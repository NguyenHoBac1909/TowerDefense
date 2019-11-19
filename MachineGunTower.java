package Entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;


import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class MachineGunTower extends Tower
{
    public static final Image image = new Image("GameFiles/Image/Entities Image/MachineGunTower.png");
    public MachineGunTower(Group group, double x, double y, double r, double dx, double dy, double dr, double health, double damage)
    {
        super(group, image, x, y, r, dx, dy, dr, health, damage);
        this.setDamage(Settings.MACHINE_GUN_BULLET_STRENGTH);
        targetRange = Settings.MACHINE_GUN_TOWER_RANGE;
        speed = Settings.MACHINE_GUN_TOWER_SPEED;
    }
}
