package VoterSim;

public class testing {

	public static void main(String[] args) {
		
		Clock clk = new Clock();
		Booth booth = new Booth();
		CheckInBooth checkInAL = new CheckInBooth(booth);
		CheckInBooth checkInMZ = new CheckInBooth(booth);
		
		VoterProducer produce = new VoterProducer(checkInAL,
				checkInMZ,20,40,20);	
		
		clk.add(produce);
		clk.add(checkInAL);
		clk.add(checkInMZ);
		clk.add(booth);
		
		clk.run(10000);
		
		System.out.println("AL q: " + checkInAL.getMaxQlength());
		System.out.println("MZ q: " + checkInMZ.getMaxQlength());
		System.out.println("q: " + booth.getMaxQlength());
		System.out.println("Voted: " + booth.getThroughPut());
	}

}
