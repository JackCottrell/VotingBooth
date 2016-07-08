public class SimStatus {
	private int throughPut = 0;
	private int leaveTime = 0;
	private int deserters = 0;
	private int totalTime = 0;
	private int regVoters = 0;
	private int specVoters = 0;
	private int limVoters = 0;
	private int timeatCheckInReg = 0;
	private int numPeopleCheckInReg = 0;
	private int timeatCheckInSpec = 0;
	private int numPeopleCheckedInSpec = 0;
	private int timeatCheckInLim = 0;
	private int numPeopleCheckedInLim = 0;
	private int numBooths;
	private int mainQSize;
	
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
}
