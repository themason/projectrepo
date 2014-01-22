package edu.rich.simulator.elevator.controller;

import java.util.List;

import edu.rich.simulator.elevator.pojo.Elevator;

public class ElevatorController {
	
	private List<Elevator> elevators;

	public ElevatorController(List<Elevator> elevators) {
		this.elevators = elevators;
	}	
	
	public Elevator getElevator(int elevatorName) {
		return elevators.get(elevators.indexOf(elevatorName));
	}
	
	public List<Elevator> getElevators() {
		return elevators;
	}

}
