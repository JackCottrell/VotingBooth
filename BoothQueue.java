package VoterSim;

import java.util.ArrayList;

public class BoothQueue implements ClockListener {

	private ArrayList<Voter> Q = new ArrayList<Voter>();

	private int maxQlength = 0;

	//person getting placed into q
	private Voter person;   

	//increments everytime someone leaves the q
	private int completed = 0;

	//arraylist of booths
	private Booth[] booths;

	//number of booths in arraylist
	private int numBooths; 

	//constructor and stuff
	public BoothQueue(Booth[] booths){
		this.booths = booths;
		numBooths = booths.length;
	}

	//adds person to Q
	public void add (Voter person)
	{
		Q.add(person);
		if (Q.size() > maxQlength)
			maxQlength = Q.size();
	}

	//check each booth if it is free
	public void event (int tick){
		if(!Q.isEmpty()){
			for(int i = 0; i < numBooths; i++){
				//if free add the person a front of line to that booth
				//also remove them from Q
				if(booths[i].isOpen()){
					booths[i].add(Q.remove(0));
				}
			}
		}
	}

	public int getLeft() {
		return Q.size();
	}

	public int getMaxQlength() {
		return maxQlength;
	}

	public int getThroughPut() {
		return completed;
	}
}
