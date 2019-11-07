import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Tester {
	
	private void refreshQueue()
	{
		//ArrayList<Question> questions = queue.getQuestions();
	}
	
	public static void main(String[] args)
	{
		Queue queue = new Queue();
		
		JFrame studentView = new JFrame();
		JFrame profView = new JFrame();
		JFrame queueView = new JFrame();
		
		JLabel idLabel = new JLabel("Enter your student ID here (ex: XXXXXXXXX)");
		studentView.add(idLabel);
		JTextField idField = new JTextField(20);
		studentView.add(idField);
		JLabel qLabel = new JLabel("Enter your question here (Limit: 50 characters)");
		studentView.add(qLabel);
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
			Question temp = new Question(qString, id);
			queue.addQuestion(temp);
			//refreshQueue();
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
