import javax.swing.*;


public class VotingBoothPanel extends JPanel {
	private JLabel input;
	private JLabel secondsToPerson;
	private JLabel avgSecondsCheckIn;
	private JLabel totalSeconds;
	private JLabel avgSecondsVoting;
	private JLabel secondsLeave;
	private JLabel numBooths;
	private JLabel output;
	private JLabel throughput;
	private JLabel avgVoterFinish;
	private JLabel numPeopleLeft;
	private JLabel maxQAL;
	private JLabel maxQMZ;
	private JLabel votingBoothLine;
	
	private JTextField txtSecondsToPerson = new JTextField(8);
	private JTextField txtAvgSecondsCheckIn = new JTextField(8);
	private JTextField txtTotalTime = new JTextField(8);
	private JTextField txtAvgSecVoting = new JTextField(8);
	private JTextField txtSecondsBeforeLeave = new JTextField(8);
	private JTextField txtNumBooths = new JTextField(8);
	
	private JButton startSim;
	private JButton quitSim;
	
	

	
}
