package pt.ulusofona.lp2.fandeisiaGame;

public class Mage extends Creature {
    public int getCost() {
        return 9;
    }

    public int getRange() {
        return 10;
    }

    public String getImagePNG() {
        if (teamID == 0) {
            if (orientation.equals("Norte")) {
                return "dragon0_norte.png";
            }
            if (orientation.equals("Sul")) {
                return "dragon0_sul.png";
            }
            if (orientation.equals("Este")) {
                return "dragon0_este.png";
            }
            if (orientation.equals("Oeste")) {
                return "dragon0_oeste.png";
            }
        } else {
            if (orientation.equals("Norte")) {
                return "dragon1_norte.png";
            }
            if (orientation.equals("Sul")) {
                return "dragon1_sul.png";
            }
            if (orientation.equals("Este")) {
                return "dragon1_este.png";
            }
            if (orientation.equals("Oeste")) {
                return "dragon1_oeste.png";
            }
        }
        return "";
    }

    public String getType() {
        return "Mago";
    }

    public String toString() {
        return id + " | Mago | " + teamID + " | " + pointCounter() + " @ (" + positionX + ", " + positionY + ") " + orientation;
    }

    public boolean movementAllowed(World world, int range) {
        switch (orientation) { /* Verifica qual a orientação do humano */
            case "Norte": {
                if (positionY > 1) { /* Verifica se o humano irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe alguma criatura a impedir o caminho */
                        if (blockerPositionX == positionX && blockerPositionY == positionY - 1) {
                            return false;
                        }

                        /* Irá verificar se existe alguma criatura na possivel nova posição */
                        if (blockerPositionX == positionX && blockerPositionY == positionY - 2) {
                            return false;
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe algum buraco a impedir o caminho */
                        if (blockerPositionX == positionX && blockerPositionY == positionY - 1) {
                            return false;
                        }

                        /* Irá verificar se existe algum buraco na possivel nova posição */
                        if (blockerPositionX == positionX && blockerPositionY == positionY - 2) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            }
            case "Este": {
                if (positionX < world.sizeX - 2) { /* Verifica se o humano irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe alguma criatura a impedir o caminho */
                        if (blockerPositionX == positionX + 1 && blockerPositionY == positionY) {
                            return false;
                        }

                        /* Irá verificar se existe alguma criatura na possivel nova posição */
                        if (blockerPositionX == positionX + 2 && blockerPositionY == positionY) {
                            return false;
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe algum buraco a impedir o caminho */
                        if (blockerPositionX == positionX + 1 && blockerPositionY == positionY) {
                            return false;
                        }

                        /* Irá verificar se existe algum buraco na possivel nova posição */
                        if (blockerPositionX == positionX + 2 && blockerPositionY == positionY) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            }
            case "Sul": {
                if (positionY < world.sizeY - 2) { /* Verifica se o humano irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe alguma criatura a impedir o caminho */
                        if (blockerPositionX == positionX && blockerPositionY == positionY + 1) {
                            return false;
                        }

                        /* Irá verificar se existe alguma criatura na possivel nova posição */
                        if (blockerPositionX == positionX && blockerPositionY == positionY + 2) {
                            return false;
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe algum buraco a impedir o caminho */
                        if (blockerPositionX == positionX && blockerPositionY == positionY + 1) {
                            return false;
                        }

                        /* Irá verificar se existe algum buraco na possivel nova posição */
                        if (blockerPositionX == positionX && blockerPositionY == positionY + 2) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            }
            case "Oeste": {
                if (positionX > 1) { /* Verifica se o gigante irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe alguma criatura a impedir o caminho */
                        if (blockerPositionX == positionX - 1 && blockerPositionY == positionY) {
                            return false;
                        }

                        /* Irá verificar se existe alguma criatura na possivel nova posição */
                        if (blockerPositionX == positionX - 2 && blockerPositionY == positionY) {
                            return false;
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe algum buraco a impedir o caminho */
                        if (blockerPositionX == positionX - 1 && blockerPositionY == positionY) {
                            return false;
                        }

                        /* Irá verificar se existe algum buraco na possivel nova posição */
                        if (blockerPositionX == positionX - 2 && blockerPositionY == positionY) {
                            return false;
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
