package pt.ulusofona.lp2.fandeisiaGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FandeisiaGameManager {

    private World world;
    private Team[] teams = new Team[2];
    private int turnCounter;
    private int colectedTreasureRound;
    private int initialTeam;

    public FandeisiaGameManager() {
    }

    public String[][] getCreatureTypes() {
        String[][] creatureTypes = new String[6][4];

        creatureTypes[0][0] = "Anão";
        creatureTypes[0][1] = "dwarf0.png";
        creatureTypes[0][2] = "Anda";
        creatureTypes[0][3] = "1";
        creatureTypes[1][0] = "Dragão";
        creatureTypes[1][1] = "dragon0.png";
        creatureTypes[1][2] = "Voa";
        creatureTypes[1][3] = "9";
        creatureTypes[2][0] = "Elfo";
        creatureTypes[2][1] = "elf0.png";
        creatureTypes[2][2] = "Anda e salta";
        creatureTypes[2][3] = "5";
        creatureTypes[3][0] = "Gigante";
        creatureTypes[3][1] = "giant0.png";
        creatureTypes[3][2] = "Anda";
        creatureTypes[3][3] = "5";
        creatureTypes[4][0] = "Humano";
        creatureTypes[4][1] = "human0.png";
        creatureTypes[4][2] = "Anda";
        creatureTypes[4][3] = "3";
        creatureTypes[5][0] = "Mago";
        creatureTypes[5][1] = "dragon0.png";
        creatureTypes[5][2] = "Anda e teleporta-se";
        creatureTypes[5][3] = "9";


        return creatureTypes;
    }

    public String[][] getSpellTypes() {
        String[][] spellTypes = new String[9][3];

        spellTypes[0][0] = "EmpurraParaNorte";
        spellTypes[0][1] = "Move a criatura 1 unidade para Norte.";
        spellTypes[0][2] = "1";
        spellTypes[1][0] = "EmpurraParaEste";
        spellTypes[1][1] = "Move a criatura 1 unidade para Este.";
        spellTypes[1][2] = "1";
        spellTypes[2][0] = "EmpurraParaSul";
        spellTypes[2][1] = "Move a criatura 1 unidade para Sul.";
        spellTypes[2][2] = "1";
        spellTypes[3][0] = "EmpurraParaOeste";
        spellTypes[3][1] = "Move a criatura 1 unidade para Oeste.";
        spellTypes[3][2] = "1";
        spellTypes[4][0] = "ReduzAlcance";
        spellTypes[4][1] = "Reduz o alcance da criatura para: MIN (alcance original, 1).";
        spellTypes[4][2] = "2";
        spellTypes[5][0] = "DuplicaAlcance";
        spellTypes[5][1] = "Aumenta o alcance da criatura para o dobro.";
        spellTypes[5][2] = "3";
        spellTypes[6][0] = "Congela";
        spellTypes[6][1] = "A criatura alvo não se move neste turno.";
        spellTypes[6][2] = "3";
        spellTypes[7][0] = "Congela4Ever";
        spellTypes[7][1] = "A criatura alvo não se move mais até ao final do jogo.";
        spellTypes[7][2] = "10";
        spellTypes[8][0] = "Descongela";
        spellTypes[8][1] = "Inverte a aplicação de um Feitiço Congela4Ever.";
        spellTypes[8][2] = "8";
        return spellTypes;
    }

    public int startGame(String[] content, int rows, int columns) {
        world = new World(columns, rows);
        Team teamA = new Team(10, "LDR", 0, 50);
        Team teamB = new Team(20, "Resistencia", 0, 50);
        teams[0] = teamA;
        teams[1] = teamB;

        for (int pos = 0; pos < content.length; pos++) {
            String[] contentSpliter = content[pos].split(",");
            if (contentSpliter.length == 6) { /* Criaturas */
                Creature creature;
                // Irá definir o tipo da criatura
                switch (contentSpliter[1].replace(" type: ", "")) {
                    case "Dragão": {
                        creature = new Dragon(); // Cria uma criatura do tipo "Dragão"
                        break;
                    }
                    case "Elfo": {
                        creature = new Elf(); // Cria uma criatura do tipo "Elfo"
                        break;
                    }
                    case "Gigante": {
                        creature = new Giant(); // Cria uma criatura do tipo "Gigante"
                        break;
                    }
                    case "Humano": {
                        creature = new Human(); // Cria uma criatura do tipo "Humano"
                        break;
                    }
                    case "Mago": {
                        creature = new Mage(); // Cria uma criatura do tipo "Mago"
                        break;
                    }
                    default: {
                        creature = new Dwarf(); // Cria uma criatura do tipo "Anão"
                        break;
                    }
                }

                creature.id = Integer.parseInt(contentSpliter[0].replace("id: ", ""));
                /* Obter apenas o ID da criatura */
                creature.teamID = Integer.parseInt(contentSpliter[2].replace(" teamId: ", ""));
                /* Obter apenas o ID da equipa a que a criatura pertence */
                for(Team team: teams){
                    if(team.id==creature.teamID){
                        team.coins-=creature.getCost();
                    }
                }
                // Remover as o custo da criatura em moedas à respetiva equipa
                creature.positionX = Integer.parseInt(contentSpliter[3].replace(" x: ", ""));
                /* Obter apenas a posição em X da criatura */
                creature.positionY = Integer.parseInt(contentSpliter[4].replace(" y: ", ""));
                /* Obter apenas a posição em Y da criatura */
                creature.orientation = contentSpliter[5].replace(" orientation: ", "");
                /* Obter apenas a orientação da criatura */
                creature.activeEnchantment = new None();

                world.creatures.add(creature);
                /* Adicionar a criatura ao mapa */
            }
            if (contentSpliter.length == 4 && !contentSpliter[1].equals(" type: hole")) { /* Tesouros */
                Treasure treasure = new Treasure();

                treasure.id = Integer.parseInt(contentSpliter[0].replace("id: ", ""));
                /* Obter apenas o ID do tesouro */
                treasure.positionX = Integer.parseInt(contentSpliter[2].replace(" x: ", ""));
                /* Obter apenas a posição em X do tesouro */
                treasure.positionY = Integer.parseInt(contentSpliter[3].replace(" y: ", ""));
                /* Obter apenas a posição em Y do tesouro */
                treasure.type = contentSpliter[1].replace(" type: ", "");
                /* Obter apenas o tipo de tesouro */
                treasure.typeToPoints();
                /* Coverte o tipo obtido anterirormente em pontos */

                world.treasures.add(treasure);
                /* Adicionar o tesouro ao mapa */
            }
            if (contentSpliter.length == 4 && contentSpliter[1].equals(" type: hole")) { /* Buracos */
                Hole hole = new Hole();

                hole.id = Integer.parseInt(contentSpliter[0].replace("id: ", ""));
                /* Obter apenas o ID do buraco */
                hole.positionX = Integer.parseInt(contentSpliter[2].replace(" x: ", ""));
                /* Obter apenas a posição em X do buraco */
                hole.positionY = Integer.parseInt(contentSpliter[3].replace(" y: ", ""));
                /* Obter apenas a posição em Y do buraco */

                world.holes.add(hole);
                /* Adicionar o buraco ao mapa */
            }
        }
        for (Creature creature: world.creatures){
            System.out.println(creature.toString());
        }
        turnCounter = 0;
        colectedTreasureRound = 0;
        return plafondChecker();
    }

    public int plafondChecker() {
        int coinsSpentTeamA = 0;
        int coinsSpentTeamB = 0;
        for (Creature creature : world.creatures) {
            if (creature.teamID == 10) {
                coinsSpentTeamA += creature.getCost();
            } else {
                coinsSpentTeamB += creature.getCost();
            }
        }
        // Verifica se as duas equipas excedem o Plafond inicial
        if (coinsSpentTeamA > 50 && coinsSpentTeamB > 50) {
            return 1;
        }
        // Verifica se apenas a equipa LDR excede o Plafond inicial
        if (coinsSpentTeamA > 50) {
            return 2;
        }
        // Verifica se apenas a equipa Resistencia excede o Plafond inicial
        if (coinsSpentTeamB > 50) {
            return 3;
        }
        // Caso nenhuma das equipas exceda o Plafond inicial
        return 0;
    }

    public void setInitialTeam(int teamId) {
        initialTeam = teamId;
    }

    public void processTurn() {
        int currentTeamID = getCurrentTeamId();
        for (Creature creature : world.creatures) {
            int range = creature.getRangeFromSpell();
            if (creature.movementAllowed(world, range)) { /* Verifica se o movimento é possivel */
                creature.moveWithOrientation(range); /* Move a criatura para a nova posição */
                /* Verifica se a nova posição da criatura tinha um tesouro, se sim, coleta o mesmo */
                if (creature.collectTreasure(world, teams)) {
                    colectedTreasureRound = 0;
                }
            } else {
                creature.rotateClockWise(); /* Muda a orientação da criatura no sentido dos ponteiros do relogio */
            }
            creature.enchant("Nenhum");
        }
        // Irá atribuir as moedas de fim de turno a cada equipa
        for (Team team : teams) {
            if (team.collectedTreasure) {
                team.coins += 2;
            } else {
                team.coins++;
            }
            team.collectedTreasure = false;
        }
        colectedTreasureRound++;
        turnCounter++;
    }

    public List<Creature> getCreatures() {
        return world.creatures;
    }

    public boolean gameIsOver() {
        int teamAPoints = teams[0].points;
        int teamBPoints = teams[1].points;
        int mapPoints = 0;

        /* Verifica se ja não existem tesouros no mapa */
        if (world.treasures.size() == 0) {
            return true;
        }

        /* Verifica se a equipa perdedora ainda consegue recuperar */
        for (int pos = 0; pos < world.treasures.size(); pos++) {
            mapPoints += world.treasures.get(pos).pointsValue;
        }
        if (teamAPoints > teamBPoints && teamBPoints + mapPoints < teamAPoints) {
            return true;
        }
        if (teamBPoints > teamAPoints && teamAPoints + mapPoints < teamBPoints) {
            return true;
        }

        /* Verifica se o ultimo tesouro apanhado foi há 15 rondas */
        if (colectedTreasureRound == 15) {
            return true;
        }

        /* Se o jogo não acabou, muda de turno */
        return false;
    }

    public List<String> getAuthors() {
        List<String> names = new ArrayList<>();
        names.add("Tiago Lourenço");
        names.add("Alexandre Godinho");
        return names;
    }

    public List<String> getResults() {
        int teamAPoints = teams[0].points;
        int teamBPoints = teams[1].points;
        List<String> results = new ArrayList<>();
        results.add("Welcome to FANDEISIA");
        if (teamAPoints == teamBPoints) {
            results.add("Resultado: EMPATE");
            results.add("LDR: " + teamAPoints);
            results.add("RESISTENCIA: " + teamBPoints);
        } else {
            if (teamAPoints > teamBPoints) {
                results.add("Resultado: Vitória da equipa LDR");
                results.add("LDR: " + teamAPoints);
                results.add("RESISTENCIA: " + teamBPoints);
            } else {
                results.add("Resultado: Vitória da equipa RESISTENCIA");
                results.add("RESISTENCIA: " + teamBPoints);
                results.add("LDR: " + teamAPoints);
            }
        }
        results.add("Nr. de Turnos jogados: " + turnCounter);
        results.add("-----");
        for (Creature creature : world.creatures) {
            results.add(creature.id + " : " + creature.getType() + " : "
                    + creature.getGoldTreasures() + " : " + creature.getSilverTreasures() + " : "
                    + creature.getBronzeTreasures() + " : " + creature.getTotalPoints());
        }
        return results;
    }

    public int getElementId(int x, int y) {
        for (Hole hole : world.holes) {
            if (x == hole.positionX && y == hole.positionY) {
                return hole.id;
            }
        }
        for (Treasure treasure : world.treasures) {
            if (x == treasure.positionX && y == treasure.positionY) {
                return treasure.id;
            }
        }
        for (Creature creature : world.creatures) {
            if (x == creature.positionX && y == creature.positionY) {
                return creature.id;
            }
        }
        return 0;
    }

    public int getCurrentTeamId() {
        if (initialTeam == 10) {
            if (turnCounter % 2 == 0) {
                return 10;
            } else {
                return 20;
            }
        }else {
            if (turnCounter % 2 == 0) {
                return 20;
            } else {
                return 10;
            }
        }
    }

    public int getCurrentScore(int teamID) {
        /* Devolve os pontos atuais da equipa com o ID dado */
        for (Team team : teams) {
            if (team.id == teamID) {
                return team.points;
            }
        }
        return 0;
    }

    public Map<String, Integer> createComputerArmy() {
        int price;
        Map<String, Integer> computerArmy;
        do {
            computerArmy = new HashMap<>();
            price = 0;
            Random rand = new Random();
            int quantity1 = rand.nextInt(3) + 1;
            int quantity2 = rand.nextInt(3) + 1;
            int quantity3 = rand.nextInt(3) + 1;
            int quantity4 = rand.nextInt(3) + 1;
            int quantity5 = rand.nextInt(3) + 1;
            computerArmy.put("Anão", quantity1);
            price += quantity1;
            computerArmy.put("Dragão", quantity2);
            price += quantity2 * 9;
            computerArmy.put("Elfo", quantity3);
            price += quantity3 * 5;
            computerArmy.put("Gigante", quantity4);
            price += quantity4 * 5;
            computerArmy.put("Humano", quantity5);
            price += quantity5 * 3;
        } while (price > 50);
        return computerArmy;
    }

    public boolean enchant(int x, int y, String spellName) {
        for (Creature creature : world.creatures) {
            // Irá verificar se a criatura se encontra na posição dada
            if (creature.positionX == x && creature.positionY == y) {
                // Irá verificar se o feitiço é permitido
                if (creature.enchantmentAllowed(world, spellName)) {
                    creature.enchant(spellName);
                    for (Team team : teams) {
                        if (team.id == getCurrentTeamId() && team.coins - creature.activeEnchantment.getCost() >= 0) {
                            team.coins -= creature.activeEnchantment.getCost();
                            creature.collectTreasure(world, teams);
                            return true;
                        }
                    }
                    creature.activeEnchantment = new None();
                }
            }

        }
        return false;
    }

    public String getSpell(int x, int y) {
        for (Creature creature : world.creatures) {
            if (creature.positionY == x && creature.positionY == y) {
                if(creature.activeEnchantment.getType().equals("Nenhum")){
                    return null;
                }
                if (creature.freezedForEver) {
                    return "Congela4Ever";
                }
                return creature.activeEnchantment.getType();
            }
        }
        return null;
    }

    public int getCoinTotal(int teamID) {
        for (Team team : teams) {
            if (team.id == teamID) {
                return team.getCoins();
            }
        }
        System.out.println("test");
        return 0;
    }

    public boolean saveGame(File fich) {
        try {
            FileWriter myWriter = new FileWriter(fich);

            myWriter.write("LDR: " + teams[0].points + " " + teams[0].coins + "\n");
            myWriter.write("Resistencia: " + teams[1].points + " " + teams[1].coins + "\n");
            myWriter.write("Turn: " + getCurrentTeamId() + "\n");
            for (Creature c : world.creatures) {
                myWriter.write("Creature: " + c.getType() + " " + c.id + " " + c.teamID + " " + c.positionX + " " + c.positionY + " " + c.orientation + "\n");
            }
            for (Treasure t : world.treasures) {
                myWriter.write("Treasure: " + t.type + " " + t.id + " " + t.pointsValue + " " + t.positionX + " " + t.positionY + "\n");
            }

            for (Hole h : world.holes) {
                myWriter.write("Hole: " + h.id + " " + h.positionX + " " + h.positionX + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    public boolean loadGame(File fich) {
        try {
            world.creatures.clear();
            world.treasures.clear();
            world.holes.clear();

            Scanner myReader = new Scanner(fich);
            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();
                String[] contentSpliter = data.split(" ");

                if (contentSpliter[0].equals("LDR:")) {
                    teams[0].points = Integer.parseInt(contentSpliter[1]);
                    teams[0].coins = Integer.parseInt(contentSpliter[2]);
                }

                if (contentSpliter[0].equals("Resistencia:")) {
                    teams[1].points = Integer.parseInt(contentSpliter[1]);
                    teams[1].coins = Integer.parseInt(contentSpliter[2]);
                }

                if (contentSpliter[0].equals("Creature:")) {
                    Creature creature;
                    switch (contentSpliter[1]) {
                        case "Dragão": {
                            creature = new Dragon(); // Cria uma criatura do tipo "Dragão"
                            break;
                        }
                        case "Elfo": {
                            creature = new Elf(); // Cria uma criatura do tipo "Elfo"
                            break;
                        }
                        case "Gigante": {
                            creature = new Giant(); // Cria uma criatura do tipo "Gigante"
                            break;
                        }
                        case "Humano": {
                            creature = new Human(); // Cria uma criatura do tipo "Humano"
                            break;
                        }
                        case "Mago": {
                            creature = new Mage(); // Cria uma criatura do tipo "Mago"
                            break;
                        }
                        default: {
                            creature = new Dwarf(); // Cria uma criatura do tipo "Anão"
                            break;
                        }
                    }
                    creature.id = Integer.parseInt(contentSpliter[2]);
                    creature.teamID = Integer.parseInt(contentSpliter[3]);
                    creature.positionX = Integer.parseInt(contentSpliter[4]);
                    creature.positionY = Integer.parseInt(contentSpliter[5]);
                    creature.orientation = contentSpliter[6];

                    world.creatures.add(creature);
                }

                if (contentSpliter[0].equals("Treasure:")) {
                    Treasure treasure = new Treasure();
                    treasure.type = contentSpliter[1];
                    treasure.id = Integer.parseInt(contentSpliter[2]);
                    treasure.pointsValue = Integer.parseInt(contentSpliter[3]);
                    treasure.positionX = Integer.parseInt(contentSpliter[4]);
                    treasure.positionY = Integer.parseInt(contentSpliter[5]);

                    world.treasures.add(treasure);
                }

                if (contentSpliter[0].equals("Hole:")) {
                    Hole hole = new Hole();
                    hole.id = Integer.parseInt(contentSpliter[1]);
                    hole.positionX = Integer.parseInt(contentSpliter[2]);
                    hole.positionY = Integer.parseInt(contentSpliter[3]);

                    world.holes.add(hole);
                }


                System.out.println(data);
            }
            myReader.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;

    }

    public String whoIsLordEder() {
        return "Éderzito António Macedo Lopes";
    }
}
