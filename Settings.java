package Entities;

public class Settings
{
    //region Bullet
    public static final long NORMAL_BULLET_TTL = 30;
    public static final long NORMAL_BULLET_STRENGTH = 30;
    public static final double NORMAL_BULLET_SPEED = 0.3;

    public static final long MACHINE_GUN_BULLET_TTL = 15;
    public static final long MACHINE_GUN_BULLET_STRENGTH = 20;
    public static final double MACHINE_GUN_BULLET_SPEED = 0.4;

    public static final long SNIPER_BULLET_TTL = 60;
    public static final long SNIPER_BULLET_STRENGTH = 120;
    public static final double SNIPER_BULLET_SPEED = 0.5;
    //endregion

    //region Tower
    public static final long NORMAL_TOWER_SPEED = 30;
    public static final double NORMAL_TOWER_RANGE = 200;

    public static final long MACHINE_GUN_TOWER_SPEED = 5;
    public static final double MACHINE_GUN_TOWER_RANGE = 350;

    public static final long SNIPER_TOWER_SPEED = 60;
    public static final double SNIPER_TOWER_RANGE = 500;
    //endregion

    //region Enemy
    public static final double ARMOR_DAMAGE_REDUCTION = 0.25;
    public static final double NORMAL_ENEMY_SIZE = 0.9;
    public static final long NORMAL_ENEMY_HEALTH = 100;
    public static final long NORMAL_ENEMY_ARMOR = 3;
    public static final double NORMAL_ENEMY_SPEED = 0.5;
    public static final long NORMAL_ENEMY_REWARD = 1;

    public static final double SMALLER_ENEMY_SIZE = 0.7;
    public static final long SMALLER_ENEMY_HEALTH = 50;
    public static final long SMALLER_ENEMY_ARMOR = 0;
    public static final double SMALLER_ENEMY_SPEED = 0.4;
    public static final long SMALLER_ENEMY_REWARD = 2;

    public static final double TANKER_ENEMY_SIZE = 1.1;
    public static final long TANKER_ENEMY_HEALTH = 300;
    public static final long TANKER_ENEMY_ARMOR = 5;
    public static final double TANKER_ENEMY_SPEED = 0.2;
    public static final long TANKER_ENEMY_REWARD = 3;

    public static final double BOSS_ENEMY_SIZE = 1.3;
    public static final long BOSS_ENEMY_HEALTH = 500;
    public static final long BOSS_ENEMY_ARMOR = 8;
    public static final double BOSS_ENEMY_SPEED = 0.3;
    public static final long BOSS_ENEMY_REWARD = 10;
    //endregion
}
