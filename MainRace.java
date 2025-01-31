/* Question 4, part (g) 
 * Test class that runs a simulation of all 60 go-karts and 20 charging stations for 200 laps. 
 */ 

import java.util.ArrayList;

public class MainRace {

	public static void main(String[] args) {
		
		int numOfChargers = 20;
		int numOfLaps = 200;
		ProKart [] pro = new ProKart [20];
		IntermediateKart [] intermediate = new IntermediateKart [20];
		BeginnerKart [] beginner = new BeginnerKart [20];
		ArrayList <ChargeStation> chargers = new ArrayList <ChargeStation>();
		
		// create 20 charge stations
		for (int i = 0; i < numOfChargers; i++ ) {
			chargers.add(new ChargeStation("Station-"+(i+1)));
		}
		
		// create and start 20 pro karts
		for (int i = 0; i < pro.length; i++ ) {
			pro[i] = new ProKart("Pro-"+(i+1), numOfLaps, chargers);
			new Thread(pro[i]).start();
		}
		
		// create and start 20 intermediate karts
		for (int i = 0; i < intermediate.length; i++ ) {
			intermediate[i] = new IntermediateKart("Intermediate-"+(i+1), numOfLaps, chargers);
			new Thread(intermediate[i]).start();
		}
		
		// create and start 20 beginner karts
		for (int i = 0; i < beginner.length; i++ ) {
			beginner[i] = new BeginnerKart("Beginner-"+(i+1), numOfLaps, chargers);
			new Thread(beginner[i]).start();
		}
	}
} // end MainRace 
