public class Student {
	private int ID;
	private int extraCredit = 0;
	
	public Student(int ID, int extraCredit)
	{
		this.ID = ID;
		this.extraCredit = extraCredit;
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
}
