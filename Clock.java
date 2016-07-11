
/**
 * @author Roger Ferguson
 */
public class Clock {

	private ClockListener[] myListeners;
	private int numListeners;
	private int MAX = 100;
	private SimStatus info;

	public Clock(SimStatus info) {
		this.info = info;
		numListeners = 0;
		myListeners = new ClockListener[MAX];
	}

	public void run(int endingTime) {
			for (int j = 0; j < numListeners; j++)
				myListeners[j].event(endingTime);
	}

	public void add(ClockListener cl) {
		myListeners[numListeners] = cl;
		numListeners++;
	}
	
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
	
	public static void removeElt( ClockListener [] arr, int remIndex )
	{
	   for ( int i = remIndex ; i < arr.length - 1 ; i++ )
	   {
	      arr[ i ] = arr[ i + 1 ] ; 
	   }
	}

	public  ClockListener[] getMyListeners() {
		return myListeners;
	}

	public void setMyListeners(ClockListener[] myListeners) {
		this.myListeners = myListeners;
	}
	
	public int getNumListeners() {
		return numListeners;
	}

	public void setNumListeners(int numListeners) {
		this.numListeners = numListeners;
	}

	public int getMAX() {
		return MAX;
	}

}