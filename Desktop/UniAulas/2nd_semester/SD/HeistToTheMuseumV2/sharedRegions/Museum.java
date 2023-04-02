package sharedRegions;

import main.*;

public class Museum {

	private int[][] rooms;
	private boolean[] carryCanvas;

	public Museum(int[][] rooms) {
		this.rooms = rooms;
		carryCanvas = new boolean[SimulationParameters.M];
	}

	public int getDistToRoom(int roomID) {
		return rooms[roomID][0];
	}

	public int getAvailableRoom() {
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i][2] == 1 && rooms[i][3] != 1) {
				return i;
			}
		}
		if (isLooted()) {
			return -2;
		}
		return -1;
	}

	public int numCanvas(int roomID) {
		if (rooms[roomID][1] > 0) {
			return rooms[roomID][1];
		}
		return 0;
	}

	public boolean hasCanvas(int thiefID) {
		return carryCanvas[thiefID];
	}

	public int numAvailRooms() {
		int count = 0;
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i][2] == 1 && rooms[i][3] != 1) {
				count++;
			}
		}
		return count;
	}

	public boolean isLooted() {
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i][3] == 1) {
				return true;
			}
		}
		return false;

	}

	public synchronized void rollACanvas(int thiefID) {
		System.out.println("Thief_"+ thiefID +"is rolling");
		while(true) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
