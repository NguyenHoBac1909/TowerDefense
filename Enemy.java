package Entities;

import Game.GameField;
import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import Game.Config;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.List;

public class Enemy extends SpriteBase
{
    public double HealthMax;
    public double speed;

    public double Shield;
    public double HP;
    public Rectangle HPBar = new Rectangle();
    public Rectangle HPMax = new Rectangle();

    public Enemy(Group EntitiesGroup, Image image, double x, double y, double r, double dx, double dy, double dr, double health, double shield, double damage)
    {
        super(EntitiesGroup, image, x, y, r, dx, dy, dr, health, damage);

        this.Shield = shield;

        this.setHealth(HealthMax);

        this.setDamage(1);

        this.HPMax.setWidth(60);
        this.HPMax.setHeight(7);
        this.HPMax.setFill(Color.DEEPSKYBLUE);
        this.HPBar.setHeight(5);
        this.HPBar.setFill(Color.RED);
        this.EntitiesGroup.getChildren().add(HPMax);
        this.EntitiesGroup.getChildren().add(HPBar);
    }
    public void ShowHP()
    {
        this.HPMax.setX(this.imageView.getTranslateX());
        this.HPMax.setY(this.imageView.getTranslateY() - 10);

        this.HPBar.setX(this.imageView.getTranslateX());
        this.HPBar.setY(this.imageView.getTranslateY() - 10);

        this.HPBar.setWidth(this.HP / HealthMax * 6);
    }
    public double getShield()
    {
        return this.Shield;
    }
    public void setShield(double shield)
    {
        this.Shield = shield;
    }
    @Override
    public void checkRemovability()
    {
        if( Double.compare( getY(), Game.Config.HEIGHT) > 0)
        {
            setRemovable(true);
        }
    }
    /*
    public void addToGroup()
    {
        super.addToGroup();
        // create health bar; has to be created here because addToLayer is called in super constructor
        // and it wouldn't exist yet if we'd create it as class member
        HealthBar = new HealthBar();
        this.EntitiesGroup.getChildren().add(this.HealthBar);
    }

    public void removeFromGroup()
    {
        super.removeFromGroup();
        this.EntitiesGroup.getChildren().remove(this.HealthBar);
    }
    */
    public void move(Path path)
    {
        PathTransition MapPathTransition = new PathTransition();
        MapPathTransition.setDuration(Duration.millis(Config.MovingTime));
        MapPathTransition.setCycleCount(Config.CycleCount);
        MapPathTransition.setPath(path);
        MapPathTransition.setNode(this.imageView);
        MapPathTransition.play();
        this.EntitiesGroup.getChildren().add(this.imageView);
    }

    /**
     * Health as a value from 0 to 1.
     * @return
     */
}