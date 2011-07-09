package validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
 
@FacesValidator("NameValidator")
public class NameValidator implements Validator{
 
	
	private static final String NAME_PATTERN = "^[a-zA-Z]*$";
	
	
	
	
	private Pattern name_pattern;
	private Matcher name_matcher; 
 
	public NameValidator(){
		  name_pattern = Pattern.compile(NAME_PATTERN);
		 
		  
	}
 
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
 
		name_matcher = name_pattern.matcher(value.toString());
		
		if(!name_matcher.matches()  ){
 
			FacesMessage msg = 
				new FacesMessage("Namen validierung fehlgeschlagen.", 
						"Kein gültiger Name");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
 
	}
}