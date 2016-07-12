/**********************************************************************
Voter that has long times for voting, checking in, and leaving 

@author Jack Cottrell, Robert Molenhouse, Colin Lewis
@version Summer 2016

@extends Voter
 **********************************************************************/
public class SpecialNeedsVoter extends Voter {
	
	/*******************************************************************
	Doubles the leave time from a normal voter leaveTime
	
	@param leaveTime time for voter to leave the a line
	 ******************************************************************/
	public void setLeaveTime(int leaveTime) {
		this.leaveTime = (leaveTime * 2);
	}
	
	/*******************************************************************
	Triples the time to vote from a regular voter boothTime
	
	@param boothTime time it takes voter to vote
	 ******************************************************************/
	public void setBoothTime(double boothTime) {
		this.boothTime = (boothTime * 3);
	}
	
	/*******************************************************************
	Multiplies the check in time by 1.5 from a regular voter
	
	@param checkInTime time for voter the check in 
	 ******************************************************************/
	public void setCheckInTime(double checkInTime) {
		this.checkInTime = (checkInTime * 1.5);
	}
}
