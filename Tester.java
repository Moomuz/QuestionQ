import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Tester {
	private static final int QUESTION_HEIGHT = 30;
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
			JPanel qPanel = new JPanel();
	        EmptyBorder panelBorder = new EmptyBorder(2, (400-qLabel.getWidth())/2, 2, (400-qLabel.getWidth())/2); //centers the question
	        qPanel.setBorder(panelBorder);
	        
	        
	        
			qPanel.add(qLabel);
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
