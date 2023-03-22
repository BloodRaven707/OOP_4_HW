package hero;

import java.util.ArrayList;

public class Farmer extends BaseHero {
    public Farmer( String name,
                   int health, int attack, int defense, int speed,
                   int damage, int posX, int posY ) {
        super( name, health, attack, defense, speed, damage, posX, posY );
        this.type = "Farmer";
    }


    public Farmer( String name, int posX, int posY  ) {
        this( name, 3, 1, 1, 1, 1, posX, posY );
    }


    public void doTurn( ArrayList<BaseHero> enemy ) {
        System.out.println( "Крестьяне не могут атаковать дистанционно" );
    }
}