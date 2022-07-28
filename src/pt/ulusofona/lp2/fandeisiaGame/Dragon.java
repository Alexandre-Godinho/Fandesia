package pt.ulusofona.lp2.fandeisiaGame;

public class Dragon extends Creature {
    public int getCost() {
        return 9;
    }

    public int getRange(){
        return 3;
    }

    public String getImagePNG() {
        if (teamID == 10) {
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
            if(orientation.equals("Noroeste")) {
                return "dragon0_noroeste.png";
            }
            if (orientation.equals("Nordeste")) {
                return "dragon0_nordeste.png";
            }
            if (orientation.equals("Sudeste")) {
                return "dragon0_sudeste.png";
            }
            if(orientation.equals("Sudoeste")) {
                return "dragon0_sudoeste.png";
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
            if(orientation.equals("Noroeste")) {
                return "dragon1_noroeste.png";
            }
            if (orientation.equals("Nordeste")) {
                return "dragon1_nordeste.png";
            }
            if (orientation.equals("Sudeste")) {
                return "dragon1_sudeste.png";
            }
            if(orientation.equals("Sudoeste")) {
                return "dragon1_sudoeste.png";
            }
        }
        return "";
    }

    public String getType() {
        return "Dragão";
    }

    public String toString() {
        return id + " | Dragão | " + teamID + " | " + pointCounter() + " @ (" + positionX + ", " + positionY + ") " + orientation;
    }

    public boolean movementAllowed(World world, int range) {
        switch (orientation) { /* Verifica qual a orientação do dragão */
            case "Norte": {
                if (positionY > range - 1) { /* Verifica se o dragão irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe alguma criatura na possivel nova posição */
                        if (blockerPositionX == positionX && blockerPositionY == positionY - range) {
                            return false;
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe algum buraco na possivel nova posição */
                        if (blockerPositionX == positionX && blockerPositionY == positionY - range) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            }
            case "Nordeste": {
                if (positionY > range - 1 && positionX < world.sizeX - range) { /* Verifica se o dragão irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe alguma criatura na possivel nova posição */
                        if (blockerPositionX == positionX + range && blockerPositionY == positionY - range) {
                            return false;
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe algum buraco na possivel nova posição */
                        if (blockerPositionX == positionX + range && blockerPositionY == positionY - range) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            }
            case "Este": {
                if (positionX < world.sizeX - range) { /* Verifica se o dragão irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe alguma criatura na possivel nova posição */
                        if (blockerPositionX == positionX + range && blockerPositionY == positionY) {
                            return false;
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe algum buraco na possivel nova posição */
                        if (blockerPositionX == positionX + range && blockerPositionY == positionY) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            }
            case "Sudeste": {
                if (positionX < world.sizeX - range && positionY < world.sizeY - range) { /* Verifica se o dragão irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe alguma criatura na possivel nova posição */
                        if (blockerPositionX == positionX + range && blockerPositionY == positionY + range) {
                            return false;
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe algum buraco na possivel nova posição */
                        if (blockerPositionX == positionX + range && blockerPositionY == positionY + range) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            }
            case "Sul": {
                if (positionY < world.sizeY - range) { /* Verifica se o dragão irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe alguma criatura na possivel nova posição */
                        if (blockerPositionX == positionX && blockerPositionY == positionY + range) {
                            return false;
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe algum buraco na possivel nova posição */
                        if (blockerPositionX == positionX && blockerPositionY == positionY + range) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            }
            case "Sudoeste": {
                if (positionY < world.sizeY - range && positionX > range - 1) { /* Verifica se o dragão irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe alguma criatura na possivel nova posição */
                        if (blockerPositionX == positionX - range && blockerPositionY == positionY + range) {
                            return false;
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe algum buraco na possivel nova posição */
                        if (blockerPositionX == positionX - range && blockerPositionY == positionY + range) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            }
            case "Oeste": {
                if (positionX > range - 1) { /* Verifica se o dragão irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe alguma criatura na possivel nova posição */
                        if (blockerPositionX == positionX - range && blockerPositionY == positionY) {
                            return false;
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe algum buraco na possivel nova posição */
                        if (blockerPositionX == positionX - range && blockerPositionY == positionY) {
                            return false;
                        }
                    }
                    return true;
                }
                break;
            }
            case "Noroeste": {
                if (positionX > range - 1 && positionY > range - 1) { /* Verifica se o dragão irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe alguma criatura na possivel nova posição */
                        if (blockerPositionX == positionX - range && blockerPositionY == positionY - range) {
                            return false;
                        }
                    }
                    for (Hole possibleBlocker : world.holes) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        /* Irá verificar se existe algum buraco na possivel nova posição */
                        if (blockerPositionX == positionX - range && blockerPositionY == positionY - range) {
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
                orientation = "Nordeste";
                break;
            }
            case "Nordeste": {
                orientation = "Este";
                break;
            }
            case "Este": {
                orientation = "Sudeste";
                break;
            }
            case "Sudeste": {
                orientation = "Sul";
                break;
            }
            case "Sul": {
                orientation = "Sudoeste";
                break;
            }
            case "Sudoeste": {
                orientation = "Oeste";
                break;
            }
            case "Oeste": {
                orientation = "Noroeste";
                break;
            }
            case "Noroeste": {
                orientation = "Norte";
                break;
            }
        }
    }
}
