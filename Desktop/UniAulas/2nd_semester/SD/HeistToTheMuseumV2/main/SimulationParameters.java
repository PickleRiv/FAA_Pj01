package main;

/**
 * Definition of simulation parameters
 */

public class SimulationParameters {
    /**
     * Number of Rooms
     */
    public static final int N = 5;

    /**
     * Number of Thieves
     */
    public static final int M = 6;

    /**
     * Number of Parties
     */
    public static final int G = 2;

    /**
     * Number of Thieves per party
     */
    public static final int K = 3;

    /**
     * Max separation distance between thieves
     */
    public static final int S = 3;
    
    public static final int minDist = 15;
    public static final int maxDist = 30;
    public static final int minCanvas = 8;
    public static final int maxCanvas = 16;
    public static final int minDisp = 2;
    public static final int maxDisp = 6;
    //Max Displacement MD = random(2 to 6)
    //Amount of paintings in each room Qi = random(8 to 16) 
    private SimulationParameters () {}
}
