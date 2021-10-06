package nl.novi.first_demo.exeption;

public class RecordNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    public RecordNotFoundException(String message) {
        super(message);
    }

}
