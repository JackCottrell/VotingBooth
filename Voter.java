package VoterSim;
/**
 * @author   Roger Ferguson
 */
public class Voter {
	private int tickTime;
	private Booth Destination;
	
	// max time person stays in line
	protected double boothTime;
	protected double checkInTime;
	
	public double getBoothTime() {
		return boothTime;
	}
	
	public double getCheckInTime() {
		return checkInTime;
	}
	
	public Booth getDestination() {
		return Destination;
	}

	public void setDestination(Booth destination) {
		Destination = destination;
	}
	
	public int getTickTime() {
		return tickTime;
	}

	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}

	public void setBoothTime(double boothTime) {
		this.boothTime = boothTime;
	}
	
	public void setCheckInTime(double checkInTime) {
		this.checkInTime = checkInTime;
	}
}