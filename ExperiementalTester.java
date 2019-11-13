import java.util.*;
public class ExperiementalTester {
	
	public static void main(String[] args)
	{
		ArrayList<Student> students = new ArrayList<Student>();
		students.add(new Student(912267879, "YEET"));
		students.add(new Student(912266879, "YAW"));
		students.add(new Student(912264879, "BOOMER"));
		Roster roster = new Roster(students);
		System.out.println(roster.getRoster());
		Formatter formatter = new Formatter();
		Queue queue = new Queue();
		Question question = new Question("this is a question", 912267879);
		queue.addQuestion(question);
		System.out.println(formatter.formatQuestion(queue, question));
		System.out.println(roster.findStudent(912264879));
	}
}
