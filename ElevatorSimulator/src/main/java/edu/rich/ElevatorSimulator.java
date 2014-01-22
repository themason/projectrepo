package edu.rich;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.rich.simulator.elevator.controller.ElevatorController;
import edu.rich.simulator.elevator.pojo.Elevator;


public class ElevatorSimulator {
	private ApplicationContext context;
	private Logger log = Logger.getLogger(getClass());
	private ElevatorController elevatorController;
	
	public static void main(String[] args) throws IOException {
		ElevatorSimulator elevatorSystem = new ElevatorSimulator();
		elevatorSystem.init();
		elevatorSystem.run();
	}
	
	public void init() {
		context = new ClassPathXmlApplicationContext("elevator-beans.xml");
		elevatorController = (ElevatorController) context.getBean("elevatorController");
		
		for (Elevator elevator : elevatorController.getElevators()) {
			log.debug("Elevator Number: "+elevator.getElevatorNumber());
			log.debug("Current Floor: " +elevator.getCurrentFloor());
			log.debug("Next Floor: " + elevator.getNextFloor());
			log.debug("Previous Floor: " +elevator.getPreviousFloor());
		}
	}
	
	@SuppressWarnings("serial")
	public void run() {
		List<String> mainOptions = new ArrayList<String>();
		mainOptions.add("1 - Display location all elevators\n");
		mainOptions.add("2 - Display location of an individual elevator\n");
		mainOptions.add("3 - Display number of floors available\n");
		mainOptions.add("Please choose a number from the above list: ");
		
		displaySelection(mainOptions);
		switch (Integer.valueOf(getUserSelection())) {
		case 1:
			for (Elevator elevator : elevatorController.getElevators()) {
				System.out.print("Elevator "+elevator.getElevatorNumber());
				System.out.print(" is currently located on floor "+elevator.getCurrentFloor());
				System.out.println();
			}
			break;
		case 2:
			displaySelection(new ArrayList<String>() {
				@Override
				public boolean add(String e) {
					// TODO Auto-generated method stub
					return super.add("Please enter number of elevator: ");
				}
			});
			elevatorController.getElevator(Integer.valueOf(getUserSelection())).getCurrentFloor();
			break;
		}
		displaySelection(mainOptions);
		System.out.println(getUserSelection());
	}
	
	public void displaySelection(List<String> options) {
		/*
		 * Present user with options
		 */
		for (String option : options) {
			System.out.print(option);
		}
	}
	
	public String getUserSelection() {
		/*
		 * Obtain a reader and read user input
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			return br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
