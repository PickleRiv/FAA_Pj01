package sharedRegions;

import entities.*;
import genclass.GenericIO;

import main.*;
import commInfra.*;

public class ControlAndCollectionSite {
	
	public ControlAndCollectionSite() {
		
	}
    
    public synchronized void startOperations(){
        ((MasterThief) Thread.currentThread()).setMasterState(MasterThiefStates.DECIDING_WHAT_TO_DO);
        //System.out.println("Master is deciding what to do");
    }
    
    /**
    *  Operation take a rest.
    *
    *  It is called by the MasterThief while waiting for thieves to arrive.
    *
    *    @return true, if his life cycle has come to an end -
    *            false, otherwise
    */
    public synchronized void takeARest(){
        ((MasterThief) Thread.currentThread()).setMasterState(MasterThiefStates.WAITING_FOR_ARRIVAL);
    
        while(true) {
        	try {
        		System.out.println("Master is waiting");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
    

    public synchronized int handACanvas(){
        return 0;
    }    

    public synchronized int collectACanvas(){
        return 0;
    }
    
    public synchronized void sumUpResults(){
        
    }

}
