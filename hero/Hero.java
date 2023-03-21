package hero;

import java.util.ArrayList;

public abstract class Hero implements Comparable<Hero> {
    private String name;

    public int attack;
    public int defense;
    public int shots;
    public int damage_min;
    public int damage_max;
    public int health;
    public int healthMax;
    public int speed;
    public int delivery;
    public int magic;
    public Point2D pos;
    public String state;


    // Полный конструктор
    public Hero(String name,
                int attack, int defense, int shots, int damage_min, int damage_max,
                int health, int speed, int delivery, int magic, int posX, int posY ) {
        this.name       = name;
        this.attack     = attack;
        this.defense    = defense;
        this.shots      = shots;
        this.damage_min = damage_min;
        this.damage_max = damage_max;
        this.health     = health;
        this.healthMax  = health;
        this.speed      = speed;
        this.delivery   = delivery;
        this.magic      = magic;

        this.pos        = new Point2D( posX, posY );
        this.state      = "Stand";
    }

    // Полный конструктор, без разброса в уроне
    public Hero(String name,
                int attack, int defense, int shots, int damage,
                int health, int speed, int delivery, int magic, int posX, int posY ) {
        this( name, attack, defense, shots, damage, damage, health, speed, delivery, magic, posX, posY );
    }


    // Геттеры, для изменения значений приватных полей
    public String getName() { return name; }
    public int getAttack()  { return attack; }
    public int getDefense() { return defense; }
    public int getShots()   { return shots; }
    public int getDamage_min()  { return damage_min; }
    public int getDamage_max()  { return damage_max; }
    public int getHealth()  { return health; }
    public int getSpeed()   { return speed; }
    public int getDelivery(){ return delivery; }
    public int getMagic()   { return magic; }

    // Сеттеры, для получения значений приватных полей
    public void setName(     String name )  { this.name     = name; }
    public void setAttack(   int attack )   { this.attack   = attack; }
    public void setDefense(  int defense )  { this.defense  = defense; }
    public void setShots(    int shots )    { this.shots    = shots; }
    public void setDamageMin(int damage_min){ this.damage_min = damage_min; }
    public void setDamageMax(int damage_max){ this.damage_max = damage_max; }
    public void setHealth(   int health )   { this.health   = health; }
    public void setSpeed(    int speed )    { this.speed    = speed; }
    public void setDelivery( int delivery ) { this.delivery = delivery; }
    public void setMagic(    int magic )    { this.magic    = magic; }

    // Вывод в строковом виде
    public String toString() {
        return String.format("\n%s: attack: %d, defense: %d, shots: %d, damage: %d-%d, health: %d, speed: %d, delivery: %d, magic: %d, pos: %s, state: %s",
            this.name,
            this.attack,
            this.defense,
            this.shots,
            this.damage_min,
            this.damage_max,
            this.health,
            this.speed,
            this.delivery,
            this.magic,
            this.pos,
            this.state);
    }

    public void step(ArrayList<Hero> team1, ArrayList<Hero> team2) {
    }

    protected int findNearest(ArrayList<Hero> allHeroes) {
        double min = Double.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < allHeroes.size(); i++) {
            if (min > pos.getDistance(allHeroes.get(i).pos)) {
                index = i;
                min = pos.getDistance(allHeroes.get(i).pos);
            }
        }
        return index;
    }

    public String getSpeedSortedInfo() { // метод для проверки сортировки
        return String.format("%s %s, скорость: %d, здоровье: %d", this.getClass().getName(), this.name, this.speed, this.health);
    }

    protected void Damage(float damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            state = "Die";
        }
        if (health > healthMax) {
            health = healthMax;
        }
    }

    // Сотировка по убыванию
    @Override
    public int compareTo( Hero o ) {
        // return Integer.compare( this.speed, o.speed );

        if ( this.speed < o.speed )
            return 1;
        else if ( this.speed > o.speed )
            return -1;
        else
            return 0;
    }
}