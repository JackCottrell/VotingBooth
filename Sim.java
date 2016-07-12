import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Sim {
	
	private SimStatus info;
	private int currentTime = 0;
	private Clock clk;
	private VoterProducer produce;
	int secondsToPerson;
	int secondsCheckIn;
	int totalSec;
	int avgTimeVoting;
	int secondsLeave;
	int numBooths;
	
	
	public Sim(SimStatus info){
		this.info = info;
		
	}
				
	public void runSim() {
		secondsToPerson = info.getSecondsToPerson();
		secondsCheckIn = info.getSecondsCheckIn();
		totalSec = info.getTotalSec();
		avgTimeVoting = info.getAvgTimeVoting();
		secondsLeave = info.getSecondsLeave();
		numBooths = info.getNumBooths();
		
		BoothQueue boothQueue = new BoothQueue(info);
		info.setBoothQueue(boothQueue);
        info.setLeaveTime(secondsLeave);

		clk = new Clock(info);
		
		CheckInBooth AL = new CheckInBooth(boothQueue, info);
		info.setAL(AL);
		CheckInBooth MZ = new CheckInBooth(boothQueue, info);
		info.setMZ(MZ);
		produce = new VoterProducer(AL, MZ, secondsToPerson,
				avgTimeVoting, secondsCheckIn, info);

		clk.add(boothQueue);
		clk.add(AL);
		clk.add(MZ);
		for (int i = 0; i < numBooths; i++) {
			Booth booth = new Booth(boothQueue, info);
			clk.add(booth);
		}
		clk.add(produce);

		// new action listener for the timer
		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (currentTime <= totalSec) {
					currentTime++;
					clk.run(currentTime);

					info.setALQsize(AL.getLeft());
					info.setMZQsize(MZ.getLeft());
					
					//VotingBoothPanel panel = new VotingBoothPanel(info);
					info.getPanel().updatePanels();
				}
			}
		};
		Timer timer = new Timer(10, action);
		timer.start();

	}
	
	public void update(){
		secondsToPerson = info.getSecondsToPerson();
		secondsCheckIn = info.getSecondsCheckIn();
		totalSec = info.getTotalSec();
		avgTimeVoting = info.getAvgTimeVoting();
		secondsLeave = info.getSecondsLeave();
		numBooths = info.getNumBooths();
		
		 produce = new VoterProducer(info.getAL(), info.getMZ(), secondsToPerson,
				avgTimeVoting, secondsCheckIn, info);
		 
		 for(int i = 0; i < clk.getMyListeners().length; i++){
			 if(clk.getMyListeners()[i] instanceof VoterProducer){
				 clk.getMyListeners()[i] = produce;
			 }
		 }
	}

	public SimStatus getInfo() {
		return info;
	}
	public void addBooth(){
		if(info.getNumBooths() < 10){
    		clk.add(new Booth(info.getBoothQueue(), info));
    		info.incrNumBooths();
    	}
	}
	public void remBooth(){
		clk.remBooth();
	}
}