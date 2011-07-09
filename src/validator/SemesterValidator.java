package validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
 
@FacesValidator("semValidator")
public class SemesterValidator implements Validator{
 
	
	private static final String SEM_PATTERN = "^[1-9]|[1-9]{2}$";
	
	
	
	
	private Pattern sem_pattern;
	private Matcher sem_matcher; 
 
	public SemesterValidator(){
		  sem_pattern = Pattern.compile(SEM_PATTERN);
		 
		  
	}
 
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
 
		sem_matcher = sem_pattern.matcher(value.toString());
		
		if(!sem_matcher.matches()  ){
 
			FacesMessage msg = 
				new FacesMessage("Semester validierung fehlgeschlagen.", 
						"Keine Zahl ! 1-9");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
 
	}
}