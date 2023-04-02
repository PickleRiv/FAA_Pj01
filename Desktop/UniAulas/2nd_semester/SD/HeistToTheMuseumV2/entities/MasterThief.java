package entities;

import main.SimulationParameters;
import sharedRegions.*;

public class MasterThief extends Thread {
	private int masterID; // Master Thief Identification
	private int masterState; // Master Thief State
	private final ControlAndCollectionSite ccSite; // Reference to the Collection Site
	private final ConcentrationSite conSite; // Reference to the Concentration Site
	private final AssaultParty[] aParty; // Reference to the Concentration Site
	private int[] partiesSent;
	private final Museum museum;

	/**
	 * Instantiation of a barber thread.
	 *
	 * @param name     thread name
	 * @param masterID master thief id
	 * @param ccSite   Reference to the Collection Site
	 */
	public MasterThief(String name, int masterID, ControlAndCollectionSite ccSite, ConcentrationSite conSite,
			AssaultParty[] aParty, Museum museum) {
		super(name);
		this.masterID = masterID;
		masterState = MasterThiefStates.PLANNING_THE_HEIST;
		this.ccSite = ccSite;
		this.conSite = conSite;
		this.aParty = aParty;
		this.museum = museum;
		partiesSent = new int[SimulationParameters.G];
	}

	public void setMasterID(int ID) {
		masterID = ID;
	}

	public int getMasterID() {
		return masterID;
	}

	/**
	 * 
	 * @param masterState new state to be set
	 */
	public void setMasterState(int state) {
		masterState = state;
	}

	/**
	 * @return masterState
	 */
	public int getMasterState() {
		return masterState;
	}

	@Override
	public void run() {
		ccSite.startOperations(); // state changes from planning the heist
		int currentParty = -1;
		int availableRoom = -1;
		while (true) {
			availableRoom = museum.getAvailableRoom();
			if (masterState == 1) {
				if (availableRoom == -2) {
					break;
				}
				if (conSite.isBusy()) {
					ccSite.takeARest();
				} else {
					currentParty = conSite.appraiseSit(); // state changes to deciding what to do
					if(currentParty !=-1) {
						conSite.prepareAssaultParty();
					}
				}
			}
			
			if(masterState==2) {
				if (availableRoom != -1) {
					conSite.sendAssaultParty(availableRoom); // state changes to deciding what to do
					partiesSent[currentParty]++;
				}
			}
			
			if(masterState ==3) {
				ccSite.collectACanvas();
			}
		}
		ccSite.sumUpResults();

	}
}
