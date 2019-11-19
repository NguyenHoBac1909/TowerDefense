package Entities;

public class Base
{
    private int health;
    private int score;

    public Base()
    {
        this.health = 1000;
        this.score = 160;
    }

    public int getHealth()
    {
        return health;
    }

    public int getScore()
    {
        return score;
    }

    public void buyTurret()
    {
        if (haveScore())
        {
            score = score - 40;
        }
    }

    public void subHealth(int amount)
    {
        health = (health - amount < 0) ? 0 : (health - amount);
    }

    public boolean isDead()
    {
        return health <= 0;
    }

    public boolean haveScore()
    {
        return score != 0;
    }
}
