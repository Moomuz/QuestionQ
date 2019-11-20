import java.util.ArrayList;

// A Queue that holds Question objects
public class Queue {

	private ArrayList<Question> queue;
	
	// Constructor to create the Queue
	public Queue() 
	{
		this.queue = new ArrayList<Question>();
		
	}
	
	// Add a Question to the Queue
	public void addQuestion(Question question)
	{
		queue.add(question);
	}
	
	// Return the first Question in the Queue
	public String getFirstQuestion()
	{
		return queue.get(0).getQuestion();
	}
	
	// Return the last Question in the Queue
	public String getLastQuestion()
	{
		return queue.get(queue.size()-1).getQuestion();
	}
	
	// Return the ith Question in the Queue
	public void deleteQuestion(int index)
	{
		queue.remove(index);
	}
	
	// Print all of the Questions in the Queue
	public void displayAll()
	{
		String question = "";
		
		for (int i = 0 ; i < queue.size(); i++)
		{
			question = queue.get(i).getQuestion();
			System.out.println(i+1 + ". " + question);
		}
	}
	
	public int indexOf(Question question)
	{
		return queue.indexOf(question);
	}

}
