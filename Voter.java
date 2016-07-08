
/**
 * @author   Roger Ferguson
 */
public class Voter {
	//total time 
	protected int tickTime;

	//the value of tick when the person enters a booth
	protected int boothInTime;
	
	// max time person stays in line
	protected double boothTime;
	
	//time to check in
	protected double checkInTime;
	
	//time untill voter leaves
	protected int leaveTime;
	
	//Time at Check in
	protected int timeAtCheckin;
	
	public int getTimeAtCheckin() {
		return timeAtCheckin;
	}

	public void setTimeAtCheckin(int timeAtCheckin) {
		this.timeAtCheckin = timeAtCheckin;
	}

	public double getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(int leaveTime) {
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