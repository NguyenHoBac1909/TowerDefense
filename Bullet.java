package Entities;

import Game.Coordinate;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet
{
    public static final Image BulletImage = new Image("GameFiles/Image/Entities Image/NormalBullet.png");
    public static ImageView ViewBullet;
    public static final double speed = Settings.NORMAL_BULLET_SPEED;
    public static double damage;
    public static double x;
    public static double y;
    public Group group;
    public static Coordinate BulletCoordinate;
    public Bullet()
    {

    }
    public Bullet(Group group, double damage, double x, double y)
    {
        this.ViewBullet = new ImageView(BulletImage);
        this.group = group;
        this.damage = damage;
        this.x = x;
        this.y = y;
        BulletCoordinate.setX(x);
        BulletCoordinate.setY(y);
    }
    public double getX()
    {
        return this.x;
    }
    public double getY()
    {
        return this.y;
    }
    public void setX(double x)
    {
        this.x = x;
    }
    public void setY(double y)
    {
        this.y = y;
    }
    public ImageView getViewBullet()
    {
        return this.ViewBullet;
    }
    public void setViewBullet(ImageView ViewBullet)
    {
        this.ViewBullet = ViewBullet;
    }
    public Coordinate getCoordinate()
    {
        return this.getCoordinate();
    }
    public void setCoordinate(Coordinate coordinate)
    {
        this.BulletCoordinate.setX(coordinate.getX());
        this.BulletCoordinate.setY(coordinate.getY());
        this.x = coordinate.getX();
        this.y = coordinate.getY();
    }
    public void setCoordinate(double x,double y)
    {
        this.BulletCoordinate.setX(x);
        this.BulletCoordinate.setY(y);
        this.x = x;
        this.y = y;
    }
    /*public void Move(Coordinate coordinate1,Coordinate coordinate2,double time)
    {
        double S = Math.sqrt((coordinate2.x - coordinate1.x) * (coordinate2.x - coordinate1.x) + (coordinate2.y - coordinate1.y) * (coordinate2.y - coordinate1.y));
        double XVelocity = (coordinate2.x - coordinate1.x) / time;
        double YVelocity = (coordinate2.y - coordinate1.y) / time;
        boolean Direction = true;
        while(Direction == true)
        {
            if (this.x == x && this.y == y)
            {
                Direction = false;
            }
            else
            {
                this.x = this.x + XVelocity;
                this.y = this.y + YVelocity;
            }
        }
    }*/
}
