package edu.rich.simulator.elevator.pojo;

public class Elevator {
	/**
	 * The number of the elevator
	 */
	private int elevatorNumber;
	/**
	 *  Provides information with regards to what floor the elevator is currently on,
	 *  what floor it has just been on and what the next floor is.
	 *  Upon application initialisation the elevator will start on the ground floor.
	 */
	private String currentFloor;
	private String nextFloor;
	private String previousFloor;
	/**
	 * Provides information as to the status of the elevator doors
	 * 		- closed
	 * 		- open
	 * Doors will remain closed until the elevator has been called 
	 * and has arrived at that floor.
	 */
	private String doorStatus;
	
	public int getElevatorNumber() {
		return elevatorNumber;
	}
	
	public void setElevatorNumber(int elevatorNumber) {
		this.elevatorNumber = elevatorNumber;
	}

	public String getCurrentFloor() {
		return currentFloor;
	}

	public void setCurrentFloor(String currentFloor) {
		this.currentFloor = currentFloor.toUpperCase();
	}

	public String getNextFloor() {
		return nextFloor;
	}

	public void setNextFloor(String nextFloor) {
		this.nextFloor = nextFloor.toUpperCase();
	}

	public String getPreviousFloor() {
		return previousFloor;
	}

	public void setPreviousFloor(String previousFloor) {
		this.previousFloor = previousFloor.toUpperCase();
	}

	public String getDoorStatus() {
		return doorStatus;
	}

	public void setDoorStatus(String doorStatus) {
		this.doorStatus = doorStatus;
	}
	
	@Override
	public String toString() {
		return elevatorNumber + ": " + currentFloor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + elevatorNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Elevator other = (Elevator) obj;
		if (elevatorNumber != other.elevatorNumber)
			return false;
		return true;
	}
	
	private enum ButtonPanel {
		GROUND(0),
		FIRST(1),
		SECOND(2),
		ALARM(3);
		
		private int button;
				
		private ButtonPanel(int button) {
			this.button = button;
		}
	}

}
