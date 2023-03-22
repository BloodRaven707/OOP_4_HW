package hero;


import java.util.ArrayList;


public abstract class BaseShooter extends BaseHero {
    public int ammo;


    public BaseShooter( String name,
                        int health, int attack, int defense, int speed,
                        int damageMin, int damageMax, int posX, int posY, int ammo ) {
        super( name, health, attack, defense, speed, damageMin, damageMax, posX, posY );
        this.ammo = ammo;

    }


    public void doTurn( ArrayList<BaseHero> enemy ) {
        if ( this.ammo > 0 && this.health > 0 ) {
            for( BaseHero hero: enemy ) {
                if ( hero.health > 0 ) {
                    System.out.print( hero + " получает урон " + getDamage( hero.defense ) );
                    this.doAttack( hero, getDamage( hero.defense ) );
                    this.ammo--;
                    return;
                }
            }
            System.out.println( "У " + this + " Нет цели для стрельбы" );
        }
    }
}