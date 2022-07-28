package pt.ulusofona.lp2.fandeisiaGame;

import java.util.ArrayList;

public class Team {
    int id;
    String name;
    int points;
    int coins;
    boolean collectedTreasure;

    public Team(int id, String name, int points, int coins) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.coins = coins;
        this.collectedTreasure = false;
    }

    int getCoins(){
        return coins;
    }
}
