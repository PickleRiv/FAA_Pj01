package entities;

public class OrdinaryThiefStates {
    
    /**
	 * 	blocking state (initial / final state)
	 */
	public static final int CONCENTRATION_SITE = 0;

	/**
	 * 	transitional state with eventual waiting
     */
	public static final int CRAWLING_INWARDS = 1;

	/**
	 * 	transitional state
	 */
	public static final int AT_A_ROOM = 2;

	/**
	 * 	transitional state with eventual waiting
	 */
	public static final int CRAWLING_OUTWARDS = 3;

	/**
	 * blocking state
	 */
	public static final int COLLECTION_SITE = 4;
	
	/**
	 *   It can not be instantiated.
	 */
	private OrdinaryThiefStates ()
	{ }
}
