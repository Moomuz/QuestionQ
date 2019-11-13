

public class Student {
	private int ID;
	private int extraCredit = 0;
	
	public Student(int ID)
	{
		this.name = name;
		this.ID = ID;
	}
	
	public int getID()
	{
		return this.ID;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getExtraCredit()
	{
		return this.extraCredit;
	}
	
	public void addExtraCredit(int points) 
	{
		this.extraCredit += points;
	}
}
