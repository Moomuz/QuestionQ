import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class Tester {
	private static final int QUESTION_HEIGHT = 50;
	private static final int QUESTION_WIDTH = 400;
	
	public static void main(String[] args)
	{
		Queue queue = new Queue();
		Formatter formatter = new Formatter();
		
		JFrame studentView = new JFrame();
		JFrame profView = new JFrame();
		JFrame queueView = new JFrame();
		JFrame rosterView = new JFrame();
		JFrame extraCreditView = new JFrame("Add extra credit");
		
		JLabel idLabel = new JLabel("Enter your student ID here (ex: XXXXXXXXX)");
		studentView.add(idLabel);
		JTextField idField = new JTextField(20);
		studentView.add(idField);
		JLabel descLabel = new JLabel("Enter your question here (Limit: 50 characters)");
		studentView.add(descLabel);
		JTextField qField = new JTextField(30);
		studentView.add(qField);
		
		ArrayList<JLabel> qLabels = new ArrayList<>();
		ArrayList<JPanel> qPanels = new ArrayList<>();
		
		qField.addActionListener(event ->
		{
			String qString = qField.getText();
			String idString = idField.getText();
			int id = 0;
	    	try 
	    	{
	    		id = Integer.parseInt(idString);
	    	}
	    	catch (Exception e)
	    	{
	    		System.out.println("Please enter a valid student ID");
	    	}
			Question question = new Question(qString, id);
			queue.addQuestion(question);
			
			JLabel qLabel = new JLabel(formatter.formatQuestion(queue, question));
			
			qLabels.add(qLabel);
			
			JPanel qPanel = new JPanel();
	        EmptyBorder panelBorder = new EmptyBorder(0, (400-qLabel.getWidth())/2, 0, (400-qLabel.getWidth())/2); //centers the question
	        qPanel.setBorder(panelBorder);
	        
			qPanel.add(qLabel);
			qPanels.add(qPanel);
			
			queueView.add(qPanel);
			qLabel.setSize(new Dimension(QUESTION_WIDTH, QUESTION_HEIGHT));
			queueView.repaint();
			
			queueView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			queueView.pack();
			queueView.setSize(400,300);
			queueView.setLocation(900, 0);
			queueView.setVisible(true);
		});

		studentView.setLayout(new FlowLayout());
		studentView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		studentView.pack();
		studentView.setSize(400,300);
		studentView.setVisible(true);
		
		
		JLabel qNum = new JLabel("Delete a question:");
		profView.add(qNum);
		JTextField deleteField = new JTextField(30);
		profView.add(deleteField);
		deleteField.addActionListener(event ->
		{
			String qPosition = deleteField.getText();
			int qPos = 0;
			qPos = Integer.parseInt(qPosition);
			queue.deleteQuestion(qPos-1);
			JLabel toBeRemoved = qLabels.get(qPos-1);
			JPanel qPanel = qPanels.get(qPos-1);
			qPanel.setBorder(new EmptyBorder(-8, 0, -8, 0));
			qPanel.remove(toBeRemoved);
			profView.remove(qPanel);
			qPanels.remove(qPos-1);
			qLabels.remove(qPos-1);
			
			for(int i =0; i< qLabels.size();i++)
			{
				Question currentQuestion = queue.getQuestion(i);
				JLabel queueLabel = qLabels.get(i);
				JPanel queuePanel = qPanels.get(i);
				queueLabel.setText(formatter.formatQuestion(queue, currentQuestion));
				queuePanel.revalidate();
				queuePanel.repaint();
			}
		});
		
		profView.setLayout(new FlowLayout());
		profView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		profView.pack();
		profView.setSize(400,300);
		profView.setLocation(450, 0);
		profView.setVisible(true);
		
		//Roster view frame
		JLabel roster = new JLabel("Student Roster");
		roster.setBorder(new EmptyBorder(0,(400-roster.getWidth())/2,0,(400-roster.getWidth())/2));
		rosterView.add(roster);
		Roster r = new Roster();
		ArrayList<Student> students = r.getRoster();
		
		JPanel rosterPanel = new JPanel();
		//GridLayout(rows, cols,horizontalGap,verticalGap)
		rosterPanel.setLayout(new GridLayout(students.size()+2,2,250,15));
		rosterPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		//header
		rosterPanel.add(new JLabel("SID"));
		rosterPanel.add(new JLabel("EC"));
		ArrayList<JLabel> ecLabels = new ArrayList<>();
		for(int i = 0; i < students.size();i++)
		{
			Student s = students.get(i);
			//turn student ID and extra credit into string so it can be put into a JLabel
			String ID = String.valueOf(s.getID());
			String EC = String.valueOf(s.getExtraCredit());
			
			JLabel studentID = new JLabel(ID);
			JLabel ecLabel = new JLabel(EC);
			rosterPanel.add(studentID);
			rosterPanel.add(ecLabel);
			ecLabels.add(ecLabel);
		}
		JScrollPane rosterScroll = new JScrollPane(rosterPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  ;
		rosterScroll.setPreferredSize(new Dimension(370,250));
		rosterView.add(rosterScroll);
		
		//Extra credit view frame
		JLabel ecStudentIDLabel = new JLabel("Enter Student ID: ");
		extraCreditView.add(ecStudentIDLabel);
		JTextField studentIDField = new JTextField(30);
		extraCreditView.add(studentIDField);
		JLabel ecNumLabel = new JLabel("Enter extra credit amount: ");
		extraCreditView.add(ecNumLabel);
		JTextField ecField = new JTextField(30);
		extraCreditView.add(ecField);
		ecField.addActionListener(event -> 
		{
			//get professor input for ID and extra credit amount
			String textID = studentIDField.getText();
			int SID = Integer.parseInt(textID);
			String textEC = ecField.getText();
			int EC = Integer.parseInt(textEC);
			
			//Get the student from professor's ID input
			Student s = r.findStudent(SID);
			//update the JLabel extra credit for the student
			int index = students.indexOf(s);
			JLabel updatedECLabel = ecLabels.get(index);
			updatedECLabel.setText(textEC);
			//update Roster.txt
			r.inputExtraCredit(SID, EC);
			
			//repaint
			rosterPanel.revalidate();
			rosterPanel.repaint();
		});
		
		extraCreditView.setLayout(new FlowLayout());
		extraCreditView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		extraCreditView.pack();
		extraCreditView.setSize(400,300);
		extraCreditView.setLocation(0, 300);
		extraCreditView.setVisible(true);
		
		rosterView.setLayout(new FlowLayout());
		rosterView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rosterView.pack();
		rosterView.setSize(400,300);
		rosterView.setLocation(450, 300);
		rosterView.setVisible(true);
		
		queueView.setLayout(new FlowLayout());
		queueView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		queueView.pack();
		queueView.setSize(400,300);
		queueView.setLocation(900, 0);
		queueView.setVisible(true);

	}
}
