package comparators;

import model.ExamAuth;
import java.util.Comparator;

public class ExamAuthNds implements Comparator<ExamAuth>{

	@Override public int compare (ExamAuth ea1, ExamAuth ea2)
	{
		return ea1.getNds().compareTo(ea2.getNds());		
	}
	
}