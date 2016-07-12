import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


/**
 * @author Roger Ferguson
 *
 */
public class Sim {
	
	private SimStatus info;
	private int currentTime = 0;
	private Clock clk;
	
	public Sim(SimStatus info){
		this.info = info;
		
	}
				
	public void runSim() {
		int secondsToPerson = info.getSecondsToPerson();
		int secondsCheckIn = info.getSecondsCheckIn();
		int totalSec = info.getTotalSec();
		int avgTimeVoting = info.getAvgTimeVoting();
		int secondsLeave = info.getSecondsLeave();
		int numBooths = info.getNumBooths();
		
		BoothQueue boothQueue = new BoothQueue(info);
		info.setBoothQueue(boothQueue);
        info.setLeaveTime(secondsLeave);

		clk = new Clock(info);
		
		CheckInBooth AL = new CheckInBooth(boothQueue, info);
		info.setAL(AL);
		CheckInBooth MZ = new CheckInBooth(boothQueue, info);
		info.setMZ(MZ);
		VoterProducer produce = new VoterProducer(AL, MZ, secondsToPerson,
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
