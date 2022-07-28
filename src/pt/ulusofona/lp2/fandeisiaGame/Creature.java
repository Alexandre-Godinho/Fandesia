package pt.ulusofona.lp2.fandeisiaGame;

import java.util.ArrayList;

abstract public class Creature {
    int id;
    int teamID;
    int positionX;
    int positionY;
    String orientation;
    ArrayList<Treasure> treasuresFound = new ArrayList<>();
    Spell activeEnchantment;
    boolean freezedForEver;

    int pointCounter() {
        return treasuresFound.size();
    }

    Creature() {

    }

    public Creature(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Creature(int id, int teamID, int positionX, int positionY, String orientation) {
        this.id = id;
        this.teamID = teamID;
        this.positionX = positionX;
        this.positionY = positionY;
        this.orientation = orientation;
    }

    public int getId() {
        return id;
    }

    abstract public String getType();

    abstract public String getImagePNG();

    abstract public int getCost();

    abstract public int getRange();

    public int getRangeFromSpell() {
        if (activeEnchantment.getType().equals("Nenhum")) {
            return getRange();
        }
        if (activeEnchantment.getType().equals("ReduzAlcance")) {
            return 1;
        }
        if (activeEnchantment.getType().equals("DuplicaAlcance")) {
            return 2 * getRange();
        }
        if (activeEnchantment.getType().equals("Congela")) {
            return 0;
        }
        if (activeEnchantment.getType().equals("Congela4Ever") || freezedForEver) {
            return 0;
        }
        return getRange();
    }

    String getOrientation() {
        return orientation;
    }

    boolean teamVerification(int currentTeamID) {
        if (teamID == currentTeamID) {
            return true;
        }
        return false;
    }

    abstract boolean movementAllowed(World world, int range);

    void moveWithOrientation(int range) {
        switch (orientation) {
            case "Norte": {
                positionY -= range;
                break;
            }
            case "Nordeste": {
                positionY -= range;
                positionX += range;
                break;
            }
            case "Este": {
                positionX += range;
                break;
            }
            case "Sudeste": {
                positionX += range;
                positionY += range;
                break;
            }
            case "Sul": {
                positionY += range;
                break;
            }
            case "Sudoeste": {
                positionY += range;
                positionX -= range;
                break;
            }
            case "Oeste": {
                positionX -= range;
                break;
            }
            case "Noroeste": {
                positionX -= range;
                positionY -= range;
                break;
            }
        }
    }

    public boolean collectTreasure(World world, Team[] teams) {
        for (Treasure treasure: world.treasures) {
            int treasurePosX = treasure.positionX;
            int treasurePosY = treasure.positionY;

            if (positionX == treasurePosX && positionY == treasurePosY) { /* Verifica se a criatura se encontra na posição de algum tesouro */
                treasuresFound.add(treasure); /* Adiciona o tesouro a lista de tesouros encontrados pela criatura */
                for (Team team : teams) {
                    if (teamID == team.id) {
                        team.points += treasure.pointsValue; /* Adiciona o valor do tesouro em pontos à equipa que o encontrou */
                        team.collectedTreasure = true;
                    }
                }
                world.treasures.remove(treasure); /* Remove o tesouro do mapa */
                return true;
            }
        }
        return false;
    }

    private void moveWithGivenOrientation(String givenOrientation) {
        switch (givenOrientation) {
            case "Norte": {
                positionY -= 1;
                break;
            }
            case "Este": {
                positionX += 1;
                break;
            }
            case "Sul": {
                positionY += 1;
                break;
            }
            case "Oeste": {
                positionX -= 1;
                break;
            }
        }
    }

    abstract void rotateClockWise();

    boolean enchantmentAllowed(World world, String spellName) {
        if (spellName == null){
            return false;
        }
        switch (spellName) {
            case "EmpurraParaNorte": {
                if (positionY > 0) { /* Verifica se a criatura irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe alguma criatura na possivel nova posição */
                        if (blockerPositionX == positionX && blockerPositionY == positionY - 1) {
                            return false;
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe algum buraco na possivel nova posição */
                        if (blockerPositionX == positionX && blockerPositionY == positionY - 1) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            }
            case "EmpurraParaEste": {
                if (positionX < world.sizeX - 1) { /* Verifica se a criatura irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe alguma criatura na possivel nova posição */
                        if (blockerPositionX == positionX + 1 && blockerPositionY == positionY) {
                            return false;
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe algum buraco na possivel nova posição */
                        if (blockerPositionX == positionX + 1 && blockerPositionY == positionY) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            }
            case "EmpurraParaSul": {
                if (positionY < world.sizeY - 1) { /* Verifica se a criatura irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe alguma criatura na possivel nova posição */
                        if (blockerPositionX == positionX && blockerPositionY == positionY + 1) {
                            return false;
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe algum buraco na possivel nova posição */
                        if (blockerPositionX == positionX && blockerPositionY == positionY + 1) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            }
            case "EmpurraParaOeste": {
                if (positionX > 0) { /* Verifica se a criatura irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe alguma criatura na possivel nova posição */
                        if (blockerPositionX == positionX - 1 && blockerPositionY == positionY) {
                            return false;
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe algum buraco na possivel nova posição */
                        if (blockerPositionX == positionX - 1 && blockerPositionY == positionY) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            }
            case "ReduzAlcance": {
                return movementAllowed(world, 1);
            }
            case "DuplicaAlcance": {
                return movementAllowed(world, 2 * getRange());
            }
            default: {
                return true;
            }
        }
        return false;
    }

    void enchant(String spell) {
        switch (spell) {
            case "EmpurraParaNorte": {
                activeEnchantment = new PushNorth();
                moveWithGivenOrientation("Norte");
                break;
            }
            case "EmpurraParaEste": {
                activeEnchantment = new PushEast();
                moveWithGivenOrientation("Este");
                break;
            }
            case "EmpurraParaSul": {
                activeEnchantment = new PushSouth();
                moveWithGivenOrientation("Sul");
                break;
            }
            case "EmpurraParaOeste": {
                activeEnchantment = new PushWest();
                moveWithGivenOrientation("Oeste");
                break;
            }
            case "Congela4Ever": {
                freezedForEver = true;
                activeEnchantment = new FreezeForEver();
                break;
            }
            case "Descongela": {
                freezedForEver = false;
                activeEnchantment = new Unfreeze();
                break;
            }
            case "ReduzAlcance": {
                activeEnchantment = new ReduceRange();
                break;
            }
            case "DuplicaAlcance": {
                activeEnchantment = new DoubleRange();
                break;
            }
            case "Congela": {
                activeEnchantment = new Freeze();
                break;
            }
            case "Nenhum": {
                activeEnchantment = new None();
                break;
            }
        }
    }

    int getGoldTreasures() {
        int goldTreasures = 0;
        for (Treasure treasure : treasuresFound) {
            if (treasure.type.equals("gold")) {
                goldTreasures++;
            }
        }
        return goldTreasures;
    }

    int getSilverTreasures() {
        int silverTreasures = 0;
        for (Treasure treasure : treasuresFound) {
            if (treasure.type.equals("silver")) {
                silverTreasures++;
            }
        }
        return silverTreasures;
    }

    int getBronzeTreasures() {
        int bronzeTreasures = 0;
        for (Treasure treasure : treasuresFound) {
            if (treasure.type.equals("bronze")) {
                bronzeTreasures++;
            }
        }
        return bronzeTreasures;
    }

    int getTotalPoints() {
        int totalPoints = 0;
        for (Treasure treasure : treasuresFound) {
            if (treasure.type.equals("bronze")) {
                totalPoints++;
            }
            if (treasure.type.equals("silver")) {
                totalPoints += 2;
            }
            if (treasure.type.equals("gold")) {
                totalPoints += 3;
            }
        }
        return totalPoints;
    }
}
