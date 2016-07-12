 import javax.swing.JFrame;

public class VotingBoothSimulation{

	public static void main (String[] args)
	{	
		JFrame frame = new JFrame ("Voting Booth Simulation");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		SimStatus info = new SimStatus();
		Sim sim = new Sim(info);
		info.setSim(sim);
		info.setPanel(new VotingBoothPanel(info));
		frame.getContentPane().add(info.getPanel());
		frame.setSize(1000, 700);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(true);
	}
}
