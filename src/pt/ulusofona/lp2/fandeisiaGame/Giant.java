package pt.ulusofona.lp2.fandeisiaGame;

public class Giant extends Creature {
    public int getCost() {
        return 5;
    }

    public int getRange() {
        return 3;
    }

    public String getImagePNG() {
        if (teamID == 10) {
            if (orientation.equals("Norte")) {
                return "giant0_norte.png";
            }
            if (orientation.equals("Sul")) {
                return "giant0_sul.png";
            }
            if (orientation.equals("Este")) {
                return "giant0_este.png";
            }
            if (orientation.equals("Oeste")) {
                return "giant0_oeste.png";
            }
        } else {
            if (orientation.equals("Norte")) {
                return "giant1_norte.png";
            }
            if (orientation.equals("Sul")) {
                return "giant1_sul.png";
            }
            if (orientation.equals("Este")) {
                return "giant1_este.png";
            }
            if (orientation.equals("Oeste")) {
                return "giant1_oeste.png";
            }
        }
        return "";
    }

    public String getType() {
        return "Gigante";
    }

    public String toString() {
        return id + " | Gigante | " + teamID + " | " + pointCounter() + " @ (" + positionX + ", " + positionY + ") " + orientation;
    }

    public boolean movementAllowed(World world, int range) {
        switch (orientation) { /* Verifica qual a orientação do gigante */
            case "Norte": {
                if (positionY > range - 1) { /* Verifica se o gigante irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;
                        String blockerType = possibleBlocker.getType();

                        for (int pos = 1; pos < range; pos++) {
                            /* Irá verificar se existe algum gigante a impedir o caminho */
                            if (blockerPositionX == positionX && blockerPositionY == positionY - pos && blockerType.equals("Gigante")) {
                                return false;
                            }
                        }
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
            case "Este": {
                if (positionX < world.sizeX - range) { /* Verifica se o gigante irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;
                        String blockerType = possibleBlocker.getType();

                        for (int pos = 1; pos < range; pos++) {
                            /* Irá verificar se existe algum gigante a impedir o caminho */
                            if (blockerPositionX == positionX + pos && blockerPositionY == positionY && blockerType.equals("Gigante")) {
                                return false;
                            }
                        }
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
            case "Sul": {
                if (positionY < world.sizeY - range) { /* Verifica se o gigante irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;
                        String blockerType = possibleBlocker.getType();

                        for (int pos = 1; pos < range; pos++) {
                            /* Irá verificar se existe algum gigante a impedir o caminho */
                            if (blockerPositionX == positionX && blockerPositionY == positionY + pos && blockerType.equals("Gigante")) {
                                return false;
                            }
                        }
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
            case "Oeste": {
                if (positionX > range - 1) { /* Verifica se o gigante irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;
                        String blockerType = possibleBlocker.getType();

                        for (int pos = 1; pos < range; pos++) {
                            /* Irá verificar se existe algum gigante a impedir o caminho */
                            if (blockerPositionX == positionX - pos && blockerPositionY == positionY && blockerType.equals("Gigante")) {
                                return false;
                            }
                        }
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
