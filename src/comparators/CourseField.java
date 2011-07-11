package comparators;

import model.Course;
import java.util.Comparator;

public class CourseField implements Comparator<Course>{

	@Override public int compare (Course c1, Course c2)
	{
		return c1.getFieldOfStudy().compareTo(c2.getFieldOfStudy());
	}
	
}