package Entities;

import java.util.List;

import Game.Coordinate;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Tower extends SpriteBase
{
    public Enemy Target; // TODO: use weakreference
    public Bullet bullet;
    public double turnRate = 0.6;
    public double speed;
    public int cost;

    double targetRange; // distance within tower can lock to enemy
    public double rotationLimitDeg = 0.0;
    public double rotationLimitRad =  Math.toDegrees( this.rotationLimitDeg);
    public double roatationEasing = 10;
    public double targetAngle = 0;
    public double currentAngle = 0;

    boolean withinFiringRange = false;

    public Tower(Group group, Image image, double x, double y, double r, double dx, double dy, double dr, double health, double damage)
    {
        super(group, image, x, y, r, dx, dy, dr, health, damage);
    }

    @Override
    public void Rotate()
    {
        SpriteBase follower = this;

        // reset within firing range
        withinFiringRange = false;

        // rotate towards target
        if (Target != null)
        {
            // parts of code used from shane mccartney (http://lostinactionscript.com/page/3/)
            double xDist = Target.getImageView().getTranslateX() - follower.getCenterX();
            double yDist = Target.getImageView().getTranslateY() - follower.getCenterY();

            this.targetAngle = Math.atan2(yDist,xDist) - Math.PI / 2;

            this.currentAngle = Math.abs(this.currentAngle) > Math.PI * 2 ? (this.currentAngle < 0 ? (this.currentAngle % Math.PI * 2 + Math.PI * 2) : (this.currentAngle % Math.PI * 2)) : (this.currentAngle);
            this.targetAngle = this.targetAngle + (Math.abs(this.targetAngle - this.currentAngle) < Math.PI ? (0) : (this.targetAngle - this.currentAngle > 0 ? ((-Math.PI) * 2) : (Math.PI * 2)));
            this.currentAngle = this.currentAngle + (this.targetAngle - this.currentAngle) / roatationEasing;  // give easing when rotation comes closer to the target point

            // check if the rotation limit has to be kept
            if((this.targetAngle - this.currentAngle) > this.rotationLimitRad)
            {
                this.currentAngle += this.rotationLimitRad;
            }
            else if((this.targetAngle - this.currentAngle) < -this.rotationLimitRad)
            {
                this.currentAngle -= this.rotationLimitRad;
            }

            follower.r = Math.toDegrees(currentAngle);

            // determine if the player ship is within firing range; currently if the player ship is within 10 degrees (-10..+10)
            withinFiringRange = Math.abs( Math.toDegrees( this.targetAngle - this.currentAngle)) < 20;

        }
        super.Rotate();
    }

    public void checkTarget()
    {
        if (Target == null)
        {
            return;
        }
        if (!Target.isAlive() || Target.isRemovable())
        {
            setTarget( null);
            return;
        }
        //get distance between follower and target
        double distanceX = Target.getCenterX() - getCenterX();
        double distanceY = Target.getCenterY() - getCenterY();

        //get total distance as one number
        double distanceTotal = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        if (Double.compare( distanceTotal, targetRange) > 0)
        {
            setTarget(null);
        }
    }
    public void findTarget (List<Enemy> targetList)
    {
        // we already have a target
        if (getTarget() != null)
        {
            return;
        }
        Enemy closestTarget = null;
        double closestDistance = 0.0;

        for (Enemy target: targetList)
        {
            if (!target.isAlive())
            {
                continue;
            }
            //get distance between follower and target
            double distanceX = target.getImageView().getTranslateX() - getImageView().getLayoutX();
            double distanceY = target.getImageView().getTranslateY() - getImageView().getLayoutY();

            //get total distance as one number
            double distanceTotal = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

            // check if enemy is within range
            if (Double.compare(distanceTotal, targetRange) > 0)
            {
                continue;
            }

            if (closestTarget == null)
            {
                closestTarget = target;
                closestDistance = distanceTotal;
            }
            else if (Double.compare(distanceTotal,closestDistance) < 0)
            {
                closestTarget = target;
                closestDistance = distanceTotal;
            }
        }
        setTarget(closestTarget);
    }

    public void FireBullet(Enemy Target)
    {
        Bullet bullet = new Bullet(this.EntitiesGroup,this.damage,this.imageView.getLayoutX(),this.imageView.getLayoutY());
        ImageView ViewBullet = new ImageView(bullet.BulletImage);
        this.EntitiesGroup.getChildren().add(ViewBullet);
        double mx = getImageView().getLayoutX();
        double my = getImageView().getLayoutY();
        MoveTo SpawnBullet = new MoveTo(mx,my);
        Path FlyPath = new Path();
        LineTo FlyLine = new LineTo(Target.getImageView().getTranslateX() + 1,Target.getImageView().getTranslateY() + 1);
        FlyPath.getElements().addAll(SpawnBullet,FlyLine);
        PathTransition FlyTransition = new PathTransition(Duration.millis(300),FlyPath,bullet.getViewBullet());
        FlyTransition.statusProperty().addListener(new ChangeListener<Animation.Status>()
        {
            @Override
            public void changed(ObservableValue<? extends Animation.Status> observable, Animation.Status oldValue, Animation.Status newValue)
            {
                if (newValue == Animation.Status.STOPPED)
                {
                    EntitiesGroup.getChildren().remove(bullet.getViewBullet());
                }
            }
        });
        FlyTransition.play();
        //Target.Health = Target.Health - 25 + Settings.ARMOR_DAMAGE_REDUCTION * Target.Shield;
    }
    public Enemy getTarget()
    {
        return Target;
    }
    public void setTarget(Enemy target)
    {
        this.Target = target;
    }
    @Override
    public void checkRemovability()
    {
        if (Double.compare(Health, 0) < 0)
        {
            setTarget(null);
            setRemovable(true);
        }
    }
    public boolean hitsTarget (Enemy enemy)
    {
        return (Target == enemy && withinFiringRange);
    }
}
