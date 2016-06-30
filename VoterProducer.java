package VoterSim;

import java.util.Random;

public class VoterProducer implements ClockListener {
	
	private int nextPerson = 0;
	private CheckInBooth booth1;
	private CheckInBooth booth2;
	private int numOfTicksNextPerson;
	private int averageBoothTime;
	private int averageCheckInTime;
	
	private Random r = new Random();
	
	public VoterProducer(CheckInBooth booth1, CheckInBooth booth2, 
			int numOfTicksNextPerson, 
			int averageBoothTime,
			int averageCheckInTime) {
		
		this.booth1 = booth1;
		this.booth2 = booth2;
		this.numOfTicksNextPerson = numOfTicksNextPerson;
		this.averageBoothTime = averageBoothTime;
		this.averageCheckInTime = averageCheckInTime;
	}
	
	public void event(int tick) {
		if (nextPerson <= tick) {
			
			//sets time until next person is created
			nextPerson = tick + (int)(numOfTicksNextPerson*0.5*
					r.nextGaussian() + numOfTicksNextPerson+.5);
			
			//creates new voter
			Voter person = new Voter();
			
			//sets how long person will take to vote
			person.setBoothTime(averageBoothTime*0.5*r.nextGaussian() + 
					averageBoothTime +.5);
			
			//sets how long person will take to check in 
			person.setCheckInTime(averageCheckInTime*0.5*
					r.nextGaussian() + averageCheckInTime + 0.5);
					
			//Add person to either booth with a 50% chance
			if(r.nextBoolean())
				booth1.add(person);
			else
				booth2.add(person);
		}
	}

}