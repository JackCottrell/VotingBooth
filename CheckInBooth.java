import java.util.ArrayList;
/**********************************************************************
CheckInBooth is where the voter stays until their check in time is met 

@author Jack Cottrell, Robert Molenhouse, Colin Lewis
@version Summer 2016

@implements ClockListener
 **********************************************************************/
public class CheckInBooth implements ClockListener {
	
	/** List of people waiting to be checked in */
	private ArrayList<Voter> Q = new ArrayList<Voter>();
	
	/** Time until the person is done checking in */
	private int timeOfNextEvent = 0;
	
	/** Maximum length the queue reaches during the simulation */
	private int maxQlength = 0;
	
	/** The voter in the Booth */
	private Voter person;  
	
	/** The main Q class that hold the voters waiting to enter a booth*/
	private BoothQueue mainQueue;
	
	/** Class that holds all the info on the simulation */
	private SimStatus stats;
	
	/*******************************************************************
	Constructor creates a new check in booth and brings in SimStatus
	
	@param stats holds all information on simulation
	@param mainQueue that line of waiting voters
	 ******************************************************************/
	public CheckInBooth(BoothQueue mainQueue, SimStatus stats){
		this.mainQueue = mainQueue;
		this.stats = stats;
	}
	
	/*******************************************************************
	Adds a voter to the queue of voters waiting to check in 
	
	@param person voter to be added to the queue
	 ******************************************************************/
	public void add (Voter person)
	{
		Q.add(person);
		if (Q.size() > maxQlength)
			maxQlength = Q.size();
	}
	
	/*******************************************************************
	Runs each time the clock ticks and checks if the person is done
	with check in. If they are, they are moved to the main queue. Also
	checks if a person has been in the line to long.
	
	@param tick current time
	 ******************************************************************/
	public void event (int tick){
		if (tick >= timeOfNextEvent) {			
			if (Q.size() >= 1) {
				//check type of voter and save off time in check in.
				if(person instanceof RegularVoter){
					stats.setTimeAtCheckInReg(tick - 
											person.getTickTime());
					stats.incNumPeopleCheckInReg();
				}
				else if(person instanceof SpecialNeedsVoter){
					stats.setTimeAtCheckInSpec(tick -
											person.getTickTime());
					stats.incNumPeopleCheckedInSpec();
				}
				else if(person instanceof LimitedTimeVoter){
					stats.setTimeAtCheckInLim(tick -
											person.getTickTime());
					stats.incNumPeopleCheckedInLim();
				}
				
				// remove person from Q and set the next event time.
				person = Q.remove(0);		
				timeOfNextEvent = tick + (int) (person.getCheckInTime()
						+ 1);
				mainQueue.add(person); 
			}	
		}
		//checks if anyone wants to leave
		for(int i = 0; i < Q.size(); i++){
			if(tick >= Q.get(i).getLeaveTime()){
				Q.remove(i);
				stats.incDeserters();
			}
		}
	}
	
	/*******************************************************************
	Returns the amount of voters in the queue
	
	@return size number of people in queue
	 ******************************************************************/
	public int getLeft() {
		return Q.size();
	}
	
	/*******************************************************************
	Returns the maximum length that the queue reached during the sim
	
	@return maxQlength maximum number of voters that was reached
	 ******************************************************************/
	public int getMaxQlength() {
		return maxQlength;
	}
	
	public ArrayList<Voter> getQ() {
		return Q;
	}
}