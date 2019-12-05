import java.util.ArrayList;

public class RosterTester {

	public static void main(String[] args)
	{
		Roster r = new Roster();
		
		//r.inputExtraCredit(12385555, 10);
		r.inputExtraCredit(12345678, 10);
		//ArrayList<Student> students = r.getRoster();
		System.out.println(r.getRoster().get(1).getID());
		String ID = String.valueOf(r.getRoster().get(0).getID());
		System.out.println(ID);
		
	}
	
}
