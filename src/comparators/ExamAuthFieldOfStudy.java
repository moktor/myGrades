

package comparators;
import model.ExamAuth;
import java.util.Comparator;

public class ExamAuthFieldOfStudy  implements Comparator<ExamAuth>{

	@Override public int compare (ExamAuth ea1, ExamAuth ea2)
	{
		return ea1.getFieldOfStudy().compareTo(ea2.getFieldOfStudy());		
	}
	
}
