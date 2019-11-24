import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Tester {
	private static final int QUESTION_HEIGHT = 50;
	private static final int QUESTION_WIDTH = 400;
	private static final int ERROR_WIDTH = 150;
	
	public static void main(String[] args)
	{
		Queue queue = new Queue();
		Formatter formatter = new Formatter();
		
		JFrame studentView = new JFrame();
		JFrame profView = new JFrame();
		JFrame queueView = new JFrame();
		
		JLabel idLabel = new JLabel("Enter your 9-digit student ID here");
		studentView.add(idLabel);
		JTextField idField = new JTextField(20);
		studentView.add(idField);
		JLabel descLabel = new JLabel("Enter your question here");
		studentView.add(descLabel);
		JTextField qField = new JTextField(30);
		studentView.add(qField);
		
		ArrayList<JLabel> qLabels = new ArrayList<>();
		ArrayList<JPanel> qPanels = new ArrayList<>();
		
		//Student inputs new question
		qField.addActionListener(event ->
		{
			String qString = qField.getText();
			String idString = idField.getText();
			int id = 0;
			boolean IDIsValid = true;
			//parse String input to int 
	    	try 
	    	{
	    		id = Integer.parseInt(idString);
	    	}
	    	//prints error message if invalid ID is inputted
	    	catch (Exception e)
	    	{
	    		IDIsValid = false;
	    		JFrame errorPopUp = new JFrame();
	    		JLabel errorLabel = new JLabel("Please enter a valid student ID");
	    		JPanel errorPanel = new JPanel();
	    		EmptyBorder errorBorder = new EmptyBorder(20, (ERROR_WIDTH-errorLabel.getWidth())/2, 0, (ERROR_WIDTH-errorLabel.getWidth())/2); //centers the error message
	   	        errorPanel.setBorder(errorBorder);
	   	        errorPanel.add(errorLabel);
	   	        errorPopUp.add(errorPanel);
	   	        
	   	        errorPopUp.setLayout(new FlowLayout());
	   	        errorPopUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   	        errorPopUp.pack();
	   	        errorPopUp.setSize(250,150);
	   			errorPopUp.setLocation(52, 52);
	   	        errorPopUp.setVisible(true);
	    	}
	    	if(IDIsValid == true)
	    	{
				Question question = new Question(qString, id);
				queue.addQuestion(question);
				
				//make new question label for queueView frame and adds to arraylist of question labels
				JLabel qLabel = new JLabel(formatter.formatQuestion(queue, question));
				qLabels.add(qLabel);
				
				JPanel qPanel = new JPanel();
		        EmptyBorder panelBorder = new EmptyBorder(0, (QUESTION_WIDTH-qLabel.getWidth())/2, 0, (QUESTION_WIDTH-qLabel.getWidth())/2); //centers the question
		        qPanel.setBorder(panelBorder);
		        
				qPanel.add(qLabel);
				qPanels.add(qPanel);
				
				//add question to frame and repaints queueView frame
				queueView.add(qPanel);
				qLabel.setSize(new Dimension(QUESTION_WIDTH, QUESTION_HEIGHT));
				queueView.repaint();
				
				queueView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				queueView.pack();
				queueView.setSize(400,300);
				queueView.setLocation(900, 0);
				queueView.setVisible(true);
			}
    	});
		
		//Professor deletes question from queue
		JLabel qNum = new JLabel("Delete a question:");
		profView.add(qNum);
		JTextField deleteField = new JTextField(30);
		profView.add(deleteField);
		deleteField.addActionListener(event ->
		{
			//Turn user's Question number into an int value
			String qPosition = deleteField.getText();
			int qPos = 0;
			boolean questionNumberIsValid = true;
			//parse String input to int 
	    	try 
	    	{
	    		qPos = Integer.parseInt(qPosition);
	    	}
	    	//prints error message if invalid question number is inputted
	    	catch (Exception e)
	    	{
	    		questionNumberIsValid = false;
	    		JFrame errorPopUp = new JFrame();
	    		JLabel errorLabel = new JLabel("Please enter a valid question number");
	    		JPanel errorPanel = new JPanel();
	    		EmptyBorder errorBorder = new EmptyBorder(20, (ERROR_WIDTH-errorLabel.getWidth())/2, 0, (ERROR_WIDTH-errorLabel.getWidth())/2); //centers the error message
	   	        errorPanel.setBorder(errorBorder);
	   	        errorPanel.add(errorLabel);
	   	        errorPopUp.add(errorPanel);
	   	        
	   	        errorPopUp.setLayout(new FlowLayout());
	   	        errorPopUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   	        errorPopUp.pack();
	   	        errorPopUp.setSize(250,150);
	   			errorPopUp.setLocation(502, 52);
	   	        errorPopUp.setVisible(true);
	    	}
	    	//inputted question number is outside queue index bound
			if(qPos > qLabels.size())
			{
	    		questionNumberIsValid = false;
	    		JFrame errorPopUp = new JFrame();
	    		JLabel errorLabel = new JLabel("Please enter a valid question number");
	    		JPanel errorPanel = new JPanel();
	    		EmptyBorder errorBorder = new EmptyBorder(20, (ERROR_WIDTH-errorLabel.getWidth())/2, 0, (ERROR_WIDTH-errorLabel.getWidth())/2); //centers the error message
	   	        errorPanel.setBorder(errorBorder);
	   	        errorPanel.add(errorLabel);
	   	        errorPopUp.add(errorPanel);
	   	        
	   	        errorPopUp.setLayout(new FlowLayout());
	   	        errorPopUp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   	        errorPopUp.pack();
	   	        errorPopUp.setSize(250,150);
	   			errorPopUp.setLocation(502, 52);
	   	        errorPopUp.setVisible(true);
			}
			//user enters valid question number, question is deleted from queue
			else if(questionNumberIsValid == true)
			{
				//delete that question from queue
				queue.deleteQuestion(qPos-1);
				//get the label and panel with the question that wants to be deleted
				JLabel toBeRemoved = qLabels.get(qPos-1);
				JPanel qPanel = qPanels.get(qPos-1);
				
				//BRUTE FORCE LUL
				qPanel.setBorder(new EmptyBorder(-8, 0, -8, 0));
				
				//remove text question label from panel
				qPanel.remove(toBeRemoved);
				
				//delete question from panel and label array list
				qPanels.remove(qPos-1);
				qLabels.remove(qPos-1);
				
				//update the number formatting of each question after deletion
				for(int i = 0; i < qLabels.size(); i++)
				{
					Question currentQuestion = queue.getQuestion(i);
					JLabel queueLabel = qLabels.get(i);
					JPanel queuePanel = qPanels.get(i);
					
					queueLabel.setText(formatter.formatQuestion(queue, currentQuestion));
					queuePanel.revalidate();
					queuePanel.repaint();
				}
			}
		});
		
		studentView.setLayout(new FlowLayout());
		studentView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		studentView.pack();
		studentView.setSize(400,300);
		studentView.setVisible(true);
		
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
