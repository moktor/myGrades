package comparators;
import model.ExamAuth;
import java.util.Comparator;

public class ExamAuthName  implements Comparator<ExamAuth>{

	@Override public int compare (ExamAuth s1, ExamAuth s2)
	{
		return s1.getName().compareTo(s2.getName());		
	}
	
}
