import java.util.Random;
/**********************************************************************
Produces different types of voters for the simulation

@author Jack Cottrell, Robert Molenhouse, Colin Lewis
@version Summer 2016

@implements ClockListener
 **********************************************************************/
public class VoterProducer implements ClockListener {
	
	/** Time when next voter will be created */ 
	private int nextPerson = 0;
	
	/** First check in booth */ 
	private CheckInBooth booth1;
	
	/** Second check in booth */ 
	private CheckInBooth booth2;
	
	/** Time between creating voters */ 
	private int numOfTicksNextPerson;
	
	/** Average time for a voter to vote */ 
	private int averageBoothTime;
	
	/** Average time for a voter to check in */ 
	private int averageCheckInTime;
	
	/** Keeps all information on the state of the simulation */ 
	private SimStatus info;
	
	/** Generates random numbers */ 
	private Random r = new Random();
	
	/*******************************************************************
	Constructor gets all voter information needed to create voters
	
	@param booth1 Check in booth for A through L
	@param booth2 Check in booth for M through Z
	@param numOfTicksNextPerson time between creating voters
	@param averageBoothTime average time it takes a voter to vote
	@param averageCheckInTime average time it takes a voter to check in
	@param info holds status of the simulation
	*******************************************************************/
	public VoterProducer(CheckInBooth booth1, CheckInBooth booth2, 
			int numOfTicksNextPerson, 
			int averageBoothTime,
			int averageCheckInTime, SimStatus info) {
		
		this.booth1 = booth1;
		this.booth2 = booth2;
		this.numOfTicksNextPerson = numOfTicksNextPerson;
		this.averageBoothTime = averageBoothTime;
		this.averageCheckInTime = averageCheckInTime;
		this.info = info;
	}
	
	/*******************************************************************
	Creates new voters of all types based on probability. Called by the 
	clock
	
	@param tick current time of the simulation
	*******************************************************************/
	public void event(int tick) {
		if (nextPerson <= tick) {
			
			//sets time until next person is created
			nextPerson = tick + (int)(numOfTicksNextPerson*0.5*
					r.nextGaussian() + numOfTicksNextPerson+.5);
			
			//randomly generates what type of voter is next.
			Voter person;
			int i = r.nextInt(10);
			
			if(i == 0){
				//50% chance of special needs been super special needs
				if(r.nextBoolean()){
					person = new SpecialNeedsVoter();
					info.incSpecVoters();
				}
				else
					person = new SuperSpecialNeeds();
					info.incSupSpecVoters();
			}
			else if((i > 0) && (i < 3)){
				person = new LimitedTimeVoter();
				info.incLimVoters();
			}
			else{
				person = new RegularVoter();
				info.incRegVoters();
			}
			
			//sets how long person will take to vote
			person.setBoothTime(averageBoothTime*0.5*r.nextGaussian() + 
					averageBoothTime +.5);
			person.setLeaveTime(tick + (info.getLeaveTime()));
			person.setCheckInTime(averageCheckInTime);
			person.setTickTime(tick);
			
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