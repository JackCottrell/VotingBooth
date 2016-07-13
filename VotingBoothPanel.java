import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;
import java.util.ArrayList;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class VotingBoothPanel extends JPanel {

	Clock clk;
	BoothQueue boothQueue;

	private SimStatus info;
	private JLabel tick;
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
	private JLabel supSpecVoters;
	private JLabel regThrough;
	private JLabel limThrough;
	private JLabel specThrough;
	private JLabel supSpecThrough;

	private JTextField txtSecondsToPerson = new JTextField(8);
	private JTextField txtAvgSecondsCheckIn = new JTextField(8);
	private JTextField txtTotalSeconds = new JTextField(8);
	private JTextField txtAvgSecVoting = new JTextField(8);
	private JTextField txtSecondsLeave = new JTextField(8);
	private JTextField txtNumBooths = new JTextField(8);

	private JButton startSim;
	private JButton quitSim;
	private JButton addBooth;
	private JButton subBooth;
	private JButton update;
	// private JButton subBooth;
	private ButtonListener listener = new ButtonListener();

	//time variable to increment
	private int currentTime = 0;

	//JPanels for visual display
	private JPanel panel;
	private JPanel checkInBoothPanel;
	private JPanel mainQPanel;
	private JPanel boothPanel;
	private JPanel keyPanel;

	//JLabel to create icons for Queues
	public JLabel[] iconsALQueue;
	public JLabel[] iconsMZQueue;
	public JLabel[] iconsMain;

	public ImageIcon regVoter;
	public ImageIcon limitedVoter;
	public ImageIcon specialVoter;
	public ImageIcon superSpecial;

	public VotingBoothPanel(SimStatus info){

		this.info = info;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		createIcons();
		//I made all of the C level stuff in its own panel so I could
		//just add anouther to the right with the graphics
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout());
		controlPanel.add(createInputPanel(), BorderLayout.NORTH);
		controlPanel.add(createButtonPanel());
		controlPanel.add(createOutputPanel(), BorderLayout.SOUTH);

		add(controlPanel);
		add(createVisualPanel());
		add(createVoterKeyPanel());
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
		inputPanel.setPreferredSize(new Dimension(1000,160));
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
		addBooth = new JButton("Add Booth");
		subBooth = new JButton("Subtract Booth");
		update = new JButton("Update Input");
		// subBooth = new JButton("Subtract Booth");

		startSim.addActionListener(listener);
		quitSim.addActionListener(listener);
		addBooth.addActionListener(listener);
		subBooth.addActionListener(listener);
		update.addActionListener(listener);
		// subBooth.addActionListener(listener);

		buttonPanel.add(startSim);
		buttonPanel.add(quitSim);
		buttonPanel.add(addBooth);
		buttonPanel.add(subBooth);
		buttonPanel.add(update);
		//buttonPanel.add(subBooth);

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
		outputPanel.setLayout(new GridLayout(21,2));

		//set instance variables to empty strings, will be changes by
		//the simulation.
		tick = new JLabel("0");
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
		supSpecVoters = new JLabel("");
		regThrough = new JLabel("");
		limThrough = new JLabel("");
		specThrough = new JLabel("");
		supSpecThrough = new JLabel("");

		//add all the labels.
		outputPanel.add(new JLabel("Output Information"));
		outputPanel.add(new JLabel("--------------------------------"));
		outputPanel.add(new JLabel("Seconds Elapsed"));
		outputPanel.add(tick);
		outputPanel.add(new JLabel("Throughput"));
		outputPanel.add(throughput);
		outputPanel.add(new JLabel("Regular Throughput"));
		outputPanel.add(regThrough);
		outputPanel.add(new JLabel("Special Throughput"));
		outputPanel.add(specThrough);
		outputPanel.add(new JLabel("Limited Time Throughput"));
		outputPanel.add(limThrough);
		outputPanel.add(new JLabel("Super Special Throughput"));
		outputPanel.add(supSpecThrough);
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
		outputPanel.add(new JLabel("Number of Limited Voters"));
		outputPanel.add(limVoters);
		outputPanel.add(new JLabel("Number of Special Voters"));
		outputPanel.add(specVoters);
		outputPanel.add(new JLabel("Number of Super Special Voters"));
		outputPanel.add(supSpecVoters);
		outputPanel.add(new JLabel("Average Regular Voter Check In"));
		outputPanel.add(avgCheckInTimeReg);
		outputPanel.add(new JLabel("Average Special Voter Check In"));
		outputPanel.add(avgCheckInTimeSpec);
		outputPanel.add(new JLabel("Average Limited Voter Check In"));
		outputPanel.add(avgCheckInTimeLim);

		//resize to fit with other panels.
		outputPanel.setPreferredSize(new Dimension(1000,350));
		outputPanel.revalidate();

		return outputPanel;

	}
	public void updatePanels(){
		//update check in Qs
		panel.removeAll();
		createCheckInQPanel();
		panel.revalidate();
		panel.repaint();

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

		// Update Labels
		tick.setText("" + info.getTick());
		throughput.setText("" + info.getThroughPut());
		regThrough.setText("" + info.getRegThrough());
		limThrough.setText("" + info.getLimThrough());
		specThrough.setText("" + info.getSpecThrough());
		supSpecThrough.setText("" + info.getSupSpecThrough());
		numPeopleLeft.setText(""
				+ (info.getAL().getLeft() + info.getMZ().getLeft() + 
						info.getBoothQueue().getLeft()));
		maxQAL.setText("" + info.getAL().getMaxQlength());
		maxQMZ.setText("" + info.getMZ().getMaxQlength());
		votingBoothLine.setText("" + info.getBoothQueue()
						.getMaxQlength());
		deserters.setText("" + info.getDeserters());
		regVoters.setText("" + info.getRegVoters());
		specVoters.setText("" + info.getSpecVoters());
		limVoters.setText("" + info.getLimVoters());
		supSpecVoters.setText("" + info.getSupSpecVoters());
		if (info.getNumPeopleCheckInReg() >= 1) {
			avgCheckInTimeReg.setText("" + (info.getTimeAtCheckInReg()/
					info.getNumPeopleCheckInReg()));
		}
		if(info.getNumPeopleCheckedInSpec() >= 1){
			avgCheckInTimeSpec.setText("" + 
		(info.getTimeAtCheckInSpec()/info.getNumPeopleCheckedInSpec()));
		}
		if(info.getNumPeopleCheckedInLim() >= 1){
			avgCheckInTimeLim.setText("" + (info.getTimeatCheckInLim()/
					info.getNumPeopleCheckedInLim()));
		}
		if(info.getThroughPut() >= 1)
			avgVoterFinish.setText("" +(info.getTotalTime()/
							info.getThroughPut()));



	}

	/*******************************************************************
	 *Creates a JPanel that visualy updates all of the Qs and booths
	 *
	 * @return JLabel of the visual representation of the simulation
	 ******************************************************************/
	private JPanel createVisualPanel(){

		JPanel visualPanel = new JPanel();
		panel = new JPanel();
		checkInBoothPanel = new JPanel();
		mainQPanel = new JPanel();
		boothPanel = new JPanel();

		createCheckInQPanel();
		createCheckInBoothPanel();
		createMainQPanel();
		createBoothPanel();

		visualPanel.add(boothPanel);
		visualPanel.add(mainQPanel);
		visualPanel.add(checkInBoothPanel);
		visualPanel.add(panel);

		visualPanel.setPreferredSize(new Dimension(1000,100));
		visualPanel.revalidate();
		return visualPanel;
	}


	//Pannel that has booth check in Qs
	//needs to be able to see the check in Qs to get their length
	private JPanel createALQ(){
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		
		iconsALQueue = new JLabel[5];
		if(info.getTick() == 0){
		for(int m = 0; m < 5; m++){
			iconsALQueue[m] = new JLabel("   ");
			panel.add(iconsALQueue[m]);
			}
		}
		//Instantiate new label for AL Queue and set to ImageIcon
		if(info.getALQsize() < 5){
		for(int j = 0; j < info.getALQsize(); j++){
			Voter person = info.getAL().getQ().get(j);
			iconsALQueue[j] = new JLabel("");
			panel.add(iconsALQueue[j]);
			if(person instanceof RegularVoter){
					iconsALQueue[j].setIcon(regVoter);
					panel.add(iconsALQueue[j]);
				} else if (person instanceof SpecialNeedsVoter) {
					iconsALQueue[j].setIcon(specialVoter);
					panel.add(iconsALQueue[j]);
				} else if (person instanceof SuperSpecialNeeds) {
					iconsALQueue[j].setIcon(superSpecial);
					panel.add(iconsALQueue[j]);
				} else {
					iconsALQueue[j].setIcon(limitedVoter);
					panel.add(iconsALQueue[j]);
				}
				if (info.getALQsize() < 5) {
					for (int n = info.getALQsize(); n < 5; n++) {
						iconsALQueue[n] = new JLabel("   ");
						panel.add(iconsALQueue[n]);
					}
				}
			}
		}
		else{
			for(int j = 0; j < 5; j++){
				Voter person = info.getAL().getQ().get(j);
				iconsALQueue[j] = new JLabel("");
				panel.add(iconsALQueue[j]);
				if(person instanceof RegularVoter){
						iconsALQueue[j].setIcon(regVoter);
						panel.add(iconsALQueue[j]);
					} else if (person instanceof SpecialNeedsVoter) {
						iconsALQueue[j].setIcon(specialVoter);
						panel.add(iconsALQueue[j]);
					} else if (person instanceof SuperSpecialNeeds) {
						iconsALQueue[j].setIcon(superSpecial);
						panel.add(iconsALQueue[j]);
					} else {
						iconsALQueue[j].setIcon(limitedVoter);
						panel.add(iconsALQueue[j]);
					}
		}
		}
		return panel;	
	}
private JPanel createMZQ(){
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,1));
		
		iconsMZQueue = new JLabel[5];
		if(info.getTick() == 0){
		for(int m = 0; m < 5; m++){
			iconsMZQueue[m] = new JLabel("   ");
			panel.add(iconsMZQueue[m]);
			}
		}
		//Instantiate new label for MZ Queue and set to ImageIcon
		if(info.getMZQsize() < 5){
		for(int i = 0; i < info.getMZQsize(); i++){
			Voter person1 = info.getMZ().getQ().get(i);
			iconsMZQueue[i] = new JLabel("");
			panel.add(iconsMZQueue[i]);
			if(person1 instanceof RegularVoter){
				iconsMZQueue[i].setIcon(regVoter);
				panel.add(iconsMZQueue[i]);
			}
			else if (person1 instanceof SpecialNeedsVoter){
				iconsMZQueue[i].setIcon(specialVoter);
				panel.add(iconsMZQueue[i]);
			}
			else if (person1 instanceof SuperSpecialNeeds){
				iconsMZQueue[i].setIcon(superSpecial);
				panel.add(iconsMZQueue[i]);
			}
			else{
				iconsMZQueue[i].setIcon(limitedVoter);
				panel.add(iconsMZQueue[i]);
			}
		}
		if (info.getMZQsize() < 5) {
			for (int n = info.getMZQsize(); n < 5; n++) {
				iconsMZQueue[n] = new JLabel("   ");
				panel.add(iconsMZQueue[n]);
			}
	}
	}
		else{
			for(int i = 0; i < 5; i++){
				Voter person1 = info.getMZ().getQ().get(i);
				iconsMZQueue[i] = new JLabel("");
				panel.add(iconsMZQueue[i]);
				if(person1 instanceof RegularVoter){
					iconsMZQueue[i].setIcon(regVoter);
					panel.add(iconsMZQueue[i]);
				}
				else if (person1 instanceof SpecialNeedsVoter){
					iconsMZQueue[i].setIcon(specialVoter);
					panel.add(iconsMZQueue[i]);
				}
				else if (person1 instanceof SuperSpecialNeeds){
					iconsMZQueue[i].setIcon(superSpecial);
					panel.add(iconsMZQueue[i]);
				}
				else{
					iconsMZQueue[i].setIcon(limitedVoter);
					panel.add(iconsMZQueue[i]);
				}
			}
		}
		return panel;
		
	}
	private void createCheckInQPanel(){

		panel.setLayout(new GridLayout(2,1));
		panel.add(createALQ());
		panel.add(createMZQ());
	}
	//Panel for Check in booths
	private void createCheckInBoothPanel(){


		Border border = LineBorder.createGrayLineBorder();

		checkInBoothPanel.setLayout(new GridLayout(2,1));

		JButton label1 = new JButton("Check in A-L");
		JButton label2 = new JButton("Check in M-Z");

		checkInBoothPanel.add(label1);
		checkInBoothPanel.add(label2);

		label1.setBorder(border);
		label2.setBorder(border);
	}
	private JPanel createCheckInBoothPanel2(){

		JPanel panel = new JPanel();
		Border border = LineBorder.createGrayLineBorder();

		panel.setLayout(new GridLayout(3,1));

		JButton label1 = new JButton("Check in A-L");
		JButton label2 = new JButton("Check in M-Z");

		panel.add(label1);
		panel.add(label2);

		label1.setBorder(border);
		label2.setBorder(border);
		
		return panel;
	}
	private JPanel createVoterKeyPanel(){
		//keyPanel.setLayout(new GridLayout(1,8));
		//Display icon and key for regular voter
		keyPanel = new JPanel();
		JLabel regLabel = new JLabel("");
		keyPanel.add(regLabel);
		regLabel.setIcon(regVoter);
		keyPanel.add(regLabel);
		JLabel regName = new JLabel("=Regular Voter  ");
		keyPanel.add(regName);

		//Display icon and key for limited Voter
		JLabel limitedLabel = new JLabel("");
		keyPanel.add(limitedLabel);
		limitedLabel.setIcon(limitedVoter);
		keyPanel.add(limitedLabel);
		JLabel limName = new JLabel("=Limited Voter"  );
		keyPanel.add(limName);

		//Display icon and key for special Needs
		JLabel specialLabel = new JLabel("");
		keyPanel.add(specialLabel);
		specialLabel.setIcon(specialVoter);
		keyPanel.add(specialLabel);
		JLabel specName = new JLabel("=Special Needs Voter"  );
		keyPanel.add(specName);

		//Display icon and key for super special Needs
		JLabel supSpecialLabel = new JLabel("");
		keyPanel.add(supSpecialLabel);
		supSpecialLabel.setIcon(superSpecial);
		keyPanel.add(supSpecialLabel);
		JLabel supSpecName = new JLabel("= Super Special Needs Voter"  );
		keyPanel.add(supSpecName);



		return keyPanel;
	}
	private void createIcons(){
		regVoter = new ImageIcon("reg.png");
		limitedVoter = new ImageIcon("limitedNeeds.png");
		specialVoter = new ImageIcon("specialNeeds.png");
		superSpecial = new ImageIcon("superSpecial.png");

		//rescale image to correct size
		Image regImage = regVoter.getImage();
		Image newRegImage = regImage.getScaledInstance(10, 10, 
						java.awt.Image.SCALE_SMOOTH);
		regVoter = new ImageIcon(newRegImage);

		//rescale limited icon
		Image limImage = limitedVoter.getImage();
		Image newLimImage = limImage.getScaledInstance(10, 10,
						java.awt.Image.SCALE_SMOOTH);
		limitedVoter = new ImageIcon(newLimImage);

		//rescale specialVoter icon
		Image specialImage = specialVoter.getImage();
		Image newSpecialImage = specialImage.getScaledInstance(10, 10, 
						java.awt.Image.SCALE_SMOOTH);
		specialVoter = new ImageIcon(newSpecialImage);

		//rescale super special icon
		Image superImage = superSpecial.getImage();
		Image newsuperImage = superImage.getScaledInstance(10, 10, 
						java.awt.Image.SCALE_SMOOTH);
		superSpecial = new ImageIcon(newsuperImage);
	}
	//Panel for main Q
	private void createMainQPanel(){

		iconsMain = new JLabel[50];
		if(info.getTick() == 0){
		for(int m = 0; m < 50; m++){
			iconsMain[m] = new JLabel("   ");
			mainQPanel.add(iconsMain[m]);
			}
		}
		mainQPanel.setLayout(new GridLayout(1, 1));
		if(info.getMainQSize() < 50){
			for (int j = 0; j < info.getMainQSize(); j++) {
				Voter person = info.getBoothQueue().getQ().get(j);
				iconsMain[j] = new JLabel("");

				if (person instanceof LimitedTimeVoter) {
					iconsMain[j].setIcon(limitedVoter);
					// mainQPanel.add(iconsMain[j]);
				} else if (person instanceof SpecialNeedsVoter) {
					iconsMain[j].setIcon(specialVoter);
					// mainQPanel.add(iconsMain[j]);
				} else if (person instanceof SuperSpecialNeeds) {
					iconsMain[j].setIcon(superSpecial);
					// mainQPanel.add(iconsMain[j]);
				} else {
					iconsMain[j].setIcon(regVoter);
					// mainQPanel.add(iconsMain[j]);
				}
				mainQPanel.add(iconsMain[j]);
			}

			if (info.getMainQSize() < 50) {
				for (int n = info.getMainQSize(); n < 50; n++) {
					iconsMain[n] = new JLabel("   ");
					mainQPanel.add(iconsMain[n]);
				}
			}
		}
		else{
			for (int j = 0; j < 50; j++) {
				Voter person = info.getBoothQueue().getQ().get(j);
				iconsMain[j] = new JLabel("");

				if (person instanceof LimitedTimeVoter) {
					iconsMain[j].setIcon(limitedVoter);
					// mainQPanel.add(iconsMain[j]);
				} else if (person instanceof SpecialNeedsVoter) {
					iconsMain[j].setIcon(specialVoter);
					// mainQPanel.add(iconsMain[j]);
				} else if (person instanceof SuperSpecialNeeds) {
					iconsMain[j].setIcon(superSpecial);
					// mainQPanel.add(iconsMain[j]);
				} else {
					iconsMain[j].setIcon(regVoter);
					// mainQPanel.add(iconsMain[j]);
				}
				mainQPanel.add(iconsMain[j]);
			}
		}
	}

	//Panel for booths
	private void createBoothPanel(){

		Border border = LineBorder.createGrayLineBorder();

		boothPanel.setLayout(new GridLayout(5,1));

		for(int i = 1; i <= info.getNumBooths(); i++){
			JButton label = new JButton("Booth "+i);
			boothPanel.add(label);
			label.setBorder(border);
		}
	}

	int totalSec = 0;


	private class ButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			if(e.getSource()==quitSim){
				System.exit(0);

			}

			//add a booth to clk and increase num of booths in info
			if(e.getSource()==addBooth){
				info.getSim().addBooth();
			}
			//subtract a booth
			if(e.getSource()==subBooth){
				if(info.getNumBooths() > 1){
					info.getSim().remBooth();
				}
			}

			if(e.getSource() == update){  	
				getUserInfo();
				info.getSim().update();
			}

			if(e.getSource()==startSim){
				if(info.getTick() == 0){
				getUserInfo();
				info.getSim().runSim();  
				}
			}


		}

		private void getUserInfo() {
			try{
				info.setSecondsToPerson
					(Integer.parseInt(txtSecondsToPerson.getText()));
				info.setSecondsCheckIn
					(Integer.parseInt(txtAvgSecondsCheckIn.getText()));
				info.setTotalSec
					(Integer.parseInt(txtTotalSeconds.getText()));
				info.setAvgTimeVoting
					(Integer.parseInt(txtAvgSecVoting.getText()));
				info.setSecondsLeave
					(Integer.parseInt(txtSecondsLeave.getText()));
				//if the sim has started, don't update booths from here.
				if(info.getTick() <= 1){
				  int booths = Integer.parseInt(txtNumBooths.getText());
				  if(booths > 10){
					  info.setNumBooths(10);
					  JOptionPane.showMessageDialog(null,
							  "Can only have 10 booths max.");
				  }
				  else
					  info.setNumBooths(booths);
				}

				int secondsToPerson = info.getSecondsToPerson();
				int secondsCheckIn = info.getSecondsCheckIn();
				int avgTimeVoting = info.getAvgTimeVoting();
				int secondsLeave = info.getSecondsLeave();
				int numBooths = info.getNumBooths();

				if (secondsToPerson < 1 || secondsCheckIn <1 || 
						info.getTotalSec() <1|| avgTimeVoting<1||
						secondsLeave <1|| numBooths < 1){
					throw new InvalidParameterException();
					//JOptionPane.showMessageDialog(null, 
					//"Input must be greater than 0");
				}
			}
			catch(NumberFormatException e1){
				JOptionPane.showMessageDialog(null, "Must be a number");
				System.exit(0);
			}
			catch(InvalidParameterException e2){
				JOptionPane.showMessageDialog(null, 
						"Numbers can not be negative");
				System.exit(0);
			}
		}

	}
}
