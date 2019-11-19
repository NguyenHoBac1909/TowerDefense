package Entities;

import Game.Coordinate;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public abstract class SpriteBase
{
    public Image image;
    public ImageView imageView;

    public Group EntitiesGroup;

    double x;
    double y;
    double r;

    double dx;
    double dy;
    double dr;

    public double Health;
    double damage;

    boolean removable = false;

    double w;
    double h;

    boolean canMove = true;

    public SpriteBase(Group EntitiesGroup, Image image, double x, double y, double r, double dx, double dy, double dr, double Health, double damage)
    {
        this.EntitiesGroup = EntitiesGroup;
        this.image = image;
        this.x = x;
        this.y = y;
        this.r = r;
        this.dx = dx;
        this.dy = dy;
        this.dr = dr;

        this.Health = Health;
        this.damage = damage;

        this.imageView = new ImageView(image);
        this.imageView.relocate(x, y);
        this.imageView.setRotate(r);

        this.w = image.getWidth(); // imageView.getBoundsInParent().getWidth();
        this.h = image.getHeight(); // imageView.getBoundsInParent().getHeight();

        //addToGroup();
    }

    public void addToGroup()
    {
        this.EntitiesGroup.getChildren().add(this.imageView);
    }

    public void removeFromGroup()
    {
        this.EntitiesGroup.getChildren().remove(this.imageView);
    }

    public Group getEntitiesGroup()
    {
        return this.EntitiesGroup;
    }

    public void setEntitiesGroup(Group EntitiesGrou[])
    {
        this.EntitiesGroup = EntitiesGroup;
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getR()
    {
        return r;
    }

    public void setR(double r)
    {
        this.r = r;
    }

    public double getDx()
    {
        return dx;
    }

    public void setDx(double dx)
    {
        this.dx = dx;
    }

    public double getDy()
    {
        return dy;
    }

    public void setDy(double dy)
    {
        this.dy = dy;
    }

    public double getDr()
    {
        return dr;
    }

    public void setDr(double dr)
    {
        this.dr = dr;
    }

    //public abstract void ShowHP();

    public Image getImage()
    {
        return this.image;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    public double getHealth()
    {
        return Health;
    }

    public double getDamage()
    {
        return this.damage;
    }

    public void setDamage(double damage)
    {
        this.damage = damage;
    }

    public void setHealth(double health)
    {
        this.Health = health;
    }

    public boolean isRemovable()
    {
        return removable;
    }

    public void setRemovable(boolean removable)
    {
        this.removable = removable;
    }

    public void Rotate()
    {
        if (!canMove)
            return;

        x += dx;
        y += dy;
        r += dr;
    }
    /*
    public void Move(Coordinate coordinate1,Coordinate coordinate2,double time)
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
                this.imageView.setLayoutX(this.x);
                this.imageView.setLayoutY(this.y);
            }
        }
    }
    */

    public boolean isAlive()
    {
        return Double.compare(this.Health, 0) > 0;
    }

    public ImageView getImageView()
    {
        return this.imageView;
    }

    public void setImageView(ImageView imageView)
    {
        this.imageView = new ImageView(this.image);
    }
    public void updateUI()
    {
        this.imageView.relocate(x, y);
        this.imageView.setRotate(r);
    }

    public double getWidth()
    {
        return w;
    }

    public double getHeight()
    {
        return h;
    }

    public double getCenterX()
    {
        return x + w * 0.5;
    }

    public double getCenterY()
    {
        return y + h * 0.5;
    }

    // TODO: per-pixel-collision
    public boolean collidesWith( SpriteBase otherSprite)
    {
        return ( otherSprite.x + otherSprite.w >= x && otherSprite.y + otherSprite.h >= y && otherSprite.x <= x + w && otherSprite.y <= y + h);
    }

    /**
     * Reduce health by the amount of damage that the given sprite can inflict
     * @param sprite
     */
    public void getDamagedBy( SpriteBase sprite)
    {
        Health -= sprite.getDamage();
    }

    /**
     * Set health to 0
     */
    public void kill()
    {
        setHealth( 0);
    }

    /**
     * Set flag that the sprite can be removed from the UI.
     */
    public void remove()
    {
        setRemovable(true);
    }

    /**
     * Set flag that the sprite can't move anymore.
     */
    public void stopMovement()
    {
        this.canMove = false;
    }

    public abstract void checkRemovability();
}
