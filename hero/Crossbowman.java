package hero;

import java.util.ArrayList;

public class Crossbowman extends Hero {
    public Crossbowman( String name,
    int attack, int defense, int shots, int damage_min, int damage_max,
    int health, int speed, int posX, int posY ) {
        super( name, attack, defense, shots, damage_min, damage_max, health, speed, 0, 0, posX, posY );
    }

    public Crossbowman( String name, int posX, int posY ) {
        this( name, 6, 3, 16, 2, 3, 10, 4, posX, posY );
    }

    @Override
    public void step(ArrayList<Hero> team1, ArrayList<Hero> team2) {
        if (state.equals("Die") || shots == 0) return;
        Hero enemy = team2.get(findNearest(team2));
        float damage = (enemy.defense - attack)>0 ? damage_min : (enemy.defense - attack)<0 ? damage_max : (damage_min+damage_max)/2;
        enemy.Damage(damage);
        for (Hero hero: team1) {
            if (hero.getClass().getName().equals("Farmer") && hero.state.equals("Stand")) {
                hero.state = "Busy";
                return;
            }
        }
        shots--;
    }
}