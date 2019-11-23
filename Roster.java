
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Roster {
	private ArrayList<Student> students;
	private ArrayList<String> formattedStudents;
	
	public Roster()
	{
		
	}
	
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
		Student nullStudent = null;
		return nullStudent; //if student is not found, return "This student does not exist"
	}
	
	// Opens a text file of the roster of the class (ID and extra credit)
	// Reads the file and allows the user to input extra credit
	// Update the roster
	public static void inputExtraCredit(int studentID, int exc)
	{
		ArrayList<Integer> fileID = new ArrayList<Integer>();
		ArrayList<Integer> fileEXC = new ArrayList<Integer>();
		
		BufferedReader br;
		
		// Extract information from text file 
		try
		{
			br = new BufferedReader(new FileReader("/Users/jenluu/Desktop/Roster.txt"));
			
			// Read the first name that contains the headers
			String headers = br.readLine();
			// Read from the second line 
			String line = br.readLine();
			
			while(line != null)
			{
				// Part 1 is ID, part 2 is exc 
				String[] placeholder = line.split(" ");
				
				int id = Integer.parseInt(placeholder[0]);
				int extraCredit = Integer.parseInt(placeholder[1]);
				
				fileID.add(id);
				fileEXC.add(extraCredit);
				
				line = br.readLine();
			}
			
			br.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		// Search the student ID ArrayList for desired student
		for (int i = 0; i < fileID.size(); i++)
		{
			
			// Update extra credit point 
			if (studentID == fileID.get(i))
			{
				fileEXC.set(i, exc);
			}
			else
			{
				System.out.println("Student ID not found. Please try again.");
			}
		}
		
		try
		{
		// Write as a text file again
		File myFile = new File("/Users/jenluu/Desktop/Roster.txt");
		FileWriter fw = new FileWriter(myFile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		// Rewrite header
		String header = "StudentID EXC";
		bw.write(header);

		
		// Rewrite columns for roster
			for (int i = 0 ; i < fileID.size(); i++)
			{
				bw.newLine();
				bw.write(fileID.get(i) + " " + fileEXC.get(i));
			}
		bw.close();
		
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}	
	
	
}
