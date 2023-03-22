package hero;

import java.util.ArrayList;

public abstract class BaseHero implements HeroInterface, Comparable<BaseHero> {
    public String name;
    public String type;

    public int health;
    public int healthMax;

    public int attack;
    public int defense;
    public int speed;

    public int damageMin;
    public int damageMax;

    public int posX;
    public int posY;

    public int state;


    // Полный конструктор
    public BaseHero( String name,
                     int health, int attack, int defense, int speed,
                     int damageMin, int damageMax, int posX, int posY ) {
        this.name       = name;
        this.type       = "";

        this.health     = health;
        this.healthMax  = health;

        this.attack     = attack;
        this.defense    = defense;
        this.speed      = speed;

        this.damageMin = damageMin;
        this.damageMax = damageMax;

        this.posX      = posX;
        this.posY      = posY;

        this.state      = 1;
    }

    // Полный конструктор, без разброса в уроне
    public BaseHero( String name,
                     int health, int attack, int defense, int speed,
                     int damage, int posX, int posY ) {
        this( name, health, attack, defense, speed, damage, damage, posX, posY );
    }


    // Геттеры, для изменения значений приватных полей
    public String getName  () { return this.name;      }
    public int getHealth   () { return this.health;    }
    public int getHealthMax() { return this.healthMax; }
    public int getAttack   () { return this.attack;    }
    public int getDefense  () { return this.defense;   }
    public int getSpeed    () { return this.speed;     }
    public int getDamageMin() { return this.damageMin; }
    public int getDamageMax() { return this.damageMax; }
    public int getPosX     () { return this.posX;      }
    public int getPosY     () { return this.posY;      }
    public int getState    () { return this.state;     }
    public Point2D getPos  () { return new Point2D(posX, posY); }


    // Сеттеры, для получения значений приватных полей
    public void setName     ( String name   ) { this.name      = name;      }
    public void setAttack   ( int attack    ) { this.attack    = attack;    }
    public void setDefense  ( int defense   ) { this.defense   = defense;   }
    public void setSpeed    ( int speed     ) { this.speed     = speed;     }
    public void setDamageMin( int damageMin ) { this.damageMin = damageMin; }
    public void setDamageMax( int damageMax ) { this.damageMax = damageMax; }
    public void setHealth   ( int health    ) { this.health    = health;    }
    public void setState    ( int state     ) { this.state     = state;     }

    // Сеттеры, для позиционирования
    public void setPos      ( Point2D pos ) { this.posX = pos.posX; this.posY = pos.posY; }
    public void setPosX     ( int posX    ) { setPos( new Point2D( posX, this.posY ) ); }
    public void setPosY     ( int posY    ) { setPos( new Point2D( this.posX, posY ) ); }

    // Рассчитываем урон по цели, минимальный урон == 1
    public int getDamage( int heroDefense ) {
        int damage = heroDefense - this.attack - ( int )( ( damageMin + damageMax ) / 2 );
        if ( damage < 0 )
            return damage * -1;
        return 1;
    }



    public void doStep( ArrayList<BaseHero> enemy ) {
        doTurn( enemy );
    }

    public void doTurn( ArrayList<BaseHero> enemy ) {
        System.out.println( "Даже не знаю, что вам ответить..." );
    }

    // Действие Атака
    public void doAttack( BaseHero hero, int damage ) {
        hero.doDamage( damage );
    }


    // Получаем урон - см. HeroInterface, он реализован там... но не переносится... х.з. эту Java
    public void doDamage( float damage ) {
            setHealth( getHealth() - (int)damage );
        if ( getHealth() <= 0 ) {
            setHealth( 0 );
            setState ( -1 );
            System.out.println( this + " персонаж погиб" );
        }
    }


    // Вывод всех полей в строковом виде
    public String toString() {
        return String.format( "\n%s: health: [%d/%d], attack: %d, defense: %d, speed: %d, damage: %d-%d, pos: %s, state: %s",
            this.name,

            this.health,
            this.healthMax,

            this.attack,
            this.defense,
            this.speed,

            this.damageMin,
            this.damageMax,

            this.getPos(),

            this.state );
    }


    protected int findEnemyNear( ArrayList<BaseHero> allHeroes ) {
        double min = Double.MAX_VALUE;
        int index = 0;
        for ( int i = 0; i < allHeroes.size(); i++ ) {
            if ( min > getPos().getDistance( allHeroes.get( i ).getPos() ) ) {
                index = i;
                min = getPos().getDistance( allHeroes.get( i ).getPos() );
            }
        }
        return index;
    }


    // Сотировка по убыванию скорости
    @Override
    public int compareTo( BaseHero hero ) {
        // return Integer.compare( this.speed, hero.speed );
        if ( this.speed < hero.speed )
            return 1;
        else if ( this.speed > hero.speed )
            return -1;
        else
            return 0;
    }
}