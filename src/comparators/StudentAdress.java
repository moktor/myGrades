package comparators;
import model.Student;
import java.util.Comparator;

public class StudentAdress  implements Comparator<Student>{

	@Override public int compare (Student s1, Student s2)
	{
		return s1.getAdresse().compareTo(s2.getAdresse());		
	}
	
}
