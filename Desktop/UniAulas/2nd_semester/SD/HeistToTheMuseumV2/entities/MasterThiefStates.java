package entities;

public class MasterThiefStates {
    
    /**
	 * 	initial state (transitional)
	 */
	public static final int PLANNING_THE_HEIST = 0;

	/**
	 * 	transitional state with eventual waiting
	 */
	public static final int DECIDING_WHAT_TO_DO = 1;

	/**
	 * 	blocking state
	 */
	public static final int ASSEMBLING_A_GROUP = 2;

	/**
	*  blocking state
	*/
	public static final int WAITING_FOR_ARRIVAL = 3;

	/**
	 * final state
	 */
	public static final int PRESENTING_THE_REPORT = 4;
	
	/**
	 *   It can not be instantiated.
	 */
	private MasterThiefStates ()
	{ }
}

