package sharedRegions;

import main.*;


import commInfra.*;
import entities.*;
import genclass.GenericIO;

public class AssaultParty {
    
    
	private int partyID;
	private int partyMembers;
	private int[][] partyInfo;
	private int distance;

	public AssaultParty(int partyID) {
		this.partyID = partyID;
		partyMembers = 0;
		distance = 0;
		partyInfo = new int[3][3];
	}

	public int getPartyID() {
		return partyID;
	}
    
    public synchronized void crawlIn(int thiefID,int maxDisp,int dist){
    	((OrdinaryThief) Thread.currentThread()).setThiefState(OrdinaryThiefStates.CRAWLING_INWARDS);
		distance = dist;
		
		int inPartyID = partyMembers;
		partyMembers++;
		partyInfo[inPartyID][0] = thiefID;
		partyInfo[inPartyID][1] = 0;

		int move = 0;
		notifyAll();
		while (partyMembers < 3) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		while (partyInfo[inPartyID][1] != distance) {
			move = allowCrawlIn(maxDisp,inPartyID);
			if (move ==0) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}else {
				partyInfo[inPartyID][1] = partyInfo[inPartyID][1] + move;
				//System.out.println("Thief_" + thiefID + " at " + partyInfo[partyID][1]);
				notifyAll();

			}
		}
		System.out.println("Thief_" + thiefID + " have arrived to room");
    	((OrdinaryThief) Thread.currentThread()).setThiefState(OrdinaryThiefStates.AT_A_ROOM);   
    	partyMembers--;
		while(partyMembers>0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    	
}

public synchronized int allowCrawlIn(int maxDisp,int posID) {
	int pos1 = 0;
	int pos2 = 0;
	int currentPosition = partyInfo[posID][1];
	int newPosition = 0;
	
	for(int i=0;i<partyInfo.length;i++) {
		if(i!=posID) {
			if(pos1==0) {
				pos1 = partyInfo[i][1];
			}else {
				pos2 = partyInfo[i][1];
			}
		}
	}
	for (int move = maxDisp;move>0;move--) {
		newPosition = currentPosition + move;
		if((newPosition-pos1)<=3 && (newPosition-pos2)<=3 && newPosition<=distance) {
			if((pos1 == distance || pos2 == distance)&& newPosition==distance) {
				return move;
			}
			if(newPosition!=pos1 && newPosition!=pos2) {
				return move;
			}
		}
	}
	return 0;
}

    public synchronized void crawlOut(int thiefID,int maxDisp){
        ((OrdinaryThief) Thread.currentThread()).setThiefState(OrdinaryThiefStates.CRAWLING_OUTWARDS);
    	System.out.println("Thief_" + thiefID + " have arrived to collectionSite");
    	
        ((OrdinaryThief) Thread.currentThread()).setThiefState(OrdinaryThiefStates.COLLECTION_SITE);
    }    

    public synchronized void reverseDirection(int thiefID){
    	System.out.println("Thief_" + thiefID + " going back ");
        ((OrdinaryThief) Thread.currentThread()).setThiefState(OrdinaryThiefStates.CRAWLING_OUTWARDS);
    } 
}
