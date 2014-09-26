/**
 * 
 */
package de.buxi.cantstop.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import de.buxi.cantstop.model.DiceNotThrownException;
import de.buxi.cantstop.model.GameController;
import de.buxi.cantstop.model.GameTransferObject;
import de.buxi.cantstop.model.InvalidClimberMovementException;
import de.buxi.cantstop.model.InvalidWayNumberException;
import de.buxi.cantstop.model.NoClimberOnWayException;
import de.buxi.cantstop.model.NoMarkerIsAvailableException;
import de.buxi.cantstop.model.NotAvailableClimberException;
import de.buxi.cantstop.model.NotEnoughPlayerException;
import de.buxi.cantstop.model.NullClimberException;
import de.buxi.cantstop.model.RopePointInvalidUsageException;
import de.buxi.cantstop.model.TwoDicesPair;
/**
 * @author buxi
 *
 */
public class GameServicesConsole implements GameService {
	private GameController gameController;
	private Log log = LogFactory.getLog(GameServicesConsole.class);
	
	/**
	 * @param gameController
	 */
	public GameServicesConsole(GameController gameController) {
		super();
		this.gameController = gameController;
	}

	@Override
	public GameTransferObject startGame() throws GameException {
		try {
			return gameController.doGameStart();
		} catch (InvalidWayNumberException | NotEnoughPlayerException
				| DiceNotThrownException e) {
			log.error(e.toString());
			throw new GameException(e);
		}
	}

	@Override
	public GameTransferObject startTurn() throws GameException {
		try {
			return gameController.doStartGameTurn();
		} catch (DiceNotThrownException | InvalidWayNumberException e) {
			log.error(e.toString());
			throw new GameException(e);
		}
	}

	@Override
	public GameTransferObject finishGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameTransferObject finishTurn() throws GameException {
		try {
			return gameController.doEndGameTurn();
		} catch (NoMarkerIsAvailableException | RopePointInvalidUsageException
				| NoClimberOnWayException | InvalidClimberMovementException
				| InvalidWayNumberException | DiceNotThrownException e) {
			log.error(e.toString());
			throw new GameException(e);
		}
	}

	@Override
	public GameTransferObject getAllGameInformation() throws GameException {
		try {
			return gameController.doGetTransferObject();
		} catch (DiceNotThrownException | InvalidWayNumberException e) {
			log.error(e.toString());
			throw new GameException(e);
		}
	}

	@Override
	public GameTransferObject throwDices() throws GameException {
		try {
			return gameController.doThrowDices();
		} catch (DiceNotThrownException | InvalidWayNumberException
				| NoMarkerIsAvailableException | RopePointInvalidUsageException
				| NoClimberOnWayException | InvalidClimberMovementException e) {
			log.error(e.toString());
			throw new GameException(e);
		}
	}

	@Override
	public GameTransferObject executePairs(TwoDicesPair chosenPair,
			int wayNumber) throws GameException {
		try {
			return gameController.doExecutePairs(chosenPair, wayNumber);
		} catch (DiceNotThrownException | RopePointInvalidUsageException
				| NotAvailableClimberException | InvalidWayNumberException
				| InvalidClimberMovementException
				| NoMarkerIsAvailableException | NullClimberException
				| NoClimberOnWayException e) {
			log.error(e.toString());
			throw new GameException(e);
		}
	}
	
	@Override
	public String addPlayer(String playerName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void loadState() {
		InputStream fis = null;
		ObjectInputStream o = null;
		try {
			fis = new FileInputStream("saved.dat");
			o = new ObjectInputStream(fis);
			GameController savedGameController = (GameController) o.readObject();
			this.gameController = savedGameController;
		} catch (IOException e) {
			System.err.println(e);
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		} finally {
			try {
				fis.close();
				o.close();
			} catch (Exception e) {
			}
		}
	}

	public void saveState() {
		OutputStream fos = null;
		ObjectOutputStream o = null;

		try {
			fos = new FileOutputStream("saved.dat");
			o = new ObjectOutputStream(fos);
			o.writeObject( this.gameController);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
				fos.close();
				o.close();
			} catch (Exception e) {
			}
		}
	}
}