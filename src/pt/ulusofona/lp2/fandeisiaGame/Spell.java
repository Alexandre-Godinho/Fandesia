package pt.ulusofona.lp2.fandeisiaGame;

abstract public class Spell {
    String type;
    int cost;

    abstract String getType();
    abstract int getCost();
}
