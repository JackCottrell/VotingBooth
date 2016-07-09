import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class VotingBoothPanel extends JPanel {

	SimStatus info = new SimStatus();

	private JLabel throughput;
	private JLabel avgVoterFinish;
	private JLabel numPeopleLeft;
	private JLabel maxQAL;
	private JLabel maxQMZ;
	private JLabel votingBoothLine;
	private JLabel deserters;
	private JLabel avgCheckInTimeSpec;
	private JLabel avgCheckInTimeLim;
	private JLabel avgCheckInTimeReg;
	private JLabel regVoters;
	private JLabel specVoters;
	private JLabel limVoters;

	private JTextField txtSecondsToPerson = new JTextField(8);
	private JTextField txtAvgSecondsCheckIn = new JTextField(8);
	private JTextField txtTotalSeconds = new JTextField(8);
	private JTextField txtAvgSecVoting = new JTextField(8);
	private JTextField txtSecondsLeave = new JTextField(8);
	private JTextField txtNumBooths = new JTextField(8);

	private JButton startSim;
	private JButton quitSim;
	private ButtonListener listener = new ButtonListener();

	//time variable to increment 
	private int currentTime = 0;

	//JPanels for visual display
	private JPanel checkInQPanel;
	private JPanel checkInBoothPanel; 
	private JPanel mainQPanel; 
	private JPanel boothPanel; 

	public VotingBoothPanel(){

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		//I made all of the C level stuff in its own panel so I could 
		//just add anouther to the right with the graphics
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout());
		controlPanel.add(createInputPanel(), BorderLayout.NORTH);
		controlPanel.add(createButtonPanel());
		controlPanel.add(createOutputPanel(), BorderLayout.SOUTH);

		add(controlPanel);
		add(createVisualPanel());
	}

	/*******************************************************************
	 * Creates a JPanel which displays all the JLabels and JTextFields
	 * that are used for the inputs for the simulation.
	 * 
	 * @return JPanel to be added to the main Panel.
	 ******************************************************************/
	private JPanel createInputPanel(){
		txtSecondsToPerson.setText("20");
		txtAvgSecondsCheckIn.setText("15");
		txtTotalSeconds.setText("10000");
		txtAvgSecVoting.setText("60");
		txtSecondsLeave.setText("900");
		txtNumBooths.setText("1");



		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(7,2));

		inputPanel.add(new JLabel("Input Information"));
		inputPanel.add(new JLabel("---------------------------------"));
		inputPanel.add(new JLabel("Seconds to Next Person"));
		inputPanel.add(txtSecondsToPerson);
		inputPanel.add(new JLabel("Average seconds for Check-In"));
		inputPanel.add(txtAvgSecondsCheckIn);
		inputPanel.add(new JLabel("Total Time in Seconds"));
		inputPanel.add(txtTotalSeconds);
		inputPanel.add(new JLabel("Average Seconds for Voting"));
		inputPanel.add(txtAvgSecVoting);
		inputPanel.add(new JLabel("Seconds before Person Leaves"));
		inputPanel.add(txtSecondsLeave);
		inputPanel.add(new JLabel("Number of Booths"));
		inputPanel.add(txtNumBooths);

		//resize the panel to fit well with the other components.
		inputPanel.setPreferredSize(new Dimension(600, 160));
		inputPanel.revalidate();

		return inputPanel;

	}

	/*******************************************************************
	 * creates the JPanel that contains the buttons to interact with the
	 * simulation.
	 * 
	 * @return JPanel to add to the main panel.
	 ******************************************************************/
	private JPanel createButtonPanel(){
		JPanel buttonPanel = new JPanel();

		startSim = new JButton("Start Simulation");
		quitSim = new JButton("Quit Simulation");

		startSim.addActionListener(listener);
		quitSim.addActionListener(listener);


		buttonPanel.add(startSim);
		buttonPanel.add(quitSim);

		return buttonPanel;
	}

	/*******************************************************************
	 * Creates a JPanel that holds the JLabels that display the output
	 * of the simulation.  Certain labels are instance variables as they
	 * will need to be able to upate information as the simulation
	 * progresses.
	 * 
	 * @return JLabel of output information to add to the main panel.
	 ******************************************************************/
	private JPanel createOutputPanel(){

		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new GridLayout(14,2));

		//set instance variables to empty strings, will be changes by
		//the simulation.
		throughput = new JLabel("");
		avgVoterFinish = new JLabel("");
		numPeopleLeft = new JLabel("");
		maxQAL = new JLabel("");
		maxQMZ = new JLabel("");
		votingBoothLine = new JLabel("");
		deserters = new JLabel("");
		avgCheckInTimeReg = new JLabel("");
		avgCheckInTimeSpec = new JLabel("");
		avgCheckInTimeLim = new JLabel("");
		regVoters = new JLabel("");
		specVoters = new JLabel("");
		limVoters = new JLabel("");

		//add all the labels.
		outputPanel.add(new JLabel("Output Information"));
		outputPanel.add(new JLabel("--------------------------------"));
		outputPanel.add(new JLabel("Throughput"));
		outputPanel.add(throughput);
		outputPanel.add(new JLabel("Average Total Voter Time"));
		outputPanel.add(avgVoterFinish);
		outputPanel.add(new JLabel("Number of People Left in Line"));
		outputPanel.add(numPeopleLeft);
		outputPanel.add(new JLabel("Max Que Length A-L"));
		outputPanel.add(maxQAL);
		outputPanel.add(new JLabel("Max Que Length M-Z"));
		outputPanel.add(maxQMZ);
		outputPanel.add(new JLabel("Max Que Length Voting Booth Line"));
		outputPanel.add(votingBoothLine);
		outputPanel.add(new JLabel("People that left before voting"));
		outputPanel.add(deserters);
		outputPanel.add(new JLabel("Number of Regular Voters"));
		outputPanel.add(regVoters);
		outputPanel.add(new JLabel("Number of Special Voters"));
		outputPanel.add(specVoters);
		outputPanel.add(new JLabel("Number of Limited Voters"));
		outputPanel.add(limVoters);
		outputPanel.add(new JLabel("Average Regular Voter Check In"));
		outputPanel.add(avgCheckInTimeReg);
		outputPanel.add(new JLabel("Average Special Voter Check In"));
		outputPanel.add(avgCheckInTimeSpec);
		outputPanel.add(new JLabel("Average Limited Voter Check In"));
		outputPanel.add(avgCheckInTimeLim);

		//resize to fit with other panels.
		outputPanel.setPreferredSize(new Dimension(600, 260));
		outputPanel.revalidate();

		return outputPanel;

	}
	/*******************************************************************
	 *Creates a JPanel that visualy updates all of the Qs and booths
	 * 
	 * @return JLabel of the visual representation of the simulation 
	 ******************************************************************/
	private JPanel createVisualPanel(){

		JPanel visualPanel = new JPanel();
		checkInQPanel = new JPanel();
		checkInBoothPanel = new JPanel();
		mainQPanel = new JPanel();
		boothPanel = new JPanel();

		createCheckInQPanel();
		createCheckInBoothPanel();
		createMainQPanel();
		createBoothPanel();

		//visualPanel.setLayout(new BoxLayout(visualPanel, BoxLayout.X_AXIS));

		visualPanel.add(checkInQPanel);
		visualPanel.add(checkInBoothPanel);
		visualPanel.add(mainQPanel);
		visualPanel.add(boothPanel);

		visualPanel.setPreferredSize(new Dimension(1000,100));
		visualPanel.revalidate();
		return visualPanel;
	}


	//Pannel that has booth check in Qs
	//needs to be able to see the check in Qs to get their length
	private void createCheckInQPanel(){
		Border border = LineBorder.createGrayLineBorder();

		checkInQPanel.setLayout(new GridLayout(2,1));
				String voters1 = "";
				String voters2 = "";

				for(int i = 0; i < info.getALQsize(); i++){
					voters1 = voters1 + "X";
				}
				
				for(int i = 0; i < info.getMZQsize(); i++){
					voters2 = voters2 + "X";
				}

		JTextField label1 = new JTextField(voters1,4);
		JTextField label2 = new JTextField(voters2,4);
		checkInQPanel.add(label1);
		checkInQPanel.add(label2);
		label1.setHorizontalAlignment(JTextField.RIGHT);
		label2.setHorizontalAlignment(JTextField.RIGHT);
		label1.setBorder(border);
		label2.setBorder(border);

	}

	//Panel for Check in booths
	private void createCheckInBoothPanel(){

		Border border = LineBorder.createGrayLineBorder();

		checkInBoothPanel.setLayout(new GridLayout(2,1));
		JLabel label1 = new JLabel("Check in A-L");
		JLabel label2 = new JLabel("Check in M-Z");

		checkInBoothPanel.add(label1);
		checkInBoothPanel.add(label2);

		label1.setBorder(border);
		label2.setBorder(border);
	}

	//Panel for main Q
	private void createMainQPanel(){

		Border border = LineBorder.createGrayLineBorder();
		
		mainQPanel.setLayout(new BorderLayout());

		String voters = "";

		for(int i = 0; i < info.getMainQSize(); i++){
			voters = voters + "X";
		}

		JTextField label = new JTextField(voters,40);
		label.setHorizontalAlignment(JTextField.RIGHT);
		mainQPanel.add(label, BorderLayout.CENTER);

		label.setBorder(border);
	}

	//Panel for booths
	private void createBoothPanel(){

		Border border = LineBorder.createGrayLineBorder();

		boothPanel.setLayout(new BoxLayout(boothPanel,BoxLayout.Y_AXIS));

		for(int i = 1; i <= info.getNumBooths(); i++){
			JLabel label = new JLabel("Booth "+i);
			boothPanel.add(label);
			label.setBorder(border);
		}
	}

	int totalSec = 0;

	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int secondsToPerson = 0;
			int secondsCheckIn = 0;
			int avgTimeVoting = 0; 
			int secondsLeave = 0;
			int numBooths = 0;

			try{
				secondsToPerson = Integer.parseInt(txtSecondsToPerson.getText());
				secondsCheckIn = Integer.parseInt(txtAvgSecondsCheckIn.getText());
				totalSec =Integer.parseInt(txtTotalSeconds.getText());
				avgTimeVoting = Integer.parseInt(txtAvgSecVoting.getText());
				secondsLeave = Integer.parseInt(txtSecondsLeave.getText());
				numBooths = Integer.parseInt(txtNumBooths.getText());
				info.setNumBooths(numBooths);
			}
			catch(NumberFormatException e1){
				JOptionPane.showMessageDialog(null, "Must be a number");
				System.exit(0);
			}

			if (secondsToPerson < 1 || secondsCheckIn <1 || totalSec <1|| avgTimeVoting<1||
					secondsLeave <1|| numBooths < 1){
				throw new InvalidParameterException();
				//JOptionPane.showMessageDialog(null, "Input must be greater than 0");
			}

			if(e.getSource()==quitSim){
				System.exit(0);

			}
			if(e.getSource()==startSim){

				
				BoothQueue boothQueue = new BoothQueue(info);
				info.setLeaveTime(secondsLeave);
				
				Clock clk = new Clock();

				CheckInBooth AL = new CheckInBooth(boothQueue, info);
				CheckInBooth MZ = new CheckInBooth(boothQueue, info);
				VoterProducer produce = new VoterProducer(AL, MZ, 
						secondsToPerson, avgTimeVoting, secondsCheckIn,
						info);

				clk.add(boothQueue);
				clk.add(AL);
				clk.add(MZ);
				for(int i = 0; i < numBooths; i++){
					Booth booth = new Booth(boothQueue, info);
					clk.add(booth);
				}
				clk.add(produce);
				
				
				//new action listener for the timer
				ActionListener action = new ActionListener()
				{   
					@Override
					public void actionPerformed(ActionEvent event)
					{
						if (currentTime <= totalSec) {
							currentTime++;
							clk.run(currentTime);
							
							info.setALQsize(AL.getLeft());
							info.setMZQsize(MZ.getLeft());
							
							//update check in Qs
							checkInQPanel.removeAll();
							createCheckInQPanel();
							checkInQPanel.revalidate();
							checkInQPanel.repaint();

							//update 
							checkInBoothPanel.removeAll();
							createCheckInBoothPanel();
							checkInBoothPanel.revalidate();
							checkInBoothPanel.repaint();

							//update main Q
							mainQPanel.removeAll();
							createMainQPanel();
							mainQPanel.revalidate();
							mainQPanel.repaint();

							//update booths
							boothPanel.removeAll();
							createBoothPanel();
							boothPanel.revalidate();
							boothPanel.repaint();
						}
					}
				};
				Timer timer = new Timer(10, action);
				timer.start();


				//Update Labels
//							throughput.setText("" +info.getThroughPut());
//							avgVoterFinish.setText("" +(info.getTotalTime()/info.getThroughPut()));
//							numPeopleLeft.setText(""+(AL.getLeft() + MZ.getLeft() + boothQueue.getLeft()));
//							maxQAL.setText("" + AL.getMaxQlength());
//							maxQMZ.setText("" + MZ.getMaxQlength());
//							votingBoothLine.setText("" + boothQueue.getMaxQlength());
//							deserters.setText("" + info.getDeserters());
//							avgCheckInTimeReg.setText("" + info.getTimeAtCheckInReg()/
//									info.getNumPeopleCheckInReg());
//							avgCheckInTimeSpec.setText("" + info.getTimeAtCheckInSpec()/
//									info.getNumPeopleCheckedInSpec());
//							avgCheckInTimeLim.setText("" + info.getTimeatCheckInLim()/
//									info.getNumPeopleCheckedInLim());
//							regVoters.setText("" +info.getRegVoters());
//							specVoters.setText("" + info.getSpecVoters());
//							limVoters.setText("" + info.getLimVoters());

			}
		}
	}



}
