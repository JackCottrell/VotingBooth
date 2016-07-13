/**********************************************************************
Booth class holds one voter at a time for the length of time it takes 
them to vote.
@author Jack Cottrell, Robert Molenhouse, Colin Lewis
@version Summer 2016
@implements ClockListener
 **********************************************************************/
public class Booth implements ClockListener {

	/** Time until person is done voting and the booth is empty */
	private int timeOfNextEvent = 0;

	/** Voter that is in the booth */
	private Voter person;

	/** Queue of voters waiting to vote */
	private BoothQueue boothQueue;

	/** Class that holds all the info on the simulation */
	private SimStatus info;

	/*******************************************************************
	Constructor creates a new booth that is feed by boothQueue
	
	@param info holds all information on sim
	@param boothQueue that line of waiting voters
	 ******************************************************************/
	public Booth(BoothQueue boothQueue, SimStatus info){
		this.boothQueue = boothQueue;
		this.info = info;
	}

	/*******************************************************************
	Runs each time the clock calls event. Checks if the booth is empty
	and if it is, it add the person from the front of the queue
	
	@param tick the current time stamp from the clock
	 ******************************************************************/
	public void event (int tick){
		if (tick >= timeOfNextEvent) {

			//Check if there are any voters waiting to vote
			if(!boothQueue.getQ().isEmpty()){
				//there is a person---get there time to leave the booth
				if(person != null){
					info.setTotalTime(tick - (person.getTickTime()));
				}

				person = boothQueue.getVoter();		
				timeOfNextEvent = tick + (int) (person.getBoothTime() + 1);
				info.incThroughPut();	

				//count each type of voter that voted
				if(person instanceof RegularVoter){
					info.incRegThrough();
				}
				if(person instanceof SpecialNeedsVoter){
					info.incSpecThrough();
				}
				if(person instanceof LimitedTimeVoter){
					info.incLimThrough();
				}
				if(person instanceof SuperSpecialNeeds){
					info.incSupSpecThrough();
				}
			}
		}
	}

}