/**
 * 
 */
package de.buxi.cantstop.spielobjekte;

import java.util.Collection;
import java.util.List;

/**
 * @author buxi
 * Transfer Object for GameController
 * is a must http://www.oracle.com/technetwork/articles/javaee/transferobject-139757.html
 * 
 * TODO UNITTEST ?
 */

public class GameTransferObject {
	public GameState gameState;
	public Player actualPlayer;
	public List<TwoDicesPair> possiblePairs;
	public Collection<TwoDicesPair> wrongPairs;
	public String boardDisplay;
	public int actualPlayerNumber;
	public List<Player> playerList;
	public Collection<Dice> dices;
	public String errorMessage;
	
}
