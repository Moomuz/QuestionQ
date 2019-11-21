

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
		if(this.ID == 0) //null student initializes ID as 0
		{
			return "This student does not exist";
		}
		else
		{
			return Integer.toString(this.ID);
		}
	}
}
