package pt.ulusofona.lp2.fandeisiaGame;

public class Human extends Creature {
    public int getCost() {
        return 3;
    }

    public int getRange() {
        return 2;
    }

    public String getImagePNG() {
        if (teamID == 10) {
            if (orientation.equals("Norte")) {
                return "human0_norte.png";
            }
            if (orientation.equals("Sul")) {
                return "human0_sul.png";
            }
            if (orientation.equals("Este")) {
                return "human0_este.png";
            }
            if (orientation.equals("Oeste")) {
                return "human0_oeste.png";
            }
        } else {
            if (orientation.equals("Norte")) {
                return "human1_norte.png";
            }
            if (orientation.equals("Sul")) {
                return "human1_sul.png";
            }
            if (orientation.equals("Este")) {
                return "human1_este.png";
            }
            if (orientation.equals("Oeste")) {
                return "human1_oeste.png";
            }
        }
        return "";
    }

    public String getType() {
        return "Humano";
    }

    public String toString() {
        return id + " | Humano | " + teamID + " | " + pointCounter() + " @ (" + positionX + ", " + positionY + ") " + orientation;
    }

    public boolean movementAllowed(World world, int range) {
        switch (orientation) { /* Verifica qual a orientação do humano */
            case "Norte": {
                if (positionY > range - 1) { /* Verifica se o humano irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        for (int pos = 1; pos <= range; pos++) {
                            /* Irá verificar se existe alguma criatura a impedir o caminho e na possivel nova posição*/
                            if (blockerPositionX == positionX && blockerPositionY == positionY - pos) {
                                return false;
                            }
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        for (int pos = 1; pos <= range; pos++) {
                            /* Irá verificar se existe algum buraco a impedir o caminho e na possivel nova posição*/
                            if (blockerPositionX == positionX && blockerPositionY == positionY - pos) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
                break;
            }
            case "Este": {
                if (positionX < world.sizeX - range) { /* Verifica se o humano irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        for (int pos = 1; pos <= range; pos++) {
                            /* Irá verificar se existe alguma criatura a impedir o caminho e na possivel nova posição*/
                            if (blockerPositionX == positionX + pos && blockerPositionY == positionY) {
                                return false;
                            }
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        for (int pos = 1; pos <= range; pos++) {
                            /* Irá verificar se existe algum buraco a impedir o caminho e na possivel nova posição*/
                            if (blockerPositionX == positionX + pos && blockerPositionY == positionY) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
                break;
            }
            case "Sul": {
                if (positionY < world.sizeY - range) { /* Verifica se o humano irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        for (int pos = 1; pos <= range; pos++) {
                            /* Irá verificar se existe algum buraco a impedir o caminho e na possivel nova posição*/
                            if (blockerPositionX == positionX && blockerPositionY == positionY + pos) {
                                return false;
                            }
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        for (int pos = 1; pos <= range; pos++) {
                            /* Irá verificar se existe algum buraco a impedir o caminho e na possivel nova posição*/
                            if (blockerPositionX == positionX && blockerPositionY == positionY + pos) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
                break;
            }
            case "Oeste": {
                if (positionX > range - 1) { /* Verifica se o gigante irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        for (int pos = 1; pos <= range; pos++) {
                            /* Irá verificar se existe algum buraco a impedir o caminho e na possivel nova posição*/
                            if (blockerPositionX == positionX - pos && blockerPositionY == positionY) {
                                return false;
                            }
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        for (int pos = 1; pos <= range; pos++) {
                            /* Irá verificar se existe algum buraco a impedir o caminho e na possivel nova posição*/
                            if (blockerPositionX == positionX - pos && blockerPositionY == positionY) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
                break;
            }
        }
        return false;
    }

    void rotateClockWise() {
        switch (orientation) {
            case "Norte": {
                orientation = "Este";
                break;
            }
            case "Este": {
                orientation = "Sul";
                break;
            }
            case "Sul": {
                orientation = "Oeste";
                break;
            }
            case "Oeste": {
                orientation = "Norte";
                break;
            }
        }
    }
}
