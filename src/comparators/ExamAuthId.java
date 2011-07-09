package comparators;

import model.ExamAuth;
import java.util.Comparator;

public class ExamAuthId implements Comparator<ExamAuth>{

	@Override public int compare (ExamAuth ea1, ExamAuth ea2)
	{
		return ea1.getId() -ea2.getId();	 	
	}
	
}