/**********************************************************************
A voter tha moves through the simulation by checking in, waiting in 
queues, and voting
@author Jack Cottrell, Robert Molenhouse, Colin Lewis
@version Summer 2016
 **********************************************************************/
public abstract class Voter {
	/** Total time */ 
	protected int tickTime;

	/** The value of tick when the person enters a booth */
	protected int boothInTime;
	
	/** Max time person stays in line */
	protected double boothTime;
	
	/** Time to check in */
	protected double checkInTime;
	
	/** Time until voter leaves */
	protected int leaveTime;
	
	/** Time at Check in */
	protected int timeAtCheckin;
	
	/*******************************************************************
	Returns the time that the voter checked in
	
	@return timeAtCheckin time when voter checked in
	*******************************************************************/
	public int getTimeAtCheckin() {
		return timeAtCheckin;
	}

	
	/*******************************************************************
	Sets the time when the voter checked in
	
	@return timeAtCheckin time the user checked in
	*******************************************************************/
	public void setTimeAtCheckin(int timeAtCheckin) {
		this.timeAtCheckin = timeAtCheckin;
	}

	
	/*******************************************************************
	Returns the time the voter will wait before leaving
	
	@return leaveTime time the voter will wait before leaving
	*******************************************************************/
	public double getLeaveTime() {
		return leaveTime;
	}

	
	/*******************************************************************
	Sets the time the voter will wait before leaving
	
	@param leaveTime time voter will wait before leaving
	*******************************************************************/
	public void setLeaveTime(int leaveTime) {
		this.leaveTime = leaveTime;
	}

	
	/*******************************************************************
	Returns the time the voter takes to vote
	
	@return boothTime time it takes the voter to vote
	*******************************************************************/
	public double getBoothTime() {
		return boothTime;
	}
	
	
	/*******************************************************************
	Returns the time it takes the voter to check in
	
	@return checkInTime time it takes the user to check in
	*******************************************************************/
	public double getCheckInTime() {
		return checkInTime;
	}
	
	
	/*******************************************************************
	Returns the total time the voter have been in the simulation
	
	@return tickTime time voter has been in the simulation
	*******************************************************************/
	public int getTickTime() {
		return tickTime;
	}

	
	/*******************************************************************
	Sets the time the voter has been in the simulation
	
	@param tickTime time voter has been in the simulation
	*******************************************************************/
	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}
	
	/*******************************************************************
	Sets the time it takes the voter to vote
	
	@param boothTime time it takes for voter to vote 
	*******************************************************************/
	public void setBoothTime(double boothTime) {
		this.boothTime = boothTime;
	}
	
	/*******************************************************************
	Sets the time it takes for the voter to check in
	
	@param checkInTime time it takes for the voter to check in
	*******************************************************************/
	public void setCheckInTime(double checkInTime) {
		this.checkInTime = checkInTime;
	}

	/*******************************************************************
	Returns the time that the voter entered a voting booth
	
	@return boothInTime time the voter enterd a voting booth
	*******************************************************************/
	public int getBoothInTime() {
		return boothInTime;
	}

	/*******************************************************************
	Sets the time the voter entered a voting booth
	
	@param boothInTime time the voter entered a voting booth
	*******************************************************************/
	public void setBoothInTime(int boothInTime) {
		this.boothInTime = boothInTime;
	}
}