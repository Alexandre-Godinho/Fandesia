package pt.ulusofona.lp2.fandeisiaGame;

public class Treasure {
    int id;
    String type;
    int pointsValue;
    int positionX;
    int positionY;

    public Treasure() {
    }

    public Treasure(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    void typeToPoints() {
        switch (type) {
            case "gold": {
                pointsValue = 3;
                break;
            }
            case "silver": {
                pointsValue = 2;
                break;
            }
            case "bronze": {
                pointsValue = 1;
                break;
            }
        }
    }
}
