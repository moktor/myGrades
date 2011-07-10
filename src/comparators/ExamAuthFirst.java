package comparators;
import model.ExamAuth;
import java.util.Comparator;

public class ExamAuthFirst  implements Comparator<ExamAuth>{

	@Override public int compare (ExamAuth ea1, ExamAuth ea2)
	{
		return ea1.getFirstname().compareTo(ea2.getFirstname());		
	}
	
}
