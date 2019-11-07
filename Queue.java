import java.util.ArrayList;

public class Queue {
	private ArrayList<Question> questions;
	
	public Queue()
	{
		questions = new ArrayList<Question>();
	}
	
	public void addQuestion(Question question)
	{
		this.questions.add(question);
	}
	
	public ArrayList<Question> getQueue()
	{
		return this.questions;
	}
}
