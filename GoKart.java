/* OOP Exam Assignment 
 * Question 4: Go-Kart Race  
 * Author: Yevheniia Tychynska 
 * Student ID: 23105729 
 * Last update: 11 May 2024  
 */

import java.util.ArrayList;

public abstract class GoKart implements Runnable {

	private String name;
	private double speed; // in meters per second
	private double initialSpeed;
	private double distanceTravelled; // in meters
	private int lapCount;
	private int lapTarget;
	private boolean newLap;
	private double batteryPercentage;
	private int chargingTime;
	private ArrayList<ChargeStation> chargeStations;

	//constructor 
	public GoKart(String name, int lapTarget, ArrayList<ChargeStation> chargeStations) {
		this.name = name;
		this.speed = 0.0;
		this.initialSpeed = 0.0;
		this.chargingTime = 0;
		this.lapTarget = lapTarget;
		this.lapCount = 0;
		this.distanceTravelled = 0.0;
		this.batteryPercentage = 100.0;
		this.newLap = true;
		this.chargeStations = chargeStations;
	}

	// getters 
	public String getName() {
		return name;
	}
	
	public int getLapCount() {
		return lapCount;
	}
	
	public int getLapTarget() {
		return lapTarget;
	}
	
	public double getSpeed() {
		return speed;
	}

	public double getInitialSpeed() {
		return initialSpeed;
	}

	public int getChargingTime() {
		return chargingTime;
	}

	public double getDistanceTravelled() {
		return distanceTravelled;
	}

	public double getBatteryPercentage() {
		return batteryPercentage;
	}

	// setters 
	public void setBatteryPercentage(double batteryPercentage) {
		this.batteryPercentage = batteryPercentage;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setInitialSpeed(double initialSpeed) {
		this.initialSpeed = initialSpeed;
	}

	public void setChargingTime(int chargingTime) {
		this.chargingTime = chargingTime;
	}

	public void setLapCount(int lapCount) {
		this.lapCount = lapCount;
	}

	@Override
	public void run() {
		System.out.printf("%s starts the race with speed %.2f m/s%n", getName(), getSpeed());
		
		// run thread till target achieved 
		while (getLapCount() < getLapTarget()) {
			try {
				Thread.sleep(1); // increase distance every 1 millisecond
			}
			catch (InterruptedException ie) {
				ie.printStackTrace();
			}

			// cut speed if battery < 20% but only once per lap 
			if (getBatteryPercentage() < 20.0 && newLap) {
				adjustSpeed();
				newLap = false;
			}
			
			// retire if speed falls below 5 kmh or battery is dead 
			if (getSpeed() < 1.4 || getBatteryPercentage() <= 1.0) {
				System.out.printf(
						"%s retires from the race due to either low speed: %.2f m/s or low battery: %.2f%% %n",
						getName(), getSpeed(), getBatteryPercentage());
				setLapCount(lapTarget); // kill Thread gently :-)
			}

			// increase distance, decrease battery 
			distanceTravelled += speed;
			reduceBattery();
			if (distanceTravelled >= 1000) {
				lapCount++;
				distanceTravelled -= 1000; // start new lap
				newLap = true;
				System.out.printf("%s completed lap %d. Current speed: %.2f m/s. Battery: %.2f%%%n",
						getName(), getLapCount(), getSpeed(), getBatteryPercentage());

				// check battery at the beginning of new lap; no need to charge if lapTarget achieved 
				if (getBatteryPercentage() < 20.0 && lapCount < lapTarget) {
					System.out.printf("%s attempts to re-charge as it's battery is %.2f%%%n",
							getName(), getBatteryPercentage());
					attemptCharge();
				}
			}
		}
		System.out.printf("%s finished the race.%n", getName());
	} // end run

	// cut speed when needed 
	public void adjustSpeed() {
		setSpeed(getSpeed() / 2);
		System.out.printf("%s reduced speed to %.2f m/s due to low battery: %.2f%%.%n",
				getName(), getSpeed(), getBatteryPercentage());
	}

	// try to charge 
	public synchronized void attemptCharge() {
		int index = findStation();
		if (index >= 0) {
			// make station unavailable for other karts 
			chargeStations.get(index).reserve();
			System.out.printf("%s is at charging station %s.%n", getName(), chargeStations.get(index).getName());
			try {
				Thread.sleep(getChargingTime()); // sleep for the specified for the type of kart time
			}
			catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			setBatteryPercentage(80.0); // set battery to 80%
			setSpeed(getInitialSpeed()); // set speed to initial as kart is charged
			chargeStations.get(index).release(); // make station available for other karts
			System.out.printf("%s resumes racing. Charge station %s is released.%n", getName(),
					chargeStations.get(index).getName());
		}
		else {
			System.out.printf("No available stations at the moment for %s.%n", getName());
		}
	} // end attemptCharge

	// search available station in the array list of stations.  
	// Return index of the first available station. If no one is available, return -1 
	public synchronized int findStation() {
		int index = -1;
		for (int i = 0; i < chargeStations.size(); i++) {
			if (chargeStations.get(i).isAvailable()) {
				index = i;
				break;
			}
		}
		return index;
	} // end findStation

// abstract method, Specified for each type of karts 
	public abstract void reduceBattery();
} // end GoKart
