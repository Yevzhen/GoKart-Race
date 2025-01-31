import java.util.ArrayList;
import java.util.Random;

public class ProKart extends GoKart{
	
	// top speed for Pro karts is 60 to 75 kmh (16.7 to 20.8 mps)
	private double minTopSpeed = 16.7;
	private double maxTopSpeed = 20.8;
	
	// constructor
	public ProKart (String name, int lapTarget, ArrayList <ChargeStation> chargeStations) {
		super(name, lapTarget, chargeStations);
		super.setInitialSpeed (generateRandomSpeed ());
		super.setSpeed(getInitialSpeed());
		// Intermediate kart needs the equivalent of 3 laps to re-charge to 80%
		super.setChargingTime((int)Math.round(1000/getSpeed()*3));
	}
	
	// randomise speed within the given range 
	public double generateRandomSpeed () {
		Random randomSpeed = new Random();
		return (minTopSpeed + (maxTopSpeed-minTopSpeed)*randomSpeed.nextDouble());
	}
	
	@Override // Pro Kart loses 80% of its battery in 5km 
	public void reduceBattery () {
		double batteryReduction = 80.0 / 5000; // reduction per meter
		setBatteryPercentage(getBatteryPercentage() - getSpeed() * batteryReduction); // reduce battery depending on distance traveled
	}
} // end ProKart
