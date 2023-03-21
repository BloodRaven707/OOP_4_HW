import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import hero.*;


public class OOP_HW_4 {
    public static void main( String[] args ) {

        ArrayList<Hero> blackTeam = getTeam(10, "black", 10);
        ArrayList<Hero> whiteTeam = getTeam(10, "white", 1);
        ArrayList<Hero> allHeroes = new ArrayList<>();
        allHeroes.addAll(blackTeam);
        allHeroes.addAll(whiteTeam);

        System.out.println("\nHeroes sorted by speed");
        sort(allHeroes);
        allHeroes.forEach(n -> System.out.print(n));

        System.out.println("\n");

        for (Hero hero: allHeroes) {
            if (whiteTeam.contains(hero))
                hero.step(whiteTeam,blackTeam);
            else
                hero.step(blackTeam, whiteTeam);
        }

        System.out.println("Heroes after Battle round:");
        allHeroes.forEach(n -> System.out.print(n));

        System.out.println("\n");
    }

    static void sort(ArrayList<Hero> team) {
        team.sort(new Comparator<Hero>() {
            @Override
            public int compare(Hero o1, Hero o2) {
                if (o2.getSpeed() == o1.getSpeed()) return o2.getHealth() - o1.getHealth();
                return o2.getSpeed() - o1.getSpeed();}
        });
    }


    public static ArrayList<Hero> getTeam(int groupSize, String teamName, int posY) {
        ArrayList<Hero> team = new ArrayList<>();
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
        while (count < groupSize) {
            switch (new Random().nextInt(start,end)) {
                case 0:
                    team.add(new Crossbowman(getName(), count+1, posY));
                    break;
                case 1:
                    team.add(new Farmer(getName(), count+1, posY));
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