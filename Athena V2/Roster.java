import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Roster {
	private ArrayList<Student> students;
	
	public Roster()
	{
		students = new ArrayList<>();
	}
	
	//return an array of students that was retrieved from Roster.txt
	public ArrayList<Student> getRoster()
	{
		ArrayList<Integer> fileID = new ArrayList<Integer>();
		ArrayList<Integer> fileEXC = new ArrayList<Integer>();
		
		BufferedReader br;
		
		// Extract information from text file 
		try
		{
			br = new BufferedReader(new FileReader("Roster.txt"));
			
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
				
				students.add(new Student(id,extraCredit));
				
				line = br.readLine();
			}
			
			br.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return this.students;
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
			br = new BufferedReader(new FileReader("Roster.txt"));
			
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
	
		
		
		// Go through the studentID list to find the desired ID to input exc
		boolean done = false;
		int index = 0;
		
		while(done!= true)
		{
			if (studentID == fileID.get(index))
			{
				fileEXC.set(index, exc);
				done = true;
			}
			else
			{
				index++;
			}
		}
			
		try
		{
		// Write as a text file again
		File myFile = new File("Roster.txt");
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
