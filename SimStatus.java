/**********************************************************************
Keeps track of all relavent data for the simulation
@author Jack Cottrell, Robert Molenhouse, Colin Lewis
@version Summer 2016
 **********************************************************************/
public class SimStatus {
	
	/** Simulation Object that runs the simulation */
	private Sim sim;
	
	/** The current time of the simulation */
	private int tick = 0;
	
	/** Number of voters that have voted */
	private int throughPut = 0;
	
	/** Time for a voter to leave the line */
	private int leaveTime = 0;
	
	/** Number of voters that have left a line */
	private int deserters = 0;
	
	/** Total time for the simulation */
	private int totalTime = 0;
	
	/** Number of regular voters that have been created */
	private int regVoters = 0;
	
	/** number of special voters that have been created */
	private int specVoters = 0;
	
	/** Number of super special voters that have been created */
	private int supSpecVoters = 0;
	
	/** Number of limited time voters that have been created */
	private int limVoters = 0;
	
	/** Time stamp when a regular voter checks in */
	private int timeatCheckInReg = 0;
	
	/** Number of regular voters that have checked in */
	private int numPeopleCheckInReg = 0;
	
	/** Time stamp when a special voter checks in */
	private int timeatCheckInSpec = 0;
	
	/** Number of special voters that have checked in */
	private int numPeopleCheckedInSpec = 0;
	
	/** Time stamp when a limited time checks in */
	private int timeatCheckInLim = 0;
	
	/** Number of limited time voters that have checked in */
	private int numPeopleCheckedInLim = 0;
	
	/** Time for the next voter to be created */
	private int secondsToPerson;
	
	/** Average time for a voter to check in */
	private int secondsCheckIn;
	
	/** Average time for a voter to finish the simulation */
	private int totalSec;
	
	/** Average time for a voter to finish the voting process */
	private int avgTimeVoting;
	
	/** Time for a voter to leave the line */
	private int secondsLeave;
	
	/** Panel that controls all of the visuals of the simulation */
	private VotingBoothPanel panel;
	
	/** The queue of voters waiting to vote */
	private BoothQueue boothQueue;
	
	/** Check in Booths for voters with last names A through L */
	private CheckInBooth AL;
	
	/** Check in Booths for voters with last names M through Z */
	private CheckInBooth MZ;
	
	/** Number of voting booths */
	private int numBooths = 0;
	
	/** Number of voter in main q at any time */
	private int mainQSize = 0;
	
	/** Number of voters in check in AL q at any time */
	private int ALQsize = 0;
	
	/** Number of voters in check in MZ q at any time */
	private int MZQsize = 0;
	
	/** Number of regular voters who voted */
	private int regThrough = 0;
	
	/** Number of limeted time voters who voted */
	private int limThrough = 0;
	
	/** Number of special needs voters who voted */
	private int specThrough = 0;
	
	/** Number of super special needs voters who voted */
	private int supSpecThrough = 0;
	
	/*******************************************************************
	Returns the main panel of the simulation
	
	@return panel visual panel for simulation
	*******************************************************************/
	public VotingBoothPanel getPanel() {
		return panel;
	}
	
	/*******************************************************************
	Updates the panel of the simulation
	
	@param panel new updated panel
	*******************************************************************/
	public void setPanel(VotingBoothPanel panel) {
		this.panel = panel;
		panel.revalidate();
		panel.repaint();
	}
	
	/*******************************************************************
	Decreases the number of booths
	*******************************************************************/
	public void decrNumBooths(){
		numBooths--;
	}
	
	/*******************************************************************
	Returns the Queue of voters waiting to vote
	
	@return boothQueue queue of waiting voters
	*******************************************************************/
	public BoothQueue getBoothQueue() {
		return boothQueue;
	}
	
	/*******************************************************************
	Sets the boothQueue
	
	@param boothQueue new queue of waiting voters
	*******************************************************************/
	public void setBoothQueue(BoothQueue boothQueue) {
		this.boothQueue = boothQueue;
	}
	
	/*******************************************************************
	Returns the queue for A through L check in
	
	@return AL queue of voters for check in
	*******************************************************************/
	public CheckInBooth getAL() {
		return AL;
	}
	
	/*******************************************************************
	Updates the queue for check in A through L
	
	@param aL updated AL queue
	*******************************************************************/
	public void setAL(CheckInBooth aL) {
		AL = aL;
	}
	
	/*******************************************************************
	Returns the queue for M through Z check in
	
	@return MZ queue of voters for check in
	*******************************************************************/
	public CheckInBooth getMZ() {
		return MZ;
	}
	
	/*******************************************************************
	Updates the queue for check in M through Z
	
	@param mZ updated MZ queue
	*******************************************************************/
	public void setMZ(CheckInBooth mZ) {
		MZ = mZ;
	}
	
	/*******************************************************************
	Returns the simulation setup  
	
	@return sim organization of the simulation
	*******************************************************************/
	public Sim getSim() {
		return sim;
	}
	
	/*******************************************************************
	Sets the simulation setup
	
	@param sim organization of the simulation
	*******************************************************************/
	public void setSim(Sim sim) {
		this.sim = sim;
	}
	
	/*******************************************************************
	Returns the number of regular voters that voted
	
	@return regThrough number of regular voters that voted
	*******************************************************************/
	public int getRegThrough() {
		return regThrough;
	}
	
	/*******************************************************************
	Increments the number of regular voters that voted
	*******************************************************************/
	public void incRegThrough() {
		regThrough++;
	}
	
	/*******************************************************************
	Returns the number of limited time voters that voted
	
	@return limThrough number of limited time voters that voted
	*******************************************************************/
	public int getLimThrough() {
		return limThrough;
	}
	
	/*******************************************************************
	Increments the number of limited time voters that voted
	*******************************************************************/
	public void incLimThrough() {
		limThrough++;
	}
	
	/*******************************************************************
	Returns the number of special needs voters that voted
	
	@return specThrough number of special needs voters that voted
	*******************************************************************/
	public int getSpecThrough() {
		return specThrough;
	}
	
	/*******************************************************************
	Increments the number of special needs voters that voted
	*******************************************************************/
	public void incSpecThrough() {
		specThrough++;
	}
	
	/*******************************************************************
	Returns the number of super special needs voters that voted
	
	@return supSpecThrough number of super special needs voters that
			voted
	*******************************************************************/
	public int getSupSpecThrough() {
		return supSpecThrough;
	}
	
	/*******************************************************************
	Increments the number of super special needs voters that voted
	*******************************************************************/
	public void incSupSpecThrough() {
		supSpecThrough++;
	}
	
	/*******************************************************************
	Returns the number of super special needs voters that were created
	
	@return cupSpecVoters number of super special needs voters created
	*******************************************************************/
	public int getSupSpecVoters() {
		return supSpecVoters;
	}
	
	/*******************************************************************
	Increments the number of super special needs voters created
	*******************************************************************/
	public void incSupSpecVoters() {
		supSpecVoters++;
	}
	
	/*******************************************************************
	Returns the current time
	
	@return tick current time
	*******************************************************************/
	public int getTick() {
		return tick;
	}
	
	/*******************************************************************
	Updates the current time
	
	@param tick new time
	*******************************************************************/
	public void setTick(int tick) {
		this.tick = tick;
	}
	
	/*******************************************************************
	Returns the size of the queue of A through L check in
	
	@return ALQsize size of AL check in queue
	*******************************************************************/
	public int getALQsize() {
		return ALQsize;
	}
	
	/*******************************************************************
	Updates the size of the queue of A through L check in
	
	@param aLQsize new queue size for A through L check in
	*******************************************************************/
	public void setALQsize(int aLQsize) {
		ALQsize = aLQsize;
	}
	
	/*******************************************************************
	Returns the queue size for M through Z check in
	
	@return MZQsize size of MZ check in queue
	*******************************************************************/
	public int getMZQsize() {
		return MZQsize;
	}
	
	/*******************************************************************
	Updates the queue size for M through Z check in
	
	@param mZQsize new size of MZ check in queue
	*******************************************************************/
	public void setMZQsize(int mZQsize) {
		MZQsize = mZQsize;
	}
	
	/*******************************************************************
	Returns size of the main queue
	
	@return mainQsize size of the main queue
	*******************************************************************/
	public int getMainQSize() {
		return mainQSize;
	}
	
	/*******************************************************************
	Updates the main queue size
	
	@param mainQSize new size for main queue
	*******************************************************************/
	public void setMainQSize(int mainQSize) {
		this.mainQSize = mainQSize;
	}
	
	/*******************************************************************
	Returns the number of booths
	
	@return numBooths number of booths in simulation
	*******************************************************************/
	public int getNumBooths() {
		return numBooths;
	}
	
	/*******************************************************************
	Updates the number of booths in the simulation
	
	@param numBooths new number of booths in the simulation
	*******************************************************************/
	public void setNumBooths(int numBooths) {
		this.numBooths = numBooths;
	}
	
	/*******************************************************************
	Increments the number of booths in the simulation
	*******************************************************************/
	public void incrNumBooths(){
		numBooths++;
	}
	
	/*******************************************************************
	Returns the number of regular voters that have been created
	
	@return regVoters number of regular voters created
	*******************************************************************/
	public int getRegVoters() {
		return regVoters;
	}
	
	/*******************************************************************
	Increments the number of regular voters that have been created
	*******************************************************************/
	public void incRegVoters() {
		regVoters ++;
	}
	
	/*******************************************************************
	Returns the number of special needs voters created
	
	@return specVoters number of special needs voters created
	*******************************************************************/
	public int getSpecVoters() {
		return specVoters;
	}
	
	/*******************************************************************
	increments the number of special needs voters created
	*******************************************************************/
	public void incSpecVoters() {
		specVoters ++;
	}
	
	/*******************************************************************
	Returns the number of limited time voters created
	
	@return limVoters number of limited time voters created
	*******************************************************************/
	public int getLimVoters() {
		return limVoters;
	}
	
	/*******************************************************************
	Increments the number of limited time voters created
	*******************************************************************/
	public void incLimVoters() {
		limVoters ++;
	}
	
	/*******************************************************************
	Updates the total amount of voters that have voted
	
	@param throughPut number of voters that have voted
	*******************************************************************/
	public void setThroughPut(int throughPut) {
		this.throughPut = throughPut;
	}
	
	/*******************************************************************
	Returns the time of check in for a regular voter
	
	@return timeatCheckInReg time at check in of regular voter
	*******************************************************************/
	public int getTimeAtCheckInReg() {
		return timeatCheckInReg;
	}
	
	/*******************************************************************
	Returns the number of regular voters that have checked in
	
	@return numPeopleCheckInReg number of regular voters that have 
			check in
	*******************************************************************/
	public int getNumPeopleCheckInReg() {
		return numPeopleCheckInReg;
	}
	
	/*******************************************************************
	Increments the number of regular voters that have checked in
	*******************************************************************/
	public void incNumPeopleCheckInReg() {
		numPeopleCheckInReg ++;
	}
	
	/*******************************************************************
	Updates the total time at check in for regular voters
	
	@param timeatCheckInReg time at regular voter check in
	*******************************************************************/
	public void setTimeAtCheckInReg(int timeatCheckInReg) {
		this.timeatCheckInReg += timeatCheckInReg;
	}
	
	/*******************************************************************
	Updates the total time at check in for special needs voters
	
	@param timeatCheckInSpec time at special needs voter check in
	*******************************************************************/
	public void setTimeAtCheckInSpec(int timeatCheckInSpec) {
		this.timeatCheckInSpec += timeatCheckInSpec;
	}
	
	/*******************************************************************
	Returns the time at check in for special needs voters
	
	@return timeatCheckInSpec time at check in for special needs voters
	*******************************************************************/
	public int getTimeAtCheckInSpec() {
		return timeatCheckInSpec;
	}
	
	/*******************************************************************
	Returns the number of special needs voters that have checked in 
	
	@return numPeepleCheckInSpec number of special needs voters that
			have checked in
	*******************************************************************/
	public int getNumPeopleCheckedInSpec() {
		return numPeopleCheckedInSpec;
	}
	
	/*******************************************************************
	Increments the number of special needs voters that have checked in
	*******************************************************************/
	public void incNumPeopleCheckedInSpec() {
		numPeopleCheckedInSpec ++;
	}
	
	/*******************************************************************
	Updates the total check in time for limited time voters
	
	@param timeatCheckInLim time at check in for limited time voter
	*******************************************************************/
	public void setTimeAtCheckInLim(int timeatCheckInLim) {
		this.timeatCheckInLim += timeatCheckInLim;
	}
	
	/*******************************************************************
	Returns the number of limited time voters that have checked in
	
	@return numPeopleCheckedInLim number of limited time voters that
			have checked in
	*******************************************************************/
	public int getNumPeopleCheckedInLim() {
		return numPeopleCheckedInLim;
	}
	
	/*******************************************************************
	Returns the check in time for limited time voters
	
	@return timeatCheckInLim time at check in for limited time voter
	*******************************************************************/
	public int getTimeatCheckInLim() {
		return timeatCheckInLim;
	}
	
	/*******************************************************************
	Increment the number of limited time voters that have checked in
	*******************************************************************/ 
	public void incNumPeopleCheckedInLim() {
		numPeopleCheckedInLim ++;
	}
	
	/*******************************************************************
	Returns the total simulation time
	
	@return totalTime time the simulation have been running
	*******************************************************************/
	public int getTotalTime() {
		return totalTime;
	}
	
	/*******************************************************************
	Updates the total time of the simulation
	
	@param totalTime time to be added to the total simulation time
	*******************************************************************/
	public void setTotalTime(int totalTime) {
		this.totalTime += totalTime;
	}
	
	/*******************************************************************
	Returns the amount of voters that have voted 
	
	@return throughPut number of voters that have voted
	*******************************************************************/
	public int getThroughPut() {
		return throughPut;
	}
	
	/*******************************************************************
	Increments the number of voters that have voted
	*******************************************************************/
	public void incThroughPut() {
		throughPut ++;
	}
	
	/*******************************************************************
	Returns the time for a voter to leave a queue
	
	@return leaveTime time until a voter leaves a queue
	*******************************************************************/
	public int getLeaveTime() {
		return leaveTime;
	}
	
	/*******************************************************************
	Updates the leave time of the voters
	
	@param leaveTime new leave time for the voters
	*******************************************************************/
	public void setLeaveTime(int leaveTime) {
		this.leaveTime = leaveTime;
	}
	
	/*******************************************************************
	Returns the number of voters that have left a queue
	
	@return deserters number of voters that have left a queue
	*******************************************************************/
	public int getDeserters() {
		return deserters;
	}
	
	/*******************************************************************
	Increments the number of voters that have left a queue
	*******************************************************************/
	public void incDeserters() {
		deserters ++;
	}
	
	/*******************************************************************
	Returns the time until another voter is produced
	
	@return secondsToPerson time until another voter is produced
	*******************************************************************/
	public int getSecondsToPerson() {
		return secondsToPerson;
	}
	
	/*******************************************************************
	Updates the time until the next voter is produced
	
	@param secondsToPerson new time until next voter is produced
	*******************************************************************/
	public void setSecondsToPerson(int secondsToPerson) {
		this.secondsToPerson = secondsToPerson;
	}
	
	/*******************************************************************
	Returns the time it takes for a voter to check in
	
	@return secondsCheckIn time for voter to check in
	*******************************************************************/
	public int getSecondsCheckIn() {
		return secondsCheckIn;
	}
	
	/*******************************************************************
	Updates the time it takes for a voter to check in
	
	@param secondsCheckIn new time for voter to check in
	*******************************************************************/
	public void setSecondsCheckIn(int secondsCheckIn) {
		this.secondsCheckIn = secondsCheckIn;
	}
	
	/*******************************************************************
	Returns the total time of the simulation
	
	@return totalSec total time of the simulation
	*******************************************************************/
	public int getTotalSec() {
		return totalSec;
	}
	
	/*******************************************************************
	Update the total time of the simulation
	
	@param totalSec new total time of the simulation 
	*******************************************************************/
	public void setTotalSec(int totalSec) {
		this.totalSec = totalSec;
	}
	
	/*******************************************************************
	returns the average time it takes a voter to vote
	
	@return avgTimeVoting average time for a voter to vote
	*******************************************************************/
	public int getAvgTimeVoting() {
		return avgTimeVoting;
	}
	
	/*******************************************************************
	Updates the average time it takes for a voter to vote
	
	@param avgTimeVoting new average time for a voter to vote
	*******************************************************************/
	public void setAvgTimeVoting(int avgTimeVoting) {
		this.avgTimeVoting = avgTimeVoting;
	}
	
	/*******************************************************************
	returns the time until a voter leave a queue
	
	@return secondsLeave time until a voter leaves a queue
	*******************************************************************/
	public int getSecondsLeave() {
		return secondsLeave;
	}
	
	/*******************************************************************
	Updates the time until a voter leaves a queue
	
	@param secondsLeave new time until a voter leaves a line
	*******************************************************************/
	public void setSecondsLeave(int secondsLeave) {
		this.secondsLeave = secondsLeave;
	}
}