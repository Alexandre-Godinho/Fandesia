package pt.ulusofona.lp2.fandeisiaGame;

public class Dwarf extends Creature {
    public int getCost() {
        return 1;
    }

    public int getRange() {
        return 1;
    }

    public String getImagePNG() {
        if (teamID == 10) {
            if (orientation.equals("Norte")) {
                return "dwarf0_norte.png";
            }
            if (orientation.equals("Sul")) {
                return "dwarf0_sul.png";
            }
            if (orientation.equals("Este")) {
                return "dwarf0_este.png";
            }
            if (orientation.equals("Oeste")) {
                return "dwarf0_oeste.png";
            }
        } else {
            if (orientation.equals("Norte")) {
                return "dwarf1_norte.png";
            }
            if (orientation.equals("Sul")) {
                return "dwarf1_sul.png";
            }
            if (orientation.equals("Este")) {
                return "dwarf1_este.png";
            }
            if (orientation.equals("Oeste")) {
                return "dwarf1_oeste.png";
            }
        }
        return "";
    }

    public String getType() {
        return "Anão";
    }

    public String toString() {
        return id + " | Anão | " + teamID + " | " + pointCounter() + " @ (" + positionX + ", " + positionY + ") " + orientation;
    }

    public boolean movementAllowed(World world, int range) {
        switch (orientation) { /* Verifica qual a orientação do anão */
            case "Norte": {
                if (positionY > range - 1) { /* Verifica se o anão irá sair do mapa */
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
                if (positionX < world.sizeX - range) { /* Verifica se o anão irá sair do mapa */
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
                if (positionY < world.sizeY - range) { /* Verifica se o anão irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        for (int pos = 1; pos <= range; pos++) {
                            /* Irá verificar se existe alguma criatura a impedir o caminho e na possivel nova posição*/
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
                if (positionX > range - 1) { /* Verifica se o anão irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        for (int pos = 1; pos <= range; pos++) {
                            /* Irá verificar se existe alguma criatura a impedir o caminho e na possivel nova posição*/
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
