import java.util.ArrayList;
/**
 * @author   Jack Cottrell
 */
public class CheckInBooth implements ClockListener {
	
	private ArrayList<Voter> Q = new ArrayList<Voter>();
	
	private int timeOfNextEvent = 0;
	private int maxQlength = 0;
	private Voter person;   
	private BoothQueue mainQueue;
	private SimStatus stats;
	
	public CheckInBooth(BoothQueue mainQueue, SimStatus stats){
		this.mainQueue = mainQueue;
		this.stats = stats;
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
	
	public int getLeft() {
		return Q.size();
	}
	
	public int getMaxQlength() {
		return maxQlength;
	}
}