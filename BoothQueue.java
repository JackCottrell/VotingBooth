
import java.util.ArrayList;

public class BoothQueue implements ClockListener {

	private ArrayList<Voter> Q = new ArrayList<Voter>();

	private int maxQlength = 0;

	//person getting placed into q
	private Voter person;   

	//increments everytime someone leaves the q
	private int deserters = 0;
	
	
	//Overall Stats Class
	private SimStatus stats;
	
	//Brings in SimulationState in 
	public BoothQueue(SimStatus stats){
		this.stats = stats;
	}

	//adds person to Q
	public void add(Voter person)
	{
		Q.add(person);
		stats.setMainQSize(Q.size());
		if (Q.size() > maxQlength)
			maxQlength = Q.size();
	}

	//remove and return the first person in line
	public Voter getVoter(){
		Voter temp = Q.remove(0);
		stats.setMainQSize(Q.size());
		return temp;
	}
	
	//make sure no one wants to leave
	public void event(int tick){
		for(int i = 0; i < Q.size(); i++){
			if(tick >= Q.get(i).getLeaveTime()){
				Q.remove(i);
				
				stats.setMainQSize(Q.size());
				stats.incDeserters();
			}
		}
	}

	//return number of voter in q
	public int getLeft() {
		return Q.size();
	}

	public int getMaxQlength() {
		return maxQlength;
	}

	//return how many people left the q
	public int getDeserters() {
		return deserters;
	}
	
	public ArrayList<Voter> getQ(){
		return Q;
	}
}