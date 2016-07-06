//
///**
// * @author Roger Ferguson
// *
// */
//public class Sim {
//	
//	private Booth[] votingBooths;
//	private int secondsToPerson;
//	private int secondsCheckIn;
//	private int totalSec;
//	private int avgTimeVoting;
//	private int secondsLeave;
//	private int numBooths;
//	
//	public Sim(int secondsToPerson, int secondsCheckIn, int totalSec, int avgTimeVoting, 
//							int secondsLeave, int numBooths){
//		this.secondsToPerson = secondsToPerson;
//		this.secondsCheckIn = secondsCheckIn;
//		this.totalSec = totalSec;
//		this.avgTimeVoting = avgTimeVoting;
//		this.secondsLeave = secondsLeave;
//		this.numBooths = numBooths;
//		
//		votingBooths = new Booth[numBooths];
//				
//		for(int i = 0; i < numBooths; i++){
//			Booth booth = new Booth();
//			votingBooths[i] = booth;	
//		}	
//	}
//				
//	public void runSim() {
//
//		Clock clk = new Clock();
//		BoothQueue boothQueue = new BoothQueue(getBooths());
//		CheckInBooth AL = new CheckInBooth(boothQueue);
//		CheckInBooth MZ = new CheckInBooth(boothQueue);
//		VoterProducer produce = new VoterProducer(AL, MZ, secondsToPerson, avgTimeVoting, secondsCheckIn);
//		
//		clk.add(boothQueue);
//		clk.add(AL);
//		clk.add(MZ);
//		clk.add(produce);
//		for(int i = 0; i < numBooths; i++)
//			clk.add(getBooths()[i]);
//
//		clk.run(10000);
//	}
//
//	public Booth[] getBooths() {
//		return votingBooths;
//	}
//
//
//	public void setBooths(Booth[] booths) {
//		this.votingBooths = booths;
//	}
//
//
//	public int getSecondsToPerson() {
//		return secondsToPerson;
//	}
//
//
//	public void setSecondsToPerson(int secondsToPerson) {
//		this.secondsToPerson = secondsToPerson;
//	}
//
//
//	public double getSecondsCheckIn() {
//		return secondsCheckIn;
//	}
//
//
//	public void setSecondsCheckIn(int secondsCheckIn) {
//		this.secondsCheckIn = secondsCheckIn;
//	}
//
//
//	public double getTotalSec() {
//		return totalSec;
//	}
//
//
//	public void setTotalSec(int totalSec) {
//		this.totalSec = totalSec;
//	}
//
//
//	public double getAvgTimeVoting() {
//		return avgTimeVoting;
//	}
//
//
//	public void setAvgTimeVoting(int avgTimeVoting) {
//		this.avgTimeVoting = avgTimeVoting;
//	}
//
//
//	public int getSecondsLeave() {
//		return secondsLeave;
//	}
//
//
//	public void setSecondsLeave(int secondsLeave) {
//		this.secondsLeave = secondsLeave;
//	}
//
//
//	public int getNumBooths() {
//		return numBooths;
//	}
//
//
//	public void setNumBooths(int numBooths) {
//		this.numBooths = numBooths;
//	}
//
//
//	
//}