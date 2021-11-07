
public class EmailAlreadyExistsException extends RuntimeException {
	
	private EmailAlreadyExistsException() {
		super();
	}
	
	public EmailAlreadyExistsException(String msg) {
		super(msg);
	}
}
