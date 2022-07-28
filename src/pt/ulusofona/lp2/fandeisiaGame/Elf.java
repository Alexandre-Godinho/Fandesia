package pt.ulusofona.lp2.fandeisiaGame;

public class Elf extends Creature {
    public int getCost() {
        return 5;
    }

    public int getRange(){
        return 2;
    }

    public String getImagePNG() {
        if (teamID == 10) {
            if (orientation.equals("Norte")) {
                return "elf0_norte.png";
            }
            if (orientation.equals("Sul")) {
                return "elf0_sul.png";
            }
            if (orientation.equals("Este")) {
                return "elf0_este.png";
            }
            if (orientation.equals("Oeste")) {
                return "elf0_oeste.png";
            }
            if(orientation.equals("Noroeste")) {
                return "elf0_noroeste.png";
            }
            if (orientation.equals("Nordeste")) {
                return "elf0_nordeste.png";
            }
            if (orientation.equals("Sudeste")) {
                return "elf0_sudeste.png";
            }
            if(orientation.equals("Sudoeste")) {
                return "elf0_sudoeste.png";
            }
        } else {
            if (orientation.equals("Norte")) {
                return "elf1_norte.png";
            }
            if (orientation.equals("Sul")) {
                return "elf1_sul.png";
            }
            if (orientation.equals("Este")) {
                return "elf1_este.png";
            }
            if (orientation.equals("Oeste")) {
                return "elf1_oeste.png";
            }
            if(orientation.equals("Noroeste")) {
                return "elf1_noroeste.png";
            }
            if (orientation.equals("Nordeste")) {
                return "elf1_nordeste.png";
            }
            if (orientation.equals("Sudeste")) {
                return "elf1_sudeste.png";
            }
            if(orientation.equals("Sudoeste")) {
                return "elf1_sudoeste.png";
            }
        }
        return "";
    }

    public String getType() {
        return "Elfo";
    }

    public String toString() {
        return id + " | Elfo | " + teamID + " | " + pointCounter() + " @ (" + positionX + ", " + positionY + ") " + orientation;
    }

    public boolean movementAllowed(World world, int range) {
        switch (orientation) { /* Verifica qual a orientação do elfo */
            case "Norte": {
                if (positionY > range - 1) { /* Verifica se o elfo irá sair do mapa */
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
                if (positionY > range - 1 && positionX < world.sizeX - range) { /* Verifica se o elfo irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        for (int pos = 1; pos <= range; pos++) {
                            /* Irá verificar se existe alguma criatura a impedir o caminho e na possivel nova posição*/
                            if (blockerPositionX == positionX + pos && blockerPositionY == positionY - pos) {
                                return false;
                            }
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
                if (positionX < world.sizeX - range) { /* Verifica se o elfo irá sair do mapa */
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
                if (positionX < world.sizeX - range && positionY < world.sizeY - range) { /* Verifica se o elfo irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        for (int pos = 1; pos <= range; pos++) {
                            /* Irá verificar se existe alguma criatura a impedir o caminho e na possivel nova posição*/
                            if (blockerPositionX == positionX + pos && blockerPositionY == positionY + pos) {
                                return false;
                            }
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
                if (positionY < world.sizeY - range) { /* Verifica se o elfo irá sair do mapa */
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
                if (positionY < world.sizeY - range && positionX > range - 1) { /* Verifica se o elfo irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        for (int pos = 1; pos <= range; pos++) {
                            /* Irá verificar se existe alguma criatura a impedir o caminho e na possivel nova posição*/
                            if (blockerPositionX == positionX - pos && blockerPositionY == positionY + pos) {
                                return false;
                            }
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
                if (positionX > range - 1) { /* Verifica se o elfo irá sair do mapa */
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
                if (positionX > range - 1 && positionY > range - 1) { /* Verifica se o elfo irá sair do mapa */
                    for (Creature possibleBlocker : world.creatures) {
                        int blockerPositionX = possibleBlocker.positionX;
                        int blockerPositionY = possibleBlocker.positionY;

                        for (int pos = 1; pos <= range; pos++) {
                            /* Irá verificar se existe alguma criatura a impedir o caminho e na possivel nova posição*/
                            if (blockerPositionX == positionX - pos && blockerPositionY == positionY - pos) {
                                return false;
                            }
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
