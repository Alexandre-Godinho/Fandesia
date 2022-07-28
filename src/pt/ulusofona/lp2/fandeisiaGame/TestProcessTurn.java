package pt.ulusofona.lp2.fandeisiaGame;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestProcessTurn {
    @Test
    public void TestSaiDoMapa (){
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[5];
        conteudoMundo[0] = "id: 1, type: Dragão, teamId: 0, x: 7, y: 0, orientation: Este";
        conteudoMundo[1] = "id: 2, type: Dragão, teamId: 0, x: 7, y: 5, orientation: Sul";
        conteudoMundo[2] = "id: 3, type: Dragão, teamId: 1, x: 0, y: 0, orientation: Norte";
        conteudoMundo[3] = "id: 4, type: Dragão, teamId: 1, x: 0, y: 5, orientation: Oeste";
        conteudoMundo[4] = "id: -1, type: treasure, x: 3, y: 3";
        gameManager.startGame(conteudoMundo, 6, 8);
        assertEquals(1, gameManager.getElementId(7, 0));
        assertEquals(2, gameManager.getElementId(7, 5));
        assertEquals(3, gameManager.getElementId(0, 0));
        assertEquals(4, gameManager.getElementId(0, 5));
        assertEquals(-1, gameManager.getElementId(3,3));
        gameManager.processTurn();
        assertEquals(1, gameManager.getElementId(7,0));
        assertEquals(2, gameManager.getElementId(7,5));
        assertEquals(3, gameManager.getElementId(0,0));
        assertEquals(4, gameManager.getElementId(0,5));

    }
    @Test
    public void TestMudaOrientacao () {
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[5];
        conteudoMundo[0] = "id: 1, type: Dragão, teamId: 0, x: 7, y: 0, orientation: Este";
        conteudoMundo[1] = "id: 2, type: Dragão, teamId: 0, x: 7, y: 5, orientation: Sul";
        conteudoMundo[2] = "id: 3, type: Dragão, teamId: 1, x: 0, y: 0, orientation: Norte";
        conteudoMundo[3] = "id: 4, type: Dragão, teamId: 1, x: 0, y: 5, orientation: Oeste";
        conteudoMundo[4] = "id: -1, type: treasure, x: 3, y: 3";
        gameManager.startGame(conteudoMundo, 6, 8);
        assertEquals("Este", gameManager.getCreatures().get(0).getOrientation());
        assertEquals("Sul", gameManager.getCreatures().get(1).getOrientation());
        assertEquals("Norte", gameManager.getCreatures().get(2).getOrientation());
        assertEquals("Oeste", gameManager.getCreatures().get(3).getOrientation());
        gameManager.processTurn();
        assertEquals("Sul", gameManager.getCreatures().get(0).getOrientation());
        assertEquals("Oeste", gameManager.getCreatures().get(1).getOrientation());
        assertEquals("Este", gameManager.getCreatures().get(2).getOrientation());
        assertEquals("Norte",gameManager.getCreatures().get(3).getOrientation());

    }
    @Test
    public void TestApanhaTesouro (){
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[4];
        conteudoMundo[0] = "id: 1, type: Dragão, teamId: 0, x: 2, y: 1, orientation: Sul";
        conteudoMundo[1] = "id: 2, type: Dragão, teamId: 1, x: 5, y: 4, orientation: Oeste";
        conteudoMundo[2] = "id: -1, type: treasure, x: 2, y: 2";
        conteudoMundo[3] = "id: -2, type: treasure, x: 4, y: 4";
        gameManager.startGame(conteudoMundo, 6, 8);
        assertEquals(1, gameManager.getElementId(2, 1));
        assertEquals(2, gameManager.getElementId(5, 4));
        assertEquals(-1, gameManager.getElementId(2,2));
        assertEquals(-2, gameManager.getElementId(4,4));
        gameManager.processTurn();
        gameManager.processTurn();
        assertEquals(1, gameManager.getElementId(2,3));
        assertEquals(2, gameManager.getElementId(3,4));
        assertEquals(0, gameManager.getElementId(2,2));
        assertEquals(0, gameManager.getElementId(4,4));
    }
    @Test
    public void TestTeamId () {
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[3];
        conteudoMundo[0] = "id: 1, type: Dragão, teamId: 0, x: 2, y: 1, orientation: Sul";
        conteudoMundo[1] = "id: 2, type: Dragão, teamId: 1, x: 5, y: 4, orientation: Oeste";
        conteudoMundo[2] = "id: -1, type: treasure, x: 2, y: 2";
        gameManager.startGame(conteudoMundo, 6, 8);
        assertEquals(0, gameManager.getCurrentTeamId());
        gameManager.processTurn();
        assertEquals(1, gameManager.getCurrentTeamId());
        gameManager.processTurn();
        assertEquals(0, gameManager.getCurrentTeamId());
    }
}
