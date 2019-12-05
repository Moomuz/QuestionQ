
public class Formatter {
	
	public String formatQuestion(Queue queue, Question question)
	{
		return queue.indexOf(question)+1 + ": " + question.getQuestion(); //add 1 since list starts at 0
	}
	
	public String formatStudent(Student student)
	{
		return "(" + student.getID() + ")";
	}
}
