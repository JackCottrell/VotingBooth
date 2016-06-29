
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class VotingBoothPanel extends JPanel {

    private JLabel throughput;
    private JLabel avgVoterFinish;
    private JLabel numPeopleLeft;
    private JLabel maxQAL;
    private JLabel maxQMZ;
    private JLabel votingBoothLine;

    private JTextField txtSecondsToPerson = new JTextField(8);
    private JTextField txtAvgSecondsCheckIn = new JTextField(8);
    private JTextField txtTotalSeconds = new JTextField(8);
    private JTextField txtAvgSecVoting = new JTextField(8);
    private JTextField txtSecondsLeave = new JTextField(8);
    private JTextField txtNumBooths = new JTextField(8);

    private JButton startSim;
    private JButton quitSim;
    private ButtonListener listener;

    public VotingBoothPanel(){

        setLayout(new BorderLayout());

        add(createInputPanel(), BorderLayout.NORTH);
        add(createButtonPanel());
        add(createOutputPanel(), BorderLayout.SOUTH);

    }

    /*******************************************************************
     * Creates a JPanel which displays all the JLabels and JTextFields
     * that are used for the inputs for the simulation.
     *
     * @return JPanel to be added to the main Panel.
     ******************************************************************/
    private JPanel createInputPanel(){

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
        outputPanel.setLayout(new GridLayout(7,2));

        //set instance variables to empty strings, will be changes by
        //the simulation.
        throughput = new JLabel("");
        avgVoterFinish = new JLabel("");
        numPeopleLeft = new JLabel("");
        maxQAL = new JLabel("");
        maxQMZ = new JLabel("");
        votingBoothLine = new JLabel("");

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

        //resize to fit with other panels.
        outputPanel.setPreferredSize(new Dimension(600, 160));
        outputPanel.revalidate();

        ButtonListener listener = new ButtonListener();
        quitSim.addActionListener(listener);
        startSim.addActionListener(listener);

        return outputPanel;

    }


    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==quitSim){
                System.exit(0);

            }
            if(e.getSource()==startSim){
                Clock clk = new Clock();
                Booth booth = new Booth();

                // int numOfTicksNextPerson = 20
                // int averageBoothTime = 18

                VoterProducer produce = new VoterProducer(booth, 20, 18);
                clk.add(produce);
                clk.add(booth);

                clk.run(10000);

               int secondsToPerson = Integer.parseInt(txtSecondsToPerson.getText());
                double secondsCheckIn = Double.parseDouble(txtAvgSecondsCheckIn.getText());
                double totalSec =Double.parseDouble(txtTotalSeconds.getText());
                double avgTimeVoting = Double.parseDouble(txtAvgSecVoting.getText());
                int secondsLeave = Integer.parseInt(txtSecondsLeave.getText());
                int numBooths = Integer.parseInt(txtNumBooths.getText());


                updateLabels(booth,secondsToPerson,secondsCheckIn,totalSec,
                                    avgTimeVoting,secondsLeave,numBooths );

            }

        }
    }

    private void  updateLabels(Booth booth, int secondsToPerson, double avgSecondsToCheckIn, double totalSeconds,
                                double avgSecVoting, int secondsToLeave, int numBooths){
        throughput.setText(""+booth.getThroughPut());
        avgVoterFinish.setText((""));
        numPeopleLeft.setText(""+booth.getLeft());
        maxQAL.setText("");
        maxQMZ.setText("");
        votingBoothLine.setText("" + booth.getMaxQlength());


    }
}