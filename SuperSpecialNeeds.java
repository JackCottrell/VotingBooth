/**
 * Created by Colin on 7/10/2016.
 */

public class SuperSpecialNeeds extends  SpecialNeedsVoter {
    public void setLeaveTime(int leaveTime) {
        this.leaveTime = (leaveTime * 4);
    }

    public void setBoothTime(double boothTime) {
        this.boothTime = (boothTime * 6);
    }

    public void setCheckInTime(double checkInTime) {
        this.checkInTime = (checkInTime * 3);
    }
}