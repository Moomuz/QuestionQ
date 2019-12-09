
public class Question {

	private String question;
	private int studentID;
	
	// Constructor for the question object
	public Question(String question, int studentID) {
		
		this.question = question;
		this.studentID = studentID;
	}
	
	// Constructor for null Question object
	public Question()
	{
		
	}
	
	// Return the Question (String)
	public String getQuestion()
	{
		return this.question;
	}
	
	// Return the student ID of the person who asked the Question
	public int getStudentID()
	{
		return this.studentID;
	}

}
