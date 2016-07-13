/**********************************************************************
Voter that has long super times for voting, checking in, and leaving 
@author Jack Cottrell, Robert Molenhouse, Colin Lewis
@version Summer 2016
@extends Voter
 **********************************************************************/
public class SuperSpecialNeeds extends Voter {
	
	/*******************************************************************
	Quadruples the leave time from a normal voter leaveTime
	
	@param leaveTime time for voter to leave the a line
	 ******************************************************************/
    public void setLeaveTime(int leaveTime) {
        this.leaveTime = (leaveTime * 4);
    }

	/*******************************************************************
	Multiplies the regular voter voting time by 6
	
	@param boothTime time it takes voter to vote
	 ******************************************************************/
    public void setBoothTime(double boothTime) {
        this.boothTime = (boothTime * 6);
    }

	/*******************************************************************
	Triples the time to check in from a regular voter boothTime
	
	@param checkInTime time it take a voter to check in
	 ******************************************************************/
    public void setCheckInTime(double checkInTime) {
        this.checkInTime = (checkInTime * 3);
    }
}