package exceptions;

public class PersonNotFoundException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2877003021094868954L;

	public PersonNotFoundException(String msg) {
        super(msg);
    }

    public PersonNotFoundException() {
        super("Person does not exist");
    }
}
