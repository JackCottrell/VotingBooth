package VoterSim;

/**
 * @author Roger Ferguson
 *
 */
public class Sim {
	
	public static void main (String[] args) {
			
			Clock clk = new Clock();
			Booth booth = new Booth();
			CheckInBooth checkInAL = new CheckInBooth(booth);
			CheckInBooth checkInMZ = new CheckInBooth(booth);
			
			VoterProducer produce = new VoterProducer(checkInAL,
					checkInMZ,20,20,20);	
			
			clk.add(produce);
			clk.add(checkInAL);
			clk.add(checkInMZ);
			clk.add(booth);
			
			clk.run(10000);
			
			System.out.println("Through put is: " + booth.getThroughPut() + " people.");
			System.out.println("People that are still in the Q:" + booth.getLeft() + " people.");
			System.out.println ("Max Q length:" + booth.getMaxQlength() + " people.");
		}
	}