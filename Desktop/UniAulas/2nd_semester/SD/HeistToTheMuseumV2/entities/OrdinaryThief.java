package entities;

import sharedRegions.*;
import main.*;

public class OrdinaryThief extends Thread {
	/**
	 * ThiefID
	 */
	private int thiefID;

	/**
	 * Thief State
	 */
	private int thiefState;

	private boolean carryCanvas;
	private int targetRoom;

	/**
	 * Reference to shared regions (Museum, AssaultParty, ConcentrationSite,
	 * CollectionSite)
	 */
	private final ConcentrationSite conSite;
	private final AssaultParty aParty;
	private final Museum museum;
	private final ControlAndCollectionSite ccSite;
	private int maxDisp;

	/**
	 * Thief Thread instantiation
	 * 
	 * @param thiefID Id of the thief
	 * @param cSite   reference to the concentration site
	 * @param aParty  reference to the Assault Party
	 * @param museum  reference to the Museum
	 * @param ccSite  reference to the Control and Collection Site
	 */

	public OrdinaryThief(String name, int thiefID, ConcentrationSite conSite, AssaultParty aParty, Museum museum,
			ControlAndCollectionSite ccSite, int maxDisp) {
		super(name);
		this.thiefID = thiefID;
		this.maxDisp = maxDisp;
		this.conSite = conSite;
		this.aParty = aParty;
		this.museum = museum;
		this.ccSite = ccSite;
		this.carryCanvas = false;
		targetRoom = -1;
		thiefState = OrdinaryThiefStates.CONCENTRATION_SITE;

	}

	public void setThiefID(int id) {
		thiefID = id;
	}

	public int getThiefID() {
		return thiefID;
	}

	public void setThiefState(int state) {
		thiefState = state;
	}

	public int getThiefState() {
		return thiefState;
	}

	public int getThiefPartyID() {
		return aParty.getPartyID();
	}

	@Override
	public void run() {
		while (true) {

			conSite.amINeeded(thiefID);
			targetRoom = conSite.prepareExcursion(thiefID);

			aParty.crawlIn(thiefID, maxDisp, museum.getDistToRoom(targetRoom));

			carryCanvas = museum.hasCanvas(thiefID);
			museum.rollACanvas(thiefID);
			// if canvas or room empty and not last wait()
			// if canvas or room empty and last
			aParty.reverseDirection(thiefID);

			aParty.crawlOut(thiefID, maxDisp);
			ccSite.handACanvas();
		}
	}
}
