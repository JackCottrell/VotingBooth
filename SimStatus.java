
public class SimStatus {
	private int throughPut = 0;
	private int leaveTime = 0;
	private int deserters = 0;
	private int totalTime = 0;
	
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
