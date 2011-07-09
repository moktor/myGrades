package validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
 
@FacesValidator("emailValidator")
public class EmailValidator implements Validator{
 
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
			"[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
			"(\\.[A-Za-z]{2,})$";
	
	
	
	private Pattern email_pattern;
	private Matcher email_matcher; 
 
	public EmailValidator(){
		 email_pattern = Pattern.compile(EMAIL_PATTERN);
		 
		  
	}
 
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
 
		email_matcher = email_pattern.matcher(value.toString());
		
		if(!email_matcher.matches()  ){
 
			FacesMessage msg = 
				new FacesMessage("Email validierung fehlgeschlagen.", 
						"Ung�ltige Email Adresse.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
 
	}
}