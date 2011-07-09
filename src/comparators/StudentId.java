package comparators;

import model.Student;
import java.util.Comparator;

public class StudentId implements Comparator<Student>{

	@Override public int compare (Student s1, Student s2)
	{
		return s1.getId() -s2.getId();	 	
	}
	
}