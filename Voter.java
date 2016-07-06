package VoterSim;
/**
 * @author   Roger Ferguson
 */
public class Voter {
	//total time 
	private int tickTime;
	
	//the value of tick when the person enters a booth
	private int boothInTime;
	
	// max time person stays in line
	protected double boothTime;
	
	//time to check in
	protected double checkInTime;
	
	//time untill voter leaves
	protected double leaveTime;
	
	public double getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(double leaveTime) {
		this.leaveTime = leaveTime;
	}

	public double getBoothTime() {
		return boothTime;
	}
	
	public double getCheckInTime() {
		return checkInTime;
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

	public int getBoothInTime() {
		return boothInTime;
	}

	public void setBoothInTime(int boothInTime) {
		this.boothInTime = boothInTime;
	}
}