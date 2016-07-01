package VoterSim;

import java.util.ArrayList;

public class Booth implements ClockListener {

	private int timeOfNextEvent = 0;

	// this is the person at the booth
	private Voter person = null;  

	// number of people that have completed voting from all booths
	private static int completed = 0; 

	//adds person to the booth 
	public void add (Voter person){
		this.person = person;
	}

	//
	public void event (int tick){
		if(tick >+ person.getBoothInTime() + person.getBoothTime() + 1){
			completed++;
			person = null;
		}
	}

	public int getThroughPut() {
		return completed;
	}
	
	public boolean isOpen(){
		if(person == null){
			return false;
		}
		else
			return true;
	}
}

