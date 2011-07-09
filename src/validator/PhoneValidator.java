package validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
 
@FacesValidator("phoneValidator")
public class PhoneValidator implements Validator{
 
	
	//private static final String PHONE_PATTERN = "^(\+\W?|[0]\W?)\d{2,}(\W|\-)?\(?\W?\d?\W?\)?\W?\d*\W?(\W|\-|\/)?(\d?|\W?)+$\z";
	private static final String PHONE_PATTERN = "^[0-9]*$";
	
	private Pattern phone_pattern;
	private Matcher phone_matcher; 
 
	public PhoneValidator(){
		 phone_pattern = Pattern.compile(PHONE_PATTERN);
		 
		  
	}
 
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
 
		phone_matcher = phone_pattern.matcher(value.toString());
		
		if(!phone_matcher.matches()  ){
 
			FacesMessage msg = 
				new FacesMessage("Telefon validierung fehlgeschlagen.", 
						"Ungültige Telefonnummer.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
 
	}
}