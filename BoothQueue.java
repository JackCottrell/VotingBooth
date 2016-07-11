import java.util.ArrayList;
/**********************************************************************
Hold the voters that are waiting to enter a booth to vote

@author Jack Cottrell, Robert Molenhouse, Colin Lewis
@version Summer 2016

@implements ClockListener
 **********************************************************************/
public class BoothQueue implements ClockListener {

	/** List of all the voters that are waiting */
	private ArrayList<Voter> Q = new ArrayList<Voter>();

	/** Records the max length the queue reaches */
	private int maxQlength = 0;

	/** Counts the voters who got tired of waiting */
	private int deserters = 0;
	
	/** List of all the voters that are waiting */
	private SimStatus stats;
	
	/*******************************************************************
	Constructor brings in the SimStatus class to record any needed 
	information for the simulation
	
	@param stats holds the information for the simulation
	 ******************************************************************/
	public BoothQueue(SimStatus stats){
		this.stats = stats;
	}

	/*******************************************************************
	Adds the given voter the the queue of voters waiting to vote
	
	@param person the voter getting added to the queue
	 ******************************************************************/
	public void add(Voter person)
	{
		Q.add(person);
		stats.setMainQSize(Q.size());
		if (Q.size() > maxQlength)
			maxQlength = Q.size();
	}

	/*******************************************************************
	Removes and returns the voter in the front of the queue
	
	@return Voter the voter that was next in line to vote
	 ******************************************************************/
	public Voter getVoter(){
		Voter temp = Q.remove(0);
		stats.setMainQSize(Q.size());
		return temp;
	}
	
	/*******************************************************************
	Runs each time the clock calls event. Checks to see if anyone has
	been waiting in line to long. If they have, they are removed
	
	@param tick the current time stamp from the clock
	 ******************************************************************/
	public void event(int tick){
		stats.setTick(tick);
		for(int i = 0; i < Q.size(); i++){
			if(tick >= Q.get(i).getLeaveTime()){
				Q.remove(i);
				
				stats.setMainQSize(Q.size());
				stats.incDeserters();
			}
		}
	}

	/*******************************************************************
	Returns the number of voters in the queue
	
	@return size of arrayList Q
	 ******************************************************************/
	public int getLeft() {
		return Q.size();
	}

	/*******************************************************************
	Returns the maximum length that the queue reaching during the
	simulation
	
	@return maxQlength the maximum length the queue reached
	 ******************************************************************/
	public int getMaxQlength() {
		return maxQlength;
	}

	/*******************************************************************
	Returns the number of voters that left the line
	
	@return deserters the number of voters that left the line
	 ******************************************************************/
	public int getDeserters() {
		return deserters;
	}
	
	/*******************************************************************
	Returns the entire queue of voters
	
	@return Q the arrayList of voters waiting in the queue
	 ******************************************************************/
	public ArrayList<Voter> getQ(){
		return Q;
	}
}