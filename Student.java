

public class Student {
	private int ID;
	private int extraCredit = 0;
	private String name;
	
	public Student(int ID, String name)
	{
		this.ID = ID;
		this.name = name;
	}
	
	public Student()
	{
		name = "This student got yeeted";
	}
	
	public int getID()
	{
		return this.ID;
	}
	
	public int getExtraCredit()
	{
		return this.extraCredit;
	}
	
	public void addExtraCredit(int points) 
	{
		this.extraCredit += points;
	}
	
	public String toString()
	{
		return this.name;
	}
}
