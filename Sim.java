
/**
 * @author Roger Ferguson
 *
 */
public class Sim {
	
	private Booth[] votingBooths;
	private int secondsToPerson;
	private double secondsCheckIn;
	private double totalSec;
	private double avgTimeVoting;
	private int secondsLeave;
	private int numBooths;
	
	public Sim(int secondsToPerson, double secondsCheckIn, double totalSec, double avgTimeVoting, 
							int secondsLeave, int numBooths){
		this.secondsToPerson = secondsToPerson;
		this.secondsCheckIn = secondsCheckIn;
		this.totalSec = totalSec;
		this.avgTimeVoting = avgTimeVoting;
		this.secondsLeave = secondsLeave;
		this.numBooths = numBooths;
		
		votingBooths = new Booth[numBooths];
				
		for(int i = 0; i < numBooths; i++){
			Booth booth = new Booth();
			votingBooths[i] = booth;	
		}	
	}
				
	public void runSim() {

		Clock clk = new Clock();
		CheckInBooth AL = new CheckInBooth()
		VoterProducer produce = new VoterProducer(booth, 20, 18);
		clk.add(produce);
		for(int i = 0; i < numBooths; i++)
			clk.add(getBooths()[i]);

		clk.run(10000);

		System.out.println("Through put is: " + booth.getThroughPut() +
															" people.");
		
		System.out.println("People that are still in the Q:" +
										  booth.getLeft() + " people.");
		
		System.out.println("Max Q length:" + booth.getMaxQlength() + 
				                                            " people.");
	}

	public Booth[] getBooths() {
		return votingBooths;
	}


	public void setBooths(Booth[] booths) {
		this.votingBooths = booths;
	}


	public int getSecondsToPerson() {
		return secondsToPerson;
	}


	public void setSecondsToPerson(int secondsToPerson) {
		this.secondsToPerson = secondsToPerson;
	}


	public double getSecondsCheckIn() {
		return secondsCheckIn;
	}


	public void setSecondsCheckIn(double secondsCheckIn) {
		this.secondsCheckIn = secondsCheckIn;
	}


	public double getTotalSec() {
		return totalSec;
	}


	public void setTotalSec(double totalSec) {
		this.totalSec = totalSec;
	}


	public double getAvgTimeVoting() {
		return avgTimeVoting;
	}


	public void setAvgTimeVoting(double avgTimeVoting) {
		this.avgTimeVoting = avgTimeVoting;
	}


	public int getSecondsLeave() {
		return secondsLeave;
	}


	public void setSecondsLeave(int secondsLeave) {
		this.secondsLeave = secondsLeave;
	}


	public int getNumBooths() {
		return numBooths;
	}


	public void setNumBooths(int numBooths) {
		this.numBooths = numBooths;
	}


	
}