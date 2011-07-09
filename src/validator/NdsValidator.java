package validator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
 
@FacesValidator("loginValidator")
public class NdsValidator implements Validator{
 
	
	private static final String NDS_PATTERN = "^[a-z]{3}[0-9]{5}$|^[0-9]{5}$|^[a][d][m][i][n]$|^[s][t][u][d][e][n][t]$|^[a][u][t][h]$";
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\." +
			"[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" +
			"(\\.[A-Za-z]{2,})$";
	
	
	
	private Pattern nds_pattern;
	private Matcher nds_matcher; 
 
	public NdsValidator(){
		  nds_pattern = Pattern.compile(NDS_PATTERN);
		 
		  
	}
 
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
 
		nds_matcher = nds_pattern.matcher(value.toString());
		
		if(!nds_matcher.matches()  ){
 
			FacesMessage msg = 
				new FacesMessage("Nds validierung fehlgeschlagen.", 
						"Ungültiges Nds Kürzel.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
 
	}
}