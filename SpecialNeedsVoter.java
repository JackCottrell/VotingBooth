public class SpecialNeedsVoter extends Voter {

	public void setLeaveTime(int leaveTime) {
		this.leaveTime = (leaveTime * 2);
	}
	
	public void setBoothTime(double boothTime) {
		this.boothTime = (boothTime * 3);
	}
	public void setCheckInTime(double checkInTime) {
		this.checkInTime = (checkInTime * 1.5);
	}
}
