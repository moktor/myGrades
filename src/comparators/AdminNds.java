package comparators;
import model.Admin;
import java.util.Comparator;

public class AdminNds  implements Comparator<Admin>{

	@Override public int compare (Admin a1, Admin a2)
	{
		return a1.getFirstname().compareTo(a2.getFirstname());		
	}
	
}
