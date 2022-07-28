package pt.ulusofona.lp2.fandeisiaGame;

import java.util.ArrayList;

public class World {
    int sizeX;
    int sizeY;
    ArrayList<Treasure> treasures = new ArrayList<>();
    ArrayList<Creature> creatures = new ArrayList<>();
    ArrayList<Hole> holes = new ArrayList<>();

    public World(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public int getSizeX(){
        return sizeX;
    }

    public int getSizeY(){
        return sizeY;
    }

    public ArrayList<Treasure> getTreasures(){
        return treasures;
    }

    public ArrayList<Creature> getCreatures(){
        return creatures;
    }

    public ArrayList<Hole> getHoles(){
        return holes;
    }
}
