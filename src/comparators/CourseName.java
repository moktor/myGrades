package comparators;

import model.Course;
import java.util.Comparator;

public class CourseName implements Comparator<Course>{

	@Override public int compare (Course c1, Course c2)
	{
		return c1.getName().compareTo(c2.getName());
	}
	
}