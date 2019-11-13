import java.util.*;

public class Roster {
	private ArrayList<Student> students;
	private ArrayList<String> formattedStudents;
	
	public Roster(ArrayList<Student> students)
	{
		Formatter formatter = new Formatter();
		this.students = students;
		this.formattedStudents = new ArrayList<String>();
		for(Student student: this.students)
		{
			formattedStudents.add(formatter.formatStudent(student));
		}
	}
	
	public ArrayList<String> getRoster()
	{
		return this.formattedStudents;
	}
	
	public Student findStudent(int ID)
	{
		for(Student student: students)
		{
			if(student.getID() == ID)
			{
				return student;
			}
		}
		return new Student();
	}
	
}
