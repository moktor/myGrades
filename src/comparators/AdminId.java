package comparators;

import model.Admin;
import java.util.Comparator;

public class AdminId implements Comparator<Admin>{

	@Override public int compare (Admin a1, Admin a2)
	{
		return a1.getId() -a2.getId();	 	
	}
	
}