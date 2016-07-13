
/**********************************************************************
Clock is what controls the timing of the simulation. It lets all the 
clock listeners know when they need to run their even methods 
@author Jack Cottrell, Robert Molenhouse, Colin Lewis
@version Summer 2016
 **********************************************************************/
public class Clock {
	
	/** Array of all objects that are clock listeners */
	private ClockListener[] myListeners;
	
	/** Number of objects in the myListener array */
	private int numListeners;
	
	/** Maximum number of clock listeners allowed */
	private int MAX = 100;
	
	/** Class that hold all information on the simulation */
	private SimStatus info;

	/*******************************************************************
	Constructor creates a new clock and instantiates the array of 
	clockListeners. Also brings in the SimStatus.
	
	@param info Keeps all the information on the simulation
	 ******************************************************************/
	public Clock(SimStatus info) {
		this.info = info;
		numListeners = 0;
		myListeners = new ClockListener[MAX];
	}

	/*******************************************************************
	Passes the current time to all of the clockListeners
	
	@param endTime the current time
	 ******************************************************************/
	public void run(int endingTime) {
			for (int j = 0; j < numListeners; j++)
				myListeners[j].event(endingTime);
	}

	/*******************************************************************
	Adds a clockListner object to the myListeners array
	
	@param stats holds all information on simulation
	 ******************************************************************/
	public void add(ClockListener cl) {
		myListeners[numListeners] = cl;
		numListeners++;
	}
	
	/*******************************************************************
	Removes a booth from the array of clockListners 
	 ******************************************************************/
	public void remBooth(){
		
		int flag = 0;
		
		//run through all clockListners
		for(int i = 0; i < myListeners.length; i++)
		{
			//find first booth
			if(myListeners[i] instanceof Booth && flag == 0)
			{
				flag = 1;
				//remove it
				removeElt(myListeners,i);
				numListeners--;
				//decrease number of booths in SimStatus
				info.decrNumBooths();
			}
		}
	}
	
	/*******************************************************************
	Removes an element from an array and shifts all remaining values
	
	@param arr array of ClockListners
	@param remIndex position on array of what is to be removed
	 ******************************************************************/
	public static void removeElt( ClockListener [] arr, int remIndex )
	{
	   for ( int i = remIndex ; i < arr.length - 1 ; i++ )
	   {
	      arr[ i ] = arr[ i + 1 ] ; 
	   }
	}

	
	/*******************************************************************
	Returns myListeners which is the array of ClockListeners
	
	@return myListeners array of ClockListeners
	 ******************************************************************/
	public  ClockListener[] getMyListeners() {
		return myListeners;
	}

	/*******************************************************************
	Sets the array of ClockListenrs
	
	@param myListeners array of ClockListeners
	 ******************************************************************/
	public void setMyListeners(ClockListener[] myListeners) {
		this.myListeners = myListeners;
	}
	
	/*******************************************************************
	Returns the number of ClockListners in the Simulation
	
	@return numListeners the number of ClockListeners in myListeners
	 ******************************************************************/
	public int getNumListeners() {
		return numListeners;
	}

	/*******************************************************************
	Sets the number of ClockListners in the simulation
	
	@param numListeners
	 ******************************************************************/
	public void setNumListeners(int numListeners) {
		this.numListeners = numListeners;
	}

	/*******************************************************************
	Constructor creates a new check in booth and brings in SimStatus
	
	@return MAX the maximum amount of ClockListeners allowed
	 ******************************************************************/
	public int getMAX() {
		return MAX;
	}

}