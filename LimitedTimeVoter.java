/**********************************************************************
Voter that has short times for voting, checking in, and leaving 

@author Jack Cottrell, Robert Molenhouse, Colin Lewis
@version Summer 2016

@extends Voter
 **********************************************************************/
public class LimitedTimeVoter extends Voter {

	/*******************************************************************
	Cuts the leave time in half from a normal voter leaveTime
	
	@param leaveTime time for voter to leave the a line
	 ******************************************************************/
	public void setLeaveTime(int leaveTime) {
		this.leaveTime = (leaveTime/2);
	}
	
	/*******************************************************************
	Cuts the voting time in half from a regular voter boothTime
	
	@param boothTime time it takes voter to vote
	 ******************************************************************/
	public void setBoothTime(double boothTime) {
		this.boothTime = (boothTime/2);
	}
	
	/*******************************************************************
	Sets the check in time the same as a regular voter
	
	@param checkInTime time for voter the check in 
	 ******************************************************************/
	public void setCheckInTime(double checkInTime) {
		this.checkInTime = checkInTime;
	}
}