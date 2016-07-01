import javax.swing.JFrame;

public class VotingBoothSimulation{

	public static void main (String[] args)
	{	
		JFrame frame = new JFrame ("Voting Booth Simulation");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		VotingBoothPanel panel = new VotingBoothPanel();
		frame.getContentPane().add(panel);
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
