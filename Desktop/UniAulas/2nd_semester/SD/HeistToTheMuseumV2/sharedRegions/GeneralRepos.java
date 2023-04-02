package sharedRegions;

import main.*;
import entities.*;
import genclass.GenericIO;
import genclass.TextFile;
import java.util.Objects;

// /**
//  *  General Repository.
//  * 
//  *    It is responsible to keep the visible internal state of the problem and to provide means for it
//  *    to be printed in the logging file.
//  *    It is implemented as an implicit monitor.
//  *    All public methods are executed in mutual exclusion.
//  *    There are no internal synchronization points.
//  */

public class GeneralRepos
{

   
   public GeneralRepos ()
   {
;
      
   }
   
   public synchronized void setThiefSituation(boolean Situation) {

   }
   
   

  /**
   *   Set master state.
   *
   *     @param id master id
   *     @param state master state
   */

   public synchronized void setMasterState (int state)
   {

   }

  /**
   *   Set customer state.
   *
   *     @param id ordinary thief id
   *     @param state ordinary thief state
   */

   public synchronized void setThiefState (int id, int state)
   {

   }

  /**
   *   Set Master and OrdinaryThief state.
   *customerState
   *     @param masterState master state
   *     @param cId customer id
   *     @param thiefState ordinary thief state
   */

   public synchronized void setMasterOrdinaryState (int masterState, int thiefID, int thiefState)
   {
  
   }

  /**
   *  Write the header to the logging file.
   */

   private void reportInitialStatus ()
   {
      
   }

  /**
   *  Write a state line at the end of the logging file.
   *
   *  The current state of the barbers and the customers is organized in a line to be printed.
   *  Internal operation.
   */

   private void reportStatus ()
   {
 
   }
   
   public void reportLegend()
	{
		
	}

   
}