public class ChargeStation {
	
	private boolean isAvailable;
	private String name;
	
	//constructor
	public ChargeStation (String name) {
		this.name = name;
		this.isAvailable = true;
	}
	
	//getters
	public boolean isAvailable () {
		return isAvailable;
	}
	
	public String getName() {
		return name;
	}
	
	public synchronized void reserve () {
		if (isAvailable)
			isAvailable = false;
		else 
			System.out.println ("Charge station is unavailable. Please, try again later");
	}
	
	public synchronized void release () {
		isAvailable = true;
	} 
} // end ChargeStation 
