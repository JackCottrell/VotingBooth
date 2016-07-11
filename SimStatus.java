public class SimStatus {
	private Sim sim;
	private int tick = 0;
	private int throughPut = 0;
	private int leaveTime = 0;
	private int deserters = 0;
	private int totalTime = 0;
	private int regVoters = 0;
	private int specVoters = 0;
	private int supSpecVoters = 0;
	private int limVoters = 0;
	private int timeatCheckInReg = 0;
	private int numPeopleCheckInReg = 0;
	private int timeatCheckInSpec = 0;
	private int numPeopleCheckedInSpec = 0;
	private int timeatCheckInLim = 0;
	private int numPeopleCheckedInLim = 0;
	private int secondsToPerson;
	private int secondsCheckIn;
	private int totalSec;
	private int avgTimeVoting;
	private int secondsLeave;
	private VotingBoothPanel panel;
	private BoothQueue boothQueue;
	private CheckInBooth AL;
	private CheckInBooth MZ;
	
	
	//number of voting booths
	private int numBooths = 0;
	//number of voter in main q at any time
	private int mainQSize = 0;
	//number of voters in check in AL q at any time
	private int ALQsize = 0;
	//number of voters in check in MZ q at any time
	private int MZQsize = 0;
	//number of regular voters who voted
	private int regThrough = 0;
	//number of limeted time voters who voted
	private int limThrough = 0;
	//number of special needs voters who voted
	private int specThrough = 0;
	//number of super special needs voters who voted
	private int supSpecThrough = 0;
	
	public VotingBoothPanel getPanel() {
		return panel;
	}
	public void setPanel(VotingBoothPanel panel) {
		this.panel = panel;
		panel.revalidate();
		panel.repaint();
	}
	public void decrNumBooths(){
		numBooths--;
	}
	public BoothQueue getBoothQueue() {
		return boothQueue;
	}
	public void setBoothQueue(BoothQueue boothQueue) {
		this.boothQueue = boothQueue;
	}
	public CheckInBooth getAL() {
		return AL;
	}
	public void setAL(CheckInBooth aL) {
		AL = aL;
	}
	public CheckInBooth getMZ() {
		return MZ;
	}
	public void setMZ(CheckInBooth mZ) {
		MZ = mZ;
	}
	public Sim getSim() {
		return sim;
	}
	public void setSim(Sim sim) {
		this.sim = sim;
	}
	public int getRegThrough() {
		return regThrough;
	}
	public void incRegThrough() {
		regThrough++;
	}
	public int getLimThrough() {
		return limThrough;
	}
	public void incLimThrough() {
		limThrough++;
	}
	public int getSpecThrough() {
		return specThrough;
	}
	public void incSpecThrough() {
		specThrough++;
	}
	public int getSupSpecThrough() {
		return supSpecThrough;
	}
	public void incSupSpecThrough() {
		supSpecThrough++;
	}
	public int getSupSpecVoters() {
		return supSpecVoters;
	}
	public void incSupSpecVoters() {
		supSpecVoters++;
	}
	public int getTick() {
		return tick;
	}
	public void setTick(int tick) {
		this.tick = tick;
	}
	public int getALQsize() {
		return ALQsize;
	}
	public void setALQsize(int aLQsize) {
		ALQsize = aLQsize;
	}
	public int getMZQsize() {
		return MZQsize;
	}
	public void setMZQsize(int mZQsize) {
		MZQsize = mZQsize;
	}
	public int getMainQSize() {
		return mainQSize;
	}
	public void setMainQSize(int mainQSize) {
		this.mainQSize = mainQSize;
	}
	public int getNumBooths() {
		return numBooths;
	}
	public void setNumBooths(int numBooths) {
		this.numBooths = numBooths;
	}
	public void incrNumBooths(){
		numBooths++;
	}
	public int getRegVoters() {
		return regVoters;
	}
	public void incRegVoters() {
		regVoters ++;
	}
	public int getSpecVoters() {
		return specVoters;
	}
	public void incSpecVoters() {
		specVoters ++;
	}
	public int getLimVoters() {
		return limVoters;
	}
	public void incLimVoters() {
		limVoters ++;
	}
	public void setThroughPut(int throughPut) {
		this.throughPut = throughPut;
	}
	
	public int getTimeAtCheckInReg() {
		return timeatCheckInReg;
	}
	public int getNumPeopleCheckInReg() {
		return numPeopleCheckInReg;
	}
	public void incNumPeopleCheckInReg() {
		numPeopleCheckInReg ++;
	}
	public void setTimeAtCheckInReg(int timeatCheckInReg) {
		this.timeatCheckInReg += timeatCheckInReg;
	}
	
	public void setTimeAtCheckInSpec(int timeatCheckInSpec) {
		this.timeatCheckInSpec += timeatCheckInSpec;
	}
	public int getTimeAtCheckInSpec() {
		return timeatCheckInSpec;
	}
	public int getNumPeopleCheckedInSpec() {
		return numPeopleCheckedInSpec;
	}
	public void incNumPeopleCheckedInSpec() {
		numPeopleCheckedInSpec ++;
	}
	
	public void setTimeAtCheckInLim(int timeatCheckInLim) {
		this.timeatCheckInLim += timeatCheckInLim;
	}
	public int getNumPeopleCheckedInLim() {
		return numPeopleCheckedInLim;
	}
	public int getTimeatCheckInLim() {
		return timeatCheckInLim;
	}
	public void incNumPeopleCheckedInLim() {
		numPeopleCheckedInLim ++;
	}
	
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime += totalTime;
	}
	public int getThroughPut() {
		return throughPut;
	}
	public void incThroughPut() {
		throughPut ++;
	}
	public int getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(int leaveTime) {
		this.leaveTime = leaveTime;
	}
	public int getDeserters() {
		return deserters;
	}
	public void incDeserters() {
		deserters ++;
	}
	public int getSecondsToPerson() {
		return secondsToPerson;
	}
	public void setSecondsToPerson(int secondsToPerson) {
		this.secondsToPerson = secondsToPerson;
	}
	public int getSecondsCheckIn() {
		return secondsCheckIn;
	}
	public void setSecondsCheckIn(int secondsCheckIn) {
		this.secondsCheckIn = secondsCheckIn;
	}
	public int getTotalSec() {
		return totalSec;
	}
	public void setTotalSec(int totalSec) {
		this.totalSec = totalSec;
	}
	public int getAvgTimeVoting() {
		return avgTimeVoting;
	}
	public void setAvgTimeVoting(int avgTimeVoting) {
		this.avgTimeVoting = avgTimeVoting;
	}
	public int getSecondsLeave() {
		return secondsLeave;
	}
	public void setSecondsLeave(int secondsLeave) {
		this.secondsLeave = secondsLeave;
	}
}