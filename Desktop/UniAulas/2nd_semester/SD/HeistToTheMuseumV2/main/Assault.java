package main;

import entities.*;
import sharedRegions.*;

/**
 * Simulation of the Museum Heist
 */
public class Assault {

	public static void main(String[] args) {
		MasterThief master;
		OrdinaryThief[] thief = new OrdinaryThief[SimulationParameters.M];
		AssaultParty[] aParty = new AssaultParty[SimulationParameters.G];
		ConcentrationSite conSite;
		ControlAndCollectionSite ccSite;
		Museum museum;
		int[][] rooms = new int[SimulationParameters.N][4]; // roomsDistance, roomsCanvas,Availability
		int maxDisp;
		GeneralRepos repos;
		String fileName;


//        /* problem initialization */
//        GenericIO.writelnString ("\n" + "      Problem of the Sleeping Barbers\n");
//        GenericIO.writeString ("Number of iterations of the customer life cycle? ");
//        nIter = GenericIO.readlnInt ();
//        do
//        { GenericIO.writeString ("Logging file name? ");
//          fileName = GenericIO.readlnString ();
//          if (FileOp.exists (".", fileName))
//             { do
//               { GenericIO.writeString ("There is already a file with this name. Delete it (y - yes; n - no)? ");
//                 opt = GenericIO.readlnChar ();
//               } while ((opt != 'y') && (opt != 'n'));
//               if (opt == 'y')
//                  success = true;
//                  else success = false;
//             }
//             else success = true;
//        } while (!success);
//        repos = new GeneralRepos (fileName, nIter);
//        bShop = new BarberShop (repos);
		for (int[] room : rooms) {
			room[0] = (int) (Math.random() * (SimulationParameters.maxDist - SimulationParameters.minDist))
					+ SimulationParameters.minDist;
			room[1] = (int) (Math.random() * (SimulationParameters.maxCanvas - SimulationParameters.minCanvas))
					+ SimulationParameters.minCanvas;
			room[2] = 1;
			room[3] = 0;
		}
		
		for (int i = 0; i < aParty.length; i++) {
			aParty[i] = new AssaultParty(i);

		}
		conSite = new ConcentrationSite();
		ccSite = new ControlAndCollectionSite();
		museum = new Museum(rooms);

		// initialisation of master
		master = new MasterThief("Master", 1, ccSite, conSite, aParty, museum);
		//System.out.println("Master Created");

		// initialisation of thieves
		for (int i = 0; i < SimulationParameters.M; i++) {
			maxDisp = (int) (Math.random() * (SimulationParameters.maxDisp - SimulationParameters.minDisp))
					+ SimulationParameters.minDisp;
			if (i < 3) {
				thief[i] = new OrdinaryThief("Thief_" + (i + 1), i, conSite, aParty[0], museum, ccSite, maxDisp);
			} else {
				thief[i] = new OrdinaryThief("Thief_" + (i + 1), i, conSite, aParty[1], museum, ccSite, maxDisp);
			}
			//System.out.println("Thief_" + i + "Created");
		}

		System.out.println("Start of simulation.");

		master.start();
		//System.out.println("Master Started");
		for (int i = 0; i < SimulationParameters.M; i++) {
			thief[i].start();
			//System.out.println("Thief_" + i + " Started");
		}

		/* waiting for the end of the simulation */

		// GenericIO.writelnString ();
		for (int i = 0; i < SimulationParameters.M; i++) {
			try {
				thief[i].join();
				System.out.println("Thief_" + i + " terminated");
			} catch (InterruptedException e) {
			}
			// GenericIO.writelnString ("The customer " + (i+1) + " has terminated.");
		}

		// GenericIO.writelnString ();
		while (master.isAlive()) {
			master.interrupt();
			Thread.yield();
		}
		try {
			master.join();
		} catch (InterruptedException e) {
		}
		// GenericIO.writelnString ("The barber " + (i+1) + " has terminated.");#
		System.out.println("Master has been terminated");
	}
	// GenericIO.writelnString ();
}
