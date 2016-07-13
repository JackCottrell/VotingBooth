import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
/**********************************************************************
Sim class organizes and instantiates everything that is in the 
simulation
@author Jack Cottrell, Robert Molenhouse, Colin Lewis
@version Summer 2016
 **********************************************************************/
public class Sim {
	
	/** Keeps track of all information on the simulation */
	private SimStatus info;
	
	/** Current time of the simulation */
	private int currentTime = 0;
	
	/** Clock that controls the time of the simulation */
	private Clock clk;
	
	/** Produces voters that are introduced into the simulation */
	private VoterProducer produce;
	
	/** Time until the next voter is created */
	int secondsToPerson;
	
	/** Average seconds for a voter to check in */
	int secondsCheckIn;
	
	/** Total time the simulation will run */
	int totalSec;
	
	/** Average time it takes a voter to vote */
	int avgTimeVoting;
	
	/** Time a voter will wait before they leave */
	int secondsLeave;
	
	/** Number of voting booths in the simulation */
	int numBooths;
	
	/*******************************************************************
	Constructor brings in info, which stors all information on the
	simulation
	
	@param info holds all information on sim
	 ******************************************************************/
	public Sim(SimStatus info){
		this.info = info;
		
	}
	
	/*******************************************************************
	Organizes all of the simulation elements and begins the simulation
	*******************************************************************/
	public void runSim() {
		
		//Get all information from SimStatus
		secondsToPerson = info.getSecondsToPerson();
		secondsCheckIn = info.getSecondsCheckIn();
		totalSec = info.getTotalSec();
		avgTimeVoting = info.getAvgTimeVoting();
		secondsLeave = info.getSecondsLeave();
		numBooths = info.getNumBooths();
		
		//create and boothQueue and give it to SimStatus
		BoothQueue boothQueue = new BoothQueue(info);
		info.setBoothQueue(boothQueue);
        info.setLeaveTime(secondsLeave);
        
        //create new clock
		clk = new Clock(info);
		
		//create check in booths
		CheckInBooth AL = new CheckInBooth(boothQueue, info);
		info.setAL(AL);
		CheckInBooth MZ = new CheckInBooth(boothQueue, info);
		info.setMZ(MZ);
		
		//create new voter producer
		produce = new VoterProducer(AL, MZ, secondsToPerson,
				avgTimeVoting, secondsCheckIn, info);

		//add elements to the clock
		clk.add(boothQueue);
		clk.add(AL);
		clk.add(MZ);
		
		//create the desired number of voting booths and add them to clk
		for (int i = 0; i < numBooths; i++) {
			Booth booth = new Booth(boothQueue, info);
			clk.add(booth);
		}
		
		//add voter producer to the clock
		clk.add(produce);

		// new action listener for the timer
		ActionListener action = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//check if the simulation is over
				if (currentTime <= totalSec) {
					currentTime++;
					//run the simulation for the current time
					clk.run(currentTime);
					
					//date the queue lengths
					info.setALQsize(AL.getLeft());
					info.setMZQsize(MZ.getLeft());
					
					//update visual panels
					info.getPanel().updatePanels();
				}
			}
		};
		
		//create and start new swing timer
		Timer timer = new Timer(10, action);
		timer.start();
	}
	
	
	/*******************************************************************
	Updates all of the info for the simulation from user input
	 ******************************************************************/
	public void update(){
		
		//update all variables
		secondsToPerson = info.getSecondsToPerson();
		secondsCheckIn = info.getSecondsCheckIn();
		totalSec = info.getTotalSec();
		avgTimeVoting = info.getAvgTimeVoting();
		secondsLeave = info.getSecondsLeave();
		//numBooths = info.getNumBooths();
		
		//update the voter producer
		 produce = new VoterProducer(info.getAL(), info.getMZ(), 
				 secondsToPerson,
				avgTimeVoting, secondsCheckIn, info);
		 
		 //find the voter producer to update it
		 for(int i = 0; i < clk.getMyListeners().length; i++){
			 if(clk.getMyListeners()[i] instanceof VoterProducer){
				 clk.getMyListeners()[i] = produce;
			 }
		 }
	}

	
	/*******************************************************************
	Returns the status of the simulation
	
	@return info SimStatus for the simulation
	 ******************************************************************/
	public SimStatus getInfo() {
		return info;
	}
	
	/*******************************************************************
	Adds a voting booth to the simulation
	 ******************************************************************/
	public void addBooth(){
		
		//make sure there is no more than 10 booths
		if(info.getNumBooths() < 10){
			//add a voting booth to clock
    		clk.add(new Booth(info.getBoothQueue(), info));
    		//increment the number of booths for SimStatus
    		info.incrNumBooths();
    	}
	}
	
	/*******************************************************************
	Removes a voting booth from the simulation
	 ******************************************************************/
	public void remBooth(){
		clk.remBooth();
	}
}