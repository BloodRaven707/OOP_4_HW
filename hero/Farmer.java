package hero;


public class Farmer extends Hero {
    public Farmer( String name,
    int attack, int defense, int damage,
    int health, int speed, int delivery, int posX, int posY ) {
        super( name, attack, defense, 0, damage, damage, health, speed, delivery, 0, posX, posY );
    }

    public Farmer( String name, int posX, int posY  ) {
        this( name, 1, 1, 1, 1, 3, 1, posX, posY );
    }
}