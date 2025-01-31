import java.util.ArrayList;
import java.util.Random;

public class BeginnerKart extends GoKart {
	
	// top speed for beginner karts is 30 to 40 kmh (8.3 to 11.1 mps) 
	private double minTopSpeed = 8.3;
	private double maxTopSpeed = 11.1;
	
	// constructor 
	public BeginnerKart (String name, int lapTarget, ArrayList <ChargeStation> chargeStations) {
		super(name, lapTarget, chargeStations);
		super.setInitialSpeed (generateRandomSpeed ());
		super.setSpeed(getInitialSpeed());
		// beginner kart needs the equivalent of 1 lap to re-charge to 80%
		super.setChargingTime ((int)Math.round(1000/getSpeed()*1));
	}
	
	// randomise speed within the given range 
	public double generateRandomSpeed () {
		Random randomSpeed = new Random();
		return (minTopSpeed + (maxTopSpeed-minTopSpeed)*randomSpeed.nextDouble());
	}
	
	@Override // beginner kart loses 80% of its battery in 10 km 
	public void reduceBattery () {
		double batteryReduction = 80.0 / 10000; // reduction per meter
		setBatteryPercentage(getBatteryPercentage() - getSpeed() * batteryReduction); // reduce battery depending on distance traveled
	}
} // end BeginnerKart 
