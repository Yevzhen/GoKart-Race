/* Question 4, part (e) 
 * Test class that runs a simulation of 12 go-karts (four of each spec)  
 * given a 100-lap target, and one ChargeStation object 
 */ 

import java.util.ArrayList;

public class TestKart {

	public static void main(String[] args) {
		
		int lapTarget = 100;
		ChargeStation station = new ChargeStation ("Station 1");
		ArrayList <ChargeStation> chargers = new ArrayList <ChargeStation>();
		chargers.add(station);
		
		// create karts
		ProKart pro1 = new ProKart("Pro1", lapTarget, chargers); 
		ProKart pro2 = new ProKart("Pro2", lapTarget, chargers);
		ProKart pro3 = new ProKart("Pro3", lapTarget, chargers); 
		ProKart pro4 = new ProKart("Pro4", lapTarget, chargers);
		IntermediateKart intermediate1 = new IntermediateKart("Intermediate1", lapTarget, chargers); 
		IntermediateKart intermediate2 = new IntermediateKart("Intermediate2", lapTarget, chargers);
		IntermediateKart intermediate3 = new IntermediateKart("Intermediate3", lapTarget, chargers); 
		IntermediateKart intermediate4 = new IntermediateKart("Intermediate4", lapTarget, chargers);
		BeginnerKart beginner1 = new BeginnerKart("Beginner1", lapTarget, chargers); 
		BeginnerKart beginner2 = new BeginnerKart("Beginner2", lapTarget, chargers);
		BeginnerKart beginner3 = new BeginnerKart("Beginner3", lapTarget, chargers); 
		BeginnerKart beginner4 = new BeginnerKart("Beginner4", lapTarget, chargers);
		
		// start karts 
		new Thread(pro1).start();
		new Thread(pro2).start();
		new Thread(pro3).start();
		new Thread(pro4).start();
		new Thread(intermediate1).start();
		new Thread(intermediate2).start();
		new Thread(intermediate3).start();
		new Thread(intermediate4).start();
		new Thread(beginner1).start();
		new Thread(beginner2).start();
		new Thread(beginner3).start();
		new Thread(beginner4).start();
	}
} // end TestKart 
