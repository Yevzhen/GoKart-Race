# GoKart-Race-Simulation
Java-program that simulates electric karts race using multithreading

**Note** <br>
If you are running the program on your machine, choose which variant you want to run - either TestKart or MainRace - as both of these classes have a main method. <br>
You might want to either delete or comment out one of these classes. <br>

**Simulation Description**

A friend of mine runs a 1000 meter indoor go-kart track that has recently transitioned to an all electric fleet. There are 60 go-karts but only 20 charging stations. Ordinarily this is not a problem because of an efficient charging schedule employed throughout the day. However, the friend plans to run an all-day marathon race in the future and needs my help to simulate a scenario in which as many go-karts are racing on the track as possible, with frequent pit-stops for recharging. <br>

There are three specs of go-kart: <br>

• **Pro** – which have a top speed between 60 and 75km/h 

• **Intermediate** – which have a top speed between 50 and 60km/h 

• **Beginner** – which have a top speed between 30 and 40km/h 

The class called **GoKart** runs its own thread (by extending Thread or implementing the Runnable interface) and oversee its own simulated movement. This is done by increasing the distanceTravelled by the kart’s speed in metres per second every 1 millisecond (to speed up the simulation). The lap count increments after every 1000 metres, and the thread finishes execution when the lap target is reached.  

A **ChargeStation** class has member variables for name and isAvailable, with appropriate constructor and public interface methods. 

Each go-kart reduces speed by half if its battery level falls below 20%. It then stops at the beginning of the next lap and attempts to re-charge. It checks the isAvailable variable of a ChargeStation and if it’s true, sets it to false while it charges. It then sets it to true again after the charging time has elapsed. If there are no available charging stations, the kart rejoin the race and continue to half its speed for each subsequent lap that it remains on the track until a charging station becomes available or the kart’s speed falls below 5km/h. At this point it retires from the race. 

After each change in distance travelled, the battery percentage level reduces accordingly. A suitable battery reduction per lap, and a suitable period of time for the kart to wait while charging are determined from the information below: 

• Pro karts need to recharge every 5km and take the equivalent of 3 laps to re-charge to 80%. 

• Intermediate karts need to recharge every 8km and take the equivalent of 2 laps to re-charge to 80%. 

• Beginner karts need to recharge every 10km and take the equivalent of 1 lap to recharge to 80%. 

*Note:* Karts re-join the race once they reach 80% charge. 

Print messages in the GoKart class outputs to the terminal log whenever a kart needs to recharge, reduces speed, begins charging, ends charging, fails to find a free charger, or retires from the race due to slow speed.  

Access to the ChargeStation objects is restricted to ensure concurrency between threads. Only one thread is able to access and change the isAvailable variable at a time. 

**TestKart** class runs a simulation of 12 go-karts (four of each spec) given a 100-lap target, and one ChargeStation object. 

**MainRace** class runs a simulation of 60 go-karts and 20 charging stations for 200 laps. 
