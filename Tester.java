import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.*;
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
			//Turn user's Question number into an int value
			String qPosition = deleteField.getText();
			int qPos = 0;
			qPos = Integer.parseInt(qPosition);
			
			//delete that question from queue
			queue.deleteQuestion(qPos-1);
			//get the label and panel with the question that wants to be deleted
			JLabel toBeRemoved = qLabels.get(qPos-1);
			JPanel qPanel = qPanels.get(qPos-1);
			
			//Set deleted panel's border to 0
			qPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
			
			//remove text question label from panel
			qPanel.remove(toBeRemoved);
			
			//delete question from panel and label array list
			qPanels.remove(qPos-1);
			qLabels.remove(qPos-1);
			
			//update the number formatting of each question after deletion
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
		
		queueView.setLayout(new FlowLayout());
		queueView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		queueView.pack();
		queueView.setSize(400,300);
		queueView.setLocation(900, 0);
		queueView.setVisible(true);

	}
}
