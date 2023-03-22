import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import hero.*;


public class OOP_HW_4 {
    public static void main( String[] args ) {
        ArrayList<BaseHero> blackTeam = getTeam( 10, "black", 10 );
        ArrayList<BaseHero> whiteTeam = getTeam( 10, "white", 1 );
        ArrayList<BaseHero> allHeroes = new ArrayList<>();
        allHeroes.addAll( blackTeam );
        allHeroes.addAll( whiteTeam );

        System.out.println( "\nHeroes sorted by speed" );
        sort( allHeroes );
        allHeroes.forEach( n -> {
            if ( n.state != -1 ) {
                System.out.print( n );
            }
        } );

        System.out.println( "\n" );

        for ( BaseHero hero: allHeroes ) {
            if ( whiteTeam.contains( hero ) )
                hero.doStep( blackTeam );
            else
                hero.doStep( whiteTeam );
        }

        System.out.println( "\nHeroes after Battle round: (погибшие удалены)" );
        allHeroes.forEach( n -> {
            if ( n.state != -1 ) {
                System.out.print( n );
            }
        } );

        System.out.println( "\n" );
    }


    static void sort( ArrayList<BaseHero> team ) {
        team.sort( new Comparator<BaseHero>() {
            @Override
            public int compare( BaseHero o1, BaseHero o2 ) {
                if ( o2.getSpeed() == o1.getSpeed() )
                    return ( int )( o2.health - o1.health );
                return o2.speed - o1.speed;
            }
        } );
    }


    public static ArrayList<BaseHero> getTeam(int groupSize, String teamName, int posY) {
        ArrayList<BaseHero> team = new ArrayList<>();
        int start = 0;
        int end = 0;
        int count = 0;
        if (teamName.equals("white")) {
            start = 0;
            end = 2;
        }
        if (teamName.equals("black")) {
            start = 0;
            end = 2;
        }
        while ( count < groupSize ) {
            switch ( new Random().nextInt( start, end ) ) {
                case 0:
                    team.add( new Crossbowman( getName(), count + 1, posY ) );
                    break;
                case 1:
                    team.add( new Farmer( getName(), count + 1, posY ) );
                    break;
            }
            count++;
        }
        return team;
    }


    private static String getName() {
        return Names.values()[ new Random().nextInt( Names.values().length ) ].toString();
    }
}