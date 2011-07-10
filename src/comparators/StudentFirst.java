package comparators;
import model.Student;
import java.util.Comparator;

public class StudentFirst  implements Comparator<Student>{

	@Override public int compare (Student s1, Student s2)
	{
		return s1.getFirstname().compareTo(s2.getFirstname());		
	}
	
}
