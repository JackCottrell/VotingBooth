import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.InvalidParameterException;
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
    private JLabel regThrough;
    private JLabel limThrough;
    private JLabel specThrough;

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
   // private JButton subBooth;
    private ButtonListener listener = new ButtonListener();

    //time variable to increment
    private int currentTime = 0;

    //JPanels for visual display
    private JPanel checkInQPanel;
    private JPanel checkInBoothPanel;
    private JPanel mainQPanel;
    private JPanel boothPanel;

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
        addBooth = new JButton("Add Booth");
        subBooth = new JButton("Subtract Booth");
       // subBooth = new JButton("Subtract Booth");

        startSim.addActionListener(listener);
        quitSim.addActionListener(listener);
        addBooth.addActionListener(listener);
        subBooth.addActionListener(listener);
       // subBooth.addActionListener(listener);
        
        buttonPanel.add(startSim);
        buttonPanel.add(quitSim);
        buttonPanel.add(addBooth);
        buttonPanel.add(subBooth);
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
        outputPanel.setLayout(new GridLayout(19,2));

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
        regThrough = new JLabel("");
        limThrough = new JLabel("");
        specThrough = new JLabel("");

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
        outputPanel.setPreferredSize(new Dimension(1500, 260));
        outputPanel.revalidate();

        return outputPanel;

    }
    public void updatePanels(){
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

		// Update Labels
		tick.setText("" + info.getTick());
		throughput.setText("" + info.getThroughPut());
		regThrough.setText("" + info.getRegThrough());
		limThrough.setText("" + info.getLimThrough());
		specThrough.setText("" + info.getSpecThrough());
		numPeopleLeft.setText(""
				+ (info.getAL().getLeft() + info.getMZ().getLeft() + info
						.getBoothQueue().getLeft()));
		maxQAL.setText("" + info.getAL().getMaxQlength());
		maxQMZ.setText("" + info.getMZ().getMaxQlength());
		votingBoothLine.setText("" + info.getBoothQueue().getMaxQlength());
		deserters.setText("" + info.getDeserters());
		regVoters.setText("" + info.getRegVoters());
		specVoters.setText("" + info.getSpecVoters());
		limVoters.setText("" + info.getLimVoters());
		if (info.getNumPeopleCheckInReg() >= 1) {
			avgCheckInTimeReg.setText("" + (info.getTimeAtCheckInReg()/
                    info.getNumPeopleCheckInReg()));
        }
        if(info.getNumPeopleCheckedInSpec() >= 1){
            avgCheckInTimeSpec.setText("" + (info.getTimeAtCheckInSpec()/
                    info.getNumPeopleCheckedInSpec()));
        }
        if(info.getNumPeopleCheckedInLim() >= 1){
            avgCheckInTimeLim.setText("" + (info.getTimeatCheckInLim()/
                    info.getNumPeopleCheckedInLim()));
        }
        if(info.getThroughPut() >= 1)
            avgVoterFinish.setText("" +(info.getTotalTime()/info.getThroughPut()));

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

        regVoter = new ImageIcon("reg.png");
        limitedVoter = new ImageIcon("limitedNeeds.png");
        specialVoter = new ImageIcon("specialNeeds.png");
        superSpecial = new ImageIcon("superSpecial.png");

        //rescale image to correct size
        Image regImage = regVoter.getImage();
        Image newRegImage = regImage.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
        regVoter = new ImageIcon(newRegImage);

        //rescale limited icon
        Image limImage = limitedVoter.getImage();
        Image newLimImage = limImage.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
        limitedVoter = new ImageIcon(newLimImage);

        //rescale specialVoter icon
        Image specialImage = specialVoter.getImage();
        Image newSpecialImage = specialImage.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
        specialVoter = new ImageIcon(newSpecialImage);

        //rescale super special icon
        Image superImage = superSpecial.getImage();
        Image newsuperImage = superImage.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
        superSpecial = new ImageIcon(newSpecialImage);


        iconsALQueue = new JLabel[info.getALQsize()];
        iconsMZQueue = new JLabel[info.getMZQsize()];
        checkInQPanel.setLayout(new GridLayout(2,1));

        //Instantiate new label for AL Queue and set to ImageIcon
        for(int i = 0; i < info.getALQsize(); i++){
            iconsALQueue[i] = new JLabel("");
            checkInQPanel.add(iconsALQueue[i]);
            iconsALQueue[i].setIcon(regVoter);
            checkInQPanel.add(iconsALQueue[i]);
        }


        //Instantiate new label for MZ Queue and set to ImageIcon
        for(int i = 0; i < info.getMZQsize(); i++){
            iconsMZQueue[i] = new JLabel("");
            checkInQPanel.add(iconsMZQueue[i]);
            iconsMZQueue[i].setIcon(regVoter);
            checkInQPanel.add(iconsMZQueue[i]);
        }

    }

    //Panel for Check in booths
    private void createCheckInBoothPanel(){


        Border border = LineBorder.createGrayLineBorder();

        checkInBoothPanel.setLayout(new GridLayout(3,1));
       
        JLabel label1 = new JLabel("Check in A-L");
        JLabel label2 = new JLabel("Check in M-Z");

        checkInBoothPanel.add(label1);
        checkInBoothPanel.add(label2);

        label1.setBorder(border);
        label2.setBorder(border);
    }

    //Panel for main Q
    private void createMainQPanel(){

        regVoter = new ImageIcon("reg.png");
        limitedVoter = new ImageIcon("limitedNeeds.png");
        specialVoter = new ImageIcon("specialNeeds.png");
        superSpecial = new ImageIcon("superSpecial.png");

        //rescale image to correct size
        Image regImage = regVoter.getImage();
        Image newRegImage = regImage.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
        regVoter = new ImageIcon(newRegImage);

        //rescale limited icon
        Image limImage = limitedVoter.getImage();
        Image newLimImage = limImage.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
        limitedVoter = new ImageIcon(newLimImage);

        //rescale specialVoter icon
        Image specialImage = specialVoter.getImage();
        Image newSpecialImage = specialImage.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
        specialVoter = new ImageIcon(newSpecialImage);

        //rescale super special icon
        Image superImage = superSpecial.getImage();
        Image newsuperImage = superImage.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
        superSpecial = new ImageIcon(newSpecialImage);


        iconsMain = new JLabel[info.getMainQSize()];

        mainQPanel.setLayout(new GridLayout(1,1));

        //Instantiate new label for main Queue and set to ImageIcon
        for(int i = 0; i < info.getMainQSize(); i++){
            iconsMain[i] = new JLabel("");
            mainQPanel.add(iconsMain[i]);
            iconsMain[i].setIcon(regVoter);
            mainQPanel.add(iconsMain[i]);
        }

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

            if(e.getSource()==quitSim){
                System.exit(0);

            }
            
            //add a booth to clk and increase num of booths in info
            if(e.getSource()==addBooth){
            	info.getSim().addBooth();
            }
            if(e.getSource()==subBooth){
            	if(info.getNumBooths() > 1){
            		info.getSim().remBooth();
            	}
            }
            
            
            if(e.getSource()==startSim){

                try{
                    info.setSecondsToPerson(Integer.parseInt(txtSecondsToPerson.getText()));
                    info.setSecondsCheckIn(Integer.parseInt(txtAvgSecondsCheckIn.getText()));
                    info.setTotalSec(Integer.parseInt(txtTotalSeconds.getText()));
                    info.setAvgTimeVoting(Integer.parseInt(txtAvgSecVoting.getText()));
                    info.setSecondsLeave(Integer.parseInt(txtSecondsLeave.getText()));
                    info.setNumBooths(Integer.parseInt(txtNumBooths.getText()));
                    
                    int secondsToPerson = info.getSecondsToPerson();
                    int secondsCheckIn = info.getSecondsCheckIn();
                    int avgTimeVoting = info.getAvgTimeVoting();
                    int secondsLeave = info.getSecondsLeave();
                    int numBooths = info.getNumBooths();
                    
                    if (secondsToPerson < 1 || secondsCheckIn <1 || info.getTotalSec() <1|| avgTimeVoting<1||
                            secondsLeave <1|| numBooths < 1){
                        throw new InvalidParameterException();
                        //JOptionPane.showMessageDialog(null, "Input must be greater than 0");
                    }
                }
                catch(NumberFormatException e1){
                    JOptionPane.showMessageDialog(null, "Must be a number");
                    System.exit(0);
                }
                catch(InvalidParameterException e2){
                    JOptionPane.showMessageDialog(null, "Numbers can not be negative");
                    System.exit(0);
                }
                
                info.getSim().runSim();
                
        
    }


    }
        
}
} 
