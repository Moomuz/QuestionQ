
public class Student {
	private int ID;
	private int extraCredit = 0;
	
	public Student(int ID, int extraCredit)
	{
		this.ID = ID;
		this.extraCredit = extraCredit;
	}
	
	public Student()
	{
		
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
		if(this.ID == 0)
		{
			return "This student does not exist";
		}
		else
		{
			return Integer.toString(this.ID);
		}
	}
}
