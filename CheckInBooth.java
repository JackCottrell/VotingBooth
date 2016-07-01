package VoterSim;

import java.util.ArrayList;
/**
 * @author   Jack Cottrell
 */
public class CheckInBooth implements ClockListener {
	
	private ArrayList<Voter> Q = new ArrayList<Voter>();
	
	private int timeOfNextEvent = 0;
	private int maxQlength = 0;
	private Voter person;   
	private Booth booth;
	
	public CheckInBooth(Booth booth){
		this.booth = booth;
	}
	
	public void add (Voter person)
	{
		Q.add(person);
		if (Q.size() > maxQlength)
			maxQlength = Q.size();
	}
	
	public void event (int tick){
		if (tick >= timeOfNextEvent) {			
			if (Q.size() >= 1) {
				person = Q.remove(0);		
				timeOfNextEvent = tick + (int) (person.getCheckInTime()
						+ 1);
				booth.add(person); 
			}	
		}
	}
	
	public int getLeft() {
		return Q.size();
	}
	
	public int getMaxQlength() {
		return maxQlength;
	}
}

