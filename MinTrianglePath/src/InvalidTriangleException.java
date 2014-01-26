
public class InvalidTriangleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTriangleException(String message, Object... params) {
        super(String.format(message, params));
    }
	
	public InvalidTriangleException(String message, Exception e, Object... params) {
        super(String.format(message, params), e);
    }

}
