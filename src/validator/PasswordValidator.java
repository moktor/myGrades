package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
 
@FacesValidator("passwordValidator")
public class PasswordValidator implements Validator{
 
	// ------------------------- Sonderzeichen should be added ;) -------------------JL------------------------------
	private static final String PW_PATTERN = "^[0-9a-zA-Z]{3,10}$";
	
	
	private Pattern pw_pattern;
	private Matcher pw_matcher; 
 
	public PasswordValidator(){
		  pw_pattern = Pattern.compile(PW_PATTERN);
		 
		  
	}
 
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
 
		pw_matcher = pw_pattern.matcher(value.toString());
		
		if(!pw_matcher.matches()  ){
 
			FacesMessage msg = 
				new FacesMessage("Passwort validierung fehlgeschlagen.", 
						"Ungültiges Passwort.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
 
	}
}