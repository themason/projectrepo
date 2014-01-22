package edu.rich.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.rich.ElevatorSimulator;

public class TestUserSelection {
	List<String> options;
	ElevatorSimulator elevatorSystem;
	
	@Before
	public void initialise() {
		elevatorSystem = new ElevatorSimulator();
		options.add("Test 1");
		options.add("Test 2");
		System.setIn(new ByteArrayInputStream("Test".getBytes()));
	}

	@Test
	public void testDisplaySelection() {
		elevatorSystem.displaySelection(options);
	}
	
	@Test
	public void testGetUserSelection() {
		assertEquals("Test", elevatorSystem.getUserSelection());		
	}

}
