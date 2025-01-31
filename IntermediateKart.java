import java.util.ArrayList;
import java.util.Random;

public class IntermediateKart extends GoKart {
	
	// top speed for intermediate karts is 50 to 60 kmh (13.9 to 16.7 mps) 
	private double minTopSpeed = 13.9;
	private double maxTopSpeed = 16.7;
	
	// constructor
	public IntermediateKart (String name, int lapTarget, ArrayList <ChargeStation> chargeStations) {
		super(name, lapTarget, chargeStations);
		super.setInitialSpeed (generateRandomSpeed ());
		super.setSpeed(getInitialSpeed());
		// Intermediate kart needs the equivalent of 2 laps to re-charge to 80%.
		super.setChargingTime ((int)Math.round(1000/getSpeed()*2));
	}
	
	// randomise speed within the given range
	public double generateRandomSpeed () {
		Random randomSpeed = new Random();
		return (minTopSpeed + (maxTopSpeed-minTopSpeed)*randomSpeed.nextDouble());
	}
	
	@Override // intermediate kart loses 80% of its battery in 8 km
	public void reduceBattery () {
		double batteryReduction = 80.0 / 8000; // reduction per meter
		setBatteryPercentage(getBatteryPercentage() - getSpeed() * batteryReduction); // reduce battery depending on distance traveled
	}
} // end IntermediateKart 
